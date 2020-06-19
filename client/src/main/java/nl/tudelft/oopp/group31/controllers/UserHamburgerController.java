package nl.tudelft.oopp.group31.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class UserHamburgerController extends UserHelperController {

    @FXML
    public Label username;

    @FXML
    public Hyperlink adminView;

    @Override
    public void initialize() {
        String name = LoginSceneController.getUserName();
        username.setText(name);

        int type = LoginSceneController.getUserType();
        if (type != 1) {
            adminView.setVisible(false);
        }
    }
}