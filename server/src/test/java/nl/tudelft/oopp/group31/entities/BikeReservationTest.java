package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BikeReservationTest {

    private BikeReservation bikeReservation;
    private final String testString = "{\"id\":\"" + 0
                                    + "\", \"netID\":\"" + "maikdevries"
                                    + "\", \"buildingID\":\"" + 1 + "\"}";

    @BeforeEach
    void setup() {
        bikeReservation = new BikeReservation("maikdevries", 1);
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(bikeReservation.getNetID());
        assertNotNull(bikeReservation.getBuildingID());
    }

    @Test
    void testFullConstructor() {
        assertEquals("maikdevries", bikeReservation.getNetID());
        assertEquals(1, bikeReservation.getBuildingID());
    }

    @Test
    void testEmptyContructor() {
        BikeReservation emptyBikeReservation = new BikeReservation();
        assertNull(emptyBikeReservation.getNetID());
        assertEquals(-1, emptyBikeReservation.getBuildingID());
    }

    @Test
    void testEqualsSame() {
        assertEquals(bikeReservation, bikeReservation);
    }

    @Test
    void testEqualsFailEntity() {
        assertNotEquals(bikeReservation, new User());
    }

    @Test
    void testEqualsFailNetID() {
        BikeReservation failBikeReservation = new BikeReservation("peterpan", 1);
        assertNotEquals(bikeReservation, failBikeReservation);
    }

    @Test
    void testEqualsFailBuildingID() {
        BikeReservation failBikeReservation = new BikeReservation("maikdevries", 7);
        assertNotEquals(bikeReservation, failBikeReservation);
    }

    @Test
    void testEqualsSuccess() {
        BikeReservation successBikeReservation = new BikeReservation("maikdevries", 1);
        assertEquals(bikeReservation, successBikeReservation);
    }

    @Test
    void testToString() {
        assertEquals(testString, bikeReservation.toString());
    }

    @Test
    void getID() {
        assertEquals(0, bikeReservation.getID());
    }

    @Test
    void setID() {
        bikeReservation.setID(1);
        assertEquals(1, bikeReservation.getID());
    }

    @Test
    void getNetID() {
        assertEquals("maikdevries", bikeReservation.getNetID());
    }

    @Test
    void setNetID() {
        bikeReservation.setNetID("peterpan");
        assertEquals("peterpan", bikeReservation.getNetID());
    }

    @Test
    void getBuildingID() {
        assertEquals(1, bikeReservation.getBuildingID());
    }

    @Test
    void setBuildingID() {
        bikeReservation.setBuildingID(7);
        assertEquals(7, bikeReservation.getBuildingID());
    }
}
