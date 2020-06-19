package nl.tudelft.oopp.group31.communication;

import java.net.http.HttpClient;

import nl.tudelft.oopp.group31.entities.Room;

public class ServerCommunication {
    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * Sends an authentication request to the server.
     *
     * @return The server's response.
     */
    public static String sendAuth() {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/user/login"), client);
    }

    /**
     * Sends POST request to the server with the details of a user. The access level is 3 (user) by default.
     * The endpoint is configured to save the data in the database only if such a user doesn't exist.
     *
     * @param name The NetID of the user
     * @param pass The password of the user
     * @return The server's response
     */
    public static String doSignUp(String name, String pass) {
        String json = "{\"netID\":\"" + name + "\", \"password\":\"" + pass + "\", \"type\":\"" + "3" + "\"}";
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/user/add", json), client);
    }


    /**
     * Sends a GET request to the server to get all users from the database.
     *
     * @return The server's response, {@link nl.tudelft.oopp.group31.entities.User} objects in JSON format
     */
    public static String getAllUsers() {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/user/all"), client);
    }

    /**
     * Sends a GET request to the server to delete a user from the database.
     *
     * @param netID The netID of the user
     * @return The server's response
     */
    public static String deleteUser(String netID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/user/delete?netID=" + netID), client);
    }

    /**
     * Sends a GET request to the server to get a list of all buildings.
     *
     * @return The server's response, {@link nl.tudelft.oopp.group31.entities.Building} objects in JSON format
     */
    public static String getAllBuildings() {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/building/all"), client);
    }

    /**
     * Sends a POST request to the server to add a building to the database.
     * The server assigns the buildingId to ensure the key property of the field.
     *
     * @param name        The name of the building
     * @param openingHour The opening hour of the building in military format
     * @param closingHour The closing hour of the building in military format
     * @param description The textual description of the building
     * @return The server's response
     */
    public static String addBuilding(String name, String openingHour, String closingHour, String description) {
        String json = "{\"name\":\"" + name
                + "\", \"openingHour\":\"" + openingHour
                + "\", \"closingHour\":\"" + closingHour
                + "\", \"description\":\"" + description + "\"}";
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/building/add", json), client);
    }

    /**
     * Sends a POST request to the server to update a building in the database.
     *
     * @param buildingID  The ID of the building
     * @param name        The name of the building
     * @param openingHour The opening hour of the building in military format
     * @param closingHour The closing hour of the building in military format
     * @param description The textual description of the building
     * @return The server's response
     */
    public static String editBuilding(int buildingID, String name, String openingHour, String closingHour, String description) {
        String json = "{\"id\":\"" + buildingID
                + "\", \"name\":\"" + name
                + "\", \"openingHour\":\"" + openingHour
                + "\", \"closingHour\":\"" + closingHour
                + "\", \"description\":\"" + description + "\"}";
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/building/change", json), client);
    }

    /**
     * Sends a POST request to the server to update a building in the database.
     * This method is used to modify the number of available bikes
     *
     * @param buildingID  The ID of the building
     * @param name        The name of the building
     * @param openingHour The opening hour of the building in military format
     * @param closingHour The closing hour of the building in military format
     * @param description The textual description of the building
     * @param bikes       The number of bikes at the building
     * @return The server's response
     */
    public static String editBuilding(int buildingID, String name, String openingHour, String closingHour, String description, int bikes) {
        String json = "{\"id\":\"" + buildingID
                + "\", \"name\":\"" + name
                + "\", \"openingHour\":\"" + openingHour
                + "\", \"closingHour\":\"" + closingHour
                + "\", \"bikeRental\":\"" + bikes
                + "\", \"description\":\"" + description + "\"}";
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/building/change", json),
                client);
    }

    /**
     * Sends a GET request to the server to delete a building.
     *
     * @param id The ID of the building
     * @return The server's response
     */
    public static String deleteBuilding(int id) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/building/delete?id=" + id), client);
    }

    /**
     * Sends a GET request to the server to get a room by id.
     *
     * @param roomID The id of the room
     * @return The server's response, a {@link Room} object in JSON format.
     */
    public static String getRoom(int roomID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/room/get?id=" + roomID), client);
    }

    /**
     * Sends a GET request to the server to rent a number of bikes from a building in the database.
     *
     * @param buildingID The ID of the building
     * @param numBikes   The number of bikes to rent at the building
     * @return The server's response
     */
    public static String rentBuildingBike(int buildingID, int numBikes) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/building/rent?id=" + buildingID + "&numberOfBikes=" + numBikes), client);
    }

    /**
     * Sends a GET request to the server to delete a room in the database.
     *
     * @return The server's response, {@link Room} objects in JSON format
     */
    public static String getRooms() {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/room/all"), client);
    }

    /**
     * Sends a POST request to the server to add a room to the database.
     *
     * @param room The room entity to add
     * @return The server's response
     */
    public static String addRoom(Room room) {
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/room/add", room.toJson()), client);
    }


    /**
     * Sends a POST request to the server to update a room in the database.
     *
     * @param room The modified room entity to add
     * @return The server's response
     */
    public static String editRoom(Room room) {
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/room/change", room.toJson()), client);
    }

    /**
     * Sends a POST request to the server to delete a room in the database.
     *
     * @param roomID The ID of the room
     * @return The server's response
     */
    public static String deleteRoom(int roomID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/room/delete?id=" + roomID), client);
    }

    /**
     * Sends a GET request to the server to get all rooms in a building.
     *
     * @param buildingID The id of the building
     * @return The server's response, {@link Room} objects in JSON format
     */
    public static String getRoomsInBuilding(int buildingID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/room/building?buildingID=" + buildingID), client);
    }

    /**
     * Sends a GET request to the server to get all room reservations in the database.
     *
     * @return The server's response, {@link nl.tudelft.oopp.group31.entities.RoomReservation} objects in JSON format
     */
    public static String getRoomReservations() {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/reservation/all"), client);
    }

    /**
     * Sends a POST request to the server to add a room reservation to the database.
     *
     * @param netID        The netID of the current user
     * @param date         The date of the reservation
     * @param roomID       The ID of the chosen room
     * @param startingHour The starting hour of the reservation in military format
     * @param endingHour   The ending hour of the reservation in military format
     * @return The server's response
     */
    public static String addRoomReservation(String netID, String date, String roomID, String startingHour, String
            endingHour) {
        String json = "{\"netID\":\"" + netID
                + "\", \"date\":\"" + date
                + "\", \"roomID\":\"" + roomID
                + "\", \"startingHour\":\"" + startingHour
                + "\", \"endingHour\":\"" + endingHour + "\"}";
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/reservation/add", json), client);
    }

    /**
     * Sends a GET request to the server with custom parameters to get filtered room reservations.
     *
     * @param parameters The custom filtering parameters
     * @return The server's response, {@link nl.tudelft.oopp.group31.entities.RoomReservation} objects in JSON format
     */
    public static String getReservationsForRoom(String parameters) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/reservation/taken?" + parameters), client);
    }

    /**
     * Sends a GET request to the server to get all reservations made by a specific user.
     *
     * @param netID The netID of the user
     * @return The server's response, {@link nl.tudelft.oopp.group31.entities.RoomReservation} objects in JSON format
     */
    public static String getReservationsMadeByUser(String netID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/reservation/user?netID=" + netID), client);
    }

    /**
     * Sends a GET request to the server to delete a room reservation in the database.
     *
     * @param reservationID The id of the roomReservation to be deleted
     * @return The server's response
     */
    public static String deleteRoomReservations(int reservationID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/reservation/delete?id=" + reservationID), client);
    }

    /**
     * Sends a POST request to the server to block a room's incoming reservations on a specific date.
     *
     * @param roomID The id of the room
     * @param date   The desired date
     * @return The server's response
     */
    public static String blockRoom(int roomID, String date) {
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/reservation/block?roomID=" + roomID + "&date=" + date, "block"), client);
    }

    /**
     * Sends a GET request to the server to get all the food items associated with a building.
     *
     * @param buildingID The id of the building
     * @return The server's response, {@link nl.tudelft.oopp.group31.entities.Food} objects in JSON format
     */
    public static String getFoodItems(int buildingID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/food/building?buildingID=" + buildingID), client);
    }

    /**
     * Sends a POST request to the server to add a food item in the database.
     *
     * @param name        The name of the food
     * @param buildingID  The ID of the building associated with the food
     * @param price       The price of the food
     * @param description A textual description of the food
     * @return The server's response
     */
    public static String addFoodItem(String name, String buildingID, String price, String description) {
        String json = "{\"name\":\"" + name
                + "\", \"buildingID\":\"" + buildingID
                + "\", \"price\":\"" + price
                + "\", \"description\":\"" + description + "\"}";

        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/food/add", json), client);
    }

    /**
     * Sends a GET request to the server to remove a food item from the database.
     *
     * @param foodID The ID of the food
     * @return The server's response
     */
    public static String removeFoodItem(String foodID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/food/delete?id=" + foodID), client);
    }

    /**
     * Sends a POST request to the server to add a food reservation in the database.
     *
     * @param netID      The netID of the current user
     * @param date       The date of the reservation
     * @param buildingID The id of the building
     * @param hour       The hour of the food order
     * @param item       The food object
     * @return The server's response
     */
    public static String addFoodReservation(String netID, String date, int buildingID, String hour, int item) {
        String json = "{\"netid\":\"" + netID
                + "\", \"date\":\"" + date
                + "\", \"buildingID\":\"" + buildingID
                + "\", \"hour\":\"" + hour
                + "\", \"foodSelection\":\"" + item + "\"}";
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/foodreservation/add", json), client);
    }

    /**
     * Sends a GET request to the server to delete a bike reservation instance from the database.
     *
     * @param reservationID The ID of the bike reservation
     * @return The server's response
     */
    public static String deleteBikeRent(int reservationID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/bikereservation/delete?id=" + reservationID), client);
    }

    /**
     * Sends a POST request to the server to add a bike reservation instance to the database.
     *
     * @param netID      The netID of the user renting a bike
     * @param buildingID The ID of the building
     * @return The server's response
     */
    public static String addBikeRent(String netID, int buildingID) {
        String json = "{\"netID\":\"" + netID
                + "\", \"buildingID\":\"" + buildingID + "\"}";
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/bikereservation/add", json), client);
    }

    /**
     * Sends a GET request to the server to get all bike reservations in the database.
     *
     * @return The server's response, {@link nl.tudelft.oopp.group31.entities.BikeReservation} objects in JSON format
     */
    public static String getAllBikeReservation() {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/bikereservation/all"), client);
    }

    /**
     * Gets a specific building.
     *
     * @param buildingId The id of the building
     * @return A building in JSON form
     */
    public static String getBuilding(int buildingId) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/building/get?id=" + buildingId), client);
    }

    /**
     * Sends a GET request to the server to get a user's bike rentals in the database.
     *
     * @param netID The netID of the user
     * @return The server's response, {@link nl.tudelft.oopp.group31.entities.BikeReservation} objects in JSON format
     */
    public static String getUserRent(String netID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/bikereservation/user?netID=" + netID), client);
    }

    /**
     * Sends a POST request to the server to add an entry to a user's calendar.
     *
     * @param json A custom-configured JSON representation of the entry
     * @return The server's response
     */
    public static String sendUserCalendar(String json) {
        return RequestHelper.sendRequest(
                RequestHelper.postRequest("/calendarentry/add", json), client);
    }

    /**
     * Sends a GET request to the server to get all entries on a user's calendar.
     *
     * @param netID The user's netID
     * @return The server's response, {@link nl.tudelft.oopp.group31.entities.CalendarEntry} objects in JSON format
     */
    public static String getUserCalendar(String netID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/calendarentry/user?netID=" + netID), client);
    }

    /**
     * Sends a GET request to the server to delete an entry from a user's calendar.
     *
     * @param entryID The ID of the calendar entry
     * @return The server's response
     */
    public static String deleteUserCalendar(int entryID) {
        return RequestHelper.sendRequest(
                RequestHelper.getRequest("/calendarentry/delete?id=" + entryID), client);
    }

    /**
     * changes user details based off json given in.
     * @param json representation of new user
     * @return The server response
     */
    public static String changeUserDetails(String json) {
        return RequestHelper.sendRequest(RequestHelper.postRequest(
                "/user/change", json), client);
    }
}
