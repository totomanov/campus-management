package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RoomTest {

    private Room room;
    private final String testString = "{\"id\":\"" + 0
                                + "\", \"name\":\"" + "Project Room 8"
                                + "\", \"buildingID\":\"" + 101
                                + "\", \"whiteboard\":\"" + true
                                + "\", \"accessibility\":\"" + false
                                + "\", \"capacity\":\"" + 50 + "\"}";

    @BeforeEach
    void setup() {
        room = new Room("Project Room 8", 101, true, false, 50);
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(room.getName());
        assertNotNull(room.getBuildingID());
        assertNotNull(room.getWhiteboard());
        assertNotNull(room.getAccessibility());
        assertNotNull(room.getCapacity());
    }

    @Test
    void testFullConstructor() {
        assertEquals("Project Room 8", room.getName());
        assertEquals(101, room.getBuildingID());
        assertTrue(room.getWhiteboard());
        assertFalse(room.getAccessibility());
        assertEquals(50, room.getCapacity());
    }

    @Test
    void testBuildingConstructorNotNull() {
        Room buildingRoom = new Room(101, 50);
        assertNotNull(buildingRoom.getBuildingID());
        assertNotNull(buildingRoom.getCapacity());
    }

    @Test
    void testBuildingConstructor() {
        Room buildingRoom = new Room(101, 50);
        assertNull(buildingRoom.getName());
        assertEquals(101, buildingRoom.getBuildingID());
        assertFalse(buildingRoom.getWhiteboard());
        assertFalse(buildingRoom.getAccessibility());
        assertEquals(50, buildingRoom.getCapacity());
    }

    @Test
    void testNameConstructorNotNull() {
        Room nameRoom = new Room("Project Room 8", 101, 50);
        assertNotNull(nameRoom.getName());
        assertNotNull(nameRoom.getBuildingID());
        assertNotNull(nameRoom.getCapacity());
    }

    @Test
    void testNameConstructor() {
        Room nameRoom = new Room("Project Room 8", 101, 50);
        assertEquals("Project Room 8", nameRoom.getName());
        assertEquals(101, nameRoom.getBuildingID());
        assertFalse(nameRoom.getWhiteboard());
        assertFalse(nameRoom.getAccessibility());
        assertEquals(50, nameRoom.getCapacity());
    }

    @Test
    void testWhiteboardAccessibilityConstructorNotNull() {
        Room whiteboardAccessibilityRoom = new Room(101, true, false, 50);
        assertNotNull(whiteboardAccessibilityRoom.getBuildingID());
        assertNotNull(whiteboardAccessibilityRoom.getWhiteboard());
        assertNotNull(whiteboardAccessibilityRoom.getAccessibility());
        assertNotNull(whiteboardAccessibilityRoom.getCapacity());
    }

    @Test
    void testWhiteboardAccessibilityConstructor() {
        Room whiteboardAccessibilityRoom = new Room(101, true, false, 50);
        assertNull(whiteboardAccessibilityRoom.getName());
        assertEquals(101, whiteboardAccessibilityRoom.getBuildingID());
        assertTrue(whiteboardAccessibilityRoom.getWhiteboard());
        assertFalse(whiteboardAccessibilityRoom.getAccessibility());
        assertEquals(50, whiteboardAccessibilityRoom.getCapacity());
    }

    @Test
    void testEmptyConstructor() {
        Room emptyRoom = new Room();
        assertNull(emptyRoom.getName());
        assertEquals(-1, emptyRoom.getBuildingID());
        assertFalse(emptyRoom.getWhiteboard());
        assertFalse(emptyRoom.getAccessibility());
        assertEquals(-1, emptyRoom.getCapacity());
    }

    @Test
    void testEqualsSame() {
        assertEquals(room, room);
    }

    @Test
    void testEqualsFailEntity() {
        assertNotEquals(room, new User());
    }

    @Test
    void testEqualsFailName() {
        Room failRoom = new Room("Seminar Room 3", 101, true, false, 50);
        assertNotEquals(room, failRoom);
    }

    @Test
    void testEqualsFailBuilding() {
        Room failRoom = new Room("Project Room 8", 100, true, false, 50);
        assertNotEquals(room, failRoom);
    }

    @Test
    void testEqualsFailWhiteboard() {
        Room failRoom = new Room("Project Room 8", 101, false, false, 50);
        assertNotEquals(room, failRoom);
    }

    @Test
    void testEqualsFailAccessibility() {
        Room failRoom = new Room("Project Room 8", 101, true, true, 50);
        assertNotEquals(room, failRoom);
    }

    @Test
    void testEqualsFailCapacity() {
        Room failRoom = new Room("Project Room 8", 101, true, false, 99);
        assertNotEquals(room, failRoom);
    }

    @Test
    void testEqualsSuccess() {
        Room successRoom = new Room("Project Room 8", 101, true, false, 50);
        assertEquals(room, successRoom);
    }

    @Test
    void testToString() {
        assertEquals(testString, room.toString());
    }

    @Test
    void getID() {
        assertEquals(0, room.getID());
    }

    @Test
    void setID() {
        room.setID(1);
        assertEquals(1, room.getID());
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
        assertEquals(101, room.getBuildingID());
    }

    @Test
    void setBuildingID() {
        room.setBuildingID(10);
        assertEquals(10, room.getBuildingID());
    }

    @Test
    void getWhiteboard() {
        assertTrue(room.getWhiteboard());
    }

    @Test
    void setWhiteboard() {
        room.setWhiteboard(false);
        assertFalse(room.getWhiteboard());
    }

    @Test
    void getAccessibility() {
        assertFalse(room.getAccessibility());
    }

    @Test
    void setAccessibility() {
        room.setAccessibility(true);
        assertTrue(room.getAccessibility());
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
