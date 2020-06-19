package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import nl.tudelft.oopp.group31.entities.Reservation;
import nl.tudelft.oopp.group31.entities.Room;
import nl.tudelft.oopp.group31.entities.TimeSlot;

import org.junit.jupiter.api.Test;

public class UserTimeslotControllerTest {


    @Test
    void initResArrayTest() {
        String res = "[{\"id\":\"" + 434 + "\", \"netID\":\"" + "user" + "\", \"date\":\"" + "2020-04-12" + "\", \"roomID\":\""
                    + 168 + "\", \"startingHour\":\"" + "0800" + "\", \"endingHour\":\"" + 1000 + "\"}]";
        ArrayList<TimeSlot> resArray = UserTimeslotController.initResArray(res);
        assertEquals(1, resArray.size());
    }

    @Test
    void initResArrayTestCheckIfProperTimeslotSaved() {
        String res = "[{\"id\":\"" + 434 + "\", \"netID\":\"" + "user" + "\", \"date\":\"" + "2020-04-12" + "\", \"roomID\":\""
                    + 168 + "\", \"startingHour\":\"" + "0800" + "\", \"endingHour\":\"" + 1000 + "\"}]";
        ArrayList<TimeSlot> resArray = UserTimeslotController.initResArray(res);
        TimeSlot timeSlot = resArray.get(0);
        assertEquals("user", timeSlot.getID());
    }

    @Test
    void changeColorOfreservedTimeslotsTest() {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>(5);
        Reservation r1 = new Reservation(null, 800, 830);
        Reservation r2 = new Reservation(null, 830, 900);
        Reservation r3 = new Reservation(null, 900, 930);
        Reservation r4 = new Reservation(null, 930, 1000);
        Reservation r5 = new Reservation(null, 1000, 1030);
        reservations.add(0, r1);
        reservations.add(1, r2);
        reservations.add(2, r3);
        reservations.add(3, r4);
        reservations.add(4, r5);
        String res = "[{\"id\":\"" + 434 + "\", \"netID\":\"" + "user" + "\", \"date\":\"" + "2020-04-12"
                + "\", \"roomID\":\"" + 168 + "\", \"startingHour\":\"" + "0800" + "\", \"endingHour\":\"" + 1000
                + "\"}]";
        ArrayList<TimeSlot> resArray = UserTimeslotController.initResArray(res);
        ArrayList<Reservation> results = UserTimeslotController.changeColorRedOfReservedTimeslotsTestMethod(1, reservations, resArray);
        assertEquals(4, results.size());
    }

    @Test
    void changeColorOfreservedTimeslotsTestCheckForStartTime() {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>(5);
        Reservation r1 = new Reservation(null, 800, 830);
        Reservation r2 = new Reservation(null, 830, 900);
        Reservation r3 = new Reservation(null, 900, 930);
        Reservation r4 = new Reservation(null, 930, 1000);
        Reservation r5 = new Reservation(null, 1000, 1030);
        reservations.add(0, r1);
        reservations.add(1, r2);
        reservations.add(2, r3);
        reservations.add(3, r4);
        reservations.add(4, r5);
        String res = "[{\"id\":\"" + 434 + "\", \"netID\":\"" + "user" + "\", \"date\":\"" + "2020-04-12"
                + "\", \"roomID\":\"" + 168 + "\", \"startingHour\":\"" + "0800" + "\", \"endingHour\":\"" + 1000
                + "\"}]";
        ArrayList<TimeSlot> resArray = UserTimeslotController.initResArray(res);
        ArrayList<Reservation> results = UserTimeslotController.changeColorRedOfReservedTimeslotsTestMethod(1, reservations, resArray);
        assertEquals(800, results.get(0).getStartTime());
    }

    @Test
    void changeColorOfreservedTimeslotsTestCheckForEndTime() {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>(5);
        Reservation r1 = new Reservation(null, 800, 830);
        Reservation r2 = new Reservation(null, 830, 900);
        Reservation r3 = new Reservation(null, 900, 930);
        Reservation r4 = new Reservation(null, 930, 1000);
        Reservation r5 = new Reservation(null, 1000, 1030);
        reservations.add(0, r1);
        reservations.add(1, r2);
        reservations.add(2, r3);
        reservations.add(3, r4);
        reservations.add(4, r5);
        String res = "[{\"id\":\"" + 434 + "\", \"netID\":\"" + "user" + "\", \"date\":\"" + "2020-04-12"
                + "\", \"roomID\":\"" + 168 + "\", \"startingHour\":\"" + "0800" + "\", \"endingHour\":\"" + 1000
                + "\"}]";
        ArrayList<TimeSlot> resArray = UserTimeslotController.initResArray(res);
        ArrayList<Reservation> results = UserTimeslotController.changeColorRedOfReservedTimeslotsTestMethod(1, reservations, resArray);
        assertEquals(1000, results.get(3).getEndTime());
    }

    @Test
    void getAllRoomsTest() {
        String allRooms = "[{\"id\":\"" + 168 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}]";
        ArrayList<Room> rooms = UserTimeslotController.getAllRooms(allRooms);
        assertEquals(168, rooms.get(0).getId());
    }

    @Test
    void getAllRoomsTestForMultipleRooms() {
        String allRooms = "[{\"id\":\"" + 168 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}, {\"id\":\"" + 170 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}]";
        ArrayList<Room> rooms = UserTimeslotController.getAllRooms(allRooms);
        assertEquals(170, rooms.get(1).getId());
    }

    @Test
    void addToBoxAndHashMap() {
        String allRooms = "[{\"id\":\"" + 168 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}, {\"id\":\"" + 170 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "false" + "\", \"accessibility\":\"" + "true" + "\", \"capacity\":\"" + 1305
                + "\"}]";
        ArrayList<Room> rooms = UserTimeslotController.getAllRooms(allRooms);
        HashMap<String, String> roomHash = new  HashMap<String, String>();
        ArrayList<Room> selectedRooms = UserTimeslotController.addToBoxAndHashMaptestMethod(rooms, false, false, roomHash);
        assertEquals(2, selectedRooms.size());
    }

    @Test
    void addToBoxAndHashMapWhiteBoardTrue() {
        String allRooms = "[{\"id\":\"" + 168 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}, {\"id\":\"" + 170 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "false" + "\", \"accessibility\":\"" + "true" + "\", \"capacity\":\"" + 1305
                + "\"}]";
        ArrayList<Room> rooms = UserTimeslotController.getAllRooms(allRooms);
        HashMap<String, String> roomHash = new HashMap<String, String>();
        ArrayList<Room> selectedRooms = UserTimeslotController.addToBoxAndHashMaptestMethod(rooms, true, false,
                roomHash);
        assertEquals(1, selectedRooms.size());
    }

    @Test
    void addToBoxAndHashMapWhiteBoardTrue2() {
        String allRooms = "[{\"id\":\"" + 168 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}, {\"id\":\"" + 170 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "false" + "\", \"accessibility\":\"" + "true" + "\", \"capacity\":\"" + 1305
                + "\"}]";
        ArrayList<Room> rooms = UserTimeslotController.getAllRooms(allRooms);
        HashMap<String, String> roomHash = new HashMap<String, String>();
        ArrayList<Room> selectedRooms = UserTimeslotController.addToBoxAndHashMaptestMethod(rooms, true, false,
                roomHash);
        assertEquals(168, selectedRooms.get(0).getId());
    }

    @Test
    void addToBoxAndHashMapAccessTrue() {
        String allRooms = "[{\"id\":\"" + 168 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}, {\"id\":\"" + 170 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "false" + "\", \"accessibility\":\"" + "true" + "\", \"capacity\":\"" + 1305
                + "\"}]";
        ArrayList<Room> rooms = UserTimeslotController.getAllRooms(allRooms);
        HashMap<String, String> roomHash = new HashMap<String, String>();
        ArrayList<Room> selectedRooms = UserTimeslotController.addToBoxAndHashMaptestMethod(rooms, true, false,
                roomHash);
        assertEquals(1, selectedRooms.size());
    }

    @Test
    void addToBoxAndHashMapAccessTrue2() {
        String allRooms = "[{\"id\":\"" + 168 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}, {\"id\":\"" + 170 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "false" + "\", \"accessibility\":\"" + "true" + "\", \"capacity\":\"" + 1305
                + "\"}]";
        ArrayList<Room> rooms = UserTimeslotController.getAllRooms(allRooms);
        HashMap<String, String> roomHash = new HashMap<String, String>();
        ArrayList<Room> selectedRooms = UserTimeslotController.addToBoxAndHashMaptestMethod(rooms, false, true,
                roomHash);
        assertEquals(170, selectedRooms.get(0).getId());
    }

    @Test
    void addToBoxAndHashMapHashMapTest() {
        String allRooms = "[{\"id\":\"" + 168 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}, {\"id\":\"" + 170 + "\", \"name\":\"" + "Boole" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "false" + "\", \"accessibility\":\"" + "true" + "\", \"capacity\":\"" + 1305
                + "\"}]";
        ArrayList<Room> rooms = UserTimeslotController.getAllRooms(allRooms);
        HashMap<String, String> roomHash = new HashMap<String, String>();
        UserTimeslotController.addToBoxAndHashMaptestMethod(rooms, false, false,
                roomHash);
        assertNotNull(roomHash.get("Ampere"));
    }

    @Test
    void addToBoxAndHashMapHashMapTest2() {
        String allRooms = "[{\"id\":\"" + 168 + "\", \"name\":\"" + "Ampere" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "true" + "\", \"accessibility\":\"" + "false" + "\", \"capacity\":\"" + 1305
                + "\"}, {\"id\":\"" + 170 + "\", \"name\":\"" + "Boole" + "\", \"building\":\"" + "95"
                + "\", \"whiteboard\":\"" + "false" + "\", \"accessibility\":\"" + "true" + "\", \"capacity\":\"" + 1305
                + "\"}]";
        ArrayList<Room> rooms = UserTimeslotController.getAllRooms(allRooms);
        HashMap<String, String> roomHash = new HashMap<String, String>();
        UserTimeslotController.addToBoxAndHashMaptestMethod(rooms, false, false,
                roomHash);
        assertNotNull(roomHash.get("Boole"));
    }

    @Test
    void checkAccessTestTrue() {
        Room room = new Room("test", "100", true, true, 10);
        assertTrue(UserTimeslotController.checkAccess(false, room));
    }

    @Test
    void checkAccessTesttrueWithRoomHasAccess() {
        Room room = new Room("test", "100", true, true, 10);
        assertTrue(UserTimeslotController.checkAccess(true, room));
    }

    @Test
    void checkAccessTesttrueWithRoomDoesNotHaveAccess() {
        Room room = new Room("test", "100", true, false, 10);
        assertFalse(UserTimeslotController.checkAccess(true, room));
    }

    @Test
    void checkWhiteboardTestTrue() {
        Room room = new Room("test", "100", true, true, 10);
        assertTrue(UserTimeslotController.checkWhiteboard(false, room));
    }

    @Test
    void checkWhiteboardTesttrueWithRoomHasWhiteboard() {
        Room room = new Room("test", "100", true, true, 10);
        assertTrue(UserTimeslotController.checkWhiteboard(true, room));
    }

    @Test
    void checkWhiteboardTesttrueWithRoomDoesNotHaveWhiteboard() {
        Room room = new Room("test", "100", false, true, 10);
        assertFalse(UserTimeslotController.checkWhiteboard(true, room));
    }
}
