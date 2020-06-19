package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.Building;

public class UserOverviewBuildingsController extends UserHelperController {

    private static boolean clickedButton = false;

    @FXML
    protected Button hamburgerButton;

    @FXML
    TextField buildingName, buildingId, numberOfRooms, openingHour, openingMinute, closingHour, closingMinute;

    @FXML
    TextArea buildingDescription;

    @FXML
    FlowPane buildingParent;

    @FXML
    AnchorPane showUp;
    @FXML
    ScrollPane scrollPane;

    @Override
    public void initialize() {
        super.initialize();
        rootPane.setLeft(hamburgerMenu);

        String json = fetchBuildings();
        Gson gson = new Gson();
        Type buildingList = new TypeToken<ArrayList<Building>>() {
        }.getType();
        ArrayList<Building> buildingArray = gson.fromJson(json, buildingList);

        ArrayList<HBox> buildingElementArray = new ArrayList<>();
        for (Building b : buildingArray) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserBuildingCard.fxml"));
            HBox singleBuilding = null;
            try {
                singleBuilding = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BuildingCardController bcc = loader.getController();
            bcc.populate(b);
            buildingElementArray.add(singleBuilding);
        }
        showUp.setVisible(true);
        buildingParent.getChildren().clear();
        buildingParent.getChildren().addAll(buildingElementArray);

        try {
            if (clickedButton) {
                showUp.getChildren().clear();
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/UserRoomFilterDialog.fxml"));
                showUp.getChildren().setAll(pane);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        clickedButton = false;
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    }

    /**
     * Clciking the button.
     *
     * @param event rightclick
     * @throws IOException exception
     */
    @FXML
    void buttonClicked(ActionEvent event) throws IOException {
        clickedButton = true;
        main.sceneChange(event, "/UserOverviewBuildings.fxml");
    }

    /**
     * Method that does the Server Communication to fetch all the buildings in the server.
     *
     * @return All buildings from server.
     */
    @FXML
    String fetchBuildings() {
        return ServerCommunication.getAllBuildings();
    }

}
