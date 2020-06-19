package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;
    private final String testString = "netID: " + "maikdevries"
                                + " Pass: " + "passwordForTestingPurposes"
                                + " type: " + 1
                                + " rentedBike: " + true;

    @BeforeEach
    void setup() {
        user = new User("maikdevries", "passwordForTestingPurposes", 1, true);
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(user.getNetID());
        assertNotNull(user.getPass());
        assertNotNull(user.getType());
        assertNotNull(user.getRentedBike());
    }

    @Test
    void testFullConstructor() {
        assertEquals("maikdevries", user.getNetID());
        assertEquals("passwordForTestingPurposes", user.getPass());
        assertEquals(1, user.getType());
        assertTrue(user.getRentedBike());
    }

    @Test
    void testEmptyConstructor() {
        User emptyUser = new User();
        assertNull(emptyUser.getNetID());
        assertNull(emptyUser.getPass());
        assertEquals(-1, emptyUser.getType());
        assertFalse(emptyUser.getRentedBike());
    }

    @Test
    void testToString() {
        assertEquals(testString, user.toString());
    }

    @Test
    void getNetID() {
        assertEquals("maikdevries", user.getNetID());
    }

    @Test
    void setNetID() {
        user.setNetID("peterpan");
        assertEquals("peterpan", user.getNetID());
    }

    @Test
    void getPass() {
        assertEquals("passwordForTestingPurposes", user.getPass());
    }

    @Test
    void setPassword() {
        user.setPassword("password123");
        assertEquals("password123", user.getPass());
    }

    @Test
    void getType() {
        assertEquals(1, user.getType());
    }

    @Test
    void setType() {
        user.setType(2);
        assertEquals(2, user.getType());
    }

    @Test
    void getRentedBike() {
        assertTrue(user.getRentedBike());
    }

    @Test
    void setRentedBike() {
        user.setRentedBike(false);
        assertFalse(user.getRentedBike());
    }
}
