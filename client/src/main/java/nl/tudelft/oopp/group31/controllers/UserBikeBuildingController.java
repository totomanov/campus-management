package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import nl.tudelft.oopp.group31.communication.RequestHelper;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.BikeReservation;
import nl.tudelft.oopp.group31.entities.Building;


public class UserBikeBuildingController extends UserHelperController {

    /**
     * currBuidlingID is used for making the reservations.
     * Updates when the button "book" is clicked
     */
    public static int currBuidlingID = -1;

    private Building building;

    @FXML
    private Label buildingName;

    @FXML
    private Label bikeAmount;


    /**
     * Populate building card templater with data from the bulding object.
     *
     * @param b Building - the building object to use
     */
    public void populate(Building b) {
        this.building = b;
        buildingName.setText(b.getName() != null ? building.getName() : "[null]");

        if (building.getBikeRental() != -1) {

            String amount = Integer.toString(building.getBikeRental());

            bikeAmount.setText(" " + amount);
        } else {
            bikeAmount.setText(" No bikes available");
        }
    }

    /**
     * On clicking rent, the loigc to rent the bike is started.
     *
     * @param event leftmouse click
     */
    @FXML
    public void rent(ActionEvent event) {

        if (building.getBikeRental() > 0) { //Checks if the building has any available bikes
            String json = ServerCommunication.getUserRent(RequestHelper.getUsername());
            Gson gson = new Gson();
            Type b = new TypeToken<ArrayList<BikeReservation>>() {
            }.getType();
            ArrayList<BikeReservation> array = gson.fromJson(json, b);
            if (!array.isEmpty()) { //Checks if the user is already renting a bike 
                debugAlert("You already have a bike");
            } else {
                ServerCommunication.rentBuildingBike(building.getID(), 1);
                ServerCommunication.addBikeRent(RequestHelper.getUsername(), building.getID());
                bikeAmount.setText(" " + (building.getBikeRental() - 1));
            }
        } else {
            debugAlert("There is no bike available");
        }
    }

}
