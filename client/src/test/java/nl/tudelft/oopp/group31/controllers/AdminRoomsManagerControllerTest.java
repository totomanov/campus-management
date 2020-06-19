package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import nl.tudelft.oopp.group31.entities.Building;
import org.junit.jupiter.api.Test;

public class AdminRoomsManagerControllerTest {


    @Test
    public void testValidateInputNoName() {
        AdminRoomsManagerController a = new AdminRoomsManagerController();

        assertEquals("Please enter a room name.", a.validateInput("", "500"));
    }

    @Test
    public void testValidateInputNullName() {
        AdminRoomsManagerController a = new AdminRoomsManagerController();
        assertEquals("Please enter a room name.", a.validateInput(null, "500"));
    }

    @Test
    public void testValidateInputLongName() {
        AdminRoomsManagerController a = new AdminRoomsManagerController();
        StringBuilder name = new StringBuilder();
        name.append("a".repeat(101));

        assertEquals("Room name cannot be longer than 100 characters.",
                a.validateInput(name.toString(), "500"));
    }

    @Test
    public void testValidateInputNegativeCapacity() {
        AdminRoomsManagerController a = new AdminRoomsManagerController();
        assertEquals("Capacity cannot be 0 or negative.", a.validateInput("roomname", "-1"));
    }

    @Test
    public void testValidateInputZeroCapacity() {
        AdminRoomsManagerController a = new AdminRoomsManagerController();
        assertEquals("Capacity cannot be 0 or negative.", a.validateInput("roomname", "0"));
    }

    @Test
    public void testValidateInputNaN() {
        AdminRoomsManagerController a = new AdminRoomsManagerController();
        assertEquals("Please enter a valid number", a.validateInput("roomname", "kiwi"));
    }


    @Test
    public void testHashMapEquals() {
        AdminRoomsManagerController a = new AdminRoomsManagerController();
        Building b1 = new Building("name1", "1030", "1200", "testbuilding1");
        Building b2 = new Building("name2", "1030", "1200", "testbuilding2");

        ArrayList<Building> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);

        HashMap<String, Building> actual = a.buildingHashMap(list);
        HashMap<String, Building> expected = new HashMap<>();

        expected.put(b1.getName(), b1);
        expected.put(b2.getName(), b2);

        assertEquals(expected, actual);
    }


}
