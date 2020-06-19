package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomTest {

    private Room room;
    private final String testString = "Project Room 8";
    private final String testJson = "{\"id\":\"" + 1
                                + "\", \"name\":\"" + "Project Room 8"
                                + "\", \"buildingID\":\"" + "101"
                                + "\", \"whiteboard\":\"" + true
                                + "\", \"accessibility\":\"" + false
                                + "\", \"capacity\":\"" + 50 + "\"}";

    @BeforeEach
    void setup() {
        room = new Room(1, "Project Room 8", "101", true, false, 50);
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(room.getId());
        assertNotNull(room.getName());
        assertNotNull(room.getBid());
        assertNotNull(room.hasWhiteBoard());
        assertNotNull(room.hasAccess());
        assertNotNull(room.getCapacity());
    }

    @Test
    void testFullConstructor() {
        assertEquals(1, room.getId());
        assertEquals("Project Room 8", room.getName());
        assertEquals("101", room.getBid());
        assertTrue(room.hasWhiteBoard());
        assertFalse(room.hasAccess());
        assertEquals(50, room.getCapacity());
    }

    @Test
    void testEmptyConstructor() {
        Room emptyRoom = new Room();
        assertEquals(-1, emptyRoom.getId());
        assertNull(emptyRoom.getName());
        assertEquals("-1", emptyRoom.getBid());
        assertFalse(emptyRoom.hasWhiteBoard());
        assertFalse(emptyRoom.hasAccess());
        assertEquals(0, emptyRoom.getCapacity());
    }

    @Test
    void testToString() {
        assertEquals(testString, room.toString());
    }

    @Test
    void testToJson() {
        assertEquals(testJson, room.toJson());
    }

    @Test
    void getID() {
        assertEquals(1, room.getId());
    }

    @Test
    void setID() {
        room.setId(0);
        assertEquals(0, room.getId());
    }

    @Test
    void getName() {
        assertEquals("Project Room 8", room.getName());
    }

    @Test
    void setName() {
        room.setName("Seminar Room 3");
        assertEquals("Seminar Room 3", room.getName());
    }

    @Test
    void getBuildingID() {
        assertEquals("101", room.getBid());
    }

    @Test
    void setBuildingID() {
        room.setBuildingID("10");
        assertEquals("10", room.getBid());
    }

    @Test
    void getWhiteboard() {
        assertTrue(room.hasWhiteBoard());
    }

    @Test
    void setWhiteboard() {
        room.setWhiteboard(false);
        assertFalse(room.hasWhiteBoard());
    }

    @Test
    void getAccessibility() {
        assertFalse(room.hasAccess());
    }

    @Test
    void setAccessibility() {
        room.setAccessibility(true);
        assertTrue(room.hasAccess());
    }

    @Test
    void getCapacity() {
        assertEquals(50, room.getCapacity());
    }

    @Test
    void setCapacity() {
        room.setCapacity(99);
        assertEquals(99, room.getCapacity());
    }
}
