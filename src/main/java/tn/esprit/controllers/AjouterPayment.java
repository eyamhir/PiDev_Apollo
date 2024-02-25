package tn.esprit.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Payment;
import tn.esprit.sevices.PaymentService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterPayment {

    private Stage paymentStage;

    public Stage getPaymentStage() {
        return paymentStage;
    }

    public void setPaymentStage(Stage paymentStage) {
        this.paymentStage = paymentStage;
    }

    @FXML
    private TextField IDpay;

    @FXML
    private TextField TypePay;

    @FXML
    private TextField montant;

    private final PaymentService paymentService = new PaymentService();

    @FXML
    void AjouterP(ActionEvent event) {
        try {
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
    void ModifierP(ActionEvent event) {
        try {
            int id = Integer.parseInt(IDpay.getText());
            float amount = Float.parseFloat(montant.getText());
            String type = TypePay.getText();

            paymentService.modifier(new Payment(id, amount, type));
            showAlert("Success", "Payment updated successfully.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input format.");
        } catch (SQLException e) {
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    void SupprimerP(ActionEvent event) {
        try {
            int id = Integer.parseInt(IDpay.getText());
            paymentService.supprimer(id);
            showAlert("Success", "Payment deleted successfully.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input format.");
        } catch (SQLException e) {
            showAlert("Error", e.getMessage());
        }
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
