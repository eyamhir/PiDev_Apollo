package Esprit.Controller;

import Esprit.Models.Oeuvre;
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
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

public class Detaildesoeuvres {

    @FXML
    private Label catid;

    @FXML
    private Label date;

    @FXML
    private Label desid;

    @FXML
    private Label dimid;

    @FXML
    private ImageView imageid;

    @FXML
    private Label prixid;

    @FXML
    private Label titreid;


    @FXML
    private Button supid;
    @FXML
    private Button modifid;
    private Oeuvre oeuvre;


    public void initData(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
        titreid.setText(oeuvre.getTitre());
        dimid.setText(String.valueOf(oeuvre.getDimension()));
        prixid.setText(String.valueOf(oeuvre.getPrix()) + "DT");
        catid.setText(String.valueOf(oeuvre.getCategories().toString()));
        date.setText(String.valueOf(oeuvre.getDate_creation()));
        desid.setText(oeuvre.getDescription());
        try {
            String imageUrl = oeuvre.getImage_oeuvre(); // Suppose que vous avez une méthode getImageUrl() dans votre classe Oeuvre qui retourne l'URL de l'image
            Image image = new Image(imageUrl);
            imageid.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void modifierOeuvre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierOeuvre.fxml"));
            Parent root = loader.load();
            ModifierOeuvre controller = loader.getController();
            controller.init(oeuvre);
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
private void supprimerOeuvre(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer cette œuvre ?");
    OeuvreSevice oeuvreSevice=new OeuvreSevice();
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            oeuvreSevice.supprimer(oeuvre);
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
