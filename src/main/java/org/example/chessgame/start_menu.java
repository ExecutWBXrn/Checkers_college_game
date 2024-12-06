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
    @FXML
    private Button play_but;
    public Button bot_but;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(start_menu.class.getResource("start_menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Chess");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
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
    void initialize() {
        play_but.setOnMouseClicked(mouseEvent -> {
            mouseEvent.getButton();
            try {
                // Завантажуємо нову сцену з файлу game_scene.fxml
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene newScene = new Scene(fxmlLoader.load(), 1000, 600);

                // Отримуємо поточний Stage і встановлюємо нову сцену
                Stage currentStage = (Stage) play_but.getScene().getWindow();
                currentStage.setScene(newScene);
                currentStage.setFullScreen(true);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bot_but.setOnMouseClicked(mouseEvent -> {
            mouseEvent.getButton();
            try {
                // Завантажуємо нову сцену з файлу game_scene.fxml
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                fxmlLoader.setControllerFactory(type -> new HelloController(1));
                Scene newScene = new Scene(fxmlLoader.load(), 1000, 600);

                // Отримуємо поточний Stage і встановлюємо нову сцену
                Stage currentStage = (Stage) play_but.getScene().getWindow();
                currentStage.setScene(newScene);
                currentStage.setFullScreen(true);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}