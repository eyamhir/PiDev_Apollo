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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


                // Vérification de la date d'inscription
                if (!dateinscriptionPicker.getValue().isEqual(LocalDate.now())) {
                    displayErrorDialog("La date d'inscription doit être la date actuelle.");
                    return;
                }




                // Créer un objet Utilisateur avec le bon constructeur
                Utilisateur utilisateur = new Utilisateur(
                        nomTF.getText(),
                        prenomTF.getText(),
                        emailTF.getText(),
                        Integer.parseInt(numTelTF.getText()),
                        dateNaissancePicker.getValue(),
                        LocalDate.now(), // Utiliser la date actuelle pour la date d'inscription
                        SpecialiteartistiqueTF.getText(),
                        adresseLocaleTF.getText(), // Utiliser le champ d'adresse locale
                        roleTF.getValue(),
                        doHashing(passwordTF.getText())//  isActive est toujours true lors de la création d'un compte
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userSigninInterface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }
}
