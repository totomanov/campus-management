package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.group31.entities.Building;
import org.junit.jupiter.api.Test;

public class AdminBuildingsManagerControllerTest {

    @Test
    public void testValidateInput1() {
        Building b = new Building("", "openingHour", "closingHour", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Please enter a building name.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput2() {
        StringBuilder name = new StringBuilder();
        name.append("a".repeat(101));
        Building b = new Building(name.toString(), "openingHour", "closingHour", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Building name cannot be longer than 100 characters.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput3() {
        Building b = new Building("name", "openingHour", "closingHour", "", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Please enter description.", controller.validateInput(b));
        b = new Building("name", "openingHour", "closingHour", null, 2);
        assertEquals("Please enter description.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput4() {
        StringBuilder description = new StringBuilder();
        description.append("a".repeat(5001));
        Building b = new Building("name", "openingHour", "closingHour", description.toString(), 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Description name cannot be longer than 5000 characters.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput5() {
        Building b = new Building("name", "1200", "2500", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Please enter a valid closing hour.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput6() {
        Building b = new Building("name", "1200", "0900", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Closing hour must be after the opening hour.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput7() {
        Building b = new Building("name", "12ss", "1300", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Please enter a valid opening minute.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput8() {
        Building b = new Building("name", "1270", "1300", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Please enter a valid opening minute.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput9() {
        Building b = new Building("name", "1200", "13ss", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Please enter a valid closing minute.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput10() {
        Building b = new Building("name", "1200", "1370", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Please enter a valid closing minute.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput11() {
        Building b = new Building("name", "1250", "1240", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertEquals("Closing time must be after the opening time.", controller.validateInput(b));
    }

    @Test
    public void testValidateInput12() {
        Building b = new Building("name", "1230", "1240", "description", 2);
        AdminBuildingsManagerController controller = new AdminBuildingsManagerController();
        assertNull(controller.validateInput(b));
    }

}
