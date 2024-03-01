package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.example.Models.Utilisateur;
import org.example.Services.Service_Utilisateur;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class newPassword {
    @FXML
    private Button backBT;

    @FXML
    private Button confirmBT;

    @FXML
    private PasswordField newpassword;

    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();
    private String adresse_mail;

    public void initData(String adresse_mail) {
        this.adresse_mail = adresse_mail;
    }

    @FXML
    void confirm(ActionEvent event) {
        try {
            // Vérifier d'abord si l'e-mail existe
            if (!serviceUtilisateur.findEmail(adresse_mail)) {
                showAlert(Alert.AlertType.ERROR, "Error", "User not found.");
                return; // Sortir de la méthode si l'e-mail n'existe pas
            }

            // L'e-mail existe, continuer avec la réinitialisation du mot de passe
            Utilisateur u = serviceUtilisateur.getUserByEmail(adresse_mail);
            String hashedPassword = hashPassword(newpassword.getText());
            u.setMot_passe(hashedPassword);
            //serviceUtilisateur.mettreAJourUtilisateur(u);
            serviceUtilisateur.updatePassword(u.getId_utilisateur(), hashedPassword); // Call updatePassword method
            showAlert(Alert.AlertType.INFORMATION, "Success", "Password updated successfully.");
            closeCurrentWindow();
            openSignInWindow();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private void closeCurrentWindow() {
        Stage stage = (Stage) newpassword.getScene().getWindow();
        stage.close();
    }

    private void openSignInWindow() {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML_files/userSigninInterface.fxml"));
            primaryStage.setTitle("Connectez");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userSigninInterface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load user signin interface.");
            e.printStackTrace();
        }
    }
}
