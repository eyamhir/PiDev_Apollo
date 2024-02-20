package tn.esprit.apollogui.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.services.EvenementService;

import java.io.IOException;
import java.sql.SQLException;
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



    @FXML
    void AjouterEvenement(ActionEvent event) {
        EvenementService evenementService = new EvenementService();
        evenement ae = new evenement();
        ae.setNom(NomTF.getText());
        ae.setDescription(DescriptionTF.getText());
        ae.setType(TypeTF.getText());
        ae.setDate_debut(new Date());
        ae.setDate_fin(new Date());

        try {
            evenementService.ajouter(ae);
            Alert alert= new Alert((Alert.AlertType.CONFIRMATION));
            alert.setTitle("Success");
            alert.setContentText("Event added");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert= new Alert((Alert.AlertType.ERROR));
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


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
