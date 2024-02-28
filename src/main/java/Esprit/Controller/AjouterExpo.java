package Esprit.Controller;

import Esprit.Models.Categories;
import Esprit.Models.Exposition;
import Esprit.Models.Type_Expose;
import Esprit.Service.ExpositionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class AjouterExpo {

    @FXML
    private Button ajoutid;

    @FXML
    private DatePicker debutid;

    @FXML
    private TextArea descriptionid;

    @FXML
    private DatePicker finid;

    @FXML
    private TextField titreid;
    @FXML
    private TextField localisation;

    @FXML
    private Button select;
    private String imagePath ;

    @FXML
    private ComboBox<String> typeexpo;

    public void initialize() {
        // Initialiser le ComboBox avec des valeurs
        ObservableList<String> typeExpo = FXCollections.observableArrayList(
                "EN_LIGNE","PRESENTIEL");
        typeexpo.setItems(typeExpo);
    }
    @FXML
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

    @FXML
    void Ajouterexpo(ActionEvent event) throws SQLException {
        ExpositionService expositionService = new ExpositionService();
        String type_Expo_Value =typeexpo.getValue(); // Récupérer la valeur sélectionnée dans le ComboBox
        Type_Expose typeExpoEnum = Type_Expose.valueOf(type_Expo_Value.toUpperCase()); // Convertir en énumération Categories
        String imageUrl = imagePath; // Assurez-vous que imagePath contient l'URL de l'image
        Exposition exposition=new Exposition(
                imageUrl,
                titreid.getText(),
                descriptionid.getText(),
                debutid.getValue(),
                finid.getValue(),
                14,
                typeExpoEnum,
                localisation.getText()
        );
        expositionService.ajouter(exposition);

    }


}
