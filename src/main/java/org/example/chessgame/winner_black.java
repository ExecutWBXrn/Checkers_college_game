package org.example.chessgame;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class winner_black extends Application {
    public Button menu_btn;
    public Button restart_btn;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(winner.class.getResource("winner_black.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Checker winner");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public ImageView winner_img;

    @FXML
    void initialize() {
        menu_btn.setOnMouseClicked(mouseEvent -> {
            mouseEvent.getButton();
            try {
                // Завантажуємо нову сцену з файлу game_scene.fxml
                FXMLLoader fxmlLoader = new FXMLLoader(start_menu.class.getResource("start_menu.fxml"));
                Scene newScene = new Scene(fxmlLoader.load(), 1000, 600);

                // Отримуємо поточний Stage і встановлюємо нову сцену
                Stage currentStage = (Stage) menu_btn.getScene().getWindow();
                currentStage.setScene(newScene);
                currentStage.setFullScreen(true);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        restart_btn.setOnMouseClicked(mouseEvent -> {
            mouseEvent.getButton();
            try {
                // Завантажуємо нову сцену з файлу game_scene.fxml
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene newScene = new Scene(fxmlLoader.load(), 1000, 600);

                // Отримуємо поточний Stage і встановлюємо нову сцену
                Stage currentStage = (Stage) restart_btn.getScene().getWindow();
                currentStage.setScene(newScene);
                currentStage.setFullScreen(true);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}