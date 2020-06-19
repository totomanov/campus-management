package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;
    private final String testString = "{\"netID\":\"" + "maikdevries"
                                + "\", \"password\":\"" + "passwordForTestingPurposes"
                                + "\", \"type\":\"" + 1
                                + "\", \"rentedBike\":\"" + true + "\"}";

    @BeforeEach
    void setup() {
        user = new User("maikdevries", "passwordForTestingPurposes", 1, true);
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(user.getNetID());
        assertNotNull(user.getPassword());
        assertNotNull(user.getType());
        assertNotNull(user.getRentedBike());
    }

    @Test
    void testFullConstructor() {
        assertEquals("maikdevries", user.getNetID());
        assertEquals("passwordForTestingPurposes", user.getPassword());
        assertEquals(1, user.getType());
        assertTrue(user.getRentedBike());
    }

    @Test
    void testNetPasswordConstructorNotNull() {
        User netPasswordUser = new User("maikdevries", "passwordForTestingPurposes");
        assertNotNull(netPasswordUser.getNetID());
        assertNotNull(netPasswordUser.getPassword());
    }

    @Test
    void testNetPasswordConstructor() {
        User netPasswordUser = new User("maikdevries", "passwordForTestingPurposes");
        assertEquals("maikdevries", netPasswordUser.getNetID());
        assertEquals("passwordForTestingPurposes", netPasswordUser.getPassword());
        assertEquals(-1, netPasswordUser.getType());
        assertFalse(netPasswordUser.getRentedBike());
    }

    @Test
    void testNetPasswordTypeConstructorNotNull() {
        User netPasswordTypeUser = new User("maikdevries", "passwordForTestingPurposes", 1);
        assertNotNull(netPasswordTypeUser.getNetID());
        assertNotNull(netPasswordTypeUser.getPassword());
        assertNotNull(netPasswordTypeUser.getType());
    }

    @Test
    void testNetPasswordTypeConstructor() {
        User netPasswordTypeUser = new User("maikdevries", "passwordForTestingPurposes", 1);
        assertEquals("maikdevries", netPasswordTypeUser.getNetID());
        assertEquals("passwordForTestingPurposes", netPasswordTypeUser.getPassword());
        assertEquals(1, netPasswordTypeUser.getType());
        assertFalse(netPasswordTypeUser.getRentedBike());
    }

    @Test
    void testEmptyConstructor() {
        User emptyUser = new User();
        assertNull(emptyUser.getNetID());
        assertNull(emptyUser.getPassword());
        assertEquals(-1, emptyUser.getType());
        assertFalse(emptyUser.getRentedBike());
    }

    @Test
    void testEqualsSame() {
        assertEquals(user, user);
    }

    @Test
    void testEqualsFailEntity() {
        assertNotEquals(user, new Room());
    }

    @Test
    void testEqualsFailNetID() {
        User failUser = new User("peterpan", "passwordForTestingPurposes", 1);
        assertNotEquals(user, failUser);
    }

    @Test
    void testEqualsFailPassword() {
        User failUser = new User("maikdevries", "password123", 1);
        assertNotEquals(user, failUser);
    }

    @Test
    void testEqualsFailType() {
        User failUser = new User("maikdevries", "passwordForTestingPurposes", 2);
        assertNotEquals(user, failUser);
    }

    @Test
    void testEqualsFailRentedBike() {
        User failUser = new User("maikdevries", "passwordForTestingPurposes", 1, false);
        assertNotEquals(user, failUser);
    }

    @Test
    void testEqualsSuccess() {
        User successUser = new User("maikdevries", "passwordForTestingPurposes", 1, true);
        assertEquals(user, successUser);
    }

    @Test
    void testToString() {
        assertEquals(testString, user.toString());
    }

    @Test
    void testRentBike() {
        User rentBikeUser = new User("maikdevries", "passwordForTestingPurposes", 1, false);
        rentBikeUser.rentBike(true);
        assertTrue(rentBikeUser.getRentedBike());
    }

    @Test
    void testReturnBike() {
        user.rentBike(false);
        assertFalse(user.getRentedBike());
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
    void getPassword() {
        assertEquals("passwordForTestingPurposes", user.getPassword());
    }

    @Test
    void setPassword() {
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
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
