package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Services.Service_Utilisateur;

import java.io.IOException;

public class userLoginController {

    @FXML
    private TextField emailFT;

    @FXML
    private PasswordField passwordFT;
    @FXML
    private Button LoginFT;
    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();

    @FXML
    private void LogInAction() {
        String email = emailFT.getText();
        String password = passwordFT.getText();
        boolean isAdmin = email.equals("eya.mhir@esprit.tn") && password.equals("eya123");
// Check if email and password fields are empty
        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter email and password.");
            return;
        }

        // Authenticate the user
        boolean authenticated = serviceUtilisateur.connecter(email, password);

        // Check if authentication was successful
        if (authenticated) {
            // Successful login logic (navigate to the next scene, etc.)
            System.out.println("Login successful!");
            try {
                String fxmlFile = isAdmin ? "/FXML_files/BackSigninInterface.fxml" : "/FXML_files/Home.fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Stage stage = (Stage) LoginFT.getScene().getWindow(); // Get the current stage
                stage.setScene(new Scene(root));
                stage.setTitle(isAdmin ? "Admin" : "Home");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Display an error message for invalid credentials
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid email or password.");
        }
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


        public void SignUp (ActionEvent actionEvent){
            try {
                // Load the Adduser.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userAddInterface.fxml"));
                Parent root = loader.load();

                // Create a new stage for the Adduser interface
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Sign Up");

                // Show the Adduser stage
                stage.show();
            } catch (IOException e) {
                // Handle any potential IOException
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Sign Up interface.");
            }
        }

    }
