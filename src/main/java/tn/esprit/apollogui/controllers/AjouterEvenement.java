package tn.esprit.apollogui.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import tn.esprit.apollogui.models.GMailer;
import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.services.EvenementService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class AjouterEvenement {
    @FXML

    private DatePicker Date_debutTF;

    @FXML
    private DatePicker Date_finTF;

    @FXML
    private TextField DescriptionTF;

    @FXML
    private TextField NomTF;

    @FXML
    private TextField TypeTF;


    private final GMailer gMailer;

    public AjouterEvenement() {
        try {
            gMailer = new GMailer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void AjouterEvenement(ActionEvent event) {
        EvenementService evenementService = new EvenementService();
        evenement ae = new evenement();
        ae.setNom(NomTF.getText());
        ae.setDescription(DescriptionTF.getText());
        ae.setType(TypeTF.getText());

        ae.setDate_debut(Date_debutTF.getValue());
        ae.setDate_fin(Date_finTF.getValue());



        try {
            evenementService.ajouter(ae);
            String subject = "New event added";
            String message = "Event added with success, Event Name: " + NomTF.getText();
            gMailer.sendMail(subject, message);

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Event added");
            alert.showAndWait();
        } catch (SQLException e) {
            // Show error alert for SQL exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception ex) {
            // Show error alert for other exceptions
            afficherErreur("Error");
        }
    }


    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }


   /* public void naviguer(ActionEvent Event) {


        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherEvenement.fxml"));
            // Stage window =(Stage)list.getScene().getWindow();
            // window.setScene(new Scene(root));
            TypeTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }*/


    public void naviguer(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/apollogui/AfficherEvenement.fxml"));
            NomTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
