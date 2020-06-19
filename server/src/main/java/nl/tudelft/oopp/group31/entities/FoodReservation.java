package nl.tudelft.oopp.group31.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "food_reservation")
public class FoodReservation {

    @GeneratedValue
    @Id
    private int id;

    @Column(name = "netid")
    private String netID;

    @Column(name = "date")
    private String date;

    @Column(name = "buildingid")
    private int buildingID;

    @Column(name = "hour")
    private String hour;

    @Column(name = "foodselection")
    private int foodSelection;

    /**
     * Constructor for FoodReservation.
     */
    public FoodReservation() {
        this.netID = null;
        this.date = null;
        this.buildingID = -1;
        this.hour = null;
        this.foodSelection = -1;
    }

    /**
     * Constructor for FoodReservation with netID, date, buildingID, hour and foodSelection as params.
     *
     * @param netID String
     * @param date String
     * @param buildingID int
     * @param hour String
     * @param foodSelection int
     */
    public FoodReservation(String netID, String date, int buildingID, String hour, int foodSelection) {
        this.netID = netID;
        this.date = date;
        this.buildingID = buildingID;
        this.hour = hour;
        this.foodSelection = foodSelection;
    }

    /**
     * Checks if 2 Objects are the same FoodReservation.
     *
     * @param other Object
     * @return Boolean value based on equality
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof FoodReservation) {
            FoodReservation that = (FoodReservation) other;

            return (this.netID.equals(that.netID)
                    && this.date.equals(that.date)
                    && this.buildingID == that.buildingID
                    && this.hour.equals(that.hour)
                    && this.foodSelection == that.foodSelection);
        }

        return false;
    }

    /**
     * Converts a FoodReservation to a JSON representation of itself.
     *
     * @return a JSON representation of a FoodReservation
     */
    public String toString() {
        return ("{\"id\":\"" + id
                + "\", \"netID\":\"" + netID
                + "\", \"date\":\"" + date
                + "\", \"buildingID\":\"" + buildingID
                + "\", \"hour\":\"" + hour
                + "\", \"foodSelection\":\"" + foodSelection + "\"}");
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

    public int getBuildingID() {
        return this.buildingID;
    }

    public String getHour() {
        return this.hour;
    }

    public int getFoodSelection() {
        return this.foodSelection;
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

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setFoodSelection(int foodSelection) {
        this.foodSelection = foodSelection;
    }

}
