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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class userLoginController {

    @FXML
    private TextField emailFT;

    @FXML
    private PasswordField passwordFT;
    @FXML
    private Button LoginFT;
    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();

    // Hashage MD5
    public static String doHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @FXML
    private void LogInAction() {
        String adresse_mail = emailFT.getText();
        String mot_passe = passwordFT.getText();
        boolean isAdmin = adresse_mail.equals("eya@esprit.tn") && mot_passe.equals("0000");

        // Check if email and password fields are empty
        if (adresse_mail.isEmpty() || mot_passe.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter email and password.");
            return;
        }

        // Authenticate the user
        boolean authenticated = serviceUtilisateur.connecter(adresse_mail,doHashing(mot_passe));

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

    public void pwdOublier(ActionEvent actionEvent) {
        try {
            // Load the Adduser.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/CheckMailInterface.fxml"));
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
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load forgot password interface.");
        }
    }
}
