package nl.tudelft.oopp.group31.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "openinghour")
    private String openingHour;

    @Column(name = "closinghour")
    private String closingHour;

    @Column(name = "description")
    private String description;

    @Column(name = "bikerental")
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
     *  Constructor for Building with name, openingHour, closingHour, description and bikeRental as param.
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
     * Checks if 2 Objects are the same Building.
     *
     * @param other Object
     * @return Boolean value based on equality
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Building) {
            Building that = (Building) other;

            return (this.name.equals(that.name)
                    && this.openingHour.equals(that.openingHour)
                    && this.closingHour.equals(that.closingHour)
                    && this.description.equals(that.description)
                    && this.bikeRental == that.bikeRental);
        }

        return false;
    }

    /**
     * Converts a Building to a JSON representation of itself.
     *
     * @return a JSON representation of a Building
     */
    public String toString() {
        return "{\"id\":\"" + id
                + "\", \"name\":\"" + name
                + "\", \"openingHour\":\"" + openingHour
                + "\", \"closingHour\":\"" + closingHour
                + "\", \"description\":\"" + description
                + "\", \"bikeRental\":\"" + bikeRental + "\"}";
    }

    /**
     * Deducts/adds the number of bikes available at this Building.
     *
     * @param numberOfBikes Number of bikes to deduct from the availability. Negative int to 'return' the bikes
     */
    public void rentBike(int numberOfBikes) {
        this.bikeRental += - numberOfBikes;
    }

    // Getters & Setters
    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
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

    public int getBikeRental() {
        return this.bikeRental;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setBikeRental(int bikeRental) {
        this.bikeRental = bikeRental;
    }

}
