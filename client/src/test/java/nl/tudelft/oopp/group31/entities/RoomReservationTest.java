package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RoomReservationTest {

    private RoomReservation roomReservation;
    private final String testString = "RoomReservation{"
                                    + "id=" + 0
                                    + ", netID='" + "maikdevries"
                                    + "', date='" + "2020-03-01"
                                    + "', roomID=" + 33
                                    + ", buildingName='" + "EWI"
                                    + "', roomName='" + "Project Room 8"
                                    + "', startingHour='" + "0900"
                                    + "', endingHour='" + "1000" + "'}";

    @BeforeEach
    void setup() {
        roomReservation = new RoomReservation("maikdevries", "2020-03-01", 33, "0900", "1000");
        roomReservation.setBuildingName("EWI");
        roomReservation.setRoomName("Project Room 8");
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(roomReservation.getNetID());
        assertNotNull(roomReservation.getDate());
        assertNotNull(roomReservation.getRoomID());
        assertNotNull(roomReservation.getStartingHour());
        assertNotNull(roomReservation.getEndingHour());
    }

    @Test
    void testFullConstructor() {
        assertEquals("maikdevries", roomReservation.getNetID());
        assertEquals("2020-03-01", roomReservation.getDate());
        assertEquals(33, roomReservation.getRoomID());
        assertEquals("0900", roomReservation.getStartingHour());
        assertEquals("1000", roomReservation.getEndingHour());
    }

    @Test
    void testEqualsSame() {
        assertEquals(roomReservation, roomReservation);
    }

    @Test
    void testEqualsFailEntity() {
        assertNotEquals(roomReservation, new User());
    }

    @Test
    void testEqualsFailNetID() {
        RoomReservation failRoomrReservation = new RoomReservation("peterpan", "2020-03-01", 33, "0900", "1000");
        assertNotEquals(roomReservation, failRoomrReservation);
    }

    @Test
    void testEqualsFailDate() {
        RoomReservation failRoomrReservation = new RoomReservation("maikdevries", "2019-03-01", 33, "0900", "1000");
        assertNotEquals(roomReservation, failRoomrReservation);
    }

    @Test
    void testEqualsFailRoomID() {
        RoomReservation failRoomrReservation = new RoomReservation("maikdevries", "2020-03-01", 15, "0900", "1000");
        assertNotEquals(roomReservation, failRoomrReservation);
    }

    @Test
    void testEqualsFailStartingHour() {
        RoomReservation failRoomrReservation = new RoomReservation("maikdevries", "2020-03-01", 33, "0800", "1000");
        assertNotEquals(roomReservation, failRoomrReservation);
    }

    @Test
    void testEqualsFailEndingHour() {
        RoomReservation failRoomrReservation = new RoomReservation("maikdevries", "2020-03-01", 33, "0800", "1800");
        assertNotEquals(roomReservation, failRoomrReservation);
    }

    @Test
    void testEqualsSuccess() {
        RoomReservation successRoomReservation = new RoomReservation("maikdevries", "2020-03-01", 33, "0900", "1000");
        assertEquals(roomReservation, successRoomReservation);
    }

    @Test
    void testToString() {
        assertEquals(testString, roomReservation.toString());
    }

    @Test
    void getID() {
        assertEquals(0, roomReservation.getId());
    }

    @Test
    void setID() {
        roomReservation.setID(1);
        assertEquals(1, roomReservation.getId());
    }

    @Test
    void getNetID() {
        assertEquals("maikdevries", roomReservation.getNetID());
    }

    @Test
    void getDate() {
        assertEquals("2020-03-01", roomReservation.getDate());
    }

    @Test
    void getRoomID() {
        assertEquals(33, roomReservation.getRoomID());
    }

    @Test
    void getStartingHour() {
        assertEquals("0900", roomReservation.getStartingHour());
    }

    @Test
    void getEndingHour() {
        assertEquals("1000", roomReservation.getEndingHour());
    }

    @Test
    void getBuildingName() {
        assertEquals("EWI", roomReservation.getBuildingName());
    }

    @Test
    void setBuildingName() {
        roomReservation.setBuildingName("AE");
        assertEquals("AE", roomReservation.getBuildingName());
    }

    @Test
    void getRoomName() {
        assertEquals("Project Room 8", roomReservation.getRoomName());
    }

    @Test
    void setRoomName() {
        roomReservation.setRoomName("Seminar Room 3");
        assertEquals("Seminar Room 3", roomReservation.getRoomName());
    }

    @Test
    void testGetTimeThreeCharacterStartingHour() {
        RoomReservation rr = new RoomReservation(
                "maikdevries", "2019-03-01", 33, "900", "2000");
        assertEquals(rr.getTime(), "9:00-20:00");
    }

    @Test
    void testGetTimeFourCharacterStartingHour() {
        RoomReservation rr = new RoomReservation(
                "maikdevries", "2019-03-01", 33, "0900", "2000");
        assertEquals(rr.getTime(), "09:00-20:00");
    }

    @Test
    void testGetTimeThreeCharacterEndingHour() {
        RoomReservation rr = new RoomReservation(
                "maikdevries", "2019-03-01", 33, "2300", "900");
        assertEquals(rr.getTime(), "23:00-9:00");
    }

    @Test
    void testGetTimeFourCharacterEndingHour() {
        RoomReservation rr = new RoomReservation(
                "maikdevries", "2019-03-01", 33, "2300", "0900");
        assertEquals(rr.getTime(), "23:00-09:00");
    }

    @Test
    void testGetStatusNullDate() {
        RoomReservation rr = new RoomReservation(
                "maikdevries", null, 33, "0900", "1100");
        assertEquals(rr.getStatus(), "Upcoming");
    }

    @Test
    void testGetStatusPastDate() {
        RoomReservation rr = new RoomReservation(
                "maikdevries", "1970-03-01", 33, "0900", "1100");
        assertEquals(rr.getStatus(), "Past");
    }

    @Test
    void testGetStatusUpcomingDate() {
        RoomReservation rr = new RoomReservation(
                "maikdevries", "2100-03-01", 33, "0900", "1100");
        assertEquals(rr.getStatus(), "Upcoming");
    }
}
