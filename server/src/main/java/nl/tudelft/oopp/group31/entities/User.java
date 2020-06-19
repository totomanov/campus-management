package nl.tudelft.oopp.group31.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "netid")
    private String netID;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private int type;

    @Column(name = "rentedbike")
    private boolean rentedBike;

    /**
     * Constructor for User.
     */
    public User() {
        this.netID = null;
        this.password = null;
        this.type = -1;
    }

    /**
     * Constructor for User with netID and password as params.
     *
     * @param netID String
     * @param password String
     */
    public User(String netID, String password) {
        this.netID = netID;
        this.password = password;
        this.type = -1;
    }

    /**
     * Constructor for User with netID, password and type as params.
     *
     * @param netID String
     * @param password String
     * @param type int
     */
    public User(String netID, String password, int type) {
        this.netID = netID;
        this.password = password;
        this.type = type;
    }

    /**
     * Constructor for User with netID, password, type and rentedBike as params.
     *
     * @param netID String
     * @param password String
     * @param type int
     * @param rentedBike boolean
     */
    public User(String netID, String password, int type, boolean rentedBike) {
        this.netID = netID;
        this.password = password;
        this.type = type;
        this.rentedBike = rentedBike;
    }

    /**
     * Checks if 2 Objects are the same User.
     *
     * @param other Object
     * @return Boolean value based on equality
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof User) {
            User that = (User) other;

            return (this.netID.equals(that.netID)
                    && this.password.equals(that.password)
                    && this.type == that.type
                    && this.rentedBike == that.rentedBike);
        }

        return false;
    }

    /**
     * Converts a User to a JSON representation of itself.
     *
     * @return A JSON representation of a User.
     */
    public String toString() {
        return "{\"netID\":\"" + netID
                + "\", \"password\":\"" + password
                + "\", \"type\":\"" + type
                + "\", \"rentedBike\":\"" + rentedBike + "\"}";
    }

    /**
     * Changes status of currently renting a bike to true/false.
     *
     * @param rentedBike Boolean based on whether User is renting a bike or not
     */
    public void rentBike(boolean rentedBike) {
        this.rentedBike = rentedBike;
    }

    // Getters & Setters for User
    public String getNetID() {
        return this.netID;
    }

    public String getPassword() {
        return this.password;
    }

    public int getType() {
        return this.type;
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

}
