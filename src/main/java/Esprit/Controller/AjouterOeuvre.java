package Esprit.Controller;

import Esprit.Models.Categories;
import Esprit.Models.Oeuvre;
import Esprit.Service.OeuvreSevice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;

public class AjouterOeuvre {

    @FXML
    private Button butid;

    @FXML
    private ComboBox<String> categorie;

    @FXML
    private DatePicker datecreationid;

    @FXML
    private TextArea descriptionid;

    @FXML
    private TextField diametreid;
    private String imagePath;

    @FXML
    private TextField prixid;

    @FXML
    private TextField titreid;

    public void initialize() {
        // Initialiser le ComboBox avec des valeurs
        ObservableList<String> categories = FXCollections.observableArrayList(
                "PEINTURE","EDITION", "PHOTOGRAPHIES", "SCULPTURE", "DESSIN", "DESIGN");
        categorie.setItems(categories);
    }
public void choisirImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.gif");
    fileChooser.getExtensionFilters().add(filter);
    File selectedFile = fileChooser.showOpenDialog(new Stage());
    if (selectedFile != null) {
        imagePath = selectedFile.toURI().toString();
        System.out.println("Fichier sélectionné : " + imagePath);
    }
}

    public void ajouter(ActionEvent event) throws SQLException, IOException {
        OeuvreSevice oeuvreService = new OeuvreSevice();
        String categorieValue = categorie.getValue(); // Récupérer la valeur sélectionnée dans le ComboBox
        Categories categorieEnum = Categories.valueOf(categorieValue.toUpperCase()); // Convertir en énumération Categories
        String imageUrl = imagePath; // Assurez-vous que imagePath contient l'URL de l'image
        Oeuvre nouvelleOeuvre = new Oeuvre(
                titreid.getText(),
                imageUrl,
                descriptionid.getText(),
                datecreationid.getValue(),
                Float.parseFloat(diametreid.getText()),
                Float.parseFloat(prixid.getText()),
                true,
                15,
                14,
                categorieEnum
        );

        oeuvreService.ajouter(nouvelleOeuvre);

        }
//    public void afficherListeOeuvres(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/ListeOeuvres.fxml"));
//        Parent root = loader.load();
//        // Récupérer le contrôleur de l'interface ListeOeuvres
//        ListeOeuvres listeOeuvresController = loader.getController();
//        // Appeler la méthode afficherOeuvres() du contrôleur ListeOeuvres
//        listeOeuvresController.afficherOeuvres();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();
//    }


}
