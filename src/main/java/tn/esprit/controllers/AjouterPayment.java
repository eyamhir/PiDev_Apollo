package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.models.Payment;
import tn.esprit.sevices.PaymentService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterPayment {

    private final PaymentService paymentService = new PaymentService();

    @FXML
    private TextField IDpay;

    @FXML
    private TextField TypePay;

    @FXML
    private TextField montant;

    @FXML
    void AjouterP(ActionEvent event) {
        try {
            if (IDpay.getText().isEmpty() || montant.getText().isEmpty() || TypePay.getText().isEmpty()) {
                showAlert("Error", "All fields are required.");
                return;
            }

            int id = Integer.parseInt(IDpay.getText());
            float amount = Float.parseFloat(montant.getText());
            String type = TypePay.getText();

            paymentService.ajouter(new Payment(id, amount, type));
            showAlert("Success", "Payment added successfully.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input format.");
        } catch (SQLException e) {
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    void vers_modifier_pay(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/ModifierPayment.fxml");

    }

    @FXML
    void SupprimerP(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/SupprimerPayment.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void ListeP(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/ListePayment.fxml");
    }

    @FXML
    void versCommande(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterCommande.fxml");
    }

    @FXML
    void versPanier(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPanier.fxml");
    }
}