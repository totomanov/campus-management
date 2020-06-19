package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.Building;

public class AdminBuildingsManagerController extends AdminHelperController {
    @FXML
    private TextField addBuildingName, openingHour, closingHour, openingMinute, closingMinute;
    
    @FXML
    private TextField editBuildingName, editOpeningHour, editClosingHour, editOpeningMinute, editClosingMinute;
    
    @FXML
    private TextArea buildingDescription, editBuildingDescription;
    
    @FXML 
    private Label successLabelAdd, successLabelEdit;

    @FXML
    private ChoiceBox<Building> editBuildingDropdown;

    ArrayList<Building> buildingArray = new ArrayList<>();

    /**
     * Fetch all buildings form server and create array of building cards.
     * Currently adds an edit dialog on top, will be interactive in the future.
     */
    @Override
    public void initialize() {
        super.initialize();
        rootPane.setLeft(hamburgerMenu);
        populate();
    }

    /**
     * Retreieves all the building from the server.
     */
    public void createBuildingList() {
        String json = fetchBuildings();
        Gson gson = new Gson();
        Type buildingList = new TypeToken<ArrayList<Building>>() {
        }.getType();
        buildingArray = gson.fromJson(json, buildingList);
    }

    /**
     * Fills in the information of a Building.
     */
    @FXML
    private void populateEditForm(ActionEvent event) {
        Building b = editBuildingDropdown.getSelectionModel().getSelectedItem();
        editBuildingName.setText(b.getName());
        editBuildingDescription.setText(b.getDescription());
        editOpeningHour.setText(b.getOpeningHour().substring(0, 2));
        editOpeningMinute.setText(b.getOpeningHour().substring(2));
        editClosingHour.setText(b.getClosingHour().substring(0, 2));
        editClosingMinute.setText(b.getClosingHour().substring(2));
    }
    
    /**
     * Gets all the entered data from the UI.
     *
     */
    @FXML
    private void submitAddBuildingForm(ActionEvent event) {

        String name = addBuildingName.getText();
        String description = buildingDescription.getText();
        String openingMin = openingMinute.getText().length() == 2 ? openingMinute.getText() : "00";
        String closingMin = closingMinute.getText().length() == 2 ? closingMinute.getText() : "00";
        String openingH = openingHour.getText().length() == 2 ? openingHour.getText() : "NaN";
        String closingH = closingHour.getText().length() == 2 ? closingHour.getText() : "NaN";
        String opening = openingH + openingMin;
        String closing = closingH + closingMin;
        Building b = new Building(name, opening, closing, description);
        String error = validateInput(b);

        if (error == null) {
            ServerCommunication.addBuilding(name, opening, closing, description);
            successLabelAdd.setText("Success !");
            populate();
        } else {
            debugAlert(error);
        }
    }

    /**
     * Validates if the building that is passed in is indeed a valid building in terms of attribute values.
     * @param b the building to test.
     * @return  boolean if its valid or not.
     */
    String validateInput(Building b) {
        String name = b.getName();
        String desc = b.getDescription();
        String closeHour = b.getClosingHour().substring(0, 2);
        String openHour = b.getOpeningHour().substring(0, 2);
        String closeMin = b.getClosingHour().substring(2);
        String openMin = b.getOpeningHour().substring(2);
        // capture field data one by one and validate
        if (name == null || name.length() == 0) {
            return "Please enter a building name.";
        } else if (name.length() > 100) {
            return "Building name cannot be longer than 100 characters.";
        }

        if (desc == null || desc.length() == 0) {
            return "Please enter description.";
        } else if (desc.length() > 5000) {
            return "Description name cannot be longer than 5000 characters.";
        }

        int openHourInt;
        try {
            openHourInt = Integer.parseInt(openHour);
        } catch (NumberFormatException e) {
            return "Please enter a valid opening hour.";
        }
        if (!(openHourInt > 0 && openHourInt < 24)) {
            return "Please enter a valid opening hour.";
        }

        int closeHourInt;
        try {
            closeHourInt = Integer.parseInt(closeHour);
        } catch (NumberFormatException e) {
            return "Please enter a valid closing hour.";
        }
        if (!(closeHourInt > 0 && closeHourInt < 24)) {
            return "Please enter a valid closing hour.";

        } else if (closeHourInt < openHourInt) {
            return "Closing hour must be after the opening hour.";
        }

        int openMinInt;
        try {
            openMinInt = Integer.parseInt(openMin);
        } catch (NumberFormatException e) {
            return "Please enter a valid opening minute.";
        }
        if (!(openMinInt >= 0 && openMinInt < 60)) {
            return "Please enter a valid opening minute.";
        }

        int closeMinInt;
        try {
            closeMinInt = Integer.parseInt(closeMin);
        } catch (NumberFormatException e) {
            return "Please enter a valid closing minute.";
        }
        if (!(closeMinInt >= 0 && closeMinInt < 60)) {
            return "Please enter a valid closing minute.";
            
        } 
        if (closeHourInt == openHourInt && closeMinInt <= openMinInt) {
            return "Closing time must be after the opening time.";
        }
        return null;
    }

    /**
     * Gets all buildings from the database.
     *
     * @return      Json representation of all BUildings
     */
    @FXML
    String fetchBuildings() {
        return ServerCommunication.getAllBuildings();
    }

    /**
     * Sends an edited version of a Building to the server.
     *
     * @param event Clicking the modify button
     */
    @FXML
    void submitEditBuildingForm(ActionEvent event) {
        if (confirmAlert("Do you really want to edit this Building?",
                "Changes are permanent.")) {
            Building b = editBuildingDropdown.getSelectionModel().getSelectedItem();
            String name = editBuildingName.getText();
            String description = editBuildingDescription.getText();
            String openingMin = editOpeningMinute.getText().length() == 2 ? editOpeningMinute.getText() : "00";
            String closingMin = editOpeningMinute.getText().length() == 2 ? editClosingMinute.getText() : "00";
            String openingH = editOpeningHour.getText().length() == 2 ? editOpeningHour.getText() : "NaN";
            String closingH = editOpeningHour.getText().length() == 2 ? editClosingHour.getText() : "NaN";
            String opening = openingH + openingMin;
            String closing = closingH + closingMin;
            Building temp = new Building(name, opening, closing, description);
            String error = validateInput(temp);

            if (error == null) {
                int id = b.getID();
                ServerCommunication.editBuilding(id, name, opening, closing, description, b.getBikeRental());
                successLabelEdit.setText("Success !");
                populate();
            } else {
                debugAlert(error);
            }
        }
    }

    /**
     * Populates the choiceBox with buildings.
     */
    private void populate() {
        if (!editBuildingDropdown.getItems().isEmpty()) {
            editBuildingDropdown.getItems().removeAll(editBuildingDropdown.getItems());
        }
        createBuildingList();
        editBuildingDropdown.getItems().addAll(buildingArray);
    }
    /**
     * Sends a Get request to delete the selected Building.
     *
     * @param event Clicking the modify button
     */
    @FXML
    void submitDeleteBuildingForm(ActionEvent event) {  
        if (confirmAlert("Do you really want to remove this room?",
                "Changes are permanent.")) {
            Building b = editBuildingDropdown.getSelectionModel().getSelectedItem();
            if (b == null) {
                debugAlert("You must choose a Building to delete !");
                return;
            }
            ServerCommunication.deleteBuilding(b.getID());
            successLabelEdit.setText("The building was deleted.");
            editBuildingDropdown.getItems().remove(b);
        }    
    }  
    
    /**
     * Displays an error message on screen.
     *
     * @param errorText the error message to be displayed
     */
    @FXML
    void generateError(String errorText) {
        debugAlert(errorText);
    }

}
