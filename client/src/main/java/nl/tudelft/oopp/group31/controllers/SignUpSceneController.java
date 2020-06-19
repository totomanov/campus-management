package nl.tudelft.oopp.group31.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import nl.tudelft.oopp.group31.communication.RequestHelper;
import nl.tudelft.oopp.group31.communication.ServerCommunication;

public class SignUpSceneController {
    @FXML private TextField userPassword, userName, confirmPassword;
    @FXML private Label error;
    @FXML private Text dailyTip;
    private ArrayList<String> tips = new ArrayList<>(Arrays.asList(
            "Try logging in!",
            "Remember washing your hands regularly",
            "Practise social distancing",
            "If you guess just right you might be able to get into someone else's account!\n"
                    + "You could also just use your own info, however",
            "Why are you reading this? Log in already",
            "Don't you have anything better to do than reading tips?",
            "Remember to register for exams on time",
            "Check out all the different food the restaurants have to offer!",
            "Psychology is 110% wording"));

    /**
     * Initialize the view with a random daily tip.
     */
    @FXML
    void initialize() {
        dailyTip.setText("Daily tip: " + getTip(tips));
    }

    /**
     * The logic for when the user indicates they already have an account. Navigates the user to the login scene.
     * @throws IOException if resources are not found on the client.
     */
    @FXML
    void loginClicked(ActionEvent event) throws IOException {
        MainController main = new MainController();
        String scene = "/MainLoginScene.fxml";
        main.sceneChange(event, scene);
    }

    /**
     * The logic for when the 'Sign up' button is clicked. Data is captured, validated on the client and server,
     * and if checks pass, the user is navigated back to the login scene.
     * @throws IOException if resources are not found on the client.
     */
    @FXML
     void signupClicked(ActionEvent event) throws IOException {
        String [] signupData = captureSignupData();
        String enteredUsername = signupData[0];
        String enteredPassword = signupData[1];
        String enteredCPassword = signupData[2];

        if (clientValidateSingupData(enteredUsername, enteredPassword, enteredCPassword)) {
            RequestHelper.setUsername(enteredUsername);
            String signUp = ServerCommunication.doSignUp(enteredUsername, enteredPassword);
            if (signUp.equals("226")) {
                error.setText("Username already exists");
            } else {
                loginClicked(event); // upon success, navigate user to login screen
            }
        }
    }

    /**
     * Captures raw input data from sign up form.
     * @return a string array containing username and password
     */
    private String[] captureSignupData() {
        String enteredUsername = userName.getText();
        String enteredPassword = userPassword.getText();
        String enteredCPassword = confirmPassword.getText();
        return new String[]{enteredUsername, enteredPassword, enteredCPassword};
    }

    /**
     * Validates the user's entered information on the client side. The method makes sure the length of the username
     * and password are appropriate and that the passwords match. If an input error is detected, the red error label
     * on the form is updated.
     * @param enteredUsername the username string to validate
     * @param enteredPassword the password string to validate
     * @param enteredCPassword the confirmation password string to validate
     * @return a boolean representing whether the input is valid
     */
    private boolean clientValidateSingupData(String enteredUsername, String enteredPassword, String enteredCPassword) {
        if (enteredUsername.length() == 0) {
            error.setText("Please enter a username.");
            return false;
        } else if (enteredUsername.length() >= 100) {
            error.setText("Username cannot be longer than 100 characters.");
            return false;
        }
        if (enteredPassword.length() == 0) {
            error.setText("Please enter a password.");
            return false;
        } else if (enteredPassword.length() >= 100) {
            error.setText("Password cannot be longer than 100 characters.");
            return false;
        }
        if (enteredCPassword.length() == 0) {
            error.setText("Please confirm your password.");
            return false;
        }
        if (!enteredPassword.equals(enteredCPassword)) {
            error.setText("The passwords don't match.");
            return false;
        }
        return true;
    }

    /**
     * Gets a random tip from the list.
     * @param tipsList a list of strings
     * @return a random tip
     */
    private String getTip(ArrayList<String> tipsList) {
        int randomIndex = (int) (Math.random() * tipsList.size());
        return tipsList.get(randomIndex);
    }
}
