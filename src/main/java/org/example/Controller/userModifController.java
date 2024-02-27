package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Models.Utilisateur;
import org.example.Services.Service_Utilisateur;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class userModifController {

    @FXML
    private DatePicker dateNaissancePicker;

    @FXML
    private DatePicker dateinscriptionPicker;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField numTelTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private TextField prenomTF;

    @FXML
    private ChoiceBox<String> roleTF;

    @FXML
    private TextField specialiteTF;

    @FXML
    private TextField adresseLocaleTF;

    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();

   /* @FXML
    void initialize() {
        // Initialisez votre ChoiceBox avec les rôles prédéfinis
        roleTF.getItems().addAll("Admin", "Artiste", "Client");
    }*/


    @FXML
    void modifierUtilisateur(ActionEvent event) {
        try {
            if (allFieldsFilled()) {
                // Vérification de l'adresse email
                if (!emailTF.getText().contains("@")) {
                    displayErrorDialog("L'adresse email doit contenir un @.");
                    return;
                }

                // Vérification du numéro de téléphone (8 chiffres)
                if (numTelTF.getText().length() != 8 || !numTelTF.getText().matches("\\d{8}")) {
                    displayErrorDialog("Le numéro de téléphone doit contenir exactement 8 chiffres.");
                    return;
                }


                // Vérification de la date d'inscription (doit être aujourd'hui)
                if (!dateinscriptionPicker.getValue().isEqual(LocalDate.now())) {
                    displayErrorDialog("La date d'inscription doit être la date actuelle.");
                    return;
                }



                // Créer un objet Utilisateur avec le constructeur approprié
                Utilisateur u = new Utilisateur(
                        utilisateur.getId_utilisateur(),
                        nomTF.getText(),
                        prenomTF.getText(),
                        emailTF.getText(),
                        Integer.parseInt(numTelTF.getText()),
                        dateNaissancePicker.getValue(),
                        LocalDate.now(), // Utiliser la date actuelle pour la date d'inscription
                        roleTF.getValue(),
                        adresseLocaleTF.getText(),
                        specialiteTF.getText(),
                        passwordTF.getText()

                        );


                // Appeler la méthode du service pour modifier l'utilisateur
                serviceUtilisateur.mettreAJourUtilisateur(u);

                // Afficher une boîte de dialogue de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("L'utilisateur a été modifié avec succès.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Gérer les erreurs de connexion à la base de données ou autres erreurs SQL
            e.printStackTrace();
            displayErrorDialog("Une erreur s'est produite lors de la modification de l'utilisateur.");
        } catch (NumberFormatException e) {
            // Gérer les erreurs de format de nombre
            e.printStackTrace();
            displayErrorDialog("Le numéro de téléphone doit être un nombre entier.");
        }
    }

    // Méthode de validation pour vérifier si tous les champs sont remplis
    private boolean allFieldsFilled() {
        if (dateNaissancePicker.getValue() == null ||
                emailTF.getText().isEmpty() ||
                nomTF.getText().isEmpty() ||
                numTelTF.getText().isEmpty() ||
                passwordTF.getText().isEmpty() ||
                prenomTF.getText().isEmpty() ||
                roleTF.getValue() == null ||
                dateinscriptionPicker.getValue() == null ||
                specialiteTF.getText().isEmpty() ||
                adresseLocaleTF.getText().isEmpty()) {

            // Afficher une boîte de dialogue d'erreur indiquant les champs requis
            displayErrorDialog("Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        return true;
    }

    // Méthode pour afficher une boîte de dialogue d'erreur avec un message personnalisé
    private void displayErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private Utilisateur utilisateur;
    public void initData(Utilisateur utilisateur) {
        this.utilisateur=utilisateur;
        if (utilisateur != null) {
            nomTF.setText(utilisateur.getNom());
            prenomTF.setText(utilisateur.getPrenom());
            emailTF.setText(utilisateur.getAdresse_mail());
            numTelTF.setText(String.valueOf(utilisateur.getNum_tel()));
            // Convertir java.sql.Date en LocalDate pour dateNaissancePicker
            if (utilisateur.getDate_naissance() != null) {
                LocalDate dateNaissance = utilisateur.getDate_naissance();
                dateNaissancePicker.setValue(dateNaissance);
            }
            // Convertir java.sql.Date en LocalDate pour dateinscriptionPicker
            if (utilisateur.getDate_inscription() != null) {
                LocalDate dateInscription = utilisateur.getDate_inscription();
                dateinscriptionPicker.setValue(dateInscription);
            }
            roleTF.setValue(utilisateur.getRole()); // Assurez-vous que roleTF est initialisé avec les choix appropriés
            adresseLocaleTF.setText(utilisateur.getAdresse_locale());
            specialiteTF.setText(utilisateur.getSpecialite_artistique());
            passwordTF.setText(utilisateur.getMot_passe());

        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userShowInterface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }
}
