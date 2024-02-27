package tn.esprit.projet_java.controllers;
/*
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.projet_java.utils.MaConnecxion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AfficherEnchere {

    @FXML
    private Button button;

    @FXML
    private TextField id;

    @FXML
    private Label id_image;

    private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;

    public AfficherEnchere() {
        cnx = MaConnecxion.getInstance().getCnx();
    }

    @FXML
    void afficher_image(ActionEvent event) {
        System.out.println("Bouton cliqué. ID saisi : " + id.getText());

        try {
            String query = "SELECT image FROM enchers WHERE id_enchers = ?";
            pst = cnx.prepareStatement(query);
            pst.setString(1, id.getText());
            rs = pst.executeQuery();

            while (rs.next()) {
                String imageUrl = rs.getString("image");
                System.out.println("URL de l'image récupérée : " + imageUrl);

                // Set the image URL to the Label
                id_image.setText(imageUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'affichage de l'image : " + e.getMessage());
        }
    }
}*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.projet_java.utils.MaConnecxion;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AfficherEnchere {

    @FXML
    private Button button;

    @FXML
    private TextField id;

    @FXML
    private ImageView id_imagee;

    private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;

    public AfficherEnchere() {
        cnx = MaConnecxion.getInstance().getCnx();
    }

    @FXML
    void afficher_image(ActionEvent event) {
        System.out.println("Bouton cliqué. ID saisi : " + id.getText());

        try {
            String query = "SELECT image FROM enchers WHERE id_enchers = ?";
            pst = cnx.prepareStatement(query);
            pst.setString(1, id.getText());
            rs = pst.executeQuery();

            while (rs.next()) {
                String imagePath = rs.getString("image");
                System.out.println("Chemin de l'image récupéré : " + imagePath);

                // Load the image from file path
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());

                // Set the image to the ImageView
                id_imagee.setImage(image);

                System.out.println("Affichage de l'image réussi.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'affichage de l'image : " + e.getMessage());
        }
    }
}





