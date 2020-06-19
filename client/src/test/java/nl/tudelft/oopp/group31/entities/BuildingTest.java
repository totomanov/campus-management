package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BuildingTest {

    private Building building;
    private final String testString = "{\"id\":\"" + 0
                                + "\", \"name\":\"" + "EWI"
                                + "\", \"openingHour\":\"" + "0900"
                                + "\", \"closingHour\":\"" + "2100"
                                + "\", \"description\":\"" + "Faculty of Electrical Engineering, Mathematics and Computer Science"
                                + "\", \"bikeRental\":\"" + 75 + "\"}";

    @BeforeEach
    void setup() {
        building = new Building("EWI", "0900", "2100", "Faculty of Electrical Engineering, Mathematics and Computer Science", 75);
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(building.getName());
        assertNotNull(building.getOpeningHour());
        assertNotNull(building.getClosingHour());
        assertNotNull(building.getDescription());
        assertNotNull(building.getBikeRental());
    }

    @Test
    void testFullConstructor() {
        assertEquals("EWI", building.getName());
        assertEquals("0900", building.getOpeningHour());
        assertEquals("2100", building.getClosingHour());
        assertEquals("Faculty of Electrical Engineering, Mathematics and Computer Science", building.getDescription());
        assertEquals(75, building.getBikeRental());
    }

    @Test
    void testDescriptionConstructorNotNull() {
        Building descriptionBuilding = new Building("Faculty of Electrical Engineering, Mathematics and Computer Science");
        assertNotNull(descriptionBuilding.getDescription());
    }

    @Test
    void testDescriptionConstructor() {
        Building descriptionBuilding = new Building("Faculty of Electrical Engineering, Mathematics and Computer Science");
        assertNull(descriptionBuilding.getName());
        assertNull(descriptionBuilding.getOpeningHour());
        assertNull(descriptionBuilding.getClosingHour());
        assertEquals("Faculty of Electrical Engineering, Mathematics and Computer Science", descriptionBuilding.getDescription());
        assertEquals(-1, descriptionBuilding.getBikeRental());
    }

    @Test
    void testOpeningClosingConstructorNotNull() {
        Building openingClosingBuilding = new Building("0900", "2100");
        assertNotNull(openingClosingBuilding.getOpeningHour());
        assertNotNull(openingClosingBuilding.getClosingHour());
    }

    @Test
    void testOpeningClosingConstructor() {
        Building openingClosingBuilding = new Building("0900", "2100");
        assertNull(openingClosingBuilding.getName());
        assertNull(openingClosingBuilding.getDescription());
        assertEquals("0900", openingClosingBuilding.getOpeningHour());
        assertEquals("2100", openingClosingBuilding.getClosingHour());
        assertEquals(-1, openingClosingBuilding.getBikeRental());
    }

    @Test
    void testOpeningClosingDescriptionConstructorNotNull() {
        Building openingClosingDescription = new Building("0900", "2100", "Faculty of Electrical Engineering, Mathematics and Computer Science");
        assertNotNull(openingClosingDescription.getOpeningHour());
        assertNotNull(openingClosingDescription.getClosingHour());
        assertNotNull(openingClosingDescription.getDescription());
    }

    @Test
    void testOpeningClosingDescriptionConstructor() {
        Building openingClosingDescription = new Building("0900", "2100", "Faculty of Electrical Engineering, Mathematics and Computer Science");
        assertNull(openingClosingDescription.getName());
        assertEquals("0900", openingClosingDescription.getOpeningHour());
        assertEquals("2100", openingClosingDescription.getClosingHour());
        assertEquals("Faculty of Electrical Engineering, Mathematics and Computer Science", openingClosingDescription.getDescription());
        assertEquals(-1, openingClosingDescription.getBikeRental());
    }

    @Test
    void testNameOpeningClosingDescriptionConstructorNotNull() {
        Building nameOpeningClosingDescription = new Building("EWI", "0900", "2100",
                                                              "Faculty of Electrical Engineering, Mathematics and Computer Science");
        assertNotNull(nameOpeningClosingDescription.getName());
        assertNotNull(nameOpeningClosingDescription.getOpeningHour());
        assertNotNull(nameOpeningClosingDescription.getClosingHour());
        assertNotNull(nameOpeningClosingDescription.getDescription());
    }

    @Test
    void testNameOpeningClosingDescriptionConstructor() {
        Building nameOpeningClosingDescription = new Building("EWI", "0900", "2100",
                                                              "Faculty of Electrical Engineering, Mathematics and Computer Science");
        assertEquals("EWI", nameOpeningClosingDescription.getName());
        assertEquals("0900", nameOpeningClosingDescription.getOpeningHour());
        assertEquals("2100", nameOpeningClosingDescription.getClosingHour());
        assertEquals("Faculty of Electrical Engineering, Mathematics and Computer Science", nameOpeningClosingDescription.getDescription());
        assertEquals(-1, nameOpeningClosingDescription.getBikeRental());
    }

    @Test
    void testEmptyConstructor() {
        Building emptyBuilding = new Building();
        assertNull(emptyBuilding.getName());
        assertNull(emptyBuilding.getOpeningHour());
        assertNull(emptyBuilding.getClosingHour());
        assertNull(emptyBuilding.getDescription());
        assertEquals(-1, emptyBuilding.getBikeRental());
    }

    @Test
    void testEqualsSame() {
        assertEquals(building, building);
    }

    @Test
    void testEqualsFailEntity() {
        assertNotEquals(building, new User());
    }

    @Test
    void testEqualsFailName() {
        Building failBuilding = new Building("AE", "0900", "2100", "Faculty of Electrical Engineering, Mathematics and Computer Science");
        assertNotEquals(building, failBuilding);
    }

    @Test
    void testEqualsFailOpening() {
        Building failBuilding = new Building("EWI", "0800", "2100", "Faculty of Electrical Engineering, Mathematics and Computer Science");
        assertNotEquals(building, failBuilding);
    }

    @Test
    void testEqualsFailClosing() {
        Building failBuilding = new Building("EWI", "0900", "2000", "Faculty of Electrical Engineering, Mathematics and Computer Science");
        assertNotEquals(building, failBuilding);
    }

    @Test
    void testEqualsFailDescription() {
        Building failBuilding = new Building("EWI", "0900", "2100", "Faculty of Aerospace Engineering");
        assertNotEquals(building, failBuilding);
    }

    @Test
    void testEqualsSuccess() {
        Building successBuilding = new Building("EWI", "0900", "2100", "Faculty of Electrical Engineering, Mathematics and Computer Science", 75);
        assertEquals(building, successBuilding);
    }

    @Test
    void testToString() {
        assertEquals(testString, building.toJson());
    }

    @Test
    void getID() {
        assertEquals(0, building.getID());
    }

    @Test
    void setID() {
        building.setID(1);
        assertEquals(1, building.getID());
    }

    @Test
    void getName() {
        assertEquals("EWI", building.getName());
    }

    @Test
    void setName() {
        building.setName("AE");
        assertEquals("AE", building.getName());
    }

    @Test
    void getOpening() {
        assertEquals("0900", building.getOpeningHour());
    }

    @Test
    void setOpening() {
        building.setOpeningHour("1000");
        assertEquals("1000", building.getOpeningHour());
    }

    @Test
    void getClosing() {
        assertEquals("2100", building.getClosingHour());
    }

    @Test
    void setClosing() {
        building.setClosingHour("2200");
        assertEquals("2200", building.getClosingHour());
    }

    @Test
    void getDescription() {
        assertEquals("Faculty of Electrical Engineering, Mathematics and Computer Science", building.getDescription());
    }

    @Test
    void setDescription() {
        building.setDescription("Faculty of Aerospace Engineering");
        assertEquals("Faculty of Aerospace Engineering", building.getDescription());
    }

    @Test
    void getBikeRental() {
        assertEquals(75, building.getBikeRental());
    }

    @Test
    void setBikeRental() {
        building.setBikeRental(10);
        assertEquals(10, building.getBikeRental());
    }
}
