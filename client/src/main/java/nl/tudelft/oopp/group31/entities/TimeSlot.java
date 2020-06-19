package nl.tudelft.oopp.group31.entities;

import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nl.tudelft.oopp.group31.communication.ServerCommunication;

public class TimeSlot {

    private String netID, date, roomID, roomName;
    private int startingHour, endingHour;

    /**
     * Constructor for the Time-slots.
     *
     * @param netID        netID of the user who made the reservation
     * @param date         date of reservation
     * @param roomID       id of room for reservation
     * @param startingHour starting time
     * @param endingHour   ending time
     */
    public TimeSlot(String netID, String date, String roomID, int startingHour, int endingHour) {
        this.netID = netID;
        this.date = date;
        this.roomID = roomID;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
    }

    public int getStartTime() {
        return startingHour;
    }

    public int getEndTime() {
        return endingHour;
    }

    public String getID() {
        return netID;
    }

    public String getDate() {
        return date;
    }

    public String getRoomID() {
        return roomID;
    }

    /**
     * Calculates and returns the month as an integer of a given date.
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

    /**
     * Gets the name of the room.
     *
     * @return The name of the room.
     */
    public String getRoomName() {
        if (roomName == null) {
            String json = ServerCommunication.getRoom(Integer.parseInt(this.roomID));
            Gson gson = new Gson();

            Room r = gson.fromJson(json, Room.class);

            this.roomName = r.getName();
        }
        return this.roomName;
    }
}
