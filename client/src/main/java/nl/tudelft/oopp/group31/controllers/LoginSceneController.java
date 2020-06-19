package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
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
import nl.tudelft.oopp.group31.entities.User;

public class LoginSceneController {
    @FXML
    private TextField userName, userPassword;
    
    @FXML
    private Label error;

    @FXML
    private Text dailyTip;

    public static String netID;
    public static int userType;
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
     * The logic for when the user indicates they don't have an account. Navigates the user to the sign up scene.
     * @throws IOException if resources are not found on the client.
     */
    @FXML
    void signUpClicked(ActionEvent event) throws IOException {
        MainController main = new MainController();
        String scene = "/MainSignupScene.fxml";
        main.sceneChange(event, scene);
    }

    /**
     * The logic for when the 'Log in' button is clicked. Data is captured, validated on the client and server, and
     * if checks pass, the user is navigated to the appropriate scene.
     * @throws IOException if resources are not found on the client.
     */
    @FXML
    void loginClicked(ActionEvent event) throws IOException {
        String[] loginData = captureLoginData();
        String enteredUsername = loginData[0];
        String enteredPassword = loginData[1];

        if (clientValidateLoginData(enteredUsername, enteredPassword)) {
            RequestHelper.setUsername(userName.getText());
            RequestHelper.setPassword(userPassword.getText());
            String serverMessage = ServerCommunication.sendAuth();

            if (!serverMessage.equals("401")) { // login successful
                Gson gson = new Gson();
                User user = gson.fromJson(serverMessage, User.class);  // get user data from server
                RequestHelper.setType(user.getType());
                userType = user.getType();
                netID = enteredUsername; // username can now be accessed outside the controller

                MainController main = new MainController();
                String scene = getNextSceneByUserType(userType);
                if (!scene.equals("")) { // empty string = unrecognized user type
                    main.sceneChange(event, scene);
                }
            } else {
                resetLoginForm();
                error.setText("Wrong credentials");
            }
        } else {
            resetLoginForm();
            error.setText("Wrong credentials");
        }
    }

    /**
     * Captures raw input data from login form.
     * @return a string array containing username and password
     */
    private String[] captureLoginData() {
        String enteredUsername = userName.getText();
        String enteredPassword = userPassword.getText();
        return new String[]{enteredUsername, enteredPassword};
    }

    /**
     * Validates the user's entered information on the client side. The method makes sure the length of the username
     * and password are appropriate. If an input error is detected, the red error label on the form is updated.
     * @param enteredUsername the username string to validate
     * @param enteredPassword the password string to validate
     * @return a boolean representing whether the input is valid
     */
    private boolean clientValidateLoginData(String enteredUsername, String enteredPassword) {
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
        return true;
    }

    /**
     * Gets the next scene to be shown to the user based on their type.
     * @param userType user's numerical type, denoting access level
     * @return next scene based on userType, empty string if userType is unrecognized
     */
    private String getNextSceneByUserType(int userType) {
        if (userType == 1) {
            return "/AdminHomePage.fxml";
        } else if ((userType == 2 || userType == 3)) {
            return "/UserCalendarView.fxml";
        }
        return "";
    }

    /**
     * Resets username and password fields in the login form.
     */
    private void resetLoginForm() {
        userName.setText("");
        userPassword.setText("");
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

    /**
     * Getter for user's username (= netID).
     * @return user's username
     */
    public static String getUserName() {
        return netID;
    }

    /**
     * Getter for user's type.
     * @return user's type
     */
    public static int getUserType() {
        return userType;
    }

    /**
     * Setter for user's type.
     * @param type user's numerical type (1 - 3), denoting access level in the application
     */
    public static void setUserType(int type) {
        userType = type;
    }

}
