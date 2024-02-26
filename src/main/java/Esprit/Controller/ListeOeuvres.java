package Esprit.Controller;

import Esprit.Models.Oeuvre;
import Esprit.Service.OeuvreSevice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListeOeuvres implements Initializable {

    @FXML
    private FlowPane gridid;
    @FXML
    private ScrollPane scrolt;
    @FXML
    private HBox expositionid;
    @FXML
    private HBox portfolioID;
    @FXML
    private Button ajoutid;

    private Oeuvre oeuvreSelectionnee;
    @FXML
    public void afficherOeuvres() {

        OeuvreSevice oeuvreService = new OeuvreSevice();
        try {
            List<Oeuvre> oeuvres = oeuvreService.reupere_tout();
            for (Oeuvre oeuvre : oeuvres) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Oeuvre.fxml"));
                Parent oeuvreNode = loader.load();
                OeuvreController controller = loader.getController();
                controller.setData(oeuvre);
                oeuvreNode.setOnMouseClicked(event -> afficherDetailsOeuvre(oeuvre));
                gridid.getChildren().add(oeuvreNode);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            // Gérer les erreurs
        }
    }
    @FXML
    public void naviguerVersAjoutOeuvre(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterOeuvre.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrolt.setFitToWidth(true);
        gridid.setHgap(10); // Espacement horizontal entre les éléments
        gridid.setVgap(10);
        afficherOeuvres();

    }
    private void afficherDetailsOeuvre(Oeuvre oeuvre) {
        this.oeuvreSelectionnee = oeuvre;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Detaildesoeuvres.fxml"));
            Parent root = loader.load();
            Detaildesoeuvres controller = loader.getController();
            // Injecter les données de l'œuvre sélectionnée dans le contrôleur
            controller.initData(oeuvre);
            // Créer une nouvelle scène avec la fenêtre de détails de l'œuvre
            Scene scene = new Scene(root);
            // Créer une nouvelle fenêtre pour afficher les détails de l'œuvre
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Détails de l'œuvre");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs
        }
    }
}