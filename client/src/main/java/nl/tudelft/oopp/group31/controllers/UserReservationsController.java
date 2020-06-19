package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nl.tudelft.oopp.group31.communication.RequestHelper;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.BikeReservation;
import nl.tudelft.oopp.group31.entities.Building;
import nl.tudelft.oopp.group31.entities.Room;
import nl.tudelft.oopp.group31.entities.RoomReservation;

public class UserReservationsController extends UserHelperController {
    @FXML
    protected Button hamburgerButton;

    @FXML
    private TableView<RoomReservation> reservationTableView;

    @FXML
    private TableColumn<RoomReservation, String> building, room, date, timePeriod, status;

    @FXML
    private Label message;

    @FXML
    private Button remove;

    private BikeReservation bike;

    /**
     * Creates a List of all Reservations made by the current user.
     *
     * @return An ArrayList of the Reservations
     */
    public ArrayList<RoomReservation> createReservationList() {
        String json = ServerCommunication.getReservationsMadeByUser(RequestHelper.getUsername());
        Gson gson = new Gson();
        Type reservationList = new TypeToken<ArrayList<RoomReservation>>() {
        }.getType();
        return gson.fromJson(json, reservationList);
    }

    @Override
    @FXML
    public void initialize() {
        super.initialize();
        initializeHelper();
        rootPane.setLeft(hamburgerMenu);
        ArrayList<RoomReservation> reservations = createReservationList();
        setNames(createRoomList(), createBuildingList(), reservations);
        for (RoomReservation r : reservations) {
            reservationTableView.getItems().add(r);
        }
        hasBike();
    }

    /**
     * Prepares the table to receive data.
     */
    public void initializeHelper() {
        building.setCellValueFactory(new PropertyValueFactory<>("buildingName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        room.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        timePeriod.setCellValueFactory(new PropertyValueFactory<>("time"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    /**
     * Updates the label and the button according to whether is renting a bike or not.
     */
    public void hasBike() {
        String json = ServerCommunication.getUserRent(RequestHelper.getUsername());
        Gson gson = new Gson();
        Type rent = new TypeToken<ArrayList<BikeReservation>>() {
        }.getType();
        ArrayList<BikeReservation> userBike = gson.fromJson(json, rent);
        if (userBike.isEmpty()) { //Changes the text according to the state of the user bike reservation
            message.setText("You have no bike at the moment");
            remove.setDisable(true);
        } else {
            message.setText("You are renting a bike");
            remove.setDisable(false);
            bike = userBike.get(0);
        }
    }

    /**
     * Removes a reservation.
     */
    @FXML
    public void removeReservation(ActionEvent event) {
        RoomReservation r = reservationTableView.getSelectionModel().getSelectedItem();
        if (r != null) {
            if (confirmAlert("Do you really want to remove this reservation?",
                    "Changes are permanent.")) {
                ServerCommunication.deleteRoomReservations(r.getId());
                reservationTableView.getItems().remove(r);
            }
        }
    }

    /**
     * Changes scene to that of the overview of Building.
     * @param event         Clicking the "add reservation" button
     * @throws IOException  if the scene does not exist.
     */
    @FXML
    public void goToReservationMenu(ActionEvent event) throws IOException {
        super.navigateToBuildings(event);
    }

    /**
     * Asks if the user wants its rented bike. If yes, then it returns the bike to its associated building.
     *
     * @param event User click the "remove" button
     */
    @FXML
    public void returnBike(ActionEvent event) {
        if (confirmAlert("Do you really want to return the bike ?", "")) {
            ServerCommunication.deleteBikeRent(bike.getID());
            ServerCommunication.rentBuildingBike(bike.getBuildingID(), -1);
            remove.disableProperty().set(true);
            message.setText("You returned the bike.");
        }
    }
    
    /**
     * Sets the Building name and Room name for every RoomReservation.
     */
    public void setNames(ArrayList<Room> roomArray, ArrayList<Building> buildingArray, ArrayList<RoomReservation> reservationArray) {
        HashMap<Integer, String> buildingNames = new HashMap<>();
        HashMap<Integer, Room> roomMap = new HashMap<>();
        for (Room r: roomArray) {
            roomMap.put(r.getId(), r);
        }
        for (Building b : buildingArray) {
            buildingNames.put(b.getID(), b.getName());
        }
        for (RoomReservation r : reservationArray) {
            Room temp = roomMap.get(r.getRoomID());
            r.setRoomName(temp.getName());
            int buildingID = Integer.parseInt(temp.getBid());
            r.setBuildingName(buildingNames.get(buildingID));
        }
    }

    /**
     * Creates a List of all Rooms.
     */
    ArrayList<Room> createRoomList() {
        String json = ServerCommunication.getRooms();
        Gson gson = new Gson();
        Type list = new TypeToken<ArrayList<Room>>() {
        }.getType();
        return gson.fromJson(json, list);
    }

    /**
     * Creates a List of all Buildings.
     */
    ArrayList<Building> createBuildingList() {
        String json = ServerCommunication.getAllBuildings();
        Gson gson = new Gson();
        Type list = new TypeToken<ArrayList<Building>>() {
        }.getType();
        return gson.fromJson(json, list);
    }


}