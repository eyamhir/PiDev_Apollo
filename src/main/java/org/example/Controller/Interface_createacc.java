package org.example.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.example.Models.Utilisateur;
import org.example.Services.Service_Utilisateur;

import java.sql.SQLException;
import java.util.Date;

public class Interface_createacc {
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
    private TextField roleTF;


    @FXML
    private TextField prenomTF;

    @FXML
    private CheckBox termsCheckBox;

    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();

    @FXML
    void acceptCond(ActionEvent event) {
        if (!termsCheckBox.isSelected()) {
            // Display a dialog informing the user to accept the terms and conditions
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please accept the terms and conditions before creating an account.");

            // Add a custom OK button to the dialog
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);

            // Show the dialog and wait for user response
            alert.showAndWait();

            // Optionally, you can focus back on the checkbox
            termsCheckBox.requestFocus();
        }
    }
    @FXML
    void createAccount(ActionEvent event) {
        try {
            if (termsCheckBox.isSelected() && allFieldsFilled()) {
                // Convert LocalDate to Date
                Date dateNaissance = java.sql.Date.valueOf(dateNaissancePicker.getValue());

                // Parse String to int for numTelTF
                int numTel = Integer.parseInt(numTelTF.getText());

                // Create Utilisateur object with the correct constructor
                serviceUtilisateur.creerUtilisateur(new Utilisateur(nomTF.getText(), prenomTF.getText(), emailTF.getText(), Integer.parseInt(numTelTF.getText()), dateNaissancePicker.getValue(),dateinscriptionPicker.getValue(),roleTF.getText(),passwordTF.getText()));
                System.out.println("Account created successfully!");

                // Affichage de l'alerte de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("L'utilisateur a été ajoutée avec succès.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Gestion des erreurs
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }


    // Validation method to check if all fields are filled
    private boolean allFieldsFilled() {
        if (dateNaissancePicker.getValue() == null ||
                emailTF.getText().isEmpty() ||
                nomTF.getText().isEmpty() ||
                numTelTF.getText().isEmpty() ||
                passwordTF.getText().isEmpty() ||
                prenomTF.getText().isEmpty() ||
                roleTF.getText().isEmpty() ||
                dateinscriptionPicker.getValue() == null) {

            // Display an error dialog indicating the required fields
            displayErrorDialog("Please fill in all required fields.");
            return false;
        }
        return true;
    }

    // Method to display an error dialog with a custom message
    private void displayErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

    /////////////////////////////////////////////////////////////
   /* void createAccount(ActionEvent event) {
        try {
            dateNaissancePicker.getValue() == null
                    emailTF.getText().isEmpty()
                    nomTF.getText().isEmpty(
                    numTelTF.getText().isEmpty()
                    passwordTF.getText().isEmpty()
                    prenomTF.getText().isEmpty

            // Convertir la chaîne de date en objet Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Spécifiez le format de votre date
            Date dateFin = dateFormat.parse(dateFinText);

            // Instancier et ajouter l'enchère à votre service ou gestionnaire d'enchères (es)
            Enchers enchers = new Enchers(minMontant, dateFin, type);
            //  Enchers enchers = new Enchers( type_oeuvre, float min_montant, float max_montant, Date date_debut, Date date_fin, String artiste_associe_de_oeuvre));
            es.ajouter(enchers);

        }catch (SQLException  e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }}*/
