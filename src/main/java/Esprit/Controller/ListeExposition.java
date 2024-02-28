package Esprit.Controller;
import Esprit.Models.Exposition;
import Esprit.Models.Oeuvre;
import Esprit.Service.ExpositionService;
import Esprit.Service.OeuvreSevice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListeExposition implements Initializable {
    @FXML
    private FlowPane flow;

    @FXML
    private ScrollPane pane;
    private Exposition expositionselect;

    @FXML
    public void afficherExposition() {

        ExpositionService expositionService = new ExpositionService();
        try {
            List<Exposition> expositions = expositionService.reupere_tout();
            for (Exposition exposition : expositions) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ExpositionItem.fxml"));
                Parent exponode = loader.load();
                ExpositionItem controller = loader.getController();
                controller.setData(exposition);
                exponode.setOnMouseClicked(event -> afficherDetailsexpo(exposition));
                flow.getChildren().add(exponode);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            // Gérer les erreurs
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setFitToWidth(true);
        flow.setHgap(10); // Espacement horizontal entre les éléments
        flow.setVgap(10);
        afficherExposition();

    }
    private void afficherDetailsexpo(Exposition exposition) {
        this.expositionselect =exposition;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailsdesExposition.fxml"));
            Parent root = loader.load();
            DetailsdesExposition controller = loader.getController();

            controller.initData(exposition);
            // Créer une nouvelle scène avec la fenêtre de détails de l'œuvre
            Scene scene = new Scene(root);
            // Créer une nouvelle fenêtre pour afficher les détails de l'œuvre
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Détails de l'exposée");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs
        }
    }
    @FXML
    public void naviguerVersAjoutExpo(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterExpo.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
