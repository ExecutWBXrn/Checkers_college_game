package org.example.chessgame;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class winner_black extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(winner.class.getResource("winner_black.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
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

    public void set_image(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(winner.class.getResource("winner_black.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage2 = new Stage();
            stage2.setTitle("Chess");
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.setScene(scene);
            stage2.show();
        } catch (Exception e){};
    }
}