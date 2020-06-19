package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.Building;
import nl.tudelft.oopp.group31.entities.Room;


public class AdminRoomsManagerController extends AdminHelperController {

    @FXML private CheckBox addRoomWhiteboard, addRoomAccessibility, editRoomWhiteboard, editRoomAccessibility;

    @FXML private TextField addRoomName, addRoomCapacity, editRoomCapacity;

    @FXML private ChoiceBox<String> addRoomBuildingDropdown, editRoomBuildingDropdown, editRoomDropdown;

    @FXML private Label successLabel;

    private static final Gson gson = new Gson(); // avoid reinitializing Gson object
    ArrayList<Building> buildingList = new ArrayList<>();

    HashMap<String, Building> buildingNameHashMap = new HashMap<>(); // buildingName -> Building
    HashMap<Integer, ArrayList<Room>> roomCache = new HashMap<>(); // buildingID -> List of Rooms
    ObservableList<String> buildingNameList;

    /**
     * Initializes the controller after the root element has been completely processed.
     */
    @Override
    public void initialize() {
        super.initialize();
        rootPane.setLeft(hamburgerMenu);

        buildingList = getAllBuildings();
        buildingNameHashMap = buildingHashMap(buildingList);

        addRoomBuildingDropdown.setItems(buildingNameList); //populate dropdowns with building list
        editRoomBuildingDropdown.setItems(buildingNameList);
        addRoomBuildingDropdown.setValue(buildingList.get(0).getName()); //set first as selected
        editRoomBuildingDropdown.setValue(buildingList.get(0).getName());

        updateEditRoomDropdown(buildingList.get(0).getName());

        editRoomBuildingDropdown.setOnAction(event -> updateEditRoomDropdown(editRoomBuildingDropdown.getValue()));
    }

    /**
     * Creates a HashMap of all buildings and their name.
     * @return hashmap of all buildings
     */
    public HashMap<String, Building> buildingHashMap(ArrayList<Building> buildings) {

        HashMap<String, Building> h = new HashMap<>();

        buildingNameList = FXCollections.observableArrayList();

        for (Building b : buildings) {
            if (b.getName() != null) {
                buildingNameList.add(b.getName());
                h.put(b.getName(), b);
            }
        }

        return h;
    }

    /**
     * Retrieves all the buildings from the server.
     *
     * @return a list of all the buildings
     */
    private ArrayList<Building> getAllBuildings() {

        ArrayList<Building> a;
        String json = ServerCommunication.getAllBuildings();
        Type buildingListType = new TypeToken<ArrayList<Building>>() {
        }.getType();
        a = gson.fromJson(json, buildingListType);

        return a;
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


    /**
     * Validates input.
     *
     * @param name           name of the room
     * @param capacityString capacity of the room
     * @return Either an error message or null when there are no errors
     */
    public String validateInput(String name, String capacityString) {

        // capture field data one by one and validate
        if (name == null || name.length() == 0) {
            return "Please enter a room name.";
        } else if (name.length() > 100) {
            return "Room name cannot be longer than 100 characters.";
        }

        int capacity;

        try {
            capacity = Integer.parseInt(capacityString);

            if (capacity <= 0) {
                return "Capacity cannot be 0 or negative.";
            }
        } catch (NumberFormatException e) {
            return "Please enter a valid number";
        }
        return null;
    }


    /**
     * Gets all the entered data from the UI.
     *
     */
    @FXML
    private void submitAddRoomForm() {

        String name = addRoomName.getText();
        String capacityString = addRoomCapacity.getText();
        boolean whiteboard = addRoomWhiteboard.isSelected();
        boolean accessibility = addRoomAccessibility.isSelected();
        Building b = buildingNameHashMap.get(addRoomBuildingDropdown.getValue());

        String buildingID = String.valueOf(b.getID());

        String error = validateInput(name, capacityString);


        if (error == null) {
            int capacity = Integer.parseInt(capacityString);
            Room r = new Room(name, buildingID, whiteboard, accessibility, capacity);
            onAdd(r, b);
        } else {
            generateError(error);
        }

    }

    /**
     * Sends a room to the server to be added.
     *
     * @param inputRoom the to be added room
     * @param b         the building where the room should be added
     */
    private void onAdd(Room inputRoom, Building b) {
        ServerCommunication.addRoom(inputRoom);
        showSuccessLabel(inputRoom.getName() + " was successfully added.");
        getAllRooms(b, true); // update with id assigned by server, otherwise is -1
        updateEditRoomDropdown(editRoomBuildingDropdown.getValue());
        resetAddRoomForm();

    }

    /**
     * Gets all the entered data from the UI.
     *
     */
    @FXML
    private void submitEditRoomForm() {

        String name = editRoomDropdown.getValue();

        String capacityString = editRoomCapacity.getText();

        boolean whiteboard = editRoomWhiteboard.isSelected();
        boolean accessibility = editRoomAccessibility.isSelected();


        Building b = buildingNameHashMap.get(editRoomBuildingDropdown.getValue());

        String buildingID = String.valueOf(b.getID());

        String error = validateInput(name, capacityString);

        generateError(error);

        if (error == null) {
            int capacity = Integer.parseInt(capacityString);
            Room r = new Room(name, buildingID, whiteboard, accessibility, capacity);
            r.setId(getRoomId(b, r.getName()));
            onEdit(r);
        }


    }

    /**
     * Sends an edited version of a room to the server.
     *
     * @param inputRoom the room to be edited
     */
    private void onEdit(Room inputRoom) {
        ServerCommunication.editRoom(inputRoom);
        showSuccessLabel(inputRoom.getName() + " was successfully edited.");
        Building b = buildingNameHashMap.get(editRoomBuildingDropdown.getValue());
        ArrayList<Room> roomList = roomCache.get(b.getID());
        roomList.removeIf(r -> r.getId() == inputRoom.getId());
        roomList.add(inputRoom);
        roomCache.replace(b.getID(), roomList);

        resetEditRoomForm();
        updateEditRoomDropdown(editRoomBuildingDropdown.getValue());

    }

    /**
     * Deletes a room from the database.
     *
     */
    @FXML
    private void submitDeleteRoomForm() {
        if (confirmAlert("Do you really want to remove this room?",
                "Changes are permanent.")) {
            String roomName = editRoomDropdown.getValue();
            String buildingName = editRoomBuildingDropdown.getValue();
            Building b = buildingNameHashMap.get(buildingName);
            int roomId = getRoomId(b, roomName);

            showSuccessLabel(roomName + " was successfully deleted.");

            ServerCommunication.deleteRoom(roomId);

            updateCache(b, roomId);

            updateEditRoomDropdown(b.getName());
        }
    }

    /**
     * Updates the list of all the rooms.
     *
     * @param b      the building of which rooms need to be updated
     * @param roomId the id of the room that needs to be updated
     */
    private void updateCache(Building b, int roomId) {
        ArrayList<Room> roomList = getAllRooms(b);  // update cache
        roomList.removeIf(r -> r.getId() == roomId);
        roomCache.replace(b.getID(), roomList);
    }

    /**
     * Gets all rooms in a building.
     *
     * @param b Building of the rooms
     * @return all rooms
     */
    private ArrayList<Room> getAllRooms(Building b) {
        return getAllRooms(b, false);
    }

    /**
     * Gets all rooms in a building.
     *
     * @param b     Building of the rooms
     * @param force whether or not to force the request
     * @return an ArrayList of all the buildings in a room
     */
    private ArrayList<Room> getAllRooms(Building b, boolean force) {
        // forcing a request is needed to obtain server-assigned room id
        int id = b.getID();
        if (roomCache.containsKey(id) && !force) {    // if building's rooms are cached, return from cache
            return roomCache.get(id);
        } else {                            // else, query server and update cache
            String json = ServerCommunication.getRoomsInBuilding(id);
            Type roomListType = new TypeToken<ArrayList<Room>>() {
            }.getType();
            ArrayList<Room> roomList = gson.fromJson(json, roomListType);
            roomCache.put(id, roomList);
            return roomList;
        }
    }

    /**
     * Gets a roomId by specifying the building and its name.
     *
     * @param b        the building of the room
     * @param roomName the name of the room.
     * @return the id of the building
     */
    private int getRoomId(Building b, String roomName) {
        ArrayList<Room> roomList = getAllRooms(b);
        for (Room r : roomList) {
            if (r.getName().equals(roomName)) {
                return r.getId();
            }
        }
        return -1;
    }

    /**
     * Fills in the information of a room.
     */
    @FXML
    private void populateEditForm() {
        Building b = buildingNameHashMap.get(editRoomBuildingDropdown.getValue());
        ArrayList<Room> roomList = getAllRooms(b);
        for (Room r : roomList) {
            if (r.getName().equals(editRoomDropdown.getValue())) {
                editRoomCapacity.setText(Integer.toString(r.getCapacity()));
                editRoomAccessibility.setSelected(r.hasAccess());
                editRoomWhiteboard.setSelected(r.hasWhiteBoard());
                return;
            }
        }
    }

    /**
     * Updates the dropdown menu of edit room.
     *
     * @param buildingName adds this building to the dropdown
     */
    private void updateEditRoomDropdown(String buildingName) {
        editRoomDropdown.getItems().clear();
        Building b = buildingNameHashMap.get(buildingName);

        ArrayList<Room> roomList = getAllRooms(b);
        for (Room r : roomList) {
            editRoomDropdown.getItems().add(r.getName());
        }
        editRoomDropdown.setValue(roomList.get(0).getName());
        populateEditForm();
    }

    /**
     * Clears all the information of the add card.
     */
    private void resetAddRoomForm() {
        addRoomBuildingDropdown.setValue(buildingList.get(0).getName());
        addRoomName.clear();
        addRoomCapacity.clear();
        addRoomAccessibility.setSelected(false);
        addRoomWhiteboard.setSelected(false);
    }

    /**
     * Clears all the information of the edit card.
     */
    private void resetEditRoomForm() {
        editRoomBuildingDropdown.setValue(buildingList.get(0).getName());
        editRoomDropdown.valueProperty().set(null);
        editRoomCapacity.clear();
        editRoomAccessibility.setSelected(false);
        editRoomWhiteboard.setSelected(false);
    }

    /**
     * Displays a message.
     *
     * @param message the message to be displayed
     */
    private void showSuccessLabel(String message) {
        successLabel.setText(message);
    }
}
