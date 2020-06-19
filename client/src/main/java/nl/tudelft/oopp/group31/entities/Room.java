package nl.tudelft.oopp.group31.entities;

public class Room {

    String name, buildingID;
    boolean whiteboard, accessibility;
    int capacity, id;

    /**
     * Basic constructor for the room.
     */
    public Room() {
        this.id = -1;
        this.name = null;
        this.buildingID = "-1";
        this.capacity = 0;
        this.whiteboard = false;
        this.accessibility = false;
    }

    /**
     * Basic constructor for the room.
     * @param id id of room
     * @param name name of the room
     * @param buildingID id of building
     * @param whiteboard does it have a whiteboard
     * @param accessibility does it have access
     */
    public Room(int id, String name, String buildingID, boolean whiteboard, boolean accessibility, int capacity) {
        this.id = id;
        this.name = name;
        this.buildingID = buildingID;
        this.capacity = capacity;
        this.whiteboard = whiteboard;
        this.accessibility = accessibility;
    }

    /**
     * Basic constructor for the room.
     * @param name name of the room
     * @param buildingID id of building
     * @param whiteboard does it have a whiteboard
     * @param accessibility does it have access
     */
    public Room(String name, String buildingID, boolean whiteboard, boolean accessibility, int capacity) {
        this.name = name;
        this.buildingID = buildingID;
        this.capacity = capacity;
        this.whiteboard = whiteboard;
        this.accessibility = accessibility;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBid() {
        return buildingID;
    }

    public boolean hasWhiteBoard() {
        return whiteboard;
    }

    public boolean hasAccess() {
        return accessibility;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuildingID(String buildingID) {
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

    /**
     * Converts a Room to a JSON representation of itself.
     *
     * @return a JSON representation of a Room
     */
    public String toString() {
        return name;
    }

    /**
     * Converts a Room to a JSON representation of itself.
     *
     * @return a JSON representation of a Room
     */
    public String toJson() {
        return "{\"id\":\"" + id
                + "\", \"name\":\"" + name
                + "\", \"buildingID\":\"" + buildingID
                + "\", \"whiteboard\":\"" + whiteboard
                + "\", \"accessibility\":\"" + accessibility
                + "\", \"capacity\":\"" + capacity + "\"}";

    }
}
