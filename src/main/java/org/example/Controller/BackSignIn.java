package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.Models.Utilisateur;

import java.io.IOException;


public class BackSignIn {

    @FXML
    private Button CodePromo;

    @FXML
    private Button LogOut;

    @FXML
    private Button Utilisateur;
    private org.example.Models.Utilisateur loggedInUser; // Variable to store the logged-in user

    // Setter method for loggedInUser
    public void setLoggedInUser(Utilisateur utilisateur) {
        this.loggedInUser = utilisateur;
    }

    @FXML
    void LogOut(ActionEvent event) {
        try {
            // Close the current stage (Backend stage)
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();

            // Load the login.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userSigninInterface.fxml"));
            Parent root = loader.load();

            // Create a new stage for the login interface
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");

            // Show the login stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void Utilisateurs(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userShowInterface.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Utilisateurs");
            stage.show();

            // Close the current stage (Backend stage)
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Users.");
        }
    }


        private void showAlert(Alert.AlertType type, String title, String message) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }



    @FXML
    void codePromos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/AFFinterfaceCodePromo.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Liste des Codes Promos");
            stage.show();

            // Close the current stage (Backend stage)
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Promo.");
        }

    }

}
