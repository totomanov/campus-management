package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalendarEntryTest {

    private CalendarEntry calendarEntry;

    @BeforeEach
    void setup() {
        calendarEntry = new CalendarEntry(null, null);
    }

    @Test
    void testFullConstructor() {
        assertNull(calendarEntry.getTextArea());
        assertNull(calendarEntry.getLabel());
    }

    @Test
    void getTextArea() {
        assertNull(calendarEntry.getTextArea());
    }

    @Test
    void getLabel() {
        assertNull(calendarEntry.getLabel());
    }

    @Test
    void isUsed() {
        assertFalse(calendarEntry.isUsed());
    }

    @Test
    void setUsed() {
        calendarEntry.setUsed(true);
        assertTrue(calendarEntry.isUsed());
    }
}
