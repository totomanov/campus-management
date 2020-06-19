package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoodTest {

    private Food food;
    private final String testString = "{\"id\":\"" + 0
                                    + "\", \"name\":\"" + "Kebab"
                                    + "\", \"buildingID\":\"" + 15
                                    + "\", \"price\":\"" + 2.99
                                    + "\", \"description\":\"" + "Kebabs are various cooked meat dishes." + "\"}";

    @BeforeEach
    void setup() {
        food = new Food("Kebab", 15, 2.99, "Kebabs are various cooked meat dishes.");
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(food.getName());
        assertNotNull(food.getBuildingID());
        assertNotNull(food.getPrice());
        assertNotNull(food.getDescription());
    }

    @Test
    void testFullConstructor() {
        assertEquals("Kebab", food.getName());
        assertEquals(15, food.getBuildingID());
        assertTrue(Math.abs(food.getPrice() - 2.99) <= 0.001);
        assertEquals("Kebabs are various cooked meat dishes.", food.getDescription());
    }

    @Test
    void testNamePriceConstructorNotNull() {
        Food namePriceFood = new Food("Kebab", 15, 2.99);
        assertNotNull(namePriceFood.getName());
        assertNotNull(namePriceFood.getBuildingID());
        assertNotNull(namePriceFood.getPrice());
    }

    @Test
    void testNamePriceConstructor() {
        Food namePriceFood = new Food("Kebab", 15, 2.99);
        assertEquals("Kebab", namePriceFood.getName());
        assertEquals(15, namePriceFood.getBuildingID());
        assertTrue(Math.abs(namePriceFood.getPrice() - 2.99) <= 0.001);
        assertNull(namePriceFood.getDescription());
    }

    @Test
    void testEmptyConstructor() {
        Food emptyFood = new Food();
        assertNull(emptyFood.getName());
        assertEquals(-1, emptyFood.getBuildingID());
        assertTrue(Math.abs(emptyFood.getPrice() + 1) <= 0.001);
        assertNull(emptyFood.getDescription());
    }

    @Test
    void testEqualsSame() {
        assertEquals(food, food);
    }

    @Test
    void testEqualsFailEntity() {
        assertNotEquals(food, new User());
    }

    @Test
    void testEqualsFailName() {
        Food failFood = new Food("Doner", 15, 2.99, "Kebabs are various cooked meat dishes.");
        assertNotEquals(food, failFood);
    }

    @Test
    void testEqualsFailBuildingID() {
        Food failFood = new Food("Kebab", 67, 2.99, "Kebabs are various cooked meat dishes.");
        assertNotEquals(food, failFood);
    }

    @Test
    void testEqualsFailPrice() {
        Food failFood = new Food("Kebab", 15, 1, "Kebabs are various cooked meat dishes.");
        assertFalse(Math.abs(food.getPrice() - failFood.getPrice()) <= 0.001);
    }

    @Test
    void testEqualsSmallPriceDifference() {
        Food sameFood = new Food("Kebab", 15, 2.990001, "Kebabs are various cooked meat dishes.");
        assertEquals(food, sameFood);
    }

    @Test
    void testEqualsFailDescription() {
        Food failFood = new Food("Kebab", 15, 2.99, "Kebabs are various cooked plant dishes.");
        assertNotEquals(food, failFood);
    }

    @Test
    void testEqualsSuccess() {
        Food successFood = new Food("Kebab", 15, 2.99, "Kebabs are various cooked meat dishes.");
        assertEquals(food, successFood);
    }

    @Test
    void testToString() {
        assertEquals(testString, food.toString());
    }

    @Test
    void getID() {
        assertEquals(0, food.getID());
    }

    @Test
    void setID() {
        food.setID(1);
        assertEquals(1, food.getID());
    }

    @Test
    void getName() {
        assertEquals("Kebab", food.getName());
    }

    @Test
    void setName() {
        food.setName("Doner");
        assertEquals("Doner", food.getName());
    }

    @Test
    void getBuildingID() {
        assertEquals(15, food.getBuildingID());
    }

    @Test
    void setBuildingID() {
        food.setBuildingID(67);
        assertEquals(67, food.getBuildingID());
    }

    @Test
    void getPrice() {
        assertTrue(Math.abs(food.getPrice() - 2.99) <= 0.001);
    }

    @Test
    void setPrice() {
        food.setPrice(1);
        assertTrue(Math.abs(food.getPrice() - 1) <= 0.001);
    }

    @Test
    void getDescription() {
        assertEquals("Kebabs are various cooked meat dishes.", food.getDescription());
    }

    @Test
    void setDescription() {
        food.setDescription("Kebabs are various cooked plant dishes.");
        assertEquals("Kebabs are various cooked plant dishes.", food.getDescription());
    }
}
