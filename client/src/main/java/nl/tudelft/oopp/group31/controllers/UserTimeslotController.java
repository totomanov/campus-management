package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import nl.tudelft.oopp.group31.communication.RequestHelper;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.Reservation;
import nl.tudelft.oopp.group31.entities.Room;
import nl.tudelft.oopp.group31.entities.TimeSlot;

/*

Author: Arjun

*/

public class UserTimeslotController {

    private int currentNumber = 0;

    private static int start = 800;

    private static int end = 830;

    private String roomID = null;

    public DatePicker date;

    public AnchorPane pane;

    @FXML
    private Button ts2;

    @FXML
    private Button ts6;

    @FXML
    private Button ts10;

    @FXML
    private Button ts14;

    @FXML
    private Button ts18;

    @FXML
    private Button ts22;

    @FXML
    private Button ts3;

    @FXML
    private Button ts7;

    @FXML
    private Button ts11;

    @FXML
    private Button ts15;

    @FXML
    private Button ts19;

    @FXML
    private Button ts23;

    @FXML
    private Button ts4;

    @FXML
    private Button ts8;

    @FXML
    private Button ts12;

    @FXML
    private Button ts16;

    @FXML
    private Button ts20;

    @FXML
    private Button ts24;

    @FXML
    private Button ts1;

    @FXML
    private Button ts5;

    @FXML
    private Button ts9;

    @FXML
    private Button ts13;

    @FXML
    private Button ts17;

    @FXML
    private Button ts21;

    ArrayList<Button> buttonList = new ArrayList<>(24);

    ArrayList<Reservation> reservations = new ArrayList<>(24);

    HashMap<String, String> roomHash = new HashMap<>();

    /**
     * Method to add a button along with a start and endtime to the arraylist
     * of reservations representing each timeslot.
     *
     * @param b     button
     * @param start start time
     * @param end   end time
     */
    void add(Button b, int start, int end) {
        Reservation r = new Reservation(b, start, end);
        reservations.add(currentNumber, r);
    }

    /**
     * Adds all the buttons into the arraylist.
     */
    void initList() {
        add(ts1, 800, 830);
        add(ts2, 830, 900);
        add(ts3, 900, 930);
        add(ts4, 930, 1000);
        add(ts5, 1000, 1030);
        add(ts6, 1030, 1100);
        add(ts7, 1100, 1130);
        add(ts8, 1130, 1200);
        add(ts9, 1200, 1230);
        add(ts10, 1230, 1300);
        add(ts11, 1300, 1330);
        add(ts12, 1330, 1400);
        add(ts13, 1400, 1430);
        add(ts14, 1430, 1500);
        add(ts15, 1500, 1530);
        add(ts16, 1530, 1600);
        add(ts17, 1600, 1630);
        add(ts18, 1630, 1700);
        add(ts19, 1700, 1730);
        add(ts20, 1730, 1800);
        add(ts21, 1800, 1830);
        add(ts22, 1830, 1900);
        add(ts23, 1900, 1930);
        add(ts24, 1930, 2000);
    }

    /**
     * This will be a list of the available rooms, so the person can choose from
     * them.
     */
    @FXML
    public ChoiceBox<String> choiceBox = new ChoiceBox<>();

    /**
     * changes the color of a button to red.
     *
     * @param r reservation.
     * @param b button.
     */
    static void changeColorRed(Reservation r, Button b) {
        r.setReserved(true);
        b.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: red;");
    }

    /**
     * Method that will receive a room id, and make query the server for all the
     * timeslots reserved for the room on that a given day, and will then make all
     * the timeslots that are reserved red, so that they may not be reserved.
     *
     * @param id the id of the room chosen
     */
    void init(String id) {
        // initialize the array of buttons representing timeslots
        initList();
        String dateChosen = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date dateChosenInSqlForm = Date.valueOf(dateChosen);
        String para = "date=" + dateChosenInSqlForm + "&roomID=" + id;
        String res = ServerCommunication.getReservationsForRoom(para);
        ArrayList<TimeSlot> resArray = initResArray(res);
        int size = resArray.size();
        changeColorRedOfReservedTimeslots(size, reservations, resArray);
    }

    /**
     * Gets all of the reservations of a room.
     *
     * @return a list containing all the reservations
     */
    public static ArrayList<TimeSlot> initResArray(String res) {
        // Get all timeslots reserved for room on that day
        Gson gson = new Gson();
        Type reservationListType = new TypeToken<ArrayList<TimeSlot>>() {
        }.getType();
        return gson.fromJson(res, reservationListType);
    }

    /**
     * Changes the colour of the reserved timeslots.
     *
     * @param size         size
     * @param reservations List of all the reservations
     * @param resArray     List of all the timeslots
     */
    public static void changeColorRedOfReservedTimeslots(int size, ArrayList<Reservation> reservations, ArrayList<TimeSlot> resArray) {
        for (int i = 0; i <= size - 1; i++) {
            start = resArray.get(i).getStartTime();
            end = resArray.get(i).getEndTime();
            for (int j = 0; j <= 23; j++) {
                if ((reservations.get(j).getStartTime() >= start) && (reservations.get(j).getEndTime() <= end)) {
                    changeColorRed(reservations.get(j), reservations.get(j).getButton());
                }
            }
        }
    }

    /**
     * Same method as changeColorRedOfReservedTimeslots, but instead of chanign color of the button, it adds the buttons
     * that should have their color changed to an array of reservations to be able to test.
     *
     * @param size         size
     * @param reservations List of all the reservations
     * @param resArray     List of all the timeslots
     */
    public static ArrayList<Reservation> changeColorRedOfReservedTimeslotsTestMethod(int size, ArrayList<Reservation> reservations,
                                                                                     ArrayList<TimeSlot> resArray) {
        ArrayList<Reservation> results = new ArrayList<Reservation>();
        for (int i = 0; i <= size - 1; i++) {
            start = resArray.get(i).getStartTime();
            end = resArray.get(i).getEndTime();
            for (int j = 0; j <= reservations.size() - 1; j++) {
                if ((reservations.get(j).getStartTime() >= start) && (reservations.get(j).getEndTime() <= end)) {
                    results.add(reservations.get(j));
                }
            }
        }
        return results;
    }

    private int counter = 0;

    /**
     * Puts the rooms in the current building into the ChoiceBox menu.
     *
     * @param whiteboard    Whether the room has a whiteboard
     * @param accessibility Whether the room has additional accessibility
     */
    public void populateChoiceBox(boolean whiteboard, boolean accessibility) {
        String allRooms = ServerCommunication.getRoomsInBuilding(BuildingCardController.currBuildingID);
        ArrayList<Room> rooms = getAllRooms(allRooms);
        addToBoxAndHashMap(rooms, whiteboard, accessibility, choiceBox, roomHash);
    }

    /**
     * Gets all of the rooms of the current building.
     *
     * @return a list containing all the rooms
     */
    public static ArrayList<Room> getAllRooms(String allRooms) {
        Gson gson = new Gson();
        Type reservationListType = new TypeToken<ArrayList<Room>>() {
        }.getType();
        return gson.fromJson(allRooms, reservationListType);
    }

    /**
     * Adds the rooms into choicbox and the hashMap.
     *
     * @param rooms         a list of all the rooms
     * @param whiteboard    whether or not there is a whiteboard
     * @param accessibility whether or not it is accessible
     * @param choiceBox     Choicebox
     * @param roomHash      Hashmap of all the rooms
     */
    public static void addToBoxAndHashMap(ArrayList<Room> rooms,
                                          boolean whiteboard, boolean accessibility, ChoiceBox<String> choiceBox, HashMap<String, String> roomHash) {
        Room currentRoom;
        for (int i = 0; i < rooms.size(); i++) {
            currentRoom = rooms.get(i);
            if (checkAccess(accessibility, currentRoom) && checkWhiteboard(whiteboard, currentRoom)) {
                choiceBox.getItems().add(currentRoom.getName());
            }
            roomHash.put(currentRoom.getName(), String.valueOf(currentRoom.getId()));
        }
    }

    /**
     * Same method as addToBoxAndHashMap, but instead of adding to the choicebox,
     * the rooms are added to an array to be able to test.
     *
     * @param rooms         a list of all the rooms
     * @param whiteboard    whether or not there is a whiteboard
     * @param accessibility whether or not it is accessible
     * @param roomHash      Hashmap of all the rooms
     * @return roomNames as an array
     */
    public static ArrayList<Room> addToBoxAndHashMaptestMethod(ArrayList<Room> rooms, boolean whiteboard, boolean accessibility,
                                                               HashMap<String, String> roomHash) {
        Room currentRoom;
        ArrayList<Room> roomNames = new ArrayList<Room>(rooms.size());
        for (int i = 0; i < rooms.size(); i++) {
            currentRoom = rooms.get(i);
            if (checkAccess(accessibility, currentRoom) && checkWhiteboard(whiteboard, currentRoom)) {
                roomNames.add(currentRoom);
            }
            roomHash.put(currentRoom.getName(), String.valueOf(currentRoom.getId()));
        }
        return roomNames;
    }

    /**
     * Logic to see if the room complies with access preference.
     *
     * @param accessibility boolean
     * @param room          room to check
     * @return complies or not, true or false
     */
    public static boolean checkAccess(boolean accessibility, Room room) {
        return !accessibility || room.hasAccess();
    }

    /**
     * Logic to see if the room complies with whiteboard preference.
     *
     * @param whiteboard boolean
     * @param room       room to check
     * @return complies or not, true or false
     */
    public static boolean checkWhiteboard(boolean whiteboard, Room room) {
        return !whiteboard || room.hasWhiteBoard();
    }

    /**
     * Once chosen the room, the buttons will all update to either red or no color
     * depending on availability. If it is no color the individual can click on the
     * time slot to select it, and the selected timeslots will be given back to the
     * sever once the person clicks the next button.
     *
     * @param event ActionEvent
     */
    @FXML
    void buttonClicked(ActionEvent event) {
        String color = "white";
        Button button = (Button) event.getSource();

        if (button.getStyle().contains("white") && counter <= 8) {
            button.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: green;");
            buttonList.add(button);
            counter++;
        } else {
            if (button.getStyle().contains("green")) {
                counter--;
            }
            if (button.getStyle().contains("red")) {
                color = "red";
            }
            button.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: " + color);
        }
    }

    /**
     * On clicking next the controller checks for all the reservations the user made
     * and sends it back to the server to save them.
     *
     * @param event the event user wished to save.
     * @throws IOException exception
     */
    @FXML
    void next(ActionEvent event) throws IOException {
        int size = reservations.size() - 1;
        for (int i = 0; i <= size; i++) {
            if (reservations.get(i).getButton().getStyle().contains("green")) {
                reservations.get(i).setReserved(true);
            }
        }
        String netID = RequestHelper.getUsername();
        String dateChosen = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String roomID = this.roomID;
        String startTime;
        String endTime;
        for (int k = size; k >= 0; k--) {

            if (reservations.get(k).getButton().getStyle().contains("green")) {
                startTime = "" + reservations.get(k).getStartTime();
                startTime = startTime.length() == 4 ? startTime : "0" + startTime;
                endTime = "" + reservations.get(k).getEndTime();
                endTime = endTime.length() == 4 ? endTime : "0" + endTime;
                for (int l = k - 1; l >= 0; l--) {
                    if (reservations.get(l).getButton().getStyle().contains("green")) {
                        endTime = "" + reservations.get(l).getEndTime();
                    } else {
                        k = l;
                        l = -1;
                    }
                }
                ServerCommunication.addRoomReservation(netID, dateChosen, roomHash.get(roomID), startTime,
                        endTime);

            }
        }
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserTimeslotSuccessDialog.fxml");
        loader.setLocation(xmlUrl);
        AnchorPane p = loader.load();
        pane.getChildren().setAll(p);
    }

    @SuppressWarnings("unchecked")
    @FXML
    void onChoiceBoxClick(ActionEvent event) {
        // String str = buttonList.toString();
        init(roomHash.get(((ChoiceBox<String>) event.getSource()).getSelectionModel().getSelectedItem()));
        roomID = (((ChoiceBox<String>) event.getSource()).getSelectionModel().getSelectedItem());
    }

}
