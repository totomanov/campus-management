package nl.tudelft.oopp.group31.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class UserTimeslotSuccessController {

    @FXML
    private AnchorPane showUp;

    @FXML
    void buttonClicked(ActionEvent event) throws IOException {
        MainController main = new MainController();
        String scene;

        scene = "/UserCalendarView.fxml";
        main.sceneChange(event, scene);
    }

}
