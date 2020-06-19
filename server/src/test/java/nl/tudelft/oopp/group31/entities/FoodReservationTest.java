package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FoodReservationTest {

    private FoodReservation foodReservation;
    private final String testString = "{\"id\":\"" + 0
                                    + "\", \"netID\":\"" + "maikdevries"
                                    + "\", \"date\":\"" + "2020-03-01"
                                    + "\", \"buildingID\":\"" + 21
                                    + "\", \"hour\":\"" + "1300"
                                    + "\", \"foodSelection\":\"" + 7 + "\"}";

    @BeforeEach
    void setup() {
        foodReservation = new FoodReservation("maikdevries", "2020-03-01", 21, "1300", 7);
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(foodReservation.getNetID());
        assertNotNull(foodReservation.getDate());
        assertNotNull(foodReservation.getBuildingID());
        assertNotNull(foodReservation.getHour());
        assertNotNull(foodReservation.getFoodSelection());
    }

    @Test
    void testFullConstructor() {
        assertEquals("maikdevries", foodReservation.getNetID());
        assertEquals("2020-03-01", foodReservation.getDate());
        assertEquals(21, foodReservation.getBuildingID());
        assertEquals("1300", foodReservation.getHour());
        assertEquals(7, foodReservation.getFoodSelection());
    }

    @Test
    void testEmptyConstructor() {
        FoodReservation emptyFoodReservation = new FoodReservation();
        assertNull(emptyFoodReservation.getNetID());
        assertNull(emptyFoodReservation.getDate());
        assertEquals(-1, emptyFoodReservation.getBuildingID());
        assertNull(emptyFoodReservation.getHour());
        assertEquals(-1, emptyFoodReservation.getFoodSelection());
    }

    @Test
    void testEqualsSame() {
        assertEquals(foodReservation, foodReservation);
    }

    @Test
    void testEqualsFailEntity() {
        assertNotEquals(foodReservation, new User());
    }

    @Test
    void testEqualsFailNetID() {
        FoodReservation failFoodReservation = new FoodReservation("peterpan", "2020-03-01", 21, "1300", 7);
        assertNotEquals(foodReservation, failFoodReservation);
    }

    @Test
    void testEqualsFailDate() {
        FoodReservation failFoodReservation = new FoodReservation("maikdevries", "2019-03-01", 21, "1300", 7);
        assertNotEquals(foodReservation, failFoodReservation);
    }

    @Test
    void testEqualsFailBuildingID() {
        FoodReservation failFoodReservation = new FoodReservation("maikdevries", "2020-03-01", 55, "1300", 7);
        assertNotEquals(foodReservation, failFoodReservation);
    }

    @Test
    void testEqualsFailHour() {
        FoodReservation failFoodReservation = new FoodReservation("maikdevries", "2020-03-01", 21, "1700", 7);
        assertNotEquals(foodReservation, failFoodReservation);
    }

    @Test
    void testEqualsFailFoodSelection() {
        FoodReservation failFoodReservation = new FoodReservation("maikdevries", "2020-03-01", 21, "1300", 13);
        assertNotEquals(foodReservation, failFoodReservation);
    }

    @Test
    void testEqualsSuccess() {
        FoodReservation successFoodReservation = new FoodReservation("maikdevries", "2020-03-01", 21, "1300", 7);
        assertEquals(foodReservation, successFoodReservation);
    }

    @Test
    void testToString() {
        assertEquals(testString, foodReservation.toString());
    }

    @Test
    void getID() {
        assertEquals(0, foodReservation.getID());
    }

    @Test
    void setID() {
        foodReservation.setID(1);
        assertEquals(1, foodReservation.getID());
    }

    @Test
    void getNetID() {
        assertEquals("maikdevries", foodReservation.getNetID());
    }

    @Test
    void setNetID() {
        foodReservation.setNetID("peterpan");
        assertEquals("peterpan", foodReservation.getNetID());
    }

    @Test
    void getDate() {
        assertEquals("2020-03-01", foodReservation.getDate());
    }

    @Test
    void setDate() {
        foodReservation.setDate("2019-03-01");
        assertEquals("2019-03-01", foodReservation.getDate());
    }

    @Test
    void getBuildingID() {
        assertEquals(21, foodReservation.getBuildingID());
    }

    @Test
    void setBuildingID() {
        foodReservation.setBuildingID(55);
        assertEquals(55, foodReservation.getBuildingID());
    }

    @Test
    void getHour() {
        assertEquals("1300", foodReservation.getHour());
    }

    @Test
    void setHour() {
        foodReservation.setHour("1700");
        assertEquals("1700", foodReservation.getHour());
    }

    @Test
    void getFoodSelection() {
        assertEquals(7, foodReservation.getFoodSelection());
    }

    @Test
    void setFoodSelection() {
        foodReservation.setFoodSelection(13);
        assertEquals(13, foodReservation.getFoodSelection());
    }
}
