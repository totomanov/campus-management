package nl.tudelft.oopp.group31.entities;


public class Food {

    private int id;
    private String name;
    private int buildingID;
    private double price;
    private String description;

    /**
     * Empty Constructor for Food.
     */
    public Food() {
        this.name = null;
        this.buildingID = -1;
        this.price = -1;
        this.description = null;
    }

    /**
     * Constructor for Food with name, buildingID and price as parameters.
     *
     * @param name String
     * @param buildingID int
     * @param price double
     */
    public Food(String name, int buildingID, double price) {
        this.name = name;
        this.buildingID = buildingID;
        this.price = price;
        this.description = null;
    }

    /**
     * Constructor for Food with name, buildingID, price and description as parameters.
     *
     * @param name String
     * @param buildingID int
     * @param price double
     * @param description String
     */
    public Food(String name, int buildingID, double price, String description) {
        this.name = name;
        this.buildingID = buildingID;
        this.price = price;
        this.description = description;
    }

    /**
     * Checks if 2 Food instances are equal.
     *
     * @param other Object
     * @return Boolean value based on equality
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Food) {
            Food that = (Food) other;

            return (this.name.equals(that.name)
                    && this.buildingID == that.buildingID
                    && Math.abs(this.price - that.price) <= 0.001
                    && this.description.equals(that.description));
        }

        return false;
    }

    /**
     * Converts a Food to a JSON representation of itself.
     *
     * @return a JSON representation of the Food instance
     */
    public String toString() {
        return name + " (" + description + " )";
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

    public double getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
