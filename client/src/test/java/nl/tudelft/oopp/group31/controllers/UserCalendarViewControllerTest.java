package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import nl.tudelft.oopp.group31.entities.CalendarEntry;
import nl.tudelft.oopp.group31.entities.TimeSlot;
import nl.tudelft.oopp.group31.entities.UserCalendarEntry;

import org.junit.jupiter.api.Test;


class UserCalendarViewControllerTest {

    @Test
    void initMonthMapTestJanuary() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertNotNull(hashMap.get("January"));
    }

    @Test
    void initMonthMapTestDecember() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertNotNull(hashMap.get("December"));
    }

    @Test
    void initReserVationHashTest() {
        HashMap<String, String> reservationMap = new HashMap<>();
        ArrayList<TimeSlot> resArray = new ArrayList<>();
        TimeSlot timeSlot = new TimeSlot("test", "2020-20-20", "21", 800, 900);
        resArray.add(timeSlot);
        UserCalendarViewController.initReservationHash(reservationMap, resArray, 1);
        assertEquals("\n You have a reservation at 08:00\n" + " until 09:00 at 21", reservationMap.get("2020-20-20"));
    }

    @Test
    void initCalendarEntryMapTest() throws ParseException {
        ArrayList<UserCalendarEntry> userCalendarArray = new ArrayList<>();
        UserCalendarEntry entry = new UserCalendarEntry(10, "test", "2020-20-20", "event");
        userCalendarArray.add(entry);
        HashMap<String, String> calendarHash = new HashMap<>();
        UserCalendarViewController.initCalendarEntryMap(userCalendarArray, calendarHash);
        assertEquals("event", calendarHash.get("2020-20-20"));
    }

    @Test
    public void janTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(1, UserCalendarViewController.getMonth("January", hashMap));
    }

    @Test
    public void febTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(2, UserCalendarViewController.getMonth("February", hashMap));
    }

    @Test
    public void marchTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(3, UserCalendarViewController.getMonth("March", hashMap));
    }

    @Test
    public void aprilTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(4, UserCalendarViewController.getMonth("April", hashMap));
    }

    @Test
    public void mayTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(5, UserCalendarViewController.getMonth("May", hashMap));
    }

    @Test
    public void juneTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(6, UserCalendarViewController.getMonth("June", hashMap));
    }

    @Test
    public void julyTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(7, UserCalendarViewController.getMonth("July", hashMap));
    }

    @Test
    public void augustTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(8, UserCalendarViewController.getMonth("August", hashMap));
    }

    @Test
    public void septemberTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(9, UserCalendarViewController.getMonth("September", hashMap));
    }

    @Test
    public void octoberTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(10, UserCalendarViewController.getMonth("October", hashMap));
    }

    @Test
    public void novemberTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(11, UserCalendarViewController.getMonth("November", hashMap));
    }

    @Test
    public void decemberTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        assertEquals(12, UserCalendarViewController.getMonth("December", hashMap));
    }

    @Test
    public void calculateMonthTest() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        int[] monthYear = UserCalendarViewController.calculateMonth(2020, "January", hashMap);
        assertEquals(1, monthYear[0]);
    }

    @Test
    public void calculateMonthTest2() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        int[] monthYear = UserCalendarViewController.calculateMonth(2020, "January", hashMap);
        assertEquals(2020, monthYear[1]);
    }

    @Test
    public void calculateMonthTest3() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        int[] monthYear = UserCalendarViewController.calculateMonth(1010, "June", hashMap);
        assertEquals(6, monthYear[0]);
    }

    @Test
    public void calculateMonthTest4() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        UserCalendarViewController.initMonthMap(hashMap);
        int[] monthYear = UserCalendarViewController.calculateMonth(1010, "June", hashMap);
        assertEquals(1010, monthYear[1]);
    }

    @Test
    public void setDatesTest() {
        assertEquals(31, UserCalendarViewController.setDates(1, 2020));
    }

    @Test
    public void setDatesTest2() {
        assertEquals(29, UserCalendarViewController.setDates(2, 2020));
    }

    @Test
    public void setDatesTest3() {
        assertEquals(28, UserCalendarViewController.setDates(2, 2019));
    }

    @Test
    public void setDatesTest4() {
        assertEquals(31, UserCalendarViewController.setDates(3, 2020));
    }

    @Test
    public void setDatesTest5() {
        assertEquals(30, UserCalendarViewController.setDates(4, 2020));
    }

    @Test
    public void setDatesTest6() {
        assertEquals(31, UserCalendarViewController.setDates(5, 2020));
    }

    @Test
    public void setDatesTest7() {
        assertEquals(30, UserCalendarViewController.setDates(6, 2020));
    }

    @Test
    public void setDatesTest8() {
        assertEquals(31, UserCalendarViewController.setDates(7, 2020));
    }

    @Test
    public void setDatesTest9() {
        assertEquals(31, UserCalendarViewController.setDates(8, 2020));
    }

    @Test
    public void setDatesTest10() {
        assertEquals(30, UserCalendarViewController.setDates(9, 2020));
    }

    @Test
    public void setDatesTest11() {
        assertEquals(31, UserCalendarViewController.setDates(10, 2020));
    }

    @Test
    public void setDatesTest12() {
        assertEquals(30, UserCalendarViewController.setDates(11, 2020));
    }

    @Test
    public void setDatesTest13() {
        assertEquals(31, UserCalendarViewController.setDates(12, 2020));
    }

    @Test
    public void addReservationTest() {
        HashMap<String, String> reservationMap = new HashMap<String, String>();
        reservationMap.put("date", "event");
        CalendarEntry calendarEntry = new CalendarEntry(null, null);
        String text = UserCalendarViewController.addReservationTestMethod(reservationMap, "date", calendarEntry);
        assertEquals("event", text);
    }

    @Test
    public void addReservationTestSetUsedTest() {
        HashMap<String, String> reservationMap = new HashMap<String, String>();
        reservationMap.put("date", "event");
        CalendarEntry calendarEntry = new CalendarEntry(null, null);
        UserCalendarViewController.addReservationTestMethod(reservationMap, "date", calendarEntry);
        assertTrue(calendarEntry.isUsed());
    }

    @Test
    public void addCalendarEntryTest() {
        ArrayList<CalendarEntry> calendarEntries = new ArrayList<CalendarEntry>();
        UserCalendarViewController.addCalendarEntryTestMethod(calendarEntries, 0);
        assertEquals(1, calendarEntries.size());

    }

    @Test
    public void setLabelsAndReturnDateTest() {
        assertEquals("01", UserCalendarViewController.setLabelAndReturnDateTestMethod(1));
    }

    @Test
    public void setLabelsAndReturnDateTest2() {
        assertEquals("11", UserCalendarViewController.setLabelAndReturnDateTestMethod(11));
    }

}
