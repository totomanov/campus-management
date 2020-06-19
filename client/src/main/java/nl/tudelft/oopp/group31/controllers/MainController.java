package nl.tudelft.oopp.group31.controllers;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*

This main controller is just a common place for all controllers that would be needed to be written for all pages.
Also helps manage the number of imports needed.
To call the controllers of this class you have to first instantiate it with a constructor and then you can call the methods defined here.

*/
public class MainController {

    /**
     * Calling this will load up a new scene (the one you give in as a string), when
     * the ActionEvent event takes place.
     *
     * @param event IO event
     * @param scene name of the fxml file
     */
    void sceneChange(ActionEvent event, String scene) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource(scene);
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(new Scene(root));
        window.show();
    }
}