package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tn.esprit.models.Commande;
import tn.esprit.sevices.CommandeService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;

public class ListeCommande {

    private Stage myStage;

    public Stage getMyStage() {
        return myStage;
    }

    public void setMyStage(Stage myStage) {
        this.myStage = myStage;
    }

    @FXML
    private ListView<Commande> Myliste;



    @FXML
    void initialize() throws SQLException {
        CommandeService commandeService = new CommandeService();
        Myliste.getItems().addAll(commandeService.recuperer());
    }

    @FXML
    void BackC(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterCommande.fxml");
    }



}
