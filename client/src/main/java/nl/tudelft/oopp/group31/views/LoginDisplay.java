package nl.tudelft.oopp.group31.views;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginDisplay extends Application {

    /**
     * The first screen every user sees.
     *
     * @param args an optional array of arguments passed on by {@link nl.tudelft.oopp.group31.MainApp}.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Setup the primary stage and load the login scene, stylesheet, favicon and title.
     *
     * @param primaryStage A {@link Stage} object on which the login scene and all subsequent scenes are shown.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL xmlUrl = getClass().getResource("/MainLoginScene.fxml");
            loader.setLocation(xmlUrl);
            Parent root = loader.load();
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/stylesheets/style.css").toExternalForm());
            primaryStage.setTitle("Campus Management Software");
            Image icon = new Image("images/icon.png");
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
