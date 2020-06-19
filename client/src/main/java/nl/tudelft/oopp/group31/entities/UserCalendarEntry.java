package nl.tudelft.oopp.group31.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserCalendarEntry {

    private String netID, event, date;
    private int id;
    // private Date date;

    /**
     * Constructor for calendar entry.
     *
     * @param id    of the entry.
     * @param netID of the user.
     * @param date  of where the entry was placed.
     * @param event contents of the entry.
     */
    public UserCalendarEntry(int id, String netID, String date, String event) {
        this.netID = netID;
        this.id = id;
        this.date = date;
        this.event = event;
    }

    public String getNetId() {
        return netID;
    }

    public String getDate() {
        return date;
    }

    public String getEvent() {
        return event;
    }

    /**
     * gets the ID of an entry.
     *
     * @return int id of an entry.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the month of a date.
     *
     * @return month as int.
     * @throws ParseException if the string cannot be parsed.
     */
    public int getMonth() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        Date dateInForm = formatter.parse(this.date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateInForm);
        return cal.get(Calendar.MONTH) + 1;
    }


}
