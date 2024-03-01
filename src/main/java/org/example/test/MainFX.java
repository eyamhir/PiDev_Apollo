package org.example.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userAddInterface.fxml"));
        try {
            Parent root =loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("ajouter user");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/


        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userShowInterface.fxml"));
        try {
            Parent root =loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("afficher user");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userSigninInterface.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("SIGN IN");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

