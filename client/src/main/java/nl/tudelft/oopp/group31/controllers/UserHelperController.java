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

public class UserHelperController {

    @FXML
    protected BorderPane rootPane;

    @FXML
    protected BorderPane hamburgerMenu;

    private boolean hamburgerMenuIsOpen = true;

    MainController main = new MainController();

    /**
     * Initializes the scene.
     */
    protected void initialize() {
        // load hamburger menu into left side of root pane and hide it.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserHamburgerMenu.fxml"));
        try {
            hamburgerMenu = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        toggleHamburgerMenu(null);
    }

    /**
     * Toggles the visibility of the HamburgerMenu.
     * @param event     CLicking on the hamburgerButton.
     */
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

    /**
     * Changes scene to that of the user's homePage.
     * @param event         Clicking on "Home" in the hamburger menu
     * @throws IOException  If the the fxml file does not exist
     */
    @FXML
    void navigateToHome(ActionEvent event) throws IOException {
        main.sceneChange(event, "/UserCalendarView.fxml");
    }

    /**
     * Changes scene to that of the user Overview of Buildings.
     * @param event         Clicking on "Building" in the hamburger menu
     * @throws IOException  If the the fxml file does not exist
     */
    @FXML
    void navigateToBuildings(ActionEvent event) throws IOException {
        main.sceneChange(event, "/UserOverviewBuildings.fxml");
    }

    /**
     * Changes scene to that of the user Overview of Reservations.
     * @param event         Clicking on "My Reservations" in the hamburger menu
     * @throws IOException  If the the fxml file does not exist
     */
    @FXML
    void navigateToReservations(ActionEvent event) throws IOException {
        main.sceneChange(event, "/UserOverviewReservations.fxml");
    }

    /**
     * Changes scene to that of the main log-in screen.
     * @param event         Clicking on the "log-out" button in the hamburger menu
     * @throws IOException  if the fxml file does not exist
     */
    @FXML
    void navigateToLogin(ActionEvent event) throws IOException {
        main.sceneChange(event, "/MainLoginScene.fxml");
    }

    @FXML
    void navigateToSignup(ActionEvent event) throws IOException {
        main.sceneChange(event, "/MainSignupScene.fxml");
    }

    /**
     * Changes scene to that of the user overview of available bikes.
     * @param event         Clicking on "Bikes" in the hamburger menu
     * @throws IOException  If the the fxml file does not exist
     */
    @FXML
    void navigateToBikeSelection(ActionEvent event) throws IOException {
        main.sceneChange(event, "/UserOverviewBikes.fxml");
    }

    /**
     * Changes scene to that of the user's Calendar.
     * @param event         Clicking on "Calendar" in the hamburger menu
     * @throws IOException  If the the fxml file does not exist
     */
    @FXML
    void navigateToCalendar(ActionEvent event) throws IOException {
        main.sceneChange(event, "/UserCalendarView.fxml");
    }

    /**
     * Changes scene to that of the Reservation of Food scene.
     * @param event         Clicking on "Food" in the hamburger menu
     * @throws IOException  If the the fxml file does not exist
     */
    @FXML
    void navigateToFood(ActionEvent event) throws IOException {
        main.sceneChange(event, "/UserFoodReservation.fxml");
    }

    /**
     * Changes scene to that of the administrator's HomePage.
     * @param event         Clicking on The hyperlink in the hamburger menu
     * @throws IOException  If the the fxml file does not exist
     */
    @FXML
    void changeToAdminView(ActionEvent event) throws IOException {
        main.sceneChange(event, "/AdminHomePage.fxml");
    }

    /**
     * Create an Alert window containing the passed message.
     * @param message   The message to show in the alert window.
     */
    @FXML
    void debugAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(String.valueOf(this.getClass()));
        alert.setHeaderText("Debug message");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Create an Confirmation window in order to get confirmation from the user.
     * @param headerText    A Header
     * @param message       A message
     * @return              A boolean representing whether the user accepted or not
     */
    @FXML
    boolean confirmAlert(String headerText, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        // user clicked OK
        return result.isPresent() && result.get() != ButtonType.CANCEL; // user clicked Cancel or X
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
