package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import tn.esprit.models.Panier;
import tn.esprit.models.Payment;
import tn.esprit.sevices.PanierService;
import tn.esprit.sevices.PaymentService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;

public class ListePanier {
    @FXML
    private ListView<Panier> ListePAnier;

    @FXML
    void initialize() throws SQLException {
        PanierService panierService = new PanierService();
        this.ListePAnier.getItems().addAll(panierService.recuperer());
    }


    @FXML
    void backPanier(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPanier.fxml");

    }

}
