package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.sevices.CommandeService;
import tn.esprit.sevices.PaymentService;
import tn.esprit.test.MainFX;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class MenuCommande {

    private final CommandeService commandeService = new CommandeService();
    private final PaymentService paymentService = new PaymentService();

    @FXML
    private AreaChart<String, Integer> NbrCommandeParMois;

    @FXML
    private AreaChart<?, ?> NbrPanierParMois;

    @FXML
    private AreaChart<String, Integer> NbrPaymentParMois;

    @FXML
    private PieChart PourcentageCommandeParMois;

    @FXML
    private PieChart PourcentageCommandePourMois2;

    @FXML
    private PieChart PourcentageCommandePourMois3;

    @FXML
    private PieChart PourcentagePanierParMois3;

    @FXML
    private PieChart PourcentagePaymentParMois2;

    @FXML
    private TextField TotalNbrCommande;

    @FXML
    private TextField TotalNbrPanier;

    @FXML
    private TextField TotalNbrPayment;

    @FXML
    private Label nbrCommandesLabel;

    @FXML
    void initialize() {
        loadChartData();
        loadPaymentData();
        loadTotalNumberOfOrders();
    }

    private void loadChartData() {
        try {
            // Récupérer le nombre de commandes par mois
            Map<String, Integer> commandsByMonth = commandeService.countCommandsByMonth();

            // Créer une liste observable pour stocker les données des commandes par mois
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            commandsByMonth.forEach((month, count) -> pieChartData.add(new PieChart.Data(month, count)));

            // Ajouter les données au PieChart
            PourcentageCommandeParMois.setData(pieChartData);

            // Remplir le graphique en aires avec les données des commandes par mois
            AreaChart.Series<String, Integer> series = new AreaChart.Series<>();
            series.setName("Nombre de commandes par mois");
            commandsByMonth.forEach((month, count) -> series.getData().add(new AreaChart.Data<>(month, count)));
            NbrCommandeParMois.getData().add(series);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadPaymentData() {
        try {
            // Récupérer le nombre de paiements par mois
            Map<String, Integer> paymentsByMonth = paymentService.countPaymentsByMonth();

            // Créer une liste observable pour stocker les données des paiements par mois
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            paymentsByMonth.forEach((month, count) -> pieChartData.add(new PieChart.Data(month, count)));

            // Ajouter les données au PieChart des paiements par mois
            PourcentagePaymentParMois2.setData(pieChartData);

            // Remplir le graphique en aires avec les données des paiements par mois
            AreaChart.Series<String, Integer> series = new AreaChart.Series<>();
            series.setName("Nombre de paiements par mois");
            paymentsByMonth.forEach((month, count) -> series.getData().add(new AreaChart.Data<>(month, count)));
            NbrPaymentParMois.getData().add(series);

            // Charger d'autres données de paiement si nécessaire
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void loadTotalNumberOfOrders() {
        try {
            int total = commandeService.countTotalNumberOfOrders();
            TotalNbrCommande.setText(String.valueOf(total));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void VersCommande(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterCommande.fxml");
    }

    @FXML
    void VersPanier(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPanier.fxml");
    }

    @FXML
    void VersPayment(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPayment.fxml");
    }
}
