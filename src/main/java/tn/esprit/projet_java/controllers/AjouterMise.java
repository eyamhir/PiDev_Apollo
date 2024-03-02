package tn.esprit.projet_java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.projet_java.models.*;
import javafx.scene.control.Alert;
import tn.esprit.projet_java.services.EnchersService;
import tn.esprit.projet_java.services.MiseService;
import tn.esprit.projet_java.models.GMailer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.EventObject;

public class AjouterMise {
    @FXML
    private TextField max_montantTF;
    private final MiseService ms = new MiseService();
   // private final GMailer gMailer;
   private final GMailer gMailer;


    // Méthode pour afficher les messages d'erreur
    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }



    public AjouterMise() {
        try {
            gMailer = new GMailer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void ajouter_mise(ActionEvent actionEvent) {
        try {
            // Vérifier si le champ max_montantTF est rempli
            if (max_montantTF.getText().isEmpty()) {
                // Afficher un message d'erreur si le champ est vide
                afficherErreur("Veuillez saisir un montant.");
                return;  // Sortir de la méthode si le champ est vide
            }

            // Vérifier si le contenu du champ max_montantTF est un nombre valide
            if (!max_montantTF.getText().matches("\\d+(\\.\\d+)?")) {
                // Afficher un message d'erreur si le contenu n'est pas un nombre valide
                afficherErreur("Veuillez saisir un montant valide.");
                return;  // Sortir de la méthode si le contenu n'est pas valide
            }

            // Récupérer et convertir le montant saisi en double
            double maxMontant = Double.parseDouble(max_montantTF.getText());

            // Ajouter des conditions pour vérifier le montant
            if (maxMontant >= 0) {
                // Instancier et ajouter l'enchère à votre service ou gestionnaire d'enchères (es)
                Mise mise = new Mise(maxMontant, 2, 2);
                // Afficher un message de succès ou autre si nécessaire
                ms.ajouter(mise);
                // Envoyer un e-mail après l'ajout avec succès
                String subject = "Nouvelle Mise Ajoutée";
                String message = "Une nouvelle mise a été ajoutée avec succès. Montant : " + maxMontant;
                gMailer.sendMail(subject, message);
            } else {
                // Afficher un message d'erreur si le montant est négatif
                afficherErreur("Veuillez saisir un montant valide positif.");
            }
           // Mail mail = new Mail();
           // mail.validermise(max_montantTF.getTypeSelector());
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion de texte en nombre
            afficherErreur("Veuillez saisir un montant valide.");
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            afficherErreur("Erreur SQL : " + e.getMessage());


        } catch (Exception e) {
            // Gérer les autres exceptions
            afficherErreur("Une erreur s'est produite. Détails : " + e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierMise.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée (par exemple, la journaliser)
        }
    }

    public void annuler_mise(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficheEnchere.fxml"));
            // Stage window =(Stage)list.getScene().getWindow();
            // window.setScene(new Scene(root));
            max_montantTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


