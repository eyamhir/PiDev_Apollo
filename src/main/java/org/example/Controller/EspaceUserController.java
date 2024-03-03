package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Models.Utilisateur;
import org.example.Services.Service_Utilisateur;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class EspaceUserController {

        @FXML
        private TextField nomL;

        @FXML
        private TextField prenomL;

        @FXML
        private TextField mailL;

        @FXML
        private DatePicker dateNaissancePicker;

        @FXML
        private TextField mdpL;
        private Utilisateur utilisateur; // L'utilisateur dont les informations doivent être affichées
        private Service_Utilisateur serviceUtilisateur = new Service_Utilisateur(); // Service pour récupérer les informations de l'utilisateur

        // Méthode pour initialiser les données de l'utilisateur
        public void initData(Utilisateur utilisateur) {
            this.utilisateur = utilisateur;
            if (utilisateur != null) {
                nomL.setText(utilisateur.getNom());
                prenomL.setText(utilisateur.getPrenom());
                mailL.setText(utilisateur.getAdresse_mail());
                dateNaissancePicker.setValue(utilisateur.getDate_naissance());
                mdpL.setText(utilisateur.getMot_passe());
            }
        }

        // Méthode pour mettre à jour les informations de l'utilisateur
        @FXML
        void updateUser(ActionEvent event) {
            // Récupérer les nouvelles valeurs des champs de texte
            String newNom = nomL.getText();
            String newPrenom = prenomL.getText();
            String newEmail = mailL.getText();
            LocalDate newDate = dateNaissancePicker.getValue();
            String newPwd = mdpL.getText();
            // Créer un nouvel objet Utilisateur avec les nouvelles valeurs
            Utilisateur updatedUtilisateur = new Utilisateur();
            updatedUtilisateur.setId_utilisateur(utilisateur.getId_utilisateur());
            updatedUtilisateur.setNom(newNom);
            updatedUtilisateur.setPrenom(newPrenom);
            updatedUtilisateur.setAdresse_mail(newEmail);
            updatedUtilisateur.setDate_naissance(newDate);
            updatedUtilisateur.setMot_passe(newPwd);
            // Appeler le service pour mettre à jour les informations de l'utilisateur
            try {
                serviceUtilisateur.mettreAJourUtilisateur2(updatedUtilisateur);
                // Afficher un message de succès si la mise à jour a réussi
                showAlert(Alert.AlertType.INFORMATION, "Success", "User information updated successfully!");
            } catch (SQLException e) {
                // Afficher un message d'erreur si la mise à jour a échoué
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update user information!");
            }
        }
        // Méthode utilitaire pour afficher une boîte de dialogue d'alerte
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
                Parent root = FXMLLoader.load(getClass().getResource("/FXML_files/Home.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
            }
        }
    }

