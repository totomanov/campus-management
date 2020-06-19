package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.Building;
import nl.tudelft.oopp.group31.entities.Food;

public class AdminFoodManagerController extends AdminHelperController {

    @FXML private TextField addFoodName, addFoodPrice, addFoodDescription;

    @FXML private ChoiceBox<Building> addFoodBuildingDropdown, removeFoodBuildingDropdown;

    @FXML private ChoiceBox<Food> removeFoodNameDropdown;

    @FXML private Label successLabel;

    private ArrayList<Building> buildingList;
    private ArrayList<Food> foodList;

    @Override
    public void initialize() {
        super.initialize();
        rootPane.setLeft(hamburgerMenu);

        createBuildingList();
        fillChoiceBoxBuilding();
    }

    /**
     * Creates a List of all buildings.
     */
    public void createBuildingList() {
        String json = ServerCommunication.getAllBuildings();
        Gson gson = new Gson();
        Type buildingListType = new TypeToken<ArrayList<Building>>() {}.getType();
        this.buildingList = gson.fromJson(json, buildingListType);
    }

    /**
     * Fills in the choiceBox for the both building selections.
     */
    public void fillChoiceBoxBuilding() {
        for (Building b: this.buildingList) {
            addFoodBuildingDropdown.getItems().add(b);
            removeFoodBuildingDropdown.getItems().add(b);
        }
    }

    /**
     * Creates a List of all food items.
     */
    public void createFoodList(ActionEvent event) {
        String json = ServerCommunication.getFoodItems(removeFoodBuildingDropdown.getValue().getID());
        Gson gson = new Gson();
        Type foodList = new TypeToken<ArrayList<Food>>() {}.getType();
        this.foodList = gson.fromJson(json, foodList);
        fillChoiceBoxFood();
    }

    /**
     * Fills in the choiceBox for the food selection.
     */
    public void fillChoiceBoxFood() {
        removeFoodNameDropdown.getItems().clear();
        for (Food f: this.foodList) {
            removeFoodNameDropdown.getItems().add(f);
        }
    }

    /**
     * Verify the the Form inputs and returns a list of error messages.
     * @param id            Id of a building
     * @param name          name of the Food
     * @param foodPrice     Price of the food
     * @param description   Description of the food
     * @return  An arrayList containting all the errors of the inputs
     */
    public ArrayList<String> validateForm(Integer id, String name, String foodPrice, String description) {
        ArrayList<String> inputErrorMessages = new ArrayList<>();
        if (id == null) {
            inputErrorMessages.add("Please select a building.");
        }

        if (name == null || name.isEmpty()) {
            inputErrorMessages.add("Please enter a valid food name.");
        }
        try {
            Double.parseDouble(foodPrice);
        } catch (NullPointerException | NumberFormatException e) {
            inputErrorMessages.add("Please enter a valid price.");
        }
        if (description == null || description.isEmpty()) {
            inputErrorMessages.add("Please enter a valid description.");
        }
        return inputErrorMessages;
    }

    /**
     * Uses the inputted data to send a request to the server and a new Food in teh database.
     * @param event         Clicking the "Add" button
     */
    @FXML
    void submitAddFoodForm(ActionEvent event) {
        ArrayList<String> inputErrorMessages;
        int buildingId = addFoodBuildingDropdown.getValue().getID();
        String name = addFoodName.getText();
        String foodPrice = addFoodPrice.getText();
        String description = addFoodDescription.getText();
        inputErrorMessages = validateForm(buildingId, name, foodPrice, description);
        if (inputErrorMessages.isEmpty()) {
            ServerCommunication.addFoodItem(name, Integer.toString(buildingId), foodPrice, description);
            showSuccessLabel(name + " was successfully added to building " + buildingId);
            resetAddFoodForm();
        } else {
            showInputErrorAlert(inputErrorMessages);
        }
    }

    /**
     * Validate the remove Form input.
     * @param t     The food object to be removed
     * @return      An array of errorMessages
     */
    public ArrayList<String> validateRemoveForm(Food t) {
        ArrayList<String> inputErrorMessages = new ArrayList<>();
        if (t == null) {
            inputErrorMessages.add("Please select a food item.");
        }
        return inputErrorMessages;
    }

    /**
     * Uses the input to send a delete request to the server to delete a food in the database.
     * @param event Clicking the remove button
     */
    @FXML
    void submitRemoveFoodForm(ActionEvent event) {
        int foodId;
        Food temp = removeFoodNameDropdown.getValue();
        ArrayList<String> inputErrorMessages = validateRemoveForm(temp);

        if (inputErrorMessages.isEmpty()) {
            foodId = temp.getID();
            boolean userHasConfirmed = confirmAlert("Do you really want to remove this food item?", "Changes are permanent.");
            if (userHasConfirmed) {
                ServerCommunication.removeFoodItem(String.valueOf(foodId));
                showSuccessLabel(removeFoodNameDropdown.getValue().getName() + " was successfully removed.");
                foodList.remove(temp);
                resetRemoveFoodForm();
                fillChoiceBoxFood();
            }
        } else {
            showInputErrorAlert(inputErrorMessages);
        }
    }

    /**
     * Shows Labels which indicates that the action was succesful.
     * @param message   The message to show
     */
    private void showSuccessLabel(String message) {
        successLabel.setText(message);
    }

    /**
     * Clears the form of all inputs.
     */
    private void resetAddFoodForm() {
        addFoodName.clear();
        addFoodPrice.clear();
        addFoodDescription.clear();
    }

    /**
     * Clears the form of all inputs.
     */
    private void resetRemoveFoodForm() {
        removeFoodNameDropdown.getItems().clear();
    }
}

