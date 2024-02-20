package tn.esprit.projet_java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.services.EnchersService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AjouterEncheres {
    @FXML
    private DatePicker date_debutTF;
    @FXML
    private DatePicker date_finTF;

    @FXML
    private TextField min_montantTF;

    @FXML
    private TextField typeTF;
    private final EnchersService es = new EnchersService();


@FXML
void ajouter_enchers(ActionEvent event) {
    try {
        float minMontant = Float.parseFloat(min_montantTF.getText());
        LocalDate dateDebut = date_debutTF.getValue();
        LocalDate dateFin = date_finTF.getValue();
        String type = typeTF.getText();

        // Ajouter des conditions pour vérifier le type
        if (type != null && !type.isEmpty() && type.matches("[a-zA-Z]+")) {
            // Ajouter des conditions pour vérifier les dates
            if (dateDebut != null && dateFin != null && !dateFin.isBefore(dateDebut)) {
                // Ajouter des conditions pour vérifier le montant
                if (minMontant >= 0) {
                    // Ajouter une condition pour s'assurer que la date de début est aujourd'hui ou ultérieure
                    if (dateDebut.isEqual(LocalDate.now()) || dateDebut.isAfter(LocalDate.now())) {
                        // Instancier et ajouter l'enchère à votre service ou gestionnaire d'enchères (es)
                        Enchers enchers = new Enchers(minMontant, dateDebut, dateFin, type);
                        es.ajouter(enchers);
                    } else {
                        // Afficher un message d'erreur si la date de début est antérieure à aujourd'hui
                        afficherErreur("La date de début doit être aujourd'hui ou ultérieure.");
                    }
                } else {
                    // Afficher un message d'erreur si le montant est négatif
                    afficherErreur("Veuillez saisir un montant valide positif.");
                }
            } else {
                // Afficher un message d'erreur si les dates sont invalides
                afficherErreur("Veuillez saisir des dates valides (la date de début doit être avant la date de fin).");
            }
        } else {
            // Afficher un message d'erreur si le type contient des chiffres, des symboles ou est vide
            afficherErreur("Veuillez saisir un type valide contenant uniquement des lettres.");
        }

    } catch (SQLException e) {
        // Gérer les erreurs SQL
        afficherErreur(e.getMessage());
    } catch (NumberFormatException e) {
        // Gérer les erreurs de conversion de texte en nombre
        afficherErreur("Veuillez saisir un montant valide.");
    }
}

    // Méthode pour afficher les messages d'erreur
    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }





    public void naviguer(ActionEvent Event) {


        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherEncheres.fxml"));
           // Stage window =(Stage)list.getScene().getWindow();
           // window.setScene(new Scene(root));
            date_finTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        }

    }


