package tn.esprit.projet_java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.services.EnchersService;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.sql.SQLException;


public class ModifierEncheres {

    @FXML
    private DatePicker date_debutTF;

    @FXML
    private DatePicker date_finTF;

    @FXML
    private TextField min_montantTF;

    @FXML
    private TextField typeTF;
    private final EnchersService enchersService = new EnchersService();
    private Enchers Encher; // To store the selected Enchers object

    public void initData(Enchers Encher) {
        // This method is called to initialize the modification interface with the selected Enchers data
       // this.Encher = selectedEncher;
        this.Encher = Encher;
        // Populate UI elements with the data from the selected Enchers
        typeTF.setText(Encher.getType_oeuvre());
        min_montantTF.setText(String.valueOf(Encher.getMin_montant()));

        // Populate other UI elements as needed...
    }
    @FXML
    void naviguer(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterEncheres.fxml"));
            // Stage window =(Stage)list.getScene().getWindow();
            // window.setScene(new Scene(root));
            date_finTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void saveChanges(ActionEvent event) {
        try {
            if (allFieldsFilled()) {
                // Créer un objet Utilisateur avec le constructeur approprié
              /*  Enchers en= new Enchers(
                        selectedEncher.getId_enchers(),
                         date_debutTF.getValue(),
                         date_finTF.getValue(),
                       // min_montantTF.,
                        Double.parseDouble(min_montantTF.getText()),
                        typeTF.getText()*/

                Enchers en = new Enchers(
                       /* Encher.getId_enchers(),
                        Float.parseFloat(min_montantTF.getText()),
                        date_debutTF.getValue(),
                        date_finTF.getValue(),

                        // Assuming min_montantTF holds a numeric value
                        typeTF.getText()*/
                        Encher.getId_enchers(),
                        Float.parseFloat(min_montantTF.getText()),
                        date_debutTF.getValue(),
                        date_finTF.getValue(),
                        typeTF.getText()
                );

                // Appeler la méthode du service pour modifier l'utilisateur
                enchersService.modifier(en);


                // Afficher une boîte de dialogue de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("L'encher a été modifié avec succès.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Gérer les erreurs de connexion à la base de données ou autres erreurs SQL
            e.printStackTrace();
            displayErrorDialog("Une erreur s'est produite lors de la modification de l'encher.");
        } catch (NumberFormatException e) {
            // Gérer les erreurs de format de nombre
            e.printStackTrace();
            displayErrorDialog("Le numéro de téléphone doit être un nombre entier.");
        }




    }

    private void displayErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode de validation pour vérifier si tous les champs sont remplis
    private boolean allFieldsFilled() {
        if (date_debutTF.getValue() == null ||
                date_finTF.getValue() == null ||
                min_montantTF.getText().isEmpty() ||
                typeTF.getText().isEmpty()) {

            // Display an error dialog indicating the required fields
            displayErrorDialog("Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        return true;
    }

}


