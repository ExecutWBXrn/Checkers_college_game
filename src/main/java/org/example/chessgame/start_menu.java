package org.example.chessgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class start_menu extends Application {
    static String[] args_in;
    Stage stage_of_start;
    @Override
    public void start(Stage stage) throws IOException {
        stage_of_start=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(start_menu.class.getResource("start_menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Chess");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        args_in=args;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button play_but;

    @FXML
    void initialize() {
        play_but.setOnMouseClicked(mouseEvent -> {
            mouseEvent.getButton();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                Stage stage2 = new Stage();
                stage2.setTitle("Chess");
                stage2.initStyle(StageStyle.DECORATED);
                stage2.setScene(scene);
                stage2.show();
                stage_of_start.hide();
            } catch (Exception e){};
        });
    }
}