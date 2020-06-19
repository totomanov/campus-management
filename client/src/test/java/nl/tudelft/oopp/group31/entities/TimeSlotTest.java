package nl.tudelft.oopp.group31.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

class TimeSlotTest {

    @Test
    public void constructorTest() {
        TimeSlot test = new TimeSlot("netID", "date", "roomID", 0, 1);
        assertNotNull(test);
        assertEquals("netID", test.getID());
        assertEquals("date", test.getDate());
        assertEquals("roomID", test.getRoomID());
        assertEquals(0, test.getStartTime());
        assertEquals(1, test.getEndTime());
    }

    @Test
    public void getStartTimeTest() {
        TimeSlot test = new TimeSlot("netID", "date", "roomID", 0, 1);
        assertEquals(0, test.getStartTime());
    }

    @Test
    public void getEndTimeTest() {
        TimeSlot test = new TimeSlot("netID", "date", "roomID", 0, 1);
        assertEquals(1, test.getEndTime());
    }

    @Test
    public void getIdTest() {
        TimeSlot test = new TimeSlot("netID", "date", "roomID", 0, 1);
        assertEquals("netID", test.getID());
    }

    @Test
    public void getDateTest() {
        TimeSlot test = new TimeSlot("netID", "date", "roomID", 0, 1);
        assertEquals("date", test.getDate());
    }

    @Test
    public void getRoomIdTest() {
        TimeSlot test = new TimeSlot("netID", "date", "roomID", 0, 1);
        assertEquals("roomID", test.getRoomID());
    }

    @Test
    public void getMonthTest() throws ParseException {
        TimeSlot test = new TimeSlot("netID", "2020-10-10", "roomID", 0, 1);
        assertEquals(10, test.getMonth());
    }

    @Test
    public void getMonthTest2() throws ParseException {
        TimeSlot test = new TimeSlot("netID", "2020-09-10", "roomID", 0, 1);
        assertEquals(9, test.getMonth());
    }
}
