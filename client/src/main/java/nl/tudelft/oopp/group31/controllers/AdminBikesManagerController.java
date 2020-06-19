package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.BikeReservation;
import nl.tudelft.oopp.group31.entities.Building;

public class AdminBikesManagerController extends AdminHelperController {

    @FXML
    TextField num;

    @FXML
    TableView<Building> buildings;

    @FXML
    TableView<BikeReservation> users;

    @FXML
    ChoiceBox<Building> buildingList;

    private ArrayList<Building> buildingArray;
    private ArrayList<BikeReservation> reservationArray;

    @FXML
    TableColumn<Building, String> building, bikeNumber;

    @FXML
    TableColumn<BikeReservation, String> user, bikeBuilding;

    @Override
    public void initialize() {
        super.initialize();
        rootPane.setLeft(hamburgerMenu);
        createBuildingsArray();
        createReservationArray();
        setBuildingNames();
        initializeHelper();
        users.getItems().addAll(reservationArray);
        buildings.getItems().addAll(buildingArray);
        buildingList.getItems().addAll(buildingArray);
    }

    /**
     * Sets the building name of each BikeReservation to the correct one.
     */
    private void setBuildingNames() {
        HashMap<Integer, String> buildingNames = new HashMap<>();
        for (Building b : buildingArray) {
            buildingNames.put(b.getID(), b.getName());
        }
        for (BikeReservation r : reservationArray) {
            r.setBuildingName(buildingNames.get(r.getBuildingID()));
        }
    } 

    /**
     * Prepares the tableviews columns to be filled with data.
     */
    void initializeHelper() {
        user.setCellValueFactory(new PropertyValueFactory<>("netID"));
        bikeBuilding.setCellValueFactory(new PropertyValueFactory<>("buildingName"));
        building.setCellValueFactory(new PropertyValueFactory<>("name"));
        bikeNumber.setCellValueFactory(new PropertyValueFactory<>("bikeRental"));
    }

    /**
     * Creates a building array.
     */
    @FXML
    void createBuildingsArray() {
        String json = ServerCommunication.getAllBuildings();
        Gson gson = new Gson();
        Type buildingList = new TypeToken<ArrayList<Building>>() {
        }.getType();
        this.buildingArray = gson.fromJson(json, buildingList);
    }

    /**
     *  Creates reservation array.
     */
    @FXML
    void createReservationArray() {
        String json = ServerCommunication.getAllBikeReservation();
        Gson gson = new Gson();
        Type reservationList = new TypeToken<ArrayList<BikeReservation>>() {
        }.getType();
        this.reservationArray = gson.fromJson(json, reservationList);
    }

    /**
     * Remove a Bike Rent and returns the bike to the building.
     * @param event     Cliking the remove button
     */
    @FXML
    private void removeUserRent(ActionEvent event) {
        BikeReservation b = users.getSelectionModel().getSelectedItem();
        ServerCommunication.deleteBikeRent(b.getID()); 
        ServerCommunication.rentBuildingBike(b.getBuildingID(), -1);
        users.getItems().remove(b);
    }

    /**
     * Fills the text field with the current numebr of bike.
     * @param event     Choosing a Building in the ChoiceBox 
     */
    @FXML
    public void fillTextField(ActionEvent event) {
        Building b = buildingList.getSelectionModel().getSelectedItem();
        if (b == null) {
            return;
        }
        num.setText(Integer.toString(b.getBikeRental()));
    }

    /**
     * Modifies the number of Bike in the building.
     * @param event     Clicking on the button "Modify"
     */
    @FXML
    private void modify(ActionEvent event) {
        Building b = buildingList.getSelectionModel().getSelectedItem();
        String number = num.getText();
        int bikes = b.getBikeRental();
        try {
            bikes = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            debugAlert("Not A Number");
        }
        ServerCommunication.editBuilding(b.getID(), b.getName(), b.getOpeningHour(), b.getClosingHour(), b.getDescription(), bikes);
        createBuildingsArray(); //Reloads the building tableview.
        buildings.getItems().clear();
        buildings.getItems().addAll(this.buildingArray);
    }
}
