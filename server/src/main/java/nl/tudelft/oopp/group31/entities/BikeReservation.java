package nl.tudelft.oopp.group31.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bike_reservation")
public class BikeReservation {

    @GeneratedValue
    @Id
    private int id;

    @Column(name = "netid")
    private String netID;

    @Column(name = "buildingid")
    private int buildingID;

    /**
     * Constructor for BikeReservation.
     */
    public BikeReservation() {
        this.netID = null;
        this.buildingID = -1;
    }

    /**
     * Constructor for BikeReservation with netID, buildingID and date as params.
     *
     * @param netID String
     * @param buildingID int
     */
    public BikeReservation(String netID, int buildingID) {
        this.netID = netID;
        this.buildingID = buildingID;
    }

    /**
     * Checks if 2 Objects are the same BikeReservation.
     *
     * @param other Object
     * @return Boolean value based on equality
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof BikeReservation) {
            BikeReservation that = (BikeReservation) other;

            return (this.netID.equals(that.netID)
                    && this.buildingID == that.buildingID);
        }

        return false;
    }

    /**
     * Converts a BikeReservation to a JSON representation of itself.
     *
     * @return a JSON representation of a BikeReservation
     */
    public String toString() {
        return "{\"id\":\"" + id
                + "\", \"netID\":\"" + netID
                + "\", \"buildingID\":\"" + buildingID + "\"}";
    }

    // Getters & Setters
    public int getID() {
        return this.id;
    }

    public String getNetID() {
        return this.netID;
    }

    public int getBuildingID() {
        return this.buildingID;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

}
