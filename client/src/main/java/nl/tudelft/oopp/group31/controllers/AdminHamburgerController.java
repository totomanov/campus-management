package nl.tudelft.oopp.group31.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminHamburgerController extends AdminHelperController {

    @FXML
    public Label username;

    @Override
    public void initialize() {
        String name = LoginSceneController.getUserName();
        username.setText(name);
    }
}
