package Esprit.Controller;

import Esprit.Models.Portfolio;
import Esprit.Service.PortfolioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ModifierPortfolio {

    @FXML
    private TextField artistenom;

    @FXML
    private TextArea biographie;

    @FXML
    private DatePicker dateid;

    @FXML
    private TextField reseauid;
    @FXML
    private ImageView imgid ;
    private Portfolio portfolio;
    private String imagePath ;



    public void init(Portfolio portfolio) {
        this.portfolio= portfolio;
        artistenom.setText(portfolio.getNom_Artistique());
        biographie.setText(portfolio.getBiographie());
        dateid.setValue(portfolio.getDebut_carrier());
        reseauid.setText(portfolio.getReseau_sociaux());
        imagePath = portfolio.getImageurl();
        try {
            String imageUrl ="file:/"+ portfolio.getImageurl();
            Image image = new Image(imageUrl);
            imgid.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void modif(ActionEvent event) {
        try {
            String nomArtiste = artistenom.getText();
            String bio =biographie.getText();
            String reseau = reseauid.getText();
            LocalDate dateCarriere= dateid.getValue();
            Portfolio portfoliomodifi= new Portfolio();
            portfoliomodifi.setId_portfolio(portfolio.getId_portfolio());
            portfoliomodifi.setNom_Artistique(nomArtiste);
            portfoliomodifi.setBiographie(bio);
            portfoliomodifi.setReseau_sociaux(reseau);
            portfoliomodifi.setDebut_carrier(dateCarriere);
            if (imagePath != null && !imagePath.isEmpty()) {
                if (imagePath.startsWith("file:")) {
                    portfoliomodifi.setImageurl(imagePath);
                } else {
                    String imageUrl = imagePath;
                    portfoliomodifi.setImageurl(imageUrl);
                }
            } else {
                portfoliomodifi.setImageurl(portfoliomodifi.getImageurl());
            }
            PortfolioService portfolioService = new PortfolioService();
            portfolioService.modifier(portfoliomodifi);
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
                imgid.setImage(image);
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
