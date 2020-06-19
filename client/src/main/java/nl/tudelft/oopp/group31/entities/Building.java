package nl.tudelft.oopp.group31.entities;

public class Building {


    private int id;
    private String name;
    private String openingHour;
    private String closingHour;
    private String description;
    private int bikeRental;

    /**
     * Constructor for Building.
     */
    public Building() {
        this.name = null;
        this.openingHour = null;
        this.closingHour = null;
        this.description = null;
        this.bikeRental = -1;
    }

    /**
     * Constructor for Building with openingHour and closingHour as param.
     *
     * @param openingHour String
     * @param closingHour String
     */
    public Building(String openingHour, String closingHour) {
        this.name = null;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.description = null;
        this.bikeRental = -1;
    }

    /**
     * Constructor for Building with description as param.
     *
     * @param description String
     */
    public Building(String description) {
        this.name = null;
        this.openingHour = null;
        this.closingHour = null;
        this.description = description;
        this.bikeRental = -1;
    }

    /**
     * Constructor for Building with openingHour, closingHour and description as param.
     *
     * @param openingHour String
     * @param closingHour String
     * @param description String
     */
    public Building(String openingHour, String closingHour, String description) {
        this.name = null;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.description = description;
        this.bikeRental = -1;
    }

    /**
     *  Constructor for Building with name, openingHour, closingHour and description as param.
     * @param name String
     * @param openingHour String
     * @param closingHour String
     * @param description String
     */
    public Building(String name, String openingHour, String closingHour, String description) {
        this.name = name;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.description = description;
        this.bikeRental = -1;
    }

    /**
     * Constructor for Building with name, openingHour, closingHour, description and bikeRental as param.
     * @param name String
     * @param openingHour String
     * @param closingHour String
     * @param description String
     * @param bikeRental int
     */
    public Building(String name, String openingHour, String closingHour, String description, int bikeRental) {
        this.name = name;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.description = description;
        this.bikeRental = bikeRental;
    }


    /**
     * Checks if 2 Objects are the same building.
     *
     * @param other Object
     * @return Boolean value based on equality
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Building) {
            Building that = (Building) other;
            return (this.openingHour.equals(that.openingHour)
                    && this.closingHour.equals(that.closingHour)
                    && this.description.equals(that.description)
                    && this.name.equals(that.name));
        }

        return false;
    }

    /**
     * Converts a Building to a JSON representation of itself.
     *
     * @return a JSON representation of a Building
     */
    public String toJson() {
        return "{\"id\":\"" + id
                + "\", \"name\":\"" + name
                + "\", \"openingHour\":\"" + openingHour
                + "\", \"closingHour\":\"" + closingHour
                + "\", \"description\":\"" + description
                + "\", \"bikeRental\":\"" + bikeRental + "\"}";
    }

    /**
     * Converts a Building to a user-friendly representation used in controllers.
     *
     * @return a building's name
     */
    @Override
    public String toString() {
        return name;
    }

    // Getters & Setters
    public int getID() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeningHour() {
        return this.openingHour;
    }

    public String getClosingHour() {
        return this.closingHour;
    }

    public String getDescription() {
        return this.description;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setOpeningHour(String openingHour) {
        this.openingHour = openingHour;
    }

    public void setClosingHour(String closingHour) {
        this.closingHour = closingHour;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBikeRental() {
        return bikeRental;
    }

    public void setBikeRental(int bikeRental) {
        this.bikeRental = bikeRental;
    }
}
