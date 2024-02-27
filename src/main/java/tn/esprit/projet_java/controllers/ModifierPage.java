package tn.esprit.projet_java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.models.Mise;
import tn.esprit.projet_java.services.EnchersService;
import tn.esprit.projet_java.services.MiseService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.EventObject;


public class ModifierPage {

    @FXML
    private TextField max_montantTF;
    private final MiseService miseService = new MiseService();
    private Mise mise;


    public void setMise(Mise nmise) {
        mise = nmise;
    }


    public void initData(Mise mise) {
        // This method is called to initialize the modification interface with the selected Enchers data

        max_montantTF.setText(String.valueOf(mise.getMax_montant()));

        // Populate other UI elements as needed...
    }
    public void ajouter_mise(ActionEvent actionEvent) {
        try {
            if (allFieldsFilled()) {
                // Récupérer et convertir le max_montant saisi en double
                double maxMontant = Double.parseDouble(max_montantTF.getText());

                // Ajouter une condition pour vérifier que maxMontant est supérieur au max_montant précédent
                if (maxMontant > mise.getMax_montant()) {
                    Mise ms = new Mise(
                            mise.getId_mise(),
                            maxMontant
                    );

                    // Appeler la méthode du service pour modifier la mise
                    miseService.modifier(ms);

                    // Afficher une boîte de dialogue de confirmation
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("La mise a été modifiée avec succès.");
                    alert.showAndWait();
                } else {
                    // Afficher un message d'erreur si maxMontant n'est pas supérieur au max_montant précédent
                    displayErrorDialog("Veuillez saisir un montant supérieur à la mise précédente.");
                }
            }
        } catch (SQLException e) {
            // Gérer les erreurs de connexion à la base de données ou autres erreurs SQL
            e.printStackTrace();
            displayErrorDialog("Une erreur s'est produite lors de la modification de la mise.");
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion de texte en nombre
            displayErrorDialog("Veuillez saisir un montant valide.");
        }
    }

    // Méthode de validation pour vérifier si tous les champs sont remplis
    private boolean allFieldsFilled() {
        return !max_montantTF.getText().isEmpty();
    }

    private void displayErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

/*
    public void ajouter_mise(ActionEvent actionEvent) {

        try {
            if (allFieldsFilled()) {
                // Créer un objet Utilisateur avec le constructeur approprié
                double maxMontant = Double.parseDouble(max_montantTF.getText());

                Mise ms = new Mise(
                        mise.getId_mise(),
                        Double.parseDouble(max_montantTF.getText())
                );

                // Appeler la méthode du service pour modifier l'utilisateur
                //  enchersService.modifierUI(en);
                miseService.modifier(ms);
                System.out.println(ms);


                // Afficher une boîte de dialogue de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("La mise a été modifié avec succès.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Gérer les erreurs de connexion à la base de données ou autres erreurs SQL
            e.printStackTrace();
            displayErrorDialog("Une erreur s'est produite lors de la modification de la mise .");

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
        if (
                max_montantTF.getText().isEmpty()) {

            // Display an error dialog indicating the required fields
            displayErrorDialog("Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        return true;
    }*/

    public void annuler_mise(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherEncheres.fxml"));
            // Stage window =(Stage)list.getScene().getWindow();
            // window.setScene(new Scene(root));
            max_montantTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
