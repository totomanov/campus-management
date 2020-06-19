package nl.tudelft.oopp.group31.entities;

public class User {
    String netID;
    String password;
    int type;
    boolean rentedBike;

    /**
     * Empty Constructor for User.
     */
    public User() {
        this.netID = null;
        this.password = null;
        this.type = -1;
        this.rentedBike = false;
    }

    /**
     * Constructs a user class.
     * @param netID netID of user.
     * @param password password of user.
     * @param type type of user.
     */
    public User(String netID, String password, int type, boolean rentedBike) {
        this.netID = netID;
        this.password = password;
        this.type = type;
        this.rentedBike = rentedBike;
    }

    public String getNetID() {
        return netID;
    }

    public String getPass() {
        return password;
    }

    public int getType() {
        return type;
    }

    public boolean getRentedBike() {
        return this.rentedBike;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setRentedBike(boolean rentedBike) {
        this.rentedBike = rentedBike;
    }

    public String toString() {
        return "netID: " + netID + " Pass: " + password + " type: " + type + " rentedBike: " + rentedBike;
    }
}
