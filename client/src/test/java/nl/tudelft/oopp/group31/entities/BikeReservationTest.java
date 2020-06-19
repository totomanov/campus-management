package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BikeReservationTest {

    private BikeReservation bikeReservation;
    private final String testString = "{\"id\":\"" + 0
                                    + "\", \"netID\":\"" + "maikdevries"
                                    + "\", \"buildingID\":\"" + 1
                                    + "\", \"date\":\"" + "2020-03-28" + "\"}";

    @BeforeEach
    void setup() {
        bikeReservation = new BikeReservation("maikdevries", 1, "2020-03-28");
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(bikeReservation.getNetID());
        assertNotNull(bikeReservation.getBuildingID());
        assertNotNull(bikeReservation.getDate());
    }

    @Test
    void testFullConstructor() {
        assertEquals("maikdevries", bikeReservation.getNetID());
        assertEquals(1, bikeReservation.getBuildingID());
        assertEquals("2020-03-28", bikeReservation.getDate());
    }

    @Test
    void testEmptyContructor() {
        BikeReservation emptyBikeReservation = new BikeReservation();
        assertNull(emptyBikeReservation.getNetID());
        assertEquals(-1, emptyBikeReservation.getBuildingID());
        assertNull(emptyBikeReservation.getDate());
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
        BikeReservation failBikeReservation = new BikeReservation("peterpan", 1, "2020-03-28");
        assertNotEquals(bikeReservation, failBikeReservation);
    }

    @Test
    void testEqualsFailBuildingID() {
        BikeReservation failBikeReservation = new BikeReservation("maikdevries", 7, "2020-03-28");
        assertNotEquals(bikeReservation, failBikeReservation);
    }

    @Test
    void testEqualsFailDate() {
        BikeReservation failBikeReservation = new BikeReservation("maikdevries", 1, "2021-03-28");
        assertNotEquals(bikeReservation, failBikeReservation);
    }

    @Test
    void testEqualsSuccess() {
        BikeReservation successBikeReservation = new BikeReservation("maikdevries", 1, "2020-03-28");
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
    void getNetID() {
        assertEquals("maikdevries", bikeReservation.getNetID());
    }

    @Test
    void getBuildingID() {
        assertEquals(1, bikeReservation.getBuildingID());
    }

    @Test
    void getDate() {
        assertEquals("2020-03-28", bikeReservation.getDate());
    }

    @Test
    void getBuildingName() {
        bikeReservation.setBuildingName("EWI");
        assertEquals("EWI", bikeReservation.getBuildingName());
    }

    @Test
    void setBuildingName() {
        bikeReservation.setBuildingName("AE");
        assertEquals("AE", bikeReservation.getBuildingName());
    }
}
