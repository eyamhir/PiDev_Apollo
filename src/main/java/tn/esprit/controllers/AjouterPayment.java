package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.models.EmailSender;
import tn.esprit.models.Payment;
import tn.esprit.sevices.PaymentService;
import tn.esprit.test.MainFX;
import tn.esprit.models.EmailSender;

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
    private TextField email; // Champ d'e-mail

    private final EmailSender emailSender; // Déclaration de la variable emailSender

    public AjouterPayment() {
        // Initialisation de l'objet EmailSender avec vos informations d'identification
        this.emailSender = new EmailSender("votre_adresse_email", "votre_mot_de_passe");
    }

    // Rest

    @FXML
    void AjouterP(ActionEvent event) {
        try {
            if (IDpay.getText().isEmpty() || montant.getText().isEmpty() || TypePay.getText().isEmpty() || email.getText().isEmpty()) {
                showAlert("Error", "All fields are required.");
                return;
            }

            int id = Integer.parseInt(IDpay.getText()); // Récupérer l'ID du paiement
            float amount = Float.parseFloat(montant.getText());
            String type = TypePay.getText();
            String recipient = email.getText(); // Récupérer l'adresse e-mail saisie par l'utilisateur

            Payment payment = new Payment(id, amount, type);
            paymentService.ajouter(payment, recipient); // Passer l'adresse e-mail du destinataire
            showAlert("Success", "Payment added successfully.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input format.");
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
