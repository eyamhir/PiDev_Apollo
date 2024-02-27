package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.Models.Utilisateur;

import java.io.IOException;

public class userDetailsController {

        @FXML
        private Label nomLabel;

        @FXML
        private Label prenomLabel;

        @FXML
        private Label adresseMailLabel;

        @FXML
        private Label numTelLabel;

        @FXML
        private Label dateNaissanceLabel;

        @FXML
        private Label dateInscriptionLabel;

        @FXML
        private Label specialiteArtistiqueLabel;

        @FXML
        private Label adresseLocaleLabel;

        @FXML
        private Label roleLabel;

        @FXML
        private Label motPasseLabel;

        public void initData(Utilisateur utilisateur) {
            nomLabel.setText(utilisateur.getNom());
            prenomLabel.setText(utilisateur.getPrenom());
            adresseMailLabel.setText(utilisateur.getAdresse_mail());
            numTelLabel.setText(String.valueOf(utilisateur.getNum_tel()));
            dateNaissanceLabel.setText(utilisateur.getDate_naissance().toString());
            dateInscriptionLabel.setText(utilisateur.getDate_inscription().toString());
            specialiteArtistiqueLabel.setText(utilisateur.getSpecialite_artistique());
            adresseLocaleLabel.setText(utilisateur.getAdresse_locale());
            roleLabel.setText(utilisateur.getRole());
            motPasseLabel.setText(utilisateur.getMot_passe());
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


