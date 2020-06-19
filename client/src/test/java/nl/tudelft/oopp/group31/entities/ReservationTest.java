package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservationTest {

    private Reservation reservation;

    @BeforeEach
    void setup() {
        reservation = new Reservation(null, 10, 11);
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(reservation.getStartTime());
        assertNotNull(reservation.getEndTime());
    }

    @Test
    void testFullConstructor() {
        assertNull(reservation.getButton());
        assertEquals(10, reservation.getStartTime());
        assertEquals(11, reservation.getEndTime());
        assertFalse(reservation.isReserved());
    }

    @Test
    void getButton() {
        assertNull(reservation.getButton());
    }

    @Test
    void getStartTime() {
        assertEquals(10, reservation.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals(11, reservation.getEndTime());
    }

    @Test
    void isReserved() {
        assertFalse(reservation.isReserved());
    }

    @Test
    void setReserved() {
        reservation.setReserved(true);
        assertTrue(reservation.isReserved());
    }
}
