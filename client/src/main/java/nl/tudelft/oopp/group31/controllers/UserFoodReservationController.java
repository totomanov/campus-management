package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.Building;
import nl.tudelft.oopp.group31.entities.Food;

public class UserFoodReservationController extends UserHelperController implements Initializable {
    @FXML private DatePicker daySelection;
    @FXML private TextField hourSelection;
    @FXML private Label order;
    @FXML private ChoiceBox<Building> buildingID;
    @FXML private ChoiceBox<Food> foodSelection;
    @FXML private Label successLabel;

    private ArrayList<Food> items = new ArrayList<>();
    private ArrayList<Building> buildingArray;

    private ArrayList<Food> foodArray;

    private double price = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        Type buildingList = new TypeToken<ArrayList<Building>>() {
        }.getType();
        this.buildingArray = gson.fromJson(json, buildingList);
    }

    /**
     * Fills in the choiceBox for the building selection.
     */
    public void fillChoiceBoxBuilding() {
        for (Building r: this.buildingArray) {
            buildingID.getItems().add(r);
        }
    }

    /**
     * Creates a List of all food items.
     */
    public void createFoodList(ActionEvent event) {
        String json = ServerCommunication.getFoodItems(buildingID.getValue().getID());
        Gson gson = new Gson();
        Type foodList = new TypeToken<ArrayList<Food>>() {
        }.getType();
        this.foodArray = gson.fromJson(json, foodList);
        fillChoiceBoxFood();
    }

    /**
     * Fills in the choiceBox for the food selection.
     */
    public void fillChoiceBoxFood() {
        foodSelection.getItems().removeAll(foodSelection.getItems());
        for (Food r: this.foodArray) {
            // if(("" + r.getBuildingID()).equals(buildingID.getValue().toString()))
            foodSelection.getItems().add(r);
        }
    }

    /**
     * Adds another food item to the list when the button in clicked.
     * @param event represents the action of pressing the button
     */
    @FXML
    void addAnotherItem(ActionEvent event) {
        items.add(foodSelection.getValue());
        price = price + foodSelection.getValue().getPrice();
        this.order.setText("Your order contains: " + items.toString() + " and it costs " + price + " euros.");
    }

    /**
    * Verify the the Form inputs and returns a list of error messages.
    * @param id            Id of a building
    * @param food          A list of food items
    * @param time          Time of the reservation
    * @param date          Date of the reservation
    * @return  An arrayList containting all the errors of the inputs
    */
    public ArrayList<String> validateForm(Integer id, ArrayList<Food> food, String time, String date) {
        ArrayList<String> inputErrorMessages = new ArrayList<>();
        if (id == null) {
            inputErrorMessages.add("Please select a building.");
        }

        if (food == null || food.isEmpty()) {
            inputErrorMessages.add("Please select a food item.");
        }

        try {
            if (time.length() == 4) {
                String hour = time.substring(0,2);
                String min = time.substring(2);
                int parsedHour = Integer.parseInt(hour);
                int parsedMin = Integer.parseInt(min);
                if (parsedHour >= 24 || parsedHour < 0 || parsedMin < 0 || parsedMin >= 60) {
                    inputErrorMessages.add("Please enter a valid hour.");
                }
            } else {
                inputErrorMessages.add("Please enter a valid hour.");
            }
        } catch (NullPointerException | NumberFormatException e) {
            inputErrorMessages.add("Please enter a valid hour.");
        }

        if (date == null) {
            inputErrorMessages.add("Please select a date.");
        }
        return inputErrorMessages;
    }


    /**
     * Sends the values from the client to the server when the button is pressed.
     * @param event represents the action of pressing the button
     */
    @FXML
    void submitFoodReservationForm(ActionEvent event) {
        ArrayList<String> inputErrorMessages;
        int buildingId = buildingID.getValue().getID();
        String date = daySelection.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String netid = LoginSceneController.getUserName();
        String hour = hourSelection.getText();
        inputErrorMessages = validateForm(buildingId, items, hour, date);
        if (inputErrorMessages.isEmpty()) {
            StringBuilder addedFoodItems = new StringBuilder();
            for (Food item : items) {
                int foodItem = item.getID();
                ServerCommunication.addFoodReservation(netid, date, buildingId, date, foodItem);
                addedFoodItems.append(item.getName());
            }
            showSuccessLabel(addedFoodItems + " were successfully added to the reservation.");
            resetAddFoodForm();
        } else {
            showInputErrorAlert(inputErrorMessages);
        }
    }

    private void resetAddFoodForm() {
        hourSelection.clear();
        items.clear();
        this.order.setText("Your order doesn't contain any food item yet.");
    }

    private void showSuccessLabel(String message) {
        successLabel.setText(message);
    }


}
