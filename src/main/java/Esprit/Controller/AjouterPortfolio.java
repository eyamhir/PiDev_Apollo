package Esprit.Controller;

import Esprit.Models.Categories;
import Esprit.Models.Oeuvre;
import Esprit.Models.Portfolio;
import Esprit.Service.OeuvreSevice;
import Esprit.Service.PortfolioService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AjouterPortfolio {


    @FXML
    private TextField artistenom;

    @FXML
    private DatePicker dateid;
    @FXML
    private Button butid;
    @FXML
    private TextArea biographie;
    @FXML
    private ImageView imgid;
    @FXML
    private TextField reseauid;

    private String imagePath ;

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
                imgid.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void ajouterportfolio(ActionEvent event) throws SQLException {
        PortfolioService portfolioService = new PortfolioService();
        String imageUrl = imagePath;
        Portfolio portfolio=new Portfolio(artistenom.getText(),imageUrl,biographie.getText(),dateid.getValue(), reseauid.getText());
        portfolioService.ajouter(portfolio);
        // Affichage de l'alerte de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("le portfolio a été ajoutée avec succès.");
        alert.showAndWait();
    }

}
