package Esprit.Controller;

import Esprit.Models.Oeuvre;
import Esprit.Models.Portfolio;
import Esprit.Service.OeuvreSevice;
import Esprit.Service.PortfolioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherPortfolio implements Initializable {
    @FXML
    private FlowPane gridid;
    @FXML
    private ScrollPane scrolt;
    @FXML
    private HBox add;
//    @FXML
//    private HBox portfolioID;


    private Portfolio portfolioselection;
    @FXML
    public void afficherOeuvres() {

        PortfolioService portfolioService = new PortfolioService();
        try {
            List<Portfolio> portfolios = portfolioService.reupere_tout();
            for (Portfolio portfolio : portfolios) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PortfolioItem.fxml"));
                Parent portfolioNode = loader.load();
                PortfolioItem controller = loader.getController();
                controller.setData(portfolio);
                portfolioNode.setOnMouseClicked(event -> afficherDetailsportfolio(portfolio));
                gridid.getChildren().add(portfolioNode);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            // Gérer les erreurs
        }
    }
    @FXML
    public void naviguerVersAjoutpotfolio(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterPortfolio.fxml"));
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
    private void  afficherDetailsportfolio(Portfolio portfolio) {
        this.portfolioselection = portfolio;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Detaildesportfolio.fxml"));
            Parent root = loader.load();
           Detaildesportfolio controller = loader.getController();
            controller.initData(portfolio);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Détails des portfolios");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs
        }
    }

}
