package Esprit.Controller;

import Esprit.Models.Exposition;
import Esprit.Models.Oeuvre;
import Esprit.Service.ExpositionService;
import Esprit.Service.OeuvreSevice;
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
import java.util.Optional;

public class DetailsdesExposition {
    @FXML
    private Label datedebut;

    @FXML
    private Label datefin;

    @FXML
    private ImageView idimg;

    @FXML
    private Label local;

    @FXML
    private Label titre;

    @FXML
    private Label type;
    @FXML
    private Button supid;
    private Exposition exposition ;
    public void initData(Exposition exposition) {
        this.exposition = exposition;
        titre.setText(exposition.getTitre());
        datedebut.setText(String.valueOf(exposition.getDate_debut()));
        datefin.setText(String.valueOf(exposition.getDate_fin()));
        type.setText(String.valueOf(exposition.getTypeExpose().toString()));
        local.setText(exposition.getDescription());
        try {
            String imageUrl = exposition.getImageAffiche(); // Suppose que vous avez une méthode getImageUrl() dans votre classe Oeuvre qui retourne l'URL de l'image
            Image image = new Image(imageUrl);
            idimg.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void modifierExpose(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierExposee.fxml"));
            Parent root = loader.load();
            ModifierExposee controller = loader.getController();
            controller.init(exposition);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Attendre que l'utilisateur termine la modification avant de continuer
            afficherMessage("Modification réussie", "L'œuvre a été modifiée avec succès.", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            e.printStackTrace();
            afficherMessage("Erreur de modification", "Une erreur s'est produite lors de la modification de l'œuvre.", Alert.AlertType.ERROR);
        }
    }

    //    @FXML
    @FXML
    private void supprimerExpo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette œuvre ?");
        ExpositionService expositionService=new ExpositionService();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                expositionService.supprimer(exposition);
                afficherMessage("Suppression réussie", "L'œuvre a été supprimée avec succès.", Alert.AlertType.INFORMATION);
                // Fermer la fenêtre après la suppression
                Stage stage = (Stage) supid.getScene().getWindow();
                stage.close();
            } catch (SQLException e) {
                e.printStackTrace();
                afficherMessage("Erreur de suppression", "Une erreur s'est produite lors de la suppression de l'œuvre.", Alert.AlertType.ERROR);
            }
        }
    }

    private void afficherMessage(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
