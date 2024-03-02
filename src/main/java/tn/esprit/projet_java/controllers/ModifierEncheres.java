package tn.esprit.projet_java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.services.EnchersService;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;


public class ModifierEncheres {

    @FXML
    private DatePicker date_debutTF;

    @FXML
    private DatePicker date_finTF;

    @FXML
    private TextField min_montantTF;
    @FXML
    private TextField txtimageTF;

    @FXML
    private TextField typeTF;
    private final EnchersService enchersService = new EnchersService();
    private Enchers Encher;
    //private TextField imageTF;


    public void setEncher(Enchers encher) {
        Encher = encher;
    }
    // To store the selected Enchers object

    public void initData(Enchers Encher) {
        // This method is called to initialize the modification interface with the selected Enchers data
        // this.Encher = selectedEncher;
        // Populate UI elements with the data from the selected Enchers

        typeTF.setText(Encher.getType_oeuvre());
        min_montantTF.setText(String.valueOf(Encher.getMin_montant()));
        date_debutTF.setValue(Encher.getDate_debut());
        date_finTF.setValue(Encher.getDate_fin());
        txtimageTF.setText(Encher.getImage());

// Set the image path only if it's not null or empty
        String imagePath = Encher.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            txtimageTF.setText(imagePath);
        }
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
            if (Encher != null) {
            // Récupération des valeurs depuis les champs de saisie
            Date date_debut = java.sql.Date.valueOf(date_debutTF.getValue());
            Date date_fin = java.sql.Date.valueOf(date_finTF.getValue());
            String type = typeTF.getText();
            double minMontant = Double.parseDouble(min_montantTF.getText());
            String image = txtimageTF.getText();

            // Ajouter des conditions pour vérifier le type
            if (type != null && !type.isEmpty() && type.matches("[a-zA-Z]+")) {
                // Ajouter des conditions pour vérifier les dates
                if (date_debut != null && date_fin != null && !date_fin.before(date_debut)) {
                    // Ajouter des conditions pour vérifier le montant
                    if (minMontant >= 0) {
                        // Ajouter une condition pour s'assurer que la date de début est aujourd'hui ou ultérieure
                       // if (date_debut.toLocalDate().isEqual(LocalDate.now()) || date_debut.toLocalDate().isAfter(LocalDate.now())) {
                        if (((java.sql.Date) date_debut).toLocalDate().isEqual(LocalDate.now()) || ((java.sql.Date) date_debut).toLocalDate().isAfter(LocalDate.now())) {
                       // if (date_debutTF.getValue().isEqual(LocalDate.now()) || date_debutTF.getValue().isAfter(LocalDate.now())) {
                            // Créer un objet Enchers avec les données saisies
                            Enchers en = new Enchers(
                                    Encher.getId_enchers(),
                                    type,
                                    minMontant,
                                    date_debutTF.getValue(),
                                    date_finTF.getValue(),
                                    txtimageTF.getText()
                            );

                            // Appeler la méthode du service pour modifier l'encher
                            enchersService.modifier(en);

                            // Afficher une boîte de dialogue de confirmation
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText(null);
                            alert.setContentText("L'encher a été modifié avec succès.");
                            alert.showAndWait();
                        } else {
                            // Afficher un message d'erreur si la date de début est antérieure à aujourd'hui
                            displayErrorDialog("La date de début doit être aujourd'hui ou ultérieure.");
                        }
                    } else {
                        // Afficher un message d'erreur si le montant est négatif
                        displayErrorDialog("Veuillez saisir un montant valide positif.");
                    }
                } else {
                    // Afficher un message d'erreur si les dates sont invalides
                    displayErrorDialog("Veuillez saisir des dates valides (la date de début doit être avant la date de fin).");
                }
            } else {
                // Afficher un message d'erreur si le type contient des chiffres, des symboles ou est vide
                displayErrorDialog("Veuillez saisir un type valide contenant uniquement des lettres.");
            }
            } else {
                // Display an error dialog if Encher object is null
                displayErrorDialog("L'objet Encher est nul. Impossible de sauvegarder les modifications.");
            }
        }
    } catch (SQLException e) {
        // Gérer les erreurs SQL
        e.printStackTrace();
        displayErrorDialog("Une erreur s'est produite lors de la modification de l'encher.");
    } catch (NumberFormatException e) {
        // Gérer les erreurs de format de nombre
        e.printStackTrace();
        displayErrorDialog("Le montant doit être un nombre valide.");
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
        txtimageTF.setText(NomFichier);

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
        txtimageTF.setText(imagePath);

        try {
            BufferedImage img = ImageIO.read(selectedFile);
            // Process the image as needed...
        } catch (IOException e) {
            e.printStackTrace();
        }
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

