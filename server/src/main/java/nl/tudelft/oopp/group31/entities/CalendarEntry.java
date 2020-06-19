package nl.tudelft.oopp.group31.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "calendar")
public class CalendarEntry {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "netid")
    private String netID;

    @Column(name = "date")
    private String date;

    @Column(name = "event")
    private String event;

    /**
     * Constructor for CalendarEntry.
     */
    public CalendarEntry() {
        this.netID = null;
        this.date = null;
        this.event = null;
    }

    /**
     * Constructor for CalendarEntry with netID, date and event as param.
     *
     * @param netID String
     * @param date String
     * @param event String
     */
    public CalendarEntry(String netID, String date, String event) {
        this.netID = netID;
        this.date = date;
        this.event = event;
    }

    /**
     * Checks if 2 Objects are the same CalendarEntry.
     *
     * @param other Object
     * @return Boolean value based on equality
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof CalendarEntry) {
            CalendarEntry that = (CalendarEntry) other;

            return (this.netID.equals(that.netID)
                    && this.date.equals(that.date)
                    && this.event.equals(that.event));
        }

        return false;
    }

    /**
     * Converts a CalendarEntry to a JSON representation of itself.
     *
     * @return a JSON representation of a CalendarEntry
     */
    public String toString() {
        return "{\"id\":\"" + id
                + "\", \"netID\":\"" + netID
                + "\", \"date\":\"" + date
                + "\", \"event\":\"" + event + "\"}";
    }

    // Getters & Setters
    public int getID() {
        return this.id;
    }

    public String getNetID() {
        return this.netID;
    }

    public String getDate() {
        return this.date;
    }

    public String getEvent() {
        return this.event;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
