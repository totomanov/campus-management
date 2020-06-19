package nl.tudelft.oopp.group31.entities;

import javafx.scene.control.Button;

public class Reservation {


    private Button button;
    private int startTime;
    private int endTime;
    private boolean isSelected;

    /**
     * A Constructor for a Reservation.
     *
     * @param button    Button
     * @param startTime String
     * @param endTime   String
     */
    public Reservation(Button button, int startTime, int endTime) {
        this.button = button;
        this.startTime = startTime;
        this.endTime = endTime;
        isSelected = false;
    }

    public Button getButton() {
        return button;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setReserved(boolean bool) {
        isSelected = bool;
    }

    public boolean isReserved() {
        return isSelected;
    }
}