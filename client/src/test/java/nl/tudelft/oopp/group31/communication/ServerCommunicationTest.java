package nl.tudelft.oopp.group31.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;

import nl.tudelft.oopp.group31.entities.Building;
import nl.tudelft.oopp.group31.entities.Room;
import nl.tudelft.oopp.group31.entities.RoomReservation;
import nl.tudelft.oopp.group31.entities.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * This test class is used to test the functionality of the ServerCommunication class.
 * The server must be working in order for these test to run successfully, as they
 * test the connectivity between the server and the client.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServerCommunicationTest {

    private static Building newBuilding;
    private static Room newRoom;
    private static RoomReservation newReservation;
    private static User newUser;


    /**
     * We need a reliable way to create entities from this class on the database
     * to test the functionality of editing and deleting them, in order to be able to
     * run the test multiple times. This means that the add method test are not only
     * tested but serve as initialization of the entities in the database (assuming
     * the correctness of the actual methods) and are executed first.
     * After the execution of this program, there should be no new entities in the database,
     * nor alterations of previously existing ones.
     */

    public String getListRegex(String singleRegex) {
        return "\\Q[\\E(" + singleRegex + ")*\\Q]\\E";
    }

    private void setupAccount(String username, String password) {
        RequestHelper.setUsername(username);
        RequestHelper.setPassword(password);
    }

    @Test
    @Order(1)
    public void testSendAuthNonexistentUser() {
        setupAccount("HopefullyThisUserDoesNotExist", "fwefubhwkhf");
        String actual = ServerCommunication.sendAuth();
        assertEquals("401", actual);
    }

    @Test
    @Order(2)
    public void testSignUpNewUser() {
        String actual = ServerCommunication.doSignUp("newUser", "newPass");
        assertTrue(actual.matches("\\{\"netID\":\"newUser\", \"password\":\".*\", \"type\":\"3\", \"rentedBike\":\"false\"\\}"));
        Gson g = new Gson();
        newUser = g.fromJson(actual, User.class);
        newUser.setPassword("newPass");
        RequestHelper.setUsername(newUser.getNetID());
        RequestHelper.setPassword(newUser.getPass());
    }

    @Test
    @Order(3)
    public void testSendAuthExistingUser() {
        String actual = ServerCommunication.sendAuth();
        String regex = "\\{\"netID\":\"" + newUser.getNetID() + "\", \"password\":\".*\", \"type\":\"3\", \"rentedBike\":\"false\"\\}";
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(3)
    public void testSignUpExistingUser() {
        String actual = ServerCommunication.doSignUp(newUser.getNetID(), "rgbfeh");
        assertEquals("226", actual);
    }

    @Test
    @Order(4)
    public void testAddBuilding() {
        String actual = ServerCommunication.addBuilding("NewBuilding", "1000", "2200", "This is a new Building");
        String regex = "\\{\"id\":\".*\", \"name\":\"NewBuilding\", \"openingHour\":\"1000\","
                + " \"closingHour\":\"2200\", \"description\":\"This is a new Building\", \"bikeRental\":\"-1\"\\},?";
        assertTrue(actual.matches(regex));
        Gson g = new Gson();
        newBuilding = g.fromJson(actual, Building.class);
    }

    @Test
    @Order(5)
    public void testAddFoodItem() {
        String actual = ServerCommunication.addFoodItem("testHamburger", String.valueOf(newBuilding.getID()), "5", "A TESTy hamburger hah");
        String regex = "\\{\"id\":\".*\", \"name\":\"testHamburger\", \"buildingID\":\"" + newBuilding.getID()
                + "\", \"price\":\"5.0\", \"description\":\"A TESTy hamburger hah\"\\}";
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(5)
    public void testAddBikeRent() {
        String actual = ServerCommunication.addBikeRent(newUser.getNetID(), newBuilding.getID());
        String expected = "\\{\"id\":\".*\", \"netID\":\"" + newUser.getNetID() + "\", \"buildingID\":\""
                + newBuilding.getID() + "\", \"date\":\"null\"\\}";
        assertTrue(actual.matches(expected));
    }

    @Test
    @Order(5)
    public void testAddRoom() {
        String actual = ServerCommunication.addRoom(new Room(1, "newRoom", String.valueOf(newBuilding.getID()), true, true, 20));
        String regex = "\\{\"id\":\".*\", \"name\":\"newRoom\", \"buildingID\":\"" + newBuilding.getID()
                + "\", \"whiteboard\":\"true\", \"accessibility\":\"true\", \"capacity\":\"20\"\\}";
        assertTrue(actual.matches(regex));
        Gson g = new Gson();
        newRoom = g.fromJson(actual, Room.class);
    }

    @Test
    @Order(6)
    public void testAddReservation() {
        String actual = ServerCommunication.addRoomReservation(newUser.getNetID(), "2031-12-12", "370", "1000", "1100");
        String regex = "\\{\"id\":\".*\", \"netID\":\"" + newUser.getNetID()
                + "\", \"date\":\"2031-12-12\", \"roomID\":\"370\", \"startingHour\":\"1000\", \"endingHour\":\"1100\"\\}";
        assertTrue(actual.matches(regex));
        Gson g = new Gson();
        newReservation = g.fromJson(actual, RoomReservation.class);
    }

    @Test
    @Order(6)
    public void testGetAllBuildings() {
        String actual = ServerCommunication.getAllBuildings();
        String singleBuidlingRegex = "\\{\"id\":(\\d)*,\"name\":\".*\",\"openingHour\":\".*\","
                + "\"closingHour\":\".*\",\"description\":\".*\",\"bikeRental\":.*\\},?";
        String regex = getListRegex(singleBuidlingRegex);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(6)
    public void testGetBuilding() {
        String actual = ServerCommunication.getBuilding(newBuilding.getID());
        String expected = newBuilding.toJson();
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    public void testGetAllRooms() {
        String actual = ServerCommunication.getRooms();
        String singleRoomRegex = "\\{\"id\":.*,\"name\":\".*\",\"buildingID\":.*,\"whiteboard\":.*,\"accessibility\":.*,\"capacity\":.*\\},?";
        String regex = getListRegex(singleRoomRegex);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(6)
    public void testGetRoom() {
        String actual = ServerCommunication.getRoom(370);
        String expected = "{\"id\":\"370\", \"name\":\"PI\", \"buildingID\":\"95\", "
                + "\"whiteboard\":\"true\", \"accessibility\":\"true\", \"capacity\":\"140\"}";
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    public void testGetReservationsForRoom() {
        String actual = ServerCommunication.getReservationsForRoom("date=2030-04-07&&roomID=" + String.valueOf(newRoom.getId()));
        String expected = "\\{\"id\":\".*\", \"netID\":\".*\", \"date\":\".*\","
                + " \"roomID\":\"" + newRoom.getId() + "\", \"startingHour\":\".*\", \"endingHour\":\".*\"\\}.?";
        String regex = this.getListRegex(expected);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(6)
    public void testGetRoomsInBuilding() {
        String actual = ServerCommunication.getRoomsInBuilding(newBuilding.getID());
        String expected = "\\{\"id\":.*,\"name\":\".*\",\"buildingID\":" + newBuilding.getID()
                + ",\"whiteboard\":.*,\"accessibility\":.*,\"capacity\":.*\\},?";
        String regex = this.getListRegex(expected);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(6)
    public void testGetReservationsMadeByUser() {
        String actual = ServerCommunication.getReservationsMadeByUser(newUser.getNetID());
        String expected = "\\{\"id\":\".*\",\"netID\":\"" + newUser.getNetID()
                + "\",\"date\":\".*\",\"roomID\":\"\",\"startingHour\":\".*\",\"endingHour\":\".*\"\\}.?";
        String regex = this.getListRegex(expected);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(6)
    public void testGetAllRoomsReservations() {
        String actual = ServerCommunication.getRoomReservations();
        String expected = "\\{\"id\":.*,\"netID\":\".*\",\"date\":\".*\",\"roomID\":.*,\"startingHour\":\".*\",\"endingHour\":\".*\"\\}.?";
        String regex = this.getListRegex(expected);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(6)
    public void testGetFoodItems() {
        String actual = ServerCommunication.getFoodItems(newBuilding.getID());
        String expected = "\\{\"id\":.*,\"name\":\".*\",\"buildingID\":" + newBuilding.getID() + ",\"price\":.*,\"description\":\".*\"\\},?";
        String regex = this.getListRegex(expected);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(6)
    public void testGetAllUsers() {
        String actual = ServerCommunication.getAllUsers();
        String singleUserRegex = "\\{\"netID\":\".*\",\"password\":\".*\",\"type\":[123],\"rentedBike\":(true|false)\\},?";
        String regex = this.getListRegex(singleUserRegex);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(6)
    public void testGetAllBikeReservations() {
        String actual = ServerCommunication.getAllBikeReservation();
        String singleBikeRegex = "\\{\"id\":.*,\"netID\":\".*\",\"buildingID\":.*,\"date\":null\\},?";
        String regex = getListRegex(singleBikeRegex);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(6)
    public void testGetUserRent() {
        String actual = ServerCommunication.getUserRent("admin");
        String singleBikeRegex = "\\{\"id\":.*,\"netID\":\".*\",\"buildingID\":.*,\"date\":null\\},?";
        String regex = getListRegex(singleBikeRegex);
        assertTrue(actual.matches(regex));
    }

    @Test
    @Order(7)
    public void testEditBuilding() {
        String actual = ServerCommunication.editBuilding(newBuilding.getID(),
                "newBuilding", "08", "20", "This building is for testing purposes of the application. Please do not delete.", 100);
        String expected = "{\"id\":\"" + newBuilding.getID()
                + "\", \"name\":\"newBuilding\", \"openingHour\":\"08\", \"closingHour\":\"20\","
                + " \"description\":\"This building is for testing purposes of the application. Please do not delete.\", \"bikeRental\":\"100\"}";
        assertEquals(expected, actual);
    }

    @Test
    @Order(7)
    public void testBlockRoom() {
        String actual = ServerCommunication.blockRoom(370, "2100-12-13");
        String regex = "\\{\"id\":\".*\", \"netID\":\"null\", \"date\":\"2100-12-13\","
                + " \"roomID\":\"370\", \"startingHour\":\"null\", \"endingHour\":\"null\"\\}";
        assertTrue(actual.matches(regex));
        Gson gson = new Gson();
        RoomReservation r = gson.fromJson(actual, RoomReservation.class);
        ServerCommunication.deleteRoomReservations(r.getId());
    }

    @Test
    @Order(8)
    public void testDeleteBuilding() {
        String actual = ServerCommunication.deleteBuilding(newBuilding.getID());
        String expected = "200";
        assertEquals(expected, actual);
    }

    @Test
    @Order(8)
    public void testDeleteRoomReservation() {
        String actual = ServerCommunication.deleteRoomReservations(newReservation.getId());
        String expected = "200";
        assertEquals(expected, actual);
    }

    @Test
    @Order(8)
    public void testRentBuildingBike() {
        String actual = ServerCommunication.rentBuildingBike(newBuilding.getID(), 1);
        String expected = "{\"id\":\"" + newBuilding.getID() + "\", \"name\":\"newBuilding\", \"openingHour\":\"08\","
                + " \"closingHour\":\"20\", \"description\":\"This building is for testing purposes of the application."
                + " Please do not delete.\", \"bikeRental\":\"99\"}";
        assertEquals(expected, actual);
    }

    @Test
    @Order(9)
    public void testDeleteNonexistantRoom() {
        String actual = ServerCommunication.deleteRoom(newRoom.getId());
        String expected = "404";
        assertEquals(expected, actual);
    }

    @Test
    @Order(21)
    public void testDeleteExistingUser() {
        String actual = ServerCommunication.deleteUser("newUser");
        assertEquals("200", actual);
    }

    @Test
    @Order(20)
    public void testDeleteNonExistantUser() {
        String actual = ServerCommunication.deleteUser("thisUsernameIsNonExistentInThisInstanceOFtheDB");
        assertEquals("404", actual);
    }

}
