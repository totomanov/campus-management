package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.User;


public class AdminUsersManagerController extends AdminHelperController {

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, String> netID, type;

    @FXML
    private Label userName;

    @FXML
    private ChoiceBox<Integer> choiceBox;

    @FXML
    private AnchorPane save;

    private int userType;

    MainController main = new MainController();

    /**
     * Creates a List of all Reservations.
     *
     * @return An ArrayList of all Reservations
     */
    public ArrayList<User> createUserArray() {
        String json = ServerCommunication.getAllUsers();
        Gson gson = new Gson();
        Type userList = new TypeToken<ArrayList<User>>() {
        }.getType();
        return gson.fromJson(json, userList);
    }

    /**
     * Populates usersTable with user data.
     */
    @Override
    public void initialize() {
        super.initialize();
        rootPane.setLeft(hamburgerMenu);
        save.setVisible(false);
        addChoiceBoxItems();
        netID.setCellValueFactory(new PropertyValueFactory<>("netID"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        ArrayList<User> users = createUserArray();
        for (User u : users) {
            usersTable.getItems().add(u);
        }
    }

    /**
     * adds values to choicebox.
     */
    public void addChoiceBoxItems() {
        choiceBox.getItems().add(1);
        choiceBox.getItems().add(2);
        choiceBox.getItems().add(3);
    }

    /**
     * Sends a delete request to delete the selected user from the database.
     *
     * @param event Clicking the delete button
     */
    @FXML
    private void deleteUser(ActionEvent event) {
        User u = usersTable.getSelectionModel().getSelectedItem();
        if (u != null) {
            String headerText = "User account " + u.getNetID() + " will be permanently deleted.";
            boolean userConfirmation = confirmAlert(headerText, "Changes cannot be reverted."); //Gets confirmation from the admin
            if (userConfirmation) {
                ServerCommunication.deleteUser(u.getNetID());
                usersTable.getItems().remove(u);
            }
        }
    }

    /**
     * Sets username label with username on change clicked.
     * @param event mouse-click
     */
    @FXML
    private void changeDetails(ActionEvent event) {
        User u = usersTable.getSelectionModel().getSelectedItem();

        if (u != null) {
            userName.setText(u.getNetID());
            save.setVisible(true);
        }
    }

    /**
     * creates json of new user and sends to server to save.
     * @param event mouse-click
     */
    @FXML
    private void saveDetails(ActionEvent event) throws IOException {
        User u = usersTable.getSelectionModel().getSelectedItem();
        String json = "{\"netID\":\"" + u.getNetID()
                + "\", \"password\":\""
                + u.getPass() + "\", \"type\":\""
                + userType + "\", \"rentedBike\":\""
                + u.getRentedBike() + "\"}";
        ServerCommunication.changeUserDetails(json);
        main.sceneChange(event, "/AdminUsersManager.fxml");
    }

    @SuppressWarnings("unchecked")
    @FXML
    void typeChosen(ActionEvent event) {
        userType = (((ChoiceBox<Integer>) event.getSource()).getSelectionModel().getSelectedItem());
    }
}
