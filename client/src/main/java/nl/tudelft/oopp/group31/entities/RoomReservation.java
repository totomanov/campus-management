package nl.tudelft.oopp.group31.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomReservation {

    private int id;

    private String netID;
    private String date;
    private int roomID;
    private String buildingName, roomName;

    private String startingHour;

    private String endingHour;


    /**
     * Constructor for RoomReservation with netID, date, roomID, startingHour and endingHour as params.
     *
     * @param netID        String
     * @param date         String
     * @param roomID       int
     * @param startingHour String
     * @param endingHour   String
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


    // Getters & Setters

    public int getId() {
        return id;
    }

    public String getNetID() {
        return netID;
    }

    public String getDate() {
        return date;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getStartingHour() {
        return startingHour;
    }

    public String getEndingHour() {
        return endingHour;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * This method is used by the controller for reservation manager.
     * @return user-friendly String representing opening hours
     */
    public String getTime() {

        StringBuilder sh = new StringBuilder(startingHour);
        if (sh.length() == 3) {
            sh.insert(1, ":");
        } else {
            sh.insert(2, ":");
        }
        StringBuilder eh = new StringBuilder(endingHour);
        if (eh.length() == 3) {
            eh.insert(1, ":");
        } else {
            eh.insert(2, ":");
        }
        return sh + "-" + eh;
    }

    //only used for testing
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the room.
     *
     * @return The name of the room.
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * Gets the name of the Building associated to the reservation.
     *
     * @return The name of the building
     */
    public String getBuildingName() {
        return this.buildingName;
    }

    /**
     * Determines whether the reservation is in the past, future or present.
     * @return A String, representing the status
     */
    public String getStatus() {
        String status = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm");
        if (this.getDate() != null) {
            Date start;
            Date end;
            Date now = new java.util.Date();
            try {
                String startTime = this.getDate() + this.getTime().substring(0, 5);
                String endTime = this.getDate() + this.getTime().substring(6);
                start = formatter.parse(startTime);
                end = formatter.parse(endTime);
                if (now.compareTo(start) > 0 && now.compareTo(end) < 0) {
                    status = "In progress";
                } else if (now.compareTo(start) > 0) {
                    status = "Past";
                } else if (now.compareTo(end) < 0) {
                    status = "Upcoming";
                } else {
                    status = "Unknown";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            status = "Upcoming";
        }
        return status;
    }

    @Override
    public String toString() {
        return "RoomReservation{"
                + "id=" + id
                + ", netID='" + netID + '\''
                + ", date='" + date + '\''
                + ", roomID=" + roomID
                + ", buildingName='" + buildingName + '\''
                + ", roomName='" + roomName + '\''
                + ", startingHour='" + startingHour + '\''
                + ", endingHour='" + endingHour + '\''
                + '}';
    }
}
