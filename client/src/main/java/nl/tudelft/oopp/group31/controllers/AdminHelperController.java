package nl.tudelft.oopp.group31.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;


public class AdminHelperController {

    @FXML
    protected BorderPane rootPane;

    @FXML
    protected BorderPane hamburgerMenu;

    private boolean hamburgerMenuIsOpen = true;

    MainController main = new MainController();


    protected void initialize() {
        // load hamburger menu into left side of root pane and hide it.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminHamburgerMenu.fxml"));
        try {
            hamburgerMenu = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        toggleHamburgerMenu(null);
    }

    @FXML
    void toggleHamburgerMenu(ActionEvent event) {
        if (hamburgerMenuIsOpen) {
            hamburgerMenu.setVisible(false);
            hamburgerMenu.setPrefWidth(0);
        } else {
            hamburgerMenu.setVisible(true);
            hamburgerMenu.setPrefWidth(250);
        }
        hamburgerMenuIsOpen = !hamburgerMenuIsOpen;
    }

    @FXML
    void navigateToHome(ActionEvent event) throws IOException {
        main.sceneChange(event, "/AdminHomePage.fxml");
    }

    @FXML
    void navigateToBuildings(ActionEvent event) throws IOException {
        main.sceneChange(event, "/AdminBuildingsManager.fxml");
    }

    @FXML
    void navigateToReservations(ActionEvent event) throws IOException {
        main.sceneChange(event, "/AdminReservationsManager.fxml");
    }

    @FXML
    void navigateToRooms(ActionEvent event) throws IOException {
        main.sceneChange(event, "/AdminRoomsManager.fxml");
    }

    @FXML
    void navigateToLogin(ActionEvent event) throws IOException {
        main.sceneChange(event, "/MainLoginScene.fxml");
    }

    @FXML
    void navigateToBikeManager(ActionEvent event) throws IOException {
        main.sceneChange(event, "/AdminBikesManager.fxml");
    }

    @FXML
    void navigateToUserManager(ActionEvent event) throws IOException {
        main.sceneChange(event, "/AdminUsersManager.fxml");
    }

    @FXML
    void navigateToFoodManager(ActionEvent event) throws IOException {
        main.sceneChange(event, "/AdminFoodManager.fxml");
    }


    @FXML
    void changeToUserView(ActionEvent event) throws IOException {
        main.sceneChange(event, "/UserCalendarView.fxml");
    }

    @FXML
    void debugAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(String.valueOf(this.getClass()));
        alert.setHeaderText("Debug message");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    boolean confirmAlert(String headerText, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        // user clicked Cancel or X
        // user clicked OK
        return result.isPresent() && result.get() != ButtonType.CANCEL;
    }

    @FXML
    void infoAlert(String headerText, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected void showInputErrorAlert(ArrayList<String> inputErrorMessages) {
        infoAlert("There were " + inputErrorMessages.size() + " error(s) in your input.",
                String.join("\n", inputErrorMessages));
    }
}
