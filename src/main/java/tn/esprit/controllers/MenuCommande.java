package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MenuCommande {

    @FXML
    private AreaChart<?, ?> NbrCommandeParMois;

    @FXML
    private PieChart PourcentageCommandeParMois;

    @FXML
    private PieChart PourcentageCommandePourMois2;

    @FXML
    private PieChart PourcentageCommandePourMois3;

    @FXML
    private TextField TotalNbrCommande;

    @FXML
    private Label nbrCommandesLabel;

    @FXML
    void VersCommande(ActionEvent event) {

    }

    @FXML
    void VersPanier(ActionEvent event) {

    }

    @FXML
    void VersPayment(ActionEvent event) {

    }

}
