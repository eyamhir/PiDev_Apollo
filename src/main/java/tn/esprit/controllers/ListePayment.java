package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import tn.esprit.models.Payment;
import tn.esprit.sevices.PaymentService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;

public class ListePayment {
    @FXML
    private ListView<Payment> ListePayment;

    @FXML
    void initialize() throws SQLException {
        PaymentService paymentService = new PaymentService();
        this.ListePayment.getItems().addAll(paymentService.recuperer());
    }


    @FXML
    void backPay(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPayment.fxml");
    }

}
