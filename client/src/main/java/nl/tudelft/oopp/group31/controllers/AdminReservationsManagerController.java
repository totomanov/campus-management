package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.Building;
import nl.tudelft.oopp.group31.entities.Room;
import nl.tudelft.oopp.group31.entities.RoomReservation;

public class AdminReservationsManagerController extends AdminHelperController {

    @FXML
    private TableView<RoomReservation> past, upcoming, blocked;

    @FXML
    private TableColumn<RoomReservation, String> user, date, status, pastUser, pastDate, pastStatus, pastTime, time;

    @FXML
    private TableColumn<RoomReservation, String> building, room, pastBuilding, pastRoom, blockedRoom, blockedDate;

    @FXML
    private DatePicker fromDate, toDate;

    @FXML
    private ChoiceBox<Room> roomList;
    
    @FXML    
    private ChoiceBox<Building> buildingList;
    private ArrayList<RoomReservation> reservationArray;
    private ArrayList<Room> roomArray;
    private ArrayList<Building> buildingArray;
    
    /**
     * Loads the page. 
     */
    @Override
    public void initialize() {
        super.initialize();
        rootPane.setLeft(hamburgerMenu);
        initializeHelper();
        createReservationList();
        createRoomList();
        createBuildingList();
        setNames();
        fillTables();
        fillBuildingChoiceBox();
    }


    /**
     * Sets the Building name and Room name for every RoomReservation.
     */
    private void setNames() {
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
     * Creates a List of all Reservations.
     *
     */
    public void createReservationList() {
        String json = ServerCommunication.getRoomReservations();
        Gson gson = new Gson();
        Type reservationList = new TypeToken<ArrayList<RoomReservation>>() {
        }.getType();
        this.reservationArray = gson.fromJson(json, reservationList);
    }

    /**
     * Creates a List and a Map of all Rooms.
     */
    public void createRoomList() {
        String json = ServerCommunication.getRooms();
        Gson gson = new Gson();
        Type list = new TypeToken<ArrayList<Room>>() {
        }.getType();
        this.roomArray = gson.fromJson(json, list);
    }

    /**
     * Creates a List of all Buildings.
     */
    public void createBuildingList() {
        String json = ServerCommunication.getAllBuildings();
        Gson gson = new Gson();
        Type list = new TypeToken<ArrayList<Building>>() {
        }.getType();
        this.buildingArray = gson.fromJson(json, list);
    }

    /**
     * Fills in the tables.
     */
    public void fillTables() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (RoomReservation r : this.reservationArray) { //Insert reservations into the correct table according to the date.
            if (r.getStartingHour() != null & r.getEndingHour() != null) {
                Date temp;
                try {
                    temp = formatter.parse(r.getDate());
                    if (new java.util.Date().compareTo(temp) >= 0) { 
                        past.getItems().add(r);
                    } else {
                        upcoming.getItems().add(r);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                blocked.getItems().add(r); //Reservations with no opening or closing hours represent the blocking reservations.
            }
        }
    }

    /**
     * Fills in the choiceBox for Buildings.
     */
    public void fillBuildingChoiceBox() {
        for (Building b: this.buildingArray) {
            buildingList.getItems().add(b);
        }
    }

    /**
     * Prepares the tables to receive data.
     */
    public void initializeHelper() {
        user.setCellValueFactory(new PropertyValueFactory<>("netID"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        room.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        building.setCellValueFactory(new PropertyValueFactory<>("buildingName"));
        pastUser.setCellValueFactory(new PropertyValueFactory<>("netID"));
        pastDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        pastRoom.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        pastTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        pastBuilding.setCellValueFactory(new PropertyValueFactory<>("buildingName"));
        pastStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        blockedRoom.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        blockedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    /**
     * Sends a delete request to remove a RoomReservation from the database. 
     * @param event     Clicking the remove button
     */
    @FXML
    void removePastReservation(ActionEvent event) {
        if (confirmAlert("Do you really want to delete this reservation?",
                "Changes are permanent.")) {
            RoomReservation r = past.getSelectionModel().getSelectedItem();
            ServerCommunication.deleteRoomReservations(r.getId());
            past.getItems().remove(r);
        }
    }

    /**
     * Sends a delete request to remove a RoomReservation from the database. 
     * @param event     Clicking the remove button
     */
    @FXML
    void removeUpcomingReservation(ActionEvent event) {
        if (confirmAlert("Do you really want to delete this reservation?",
                "Changes are permanent.")) {
            RoomReservation r = upcoming.getSelectionModel().getSelectedItem();
            ServerCommunication.deleteRoomReservations(r.getId());
            upcoming.getItems().remove(r);
        }
    }

    /**
     * Sends a delete request to remove a RoomReservation from the database. 
     * @param event     Clicking the remove button
     */
    @FXML
    void removeBlockedReservation(ActionEvent event) {
        if (confirmAlert("Do you really want to delete this reservation?",
                "Changes are permanent.")) {
            RoomReservation r = blocked.getSelectionModel().getSelectedItem();
            ServerCommunication.deleteRoomReservations(r.getId());
            blocked.getItems().remove(r);
        }
    } 

    /**
     * Sends a request to add blocking reservations in the database for the selected time period. 
     * @param event     Click the "add Rule" button.
     */
    @FXML
    void addBlock(ActionEvent event) {
        Room r = roomList.getSelectionModel().getSelectedItem();
        if (r == null) {
            debugAlert("You must choose the Room for which you want to block reservations");            
            return; 
        }
        LocalDate start = fromDate.getValue();
        if (start == null) {
            debugAlert("You must choose the time period for which you want to block reservations");
            return;
        }
        LocalDate end;
        if (toDate.getValue() == null || toDate.getValue().compareTo(start) <= 0) {
            end = fromDate.getValue();
        } else {
            end = toDate.getValue();
        }
        while (!start.equals(end.plusDays(1))) { //Sends a request for each day of the selected time Period.
            String temp = start.getYear() + "-0" + start.getMonthValue() + "-" + start.getDayOfMonth();
            ServerCommunication.blockRoom(r.getId(), temp);
            start = start.plusDays(1);
        }
    }

    /**
     * Fills the choiceBox with Room objects.
     * @param event     Selecting a building.
     */
    @FXML
    void fillRoomChoiceBox(ActionEvent event) {
        roomList.getItems().clear();
        Building b = buildingList.getSelectionModel().getSelectedItem();
        for (Room r : roomArray) {
            if (r.getBid().equals(Integer.toString(b.getID()))) {
                roomList.getItems().add(r);
            }
        }
    }
}