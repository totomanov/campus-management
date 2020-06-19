package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarEntryTest {

    private CalendarEntry calendarEntry;
    private final String testString = "{\"id\":\"" + 0
                                    + "\", \"netID\":\"" + "maikdevries"
                                    + "\", \"date\":\"" + "2020-04-01"
                                    + "\", \"event\":\"" + "Eating out with friends." + "\"}";

    @BeforeEach
    void setup() {
        calendarEntry = new CalendarEntry("maikdevries", "2020-04-01", "Eating out with friends.");
    }

    @Test
    void testFullConstructorNotNull() {
        assertNotNull(calendarEntry.getNetID());
        assertNotNull(calendarEntry.getDate());
        assertNotNull(calendarEntry.getEvent());
    }

    @Test
    void testFullConstructor() {
        assertEquals("maikdevries", calendarEntry.getNetID());
        assertEquals("2020-04-01", calendarEntry.getDate());
        assertEquals("Eating out with friends.", calendarEntry.getEvent());
    }

    @Test
    void testEmptyConstructor() {
        CalendarEntry emptyCalendarEntry = new CalendarEntry();
        assertNull(emptyCalendarEntry.getNetID());
        assertNull(emptyCalendarEntry.getDate());
        assertNull(emptyCalendarEntry.getEvent());
    }

    @Test
    void testEqualsSame() {
        assertEquals(calendarEntry, calendarEntry);
    }

    @Test
    void testEqualsFailEntity() {
        assertNotEquals(calendarEntry, new User());
    }

    @Test
    void testEqualsFailNetID() {
        CalendarEntry failCalendarEntry = new CalendarEntry("peterpan", "2020-04-01", "Eating out with friends.");
        assertNotEquals(calendarEntry, failCalendarEntry);
    }

    @Test
    void testEqualsFailDate() {
        CalendarEntry failCalendarEntry = new CalendarEntry("maikdevries", "2021-05-07", "Eating out with friends.");
        assertNotEquals(calendarEntry, failCalendarEntry);
    }

    @Test
    void testEqualsFailEvent() {
        CalendarEntry failCalendarEntry = new CalendarEntry("maikdevries", "2020-04-01", "Being home alone.");
        assertNotEquals(calendarEntry, failCalendarEntry);
    }

    @Test
    void testEqualsSuccess() {
        CalendarEntry successCalendarEntry = new CalendarEntry("maikdevries", "2020-04-01", "Eating out with friends.");
        assertEquals(calendarEntry, successCalendarEntry);
    }

    @Test
    void testToString() {
        assertEquals(testString, calendarEntry.toString());
    }

    @Test
    void getID() {
        assertEquals(0, calendarEntry.getID());
    }

    @Test
    void setID() {
        calendarEntry.setID(10);
        assertEquals(10, calendarEntry.getID());
    }

    @Test
    void getNetID() {
        assertEquals("maikdevries", calendarEntry.getNetID());
    }

    @Test
    void setNetID() {
        calendarEntry.setNetID("peterpan");
        assertEquals("peterpan", calendarEntry.getNetID());
    }

    @Test
    void getDate() {
        assertEquals("2020-04-01", calendarEntry.getDate());
    }

    @Test
    void setDate() {
        calendarEntry.setDate("2021-05-07");
        assertEquals("2021-05-07", calendarEntry.getDate());
    }

    @Test
    void getEvent() {
        assertEquals("Eating out with friends.", calendarEntry.getEvent());
    }

    @Test
    void setEvent() {
        calendarEntry.setEvent("Being home alone.");
        assertEquals("Being home alone.", calendarEntry.getEvent());
    }
}
