package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.TimeSlot;

public class UserReservationFilterController {

    @FXML
    private AnchorPane showUp;

    @FXML
    private CheckBox whiteboard;

    @FXML
    private CheckBox access;

    @FXML
    private DatePicker date;

    @FXML
    private Label warning;

    /**
     * This function will get the values the user inputs onto the application and
     * then send it to the server so that it can query.
     *
     * @param event ActionEvent
     */
    @FXML
    void buttonClicked(ActionEvent event) throws IOException {
        controllerLogic();
    }


    private void controllerLogic() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/UserTimeslotDialog.fxml");
        loader.setLocation(xmlUrl);
        AnchorPane p = loader.load();
        UserTimeslotController cntrl = loader.getController();
        cntrl.populateChoiceBox(whiteboard.isSelected(), access.isSelected());

        int userType = LoginSceneController.getUserType();
        boolean reserv = userHasReservation(createListTimeSlot());
        String message = validateFilter(date.getValue(), userType, reserv);
        warning.setText(message);
        cntrl.pane = showUp;
        cntrl.date = date;

        if (message.isEmpty()) {
            showUp.getChildren().setAll(p);
        }
    }

    /**
     * Validates the input of the filter.
     * @param date      Date to use while filtering rooms
     * @param userType  userType to use while filterig rooms
     * @param reserv    Boolean representing whether the user already has a reservation
     * @return          A string which might contain a warning concerning the inputs
     */
    public String validateFilter(LocalDate date, int userType, boolean reserv) {
        String warning = "";
        System.out.print(reserv);
        if (date == null) {
            warning = "You must choose a Date !";
        } else if (date.compareTo(LocalDate.now()) < 0) {
            warning = "Date chosen is in the past";
        }
        if (warning.isEmpty() && userType > 2 && reserv) {
            warning = "You have a reservation booked already!";
        }
        return warning;
    }

    /**
     * Creates an array of the TimeSlots of reservations made by the user.
     *
     * @return arraylist of timeslot's
     */
    ArrayList<TimeSlot> createListTimeSlot() {
        String json = ServerCommunication.getReservationsMadeByUser(LoginSceneController.getUserName());
        Gson gson = new Gson();
        Type reservationListType = new TypeToken<ArrayList<TimeSlot>>() {
        }.getType();
        return gson.fromJson(json, reservationListType);
    }


    /**
     * Checks if the user alreay has an upcoming reservations.
     * @param resArray          The list of timeSlots reserved by the user, past and present
     * @return                  True if the user has a upcoming reservation, false otherwise
     */
    boolean userHasReservation(ArrayList<TimeSlot> resArray) {
        if (resArray.size() == 0) {
            return false;
        }
        for (int i = resArray.size() - 1; i >= 0; i--) {
            LocalDate date = LocalDate.parse(resArray.get(i).getDate());
            if (date.compareTo(LocalDate.now()) >= 0) { //Checks if it's an upcoming TimeSlot or not

                return true;
            }
        }
        return false;
    }
}
