package Esprit.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavbarController implements Initializable {

    @FXML
    private HBox expositionid;

    @FXML
    private GridPane gridid;

    @FXML
    private HBox portfolioID;

    @FXML
    private BorderPane bp;

    @FXML
    void AjoutPortfolio(MouseEvent event) {
        loadPage("/Afficher_portfolio.fxml");
    }

    @FXML
    void exposition(MouseEvent event) {
        loadPage("/ListeExposition.fxml");
    }

    @FXML
    void home() {
        loadPage("/ListeOeuvres.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        home();
    }

    private void loadPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
            Parent root = loader.load();
            bp.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
