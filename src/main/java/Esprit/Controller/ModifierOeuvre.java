package Esprit.Controller;

import Esprit.Models.Categories;
import Esprit.Models.Oeuvre;
import Esprit.Service.OeuvreSevice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class ModifierOeuvre {

    @FXML
    private ComboBox<String> categorie;
    private String imagePath;

    @FXML
    private TextField titreid;

    @FXML
    private TextField diametreid;

    @FXML
    private DatePicker datecreationid;

    @FXML
    private TextArea descriptionid;

    @FXML
    private TextField prixid;

    @FXML
    private ImageView imageid;

    @FXML
    private Button butid ;

    private Oeuvre oeuvre;
    public void init(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
        titreid.setText(oeuvre.getTitre());
        categorie.setValue(oeuvre.getCategories().toString());
        diametreid.setText(String.valueOf(oeuvre.getDimension()));
        datecreationid.setValue(oeuvre.getDate_creation());
        descriptionid.setText(oeuvre.getDescription());
        prixid.setText(String.valueOf(oeuvre.getPrix()));
        imagePath = oeuvre.getImage_oeuvre();
        try {
            String imageUrl = oeuvre.getImage_oeuvre();
            Image image = new Image(imageUrl);
            imageid.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void modif(ActionEvent event) {
        try {
            String titre = titreid.getText();
            String description = descriptionid.getText();
            String prixText = prixid.getText();
            String dimetre=diametreid.getText();
            Float dia=Float.parseFloat(dimetre);
            Float prix = Float.parseFloat(prixText);
            String categorieValue = categorie.getValue();
            LocalDate dateCreation = datecreationid.getValue();
            Categories categorieEnum = Categories.valueOf(categorieValue.toUpperCase());
            Oeuvre oeuvreModifiee = new Oeuvre();
            oeuvreModifiee.setId_Oeuvre(oeuvre.getId_Oeuvre());
            oeuvreModifiee.setTitre(titre);
            oeuvreModifiee.setDescription(description);
            oeuvreModifiee.setPrix(prix);
            oeuvreModifiee.setDimension(dia);
            oeuvreModifiee.setCategories(categorieEnum);
            oeuvreModifiee.setDate_creation(dateCreation);
            if (imagePath != null && !imagePath.isEmpty()) {
                if (imagePath.startsWith("file:")) {
                    oeuvreModifiee.setImage_oeuvre(imagePath);
                } else {
                    String imageUrl = "file:" + imagePath;
                    oeuvreModifiee.setImage_oeuvre(imageUrl);
                }
            } else {
                oeuvreModifiee.setImage_oeuvre(oeuvre.getImage_oeuvre());
            }
            OeuvreSevice oeuvreService = new OeuvreSevice();
            oeuvreService.modifier(oeuvreModifiee);
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
                imageid.setImage(image);
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
    }}

