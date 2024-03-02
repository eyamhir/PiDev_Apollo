package tn.esprit.projet_java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.services.EnchersService;
import tn.esprit.projet_java.utils.MaConnecxion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class AjouterEncheres extends Component {
    @FXML
    private DatePicker date_debutTF;
    @FXML
    private DatePicker date_finTF;

    @FXML
    private TextField min_montantTF;
    @FXML
    private TextField imageTF;
    private AfficheEnchere afficheEnchereController;
   // private TextField imageTF;

    @FXML
    private TextField typeTF;
    private final EnchersService es = new EnchersService();
    @FXML
    private Label txtimage;

    @FXML
    private TextField txtlien;
   // PreparedStatement pst;
  // ResultSet rs;





@FXML
void ajouter_enchers(ActionEvent event) {
    try {
        // Vérifier si tous les champs obligatoires sont remplis
        if (!allFieldsFilled()) {
            // Afficher un message d'erreur
            afficherErreur("Veuillez remplir tous les champs obligatoires.");
            return; // Sortir de la méthode si les champs ne sont pas tous remplis
        }

        // Continuer avec le reste du code si tous les champs sont remplis
        float minMontant = Float.parseFloat(min_montantTF.getText());
        LocalDate dateDebut = date_debutTF.getValue();
        LocalDate dateFin = date_finTF.getValue();
        String type = typeTF.getText();
        String image = txtlien.getText();

        // Ajouter des conditions pour vérifier le type
        if (type.matches("[a-zA-Z]+")) {
            // Ajouter des conditions pour vérifier les dates
            if (!dateFin.isBefore(dateDebut)) {
                // Ajouter des conditions pour vérifier le montant
                if (minMontant >= 0) {
                    // Ajouter une condition pour s'assurer que la date de début est aujourd'hui ou ultérieure
                    if (dateDebut.isEqual(LocalDate.now()) || dateDebut.isAfter(LocalDate.now())) {
                        // Instancier et ajouter l'enchère à votre service ou gestionnaire d'enchères (es)
                        Enchers enchers = new Enchers(minMontant, dateDebut, dateFin, type, image, 2);
                        // es.ajouterUI(enchers, 4, "C:/Users/LENOVO/Desktop/oumayma.jpg");
                        // Afficher un message de succès ou autre si nécessaire
                        es.ajouter(enchers);
                        // Load AfficheEncheres controller
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficheEnchere.fxml"));
                        Parent root = loader.load();
                        AfficheEnchere afficheEnchereController = loader.getController();

                        // Call the method to refresh the display in AfficheEncheres
                        afficheEnchereController.afficherenchers();

                        // Navigate back to AfficheEnchere
                        date_finTF.getScene().setRoot(root);

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
        afficherErreur("Erreur SQL : " + e.getMessage());
    } catch (NumberFormatException e) {
        // Gérer les erreurs de conversion de texte en nombre
        afficherErreur("Veuillez saisir un montant valide.");
    } catch (Exception e) {
        e.printStackTrace();
        // Gérer les autres exceptions
        afficherErreur("Une erreur s'est produite. Détails : " + e.getMessage());
        throw new RuntimeException(e);
    }
}

    // Méthode pour vérifier si tous les champs obligatoires sont remplis
    private boolean allFieldsFilled() {
        return date_debutTF.getValue() != null &&
                date_finTF.getValue() != null &&
                !min_montantTF.getText().isEmpty() &&
                !typeTF.getText().isEmpty();
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

/*
        @FXML
        void inserer_image(ActionEvent event) {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);

            File picture = fc.getSelectedFile();
            String path, NomFichier;
            NomFichier = picture.getAbsolutePath(); // Get the name of the selected file
            path = picture.getAbsolutePath(); // ici pour récupérer nom image
            txtlien.setText(NomFichier);

            BufferedImage img;
            try {
                img = ImageIO.read(fc.getSelectedFile());
                //ImageIcon imageIc = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(250, 200,250));


                   // txtimage.setIcon(imageIc); // afficher image choisie

            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
@FXML
void inserer_image(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Fichiers d'images", "*.png", "*.jpg", "*.jpeg", "*.gif")
    );

    File selectedFile = fileChooser.showOpenDialog(null);

    if (selectedFile != null) {
        String imagePath = selectedFile.getAbsolutePath();
        txtlien.setText(imagePath);

        try {
            BufferedImage img = ImageIO.read(selectedFile);
            // Process the image as needed...
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        // Handle the case when the file dialog is canceled
        System.out.println("File selection canceled");
    }
}


    @FXML
    void annuler(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficheEnchere.fxml"));
            // Stage window =(Stage)list.getScene().getWindow();
            // window.setScene(new Scene(root));
            date_debutTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
