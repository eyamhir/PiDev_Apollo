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

public class ModifierPayment {
    private final PaymentService paymentService = new PaymentService();

    @FXML
    private TextField Id_payment;

    @FXML
    private TextField Montant;

    @FXML
    private TextField Type_payment;

    @FXML
    void Modifier_Pay(ActionEvent event) {
        try {
            int id = Integer.parseInt(Id_payment.getText());
            float amount = Float.parseFloat(Montant.getText());
            String type = Type_payment.getText();

            paymentService.modifier(new Payment(id, amount, type));
            showAlert("Success", "Payment updated successfully.");
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
    void page_ListePayment(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/ListePayment.fxml");

    }

    @FXML
    void page_commande(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterCommande.fxml");

    }

    @FXML
    void page_panier(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPanier.fxml");

    }

    @FXML
    void vers_page_ajouter(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPayment.fxml");

    }

    @FXML
    void vers_page_supprimer(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/SupprimerPayment.fxml");

    }
}
