package Esprit.Controller;

import Esprit.Models.Categories;
import Esprit.Models.Oeuvre;
import Esprit.Models.Portfolio;
import Esprit.Service.OeuvreSevice;
import Esprit.Service.PortfolioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;


public class Detaildesportfolio {
    private Portfolio portfolio ;
    @FXML
    private Label bio;

    @FXML
    private Label nomArt;

    @FXML
    private Label rs;
    @FXML
    private ImageView img;
    @FXML
    private Button supid;


    public void initData(Portfolio portfolio) {
        this.portfolio =portfolio;
        nomArt.setText(portfolio.getNom_Artistique());
        bio.setText(portfolio.getBiographie());
        rs.setText(portfolio.getReseau_sociaux());

        try {
            String imageUrl = "file:/"+portfolio.getImageurl(); // Suppose que vous avez une méthode getImageUrl() dans votre classe Oeuvre qui retourne l'URL de l'image
            Image image = new Image(imageUrl);
            img.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void modifierportfolio(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierPortfolio.fxml"));
            Parent root = loader.load();
            ModifierPortfolio controller = loader.getController();
            controller.init(portfolio);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            afficherMessage("Modification réussie", "L'œuvre a été modifiée avec succès.", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            e.printStackTrace();
            afficherMessage("Erreur de modification", "Une erreur s'est produite lors de la modification de l'œuvre.", Alert.AlertType.ERROR);
        }
    }
    @FXML
    private void supprimerPortfolio(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette œuvre ?");
        PortfolioService portfolioService=new PortfolioService();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                portfolioService.supprimer(portfolio);
                afficherMessage("Suppression réussie", "Le portfolio a été supprimée avec succès.", Alert.AlertType.INFORMATION);
                // Fermer la fenêtre après la suppression
                Stage stage = (Stage) supid.getScene().getWindow();
                stage.close();
            } catch (SQLException e) {
                e.printStackTrace();
                afficherMessage("Erreur de suppression", "Une erreur s'est produite lors de la suppression de portfolio.", Alert.AlertType.ERROR);
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
