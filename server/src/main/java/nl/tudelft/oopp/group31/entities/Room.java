package nl.tudelft.oopp.group31.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    private int id;

    @Column (name = "name")
    private String name;

    @Column(name = "buildingid")
    private int buildingID;

    @Column(name = "whiteboard")
    private boolean whiteboard;

    @Column (name = "accessibility")
    private boolean accessibility;

    @Column (name = "capacity")
    private int capacity;


    /**
     * Constructor for Room.
     */
    public Room() {
        this.name = null;
        this.buildingID = -1;
        this.capacity = -1;
    }

    /**
     * Constructor for Room with buildingID and capacity as param.
     *
     * @param buildingID int
     * @param capacity int
     */
    public Room(int buildingID, int capacity) {
        this.name = null;
        this.buildingID = buildingID;
        this.capacity = capacity;
    }

    /**
     * Constructor for Room with buildingID, whiteboard, accessibility and capacity as params.
     *
     * @param buildingID int
     * @param whiteboard boolean
     * @param accessibility boolean
     * @param capacity int
     */
    public Room(int buildingID, boolean whiteboard, boolean accessibility, int capacity) {
        this.name = null;
        this.buildingID = buildingID;
        this.whiteboard = whiteboard;
        this.accessibility = accessibility;
        this.capacity = capacity;
    }

    /**
     * Constructor for Room with name, buildingID and capacity as params.
     *
     * @param name String
     * @param buildingID int
     * @param capacity int
     */
    public Room(String name, int buildingID, int capacity) {
        this.name = name;
        this.buildingID = buildingID;
        this.capacity = capacity;
    }

    /**
     * Constructor for Room with name, buildingID, whiteboard, accessibility and capacity as params.
     *
     * @param name String
     * @param buildingID int
     * @param whiteboard boolean
     * @param accessibility boolean
     * @param capacity int
     */
    public Room(String name, int buildingID, boolean whiteboard, boolean accessibility, int capacity) {
        this.name = name;
        this.buildingID = buildingID;
        this.whiteboard = whiteboard;
        this.accessibility = accessibility;
        this.capacity = capacity;
    }

    /**
     * Checks if 2 Objects are the same Room.
     *
     * @param other Object
     * @return Boolean value based on equality
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Room) {
            Room that = (Room) other;

            return (this.name.equals(that.name)
                    && this.buildingID == that.buildingID
                    && this.whiteboard == that.whiteboard
                    && this.accessibility == that.accessibility
                    && this.capacity == that.capacity);
        }

        return false;
    }

    /**
     * Converts a Room to a JSON representation of itself.
     *
     * @return a JSON representation of a Room
     */
    public String toString() {
        return "{\"id\":\"" + id
                + "\", \"name\":\"" + name
                + "\", \"buildingID\":\"" + buildingID
                + "\", \"whiteboard\":\"" + whiteboard
                + "\", \"accessibility\":\"" + accessibility
                + "\", \"capacity\":\"" + capacity + "\"}";
    }

    // Getters & Setters
    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getBuildingID() {
        return this.buildingID;
    }

    public boolean getWhiteboard() {
        return this.whiteboard;
    }

    public boolean getAccessibility() {
        return this.accessibility;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public void setWhiteboard(boolean whiteboard) {
        this.whiteboard = whiteboard;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
