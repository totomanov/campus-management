package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RoomReservationTest {

    private RoomReservation roomReservation;
    private final String testString = "{\"id\":\"" + 0
                                    + "\", \"netID\":\"" + "maikdevries"
                                    + "\", \"date\":\"" + "2020-03-01"
                                    + "\", \"roomID\":\"" + 33
                                    + "\", \"startingHour\":\"" + "0900"
                                    + "\", \"endingHour\":\"" + "1000" + "\"}";

    @BeforeEach
    void setup() {
        roomReservation = new RoomReservation("maikdevries", "2020-03-01", 33, "0900", "1000");
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
    void testNetDateRoomConstructorNotNull() {
        RoomReservation netDateRoomRoomReservation = new RoomReservation("maikdevries", "2020-03-01", 33);
        assertNotNull(netDateRoomRoomReservation.getNetID());
        assertNotNull(netDateRoomRoomReservation.getDate());
        assertNotNull(netDateRoomRoomReservation.getRoomID());
    }

    @Test
    void testNetDateRoomConstructor() {
        RoomReservation netDateRoomRoomReservation = new RoomReservation("maikdevries", "2020-03-01", 33);
        assertEquals("maikdevries", netDateRoomRoomReservation.getNetID());
        assertEquals("2020-03-01", netDateRoomRoomReservation.getDate());
        assertEquals(33, netDateRoomRoomReservation.getRoomID());
    }

    @Test
    void testEmptyConstructor() {
        RoomReservation emptyRoomReservation = new RoomReservation();
        assertNull(emptyRoomReservation.getNetID());
        assertNull(emptyRoomReservation.getDate());
        assertEquals(-1, emptyRoomReservation.getRoomID());
        assertNull(emptyRoomReservation.getStartingHour());
        assertNull(emptyRoomReservation.getEndingHour());
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
        assertEquals(0, roomReservation.getID());
    }

    @Test
    void setID() {
        roomReservation.setID(1);
        assertEquals(1, roomReservation.getID());
    }

    @Test
    void getNetID() {
        assertEquals("maikdevries", roomReservation.getNetID());
    }

    @Test
    void setNetID() {
        roomReservation.setNetID("peterpan");
        assertEquals("peterpan", roomReservation.getNetID());
    }

    @Test
    void getDate() {
        assertEquals("2020-03-01", roomReservation.getDate());
    }

    @Test
    void setDate() {
        roomReservation.setDate("2019-03-01");
        assertEquals("2019-03-01", roomReservation.getDate());
    }

    @Test
    void getRoomID() {
        assertEquals(33, roomReservation.getRoomID());
    }

    @Test
    void setRoomID() {
        roomReservation.setRoomID(15);
        assertEquals(15, roomReservation.getRoomID());
    }

    @Test
    void getStartingHour() {
        assertEquals("0900", roomReservation.getStartingHour());
    }

    @Test
    void setStartingHour() {
        roomReservation.setStartingHour("0800");
        assertEquals("0800", roomReservation.getStartingHour());
    }

    @Test
    void getEndingHour() {
        assertEquals("1000", roomReservation.getEndingHour());
    }

    @Test
    void setEndingHour() {
        roomReservation.setEndingHour("1800");
        assertEquals("1800", roomReservation.getEndingHour());
    }
}
