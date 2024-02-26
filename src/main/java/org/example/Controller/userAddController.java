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

public class userAddController {

    @FXML
    private DatePicker dateinscriptionPicker;

    @FXML
    private DatePicker dateNaissancePicker;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField numTelTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private ChoiceBox<String> roleTF;

    @FXML
    private TextField prenomTF;

    @FXML
    private CheckBox termsCheckBox;

    @FXML
    private TextField SpecialiteartistiqueTF;

    @FXML
    private TextField adresseLocaleTF; // Ajout du champ d'adresse locale

    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();

   /* @FXML
    void initialize() {
        // Initialisez votre ChoiceBox avec les rôles prédéfinis
        roleTF.getItems().addAll("Admin", "Artiste", "Client");
    }*/

    @FXML
    void acceptCond(ActionEvent event) {
        if (!termsCheckBox.isSelected()) {
            // Afficher une boîte de dialogue informant l'utilisateur d'accepter les termes et conditions
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez accepter les termes et conditions avant de créer un compte.");

            // Ajouter un bouton OK personnalisé à la boîte de dialogue
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            alert.showAndWait();

            // Optionnellement, vous pouvez remettre le focus sur la case à cocher
            termsCheckBox.requestFocus();
        }
    }

    @FXML
    void createAccount(ActionEvent event) {
        try {
            if (termsCheckBox.isSelected() && allFieldsFilled()) {
                // Créer un objet Utilisateur avec le bon constructeur
                Utilisateur utilisateur = new Utilisateur(
                        nomTF.getText(),
                        prenomTF.getText(),
                        emailTF.getText(),
                        Integer.parseInt(numTelTF.getText()),
                        dateNaissancePicker.getValue(),
                        dateinscriptionPicker.getValue(),
                        SpecialiteartistiqueTF.getText(),
                        adresseLocaleTF.getText(), // Utiliser le champ d'adresse locale
                        roleTF.getValue(),
                        passwordTF.getText()//  isActive est toujours true lors de la création d'un compte
                );

                // Appeler la méthode du service pour créer l'utilisateur
                serviceUtilisateur.creerUtilisateurUI(utilisateur);
                System.out.println("Compte créé avec succès !");

                // Affichage de l'alerte de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("L'utilisateur a été ajouté avec succès.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Gérer l'exception SQL
            e.printStackTrace();
            displayErrorDialog("Une erreur SQL s'est produite lors de la création de l'utilisateur.");
        } catch (NumberFormatException e) {
            // Gérer l'exception de format de nombre
            e.printStackTrace();
            displayErrorDialog("Le numéro de téléphone doit être un nombre entier.");
        } catch (IllegalArgumentException e) {
            // Gérer l'exception d'argument illégal
            e.printStackTrace();
            displayErrorDialog("Une erreur s'est produite lors de la création de l'utilisateur.");
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
                roleTF.getValue() == null || // Vérifier si une valeur est sélectionnée dans la ChoiceBox
                dateinscriptionPicker.getValue() == null ||
                adresseLocaleTF.getText().isEmpty()) { // Vérifier si le champ d'adresse locale est rempli

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
