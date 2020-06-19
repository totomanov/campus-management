package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

public class UserCalendarEntryTest {

    UserCalendarEntry uce = new UserCalendarEntry(100, "user", "2020-10-10", "Nothing happened");
    UserCalendarEntry uce2 = new UserCalendarEntry(200, "user2", "2020-09-09", "Nothing happened here either");

    @Test
    public void constructorTest() {
        assertNotNull(uce);
    }

    @Test
    public void constructorTest2() {
        assertNotNull(uce2);
    }

    @Test
    public void getIdTest() {
        assertEquals(100, uce.getId());
    }

    @Test
    public void getIdTest2() {
        assertEquals(200, uce2.getId());
    }

    @Test
    public void getNetIdTest() {
        assertEquals("user", uce.getNetId());
    }

    @Test
    public void getNetIdTest2() {
        assertEquals("user2", uce2.getNetId());
    }

    @Test
    public void getDateTest() {
        assertEquals("2020-10-10", uce.getDate());
    }

    @Test
    public void getDateTest2() {
        assertEquals("2020-09-09", uce2.getDate());
    }

    @Test
    public void getEventTest() {
        assertEquals("Nothing happened", uce.getEvent());
    }

    @Test
    public void getEventTest2() {
        assertEquals("Nothing happened here either", uce2.getEvent());
    }

    @Test
    public void getMonthTest() throws ParseException {
        assertEquals(10, uce.getMonth());
    }

    @Test
    public void getMonthTest2() throws ParseException {
        assertEquals(9, uce2.getMonth());
    }
}
