package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class UserReservationFilterControllerTest {

    @Test
    void testValidateFiltersNullDate() {
        UserReservationFilterController cc = new UserReservationFilterController();
        String warning = cc.validateFilter(null, 2, true);
        assertEquals("You must choose a Date !", warning);
    }

    @Test
    void testValidateFiltersPastDate() {
        UserReservationFilterController cc = new UserReservationFilterController();
        LocalDate test = LocalDate.now();
        test = test.minusYears(12);
        String warning = cc.validateFilter(test, 2, true);
        assertEquals("Date chosen is in the past", warning);
    }

    @Test
    void testValidateFiltersHasReservation() {
        UserReservationFilterController cc = new UserReservationFilterController();
        LocalDate test = LocalDate.now();
        test = test.plusYears(12);
        String warning = cc.validateFilter(test, 3, true);
        assertEquals("You have a reservation booked already!", warning);
    }

    @Test
    void testValidateFilters() {
        UserReservationFilterController cc = new UserReservationFilterController();
        LocalDate test = LocalDate.now();
        test = test.plusYears(12);
        String warning = cc.validateFilter(test, 2, true);
        assertTrue(warning.isEmpty());
    }


}
