package nl.tudelft.oopp.group31.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room_reservation")
public class RoomReservation {

    @GeneratedValue
    @Id
    private int id;

    @Column(name = "netid")
    private String netID;

    @Column(name = "date")
    private String date;

    @Column(name = "roomid")
    private int roomID;

    @Column(name = "startinghour")
    private String startingHour;

    @Column(name = "endinghour")
    private String endingHour;

    /**
     * Constructor for RoomReservation.
     */
    public RoomReservation() {
        this.netID = null;
        this.date = null;
        this.roomID = -1;
        this.startingHour = null;
        this.endingHour = null;
    }

    /**
     * Constructor for RoomReservation with netID, date and roomID as params.
     *
     * @param netID String
     * @param date String
     * @param roomID int
     */
    public RoomReservation(String netID, String date, int roomID) {
        this.netID = netID;
        this.date = date;
        this.roomID = roomID;
        this.startingHour = null;
        this.endingHour = null;
    }

    /**
     * Constructor for RoomReservation with netID, date, roomID, startingHour and endingHour as params.
     *
     * @param netID String
     * @param date String
     * @param roomID int
     * @param startingHour String
     * @param endingHour String
     */
    public RoomReservation(String netID, String date, int roomID, String startingHour, String endingHour) {
        this.netID = netID;
        this.date = date;
        this.roomID = roomID;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
    }

    /**
     * Checks if 2 Objects are the same RoomReservation.
     *
     * @param other Object
     * @return Boolean value based on equality
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof RoomReservation) {
            RoomReservation that = (RoomReservation) other;

            return (this.netID.equals(that.netID)
                    && this.date.equals(that.date)
                    && this.roomID == that.roomID
                    && this.startingHour.equals(that.startingHour)
                    && this.endingHour.equals(that.endingHour));
        }

        return false;
    }

    /**
     * Converts a RoomReservation to a JSON representation of itself.
     *
     * @return a JSON representation of a RoomReservation
     */
    public String toString() {
        return ("{\"id\":\"" + id
                + "\", \"netID\":\"" + netID
                + "\", \"date\":\"" + date
                + "\", \"roomID\":\"" + roomID
                + "\", \"startingHour\":\"" + startingHour
                + "\", \"endingHour\":\"" + endingHour + "\"}");
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

    public int getRoomID() {
        return this.roomID;
    }

    public String getStartingHour() {
        return this.startingHour;
    }

    public String getEndingHour() {
        return this.endingHour;
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

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setStartingHour(String startingHour) {
        this.startingHour = startingHour;
    }

    public void setEndingHour(String endingHour) {
        this.endingHour = endingHour;
    }

}
