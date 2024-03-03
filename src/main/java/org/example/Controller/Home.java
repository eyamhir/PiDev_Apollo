package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.Models.Utilisateur;

import java.io.IOException;

public class Home {

    private Utilisateur loggedInUser; // Variable to store the logged-in user

    // Setter method for loggedInUser
    public void setLoggedInUser(Utilisateur utilisateur) {
        this.loggedInUser = utilisateur;
    }
    public void LogOut(ActionEvent actionEvent) {
        try {
            // Close the current stage (Home stage)
            Node source = (Node) actionEvent.getSource();
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
    void ProfileBtn(ActionEvent event) {
        try {
            // Load the Profile.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/EspaceUserInterface.fxml"));
            Parent root = loader.load();
            EspaceUserController controller = loader.getController();
            controller.initData(loggedInUser);
            // Create a new stage for the profile interface
            Stage profileStage = new Stage();
            profileStage.setScene(new Scene(root));
            profileStage.setTitle("Profile");
            profileStage.show();

            // Close the current stage (home interface)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Profile interface.");
        }
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
