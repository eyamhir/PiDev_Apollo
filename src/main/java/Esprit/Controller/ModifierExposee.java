package Esprit.Controller;

import Esprit.Models.Categories;
import Esprit.Models.Exposition;
import Esprit.Models.Oeuvre;
import Esprit.Models.Type_Expose;
import Esprit.Service.ExpositionService;
import Esprit.Service.OeuvreSevice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ModifierExposee {
    @FXML
    private Button ajoutid;

    @FXML
    private DatePicker debutid;

    @FXML
    private TextArea descriptionid;

    @FXML
    private DatePicker finid;

    @FXML
    private TextField localisation;

    @FXML
    private Button select;

    @FXML
    private TextField titreid;

    @FXML
    private ComboBox<String> typeexpo;
    @FXML
    private ImageView img;

    private String imagePath ;
    private Exposition exposition;

    public void init(Exposition exposition) {
        this.exposition = exposition;
        titreid.setText(exposition.getTitre());
        typeexpo.setValue(exposition.getTypeExpose().toString());
        debutid.setValue(exposition.getDate_debut());
        finid.setValue(exposition.getDate_fin());
        descriptionid.setText(exposition.getDescription());
        localisation.setText(exposition.getLocalisation());
        imagePath = exposition.getImageAffiche();
        try {
            String imageUrl = exposition.getImageAffiche();
            Image image = new Image(imageUrl);
            img.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void modif(ActionEvent event) {
        try {
            String titre = titreid.getText();
            String description = descriptionid.getText();
            LocalDate datedebut = debutid.getValue();
            LocalDate datefin = finid.getValue();
            String typevalue = typeexpo.getValue();
            String local=localisation.getText();
            Type_Expose typeexpoEnum = Type_Expose.valueOf(typevalue.toUpperCase());
            Exposition  expositionmod = new Exposition();
            expositionmod.setId_Exposition(exposition.getId_Exposition());
            expositionmod.setTitre(titre);
            expositionmod.setDescription(description);
            expositionmod.setDate_debut(datedebut);
            expositionmod.setDate_fin(datefin);
            expositionmod.setTypeExpose(typeexpoEnum);
            expositionmod.setLocalisation(local);
            if (imagePath != null && !imagePath.isEmpty()) {
                if (imagePath.startsWith("file:")) {
                    expositionmod.setImageAffiche(imagePath);
                } else {
                    String imageUrl = "file:" + imagePath;
                    expositionmod.setImageAffiche(imageUrl);
                }
            } else {
                expositionmod.setImageAffiche(exposition.getImageAffiche());
            }
            ExpositionService expositionService = new ExpositionService();
            expositionService.modifier(expositionmod);
            afficherMessage("Modification réussie", "L'œuvre a été modifiée avec succès.", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            afficherMessage("Erreur de saisie", "Veuillez saisir un prix valide.", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            afficherMessage("Erreur de modification", "Une erreur s'est produite lors de la modification de l'œuvre.", Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(filter);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                imagePath = selectedFile.getAbsolutePath();
                Image image = new Image(new FileInputStream(imagePath));
                img.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void afficherMessage(String titre, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
