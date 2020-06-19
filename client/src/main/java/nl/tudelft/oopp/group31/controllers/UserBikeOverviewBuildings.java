package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.Building;


public class UserBikeOverviewBuildings extends UserHelperController {


    @FXML
    protected Button hamburgerButton;

    @FXML
    FlowPane buildingParent;
    
    @FXML
    AnchorPane showUp;

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
        for (Building b : buildingArray) { //Creates a buildingCard and fills it with the corresponding building's data
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserBikeBuildingCard.fxml"));
            HBox singleBuilding = null;
            try {
                singleBuilding = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UserBikeBuildingController bcc = loader.getController();
            bcc.populate(b); //Fills the card with the data contained in "b"
            buildingElementArray.add(singleBuilding);
        }
        showUp.setVisible(true);
        buildingParent.getChildren().clear();
        buildingParent.getChildren().addAll(buildingElementArray);
    }

    @FXML
    String fetchBuildings() {
        return ServerCommunication.getAllBuildings();
    }

}