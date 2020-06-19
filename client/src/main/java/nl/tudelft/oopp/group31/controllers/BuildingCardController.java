package nl.tudelft.oopp.group31.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.Building;

public class BuildingCardController extends UserHelperController {

    /**
     * currBuildingID is used for making the reservations.
     * Updates when the button "book" is clicked
     */
    public static int currBuildingID = -1;

    private Building building;

    @FXML
    private HBox card;
    @FXML
    private VBox child1, child2;
    @FXML
    private Label buildingName, buildingDescription;
    @FXML
    private Label openingHour;

    @FXML
    private Label closingHour;

    /**
     * Populate building card template with data from the building object.
     *
     * @param b Building - the building object to use
     */
    public void populate(Building b) {
        this.building = b;
        buildingName.setText(b.getName() != null ? building.getName() : "[null]");
        //Makes sure the hours is well formatted
        String openHour = b.getOpeningHour() != null ? b.getOpeningHour().substring(0, 2) : "[null]";
        String closeHour = b.getClosingHour() != null ? b.getClosingHour().substring(0, 2) : "[null]";
        String openMin = b.getOpeningHour() != null && b.getOpeningHour().length() > 2 ? b.getOpeningHour().substring(2,4) : "00";
        String closeMin = b.getClosingHour() != null && b.getClosingHour().length() > 2 ? b.getClosingHour().substring(2,4) : "00";
        openingHour.setText(openHour + ":" + openMin);
        closingHour.setText(closeHour + ":" + closeMin);
        buildingDescription.setText(b.getDescription());

    }
    /**
     * Remove a building from database after user confirmation.
     *
     */
    public void removeBuilding() {
        if (confirmAlert("Building\"" + building.getName() + "\" will be deleted.",
                "Changes cannot be reverted.")) {
            card.getChildren().removeAll(card.getChildren());
            ServerCommunication.deleteBuilding(building.getID()); // returns 405 for some reason
        }
    }

    /**
     * Opens up the additional menu to chose date, time and room.
     * @param event The ActionEvent, that triggers the method
     * @throws IOException Thrown by FXMLLoader, being unable to load the specified file
     */
    public void book(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserOverviewBuildings.fxml"));
        loader.load();
        UserOverviewBuildingsController uob = loader.getController();
        uob.buttonClicked(event);
        currBuildingID = this.building.getID();
    }
}
