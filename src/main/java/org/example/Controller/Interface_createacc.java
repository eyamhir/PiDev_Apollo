package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.Models.Utilisateur;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Interface_createacc{

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField numTelField;

    @FXML
    private DatePicker dateNaissancePicker;

    @FXML
    private PasswordField passwordField;

    @FXML
    void createAccount(ActionEvent event) {
        // Récupérer les valeurs des champs de texte et des autres contrôles
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String numTel = numTelField.getText();
        // Convertir la date de naissance en java.util.Date
        LocalDate dateNaissance = Date.valueOf(dateNaissancePicker.getValue()).toLocalDate();

        // Vous pouvez également valider les données saisies par l'utilisateur ici

        // Créer un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setAdresse_mail(email);
        utilisateur.setNum_tel(Integer.parseInt(numTel));
        utilisateur.setDate_naissance(dateNaissance);
        utilisateur.setMot_passe(passwordField.getText());

        // Maintenant, vous pouvez enregistrer cet utilisateur dans votre base de données ou effectuer toute autre action requise
        // Code pour enregistrer l'utilisateur dans la base de données
        // UtilisateurDAO.save(utilisateur);
        System.out.println("Utilisateur créé avec succès !");
    }
}


