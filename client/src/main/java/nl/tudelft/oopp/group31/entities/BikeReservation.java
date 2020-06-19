package nl.tudelft.oopp.group31.entities;

public class BikeReservation {

    private int id;
    private String netID;
    private int buildingID;
    private String date;
    private String buildingName;

    /**
     * Constructor for BikeReservation.
     */
    public BikeReservation() {
        this.netID = null;
        this.buildingID = -1;
        this.date = null;
    }

    /**
     * Constructor for BikeReservation with netID, buildingID and date as params.
     *
     * @param netID String
     * @param buildingID int
     * @param date String
     */
    public BikeReservation(String netID, int buildingID, String date) {
        this.netID = netID;
        this.buildingID = buildingID;
        this.date = date;
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
                    && this.buildingID == that.buildingID
                    && this.date.equals(that.date));
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
                + "\", \"buildingID\":\"" + buildingID
                + "\", \"date\":\"" + date + "\"}";
    }

    // Getters
    public int getID() {
        return this.id;
    }

    public String getNetID() {
        return this.netID;
    }

    public int getBuildingID() {
        return this.buildingID;
    }

    public String getDate() {
        return this.date;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

}
