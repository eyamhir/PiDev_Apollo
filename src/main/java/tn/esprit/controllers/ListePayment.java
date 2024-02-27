package tn.esprit.controllers;

import com.google.zxing.WriterException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    @FXML
    void generQRCode(ActionEvent event) {
        ObservableList<Payment> payments = ListePayment.getItems();
        for (Payment payment : payments) {
            // Générer le contenu du code QR pour chaque paiement
            String qrContent = "Payment ID: " + payment.getId_Payment() + ", Amount: " + payment.getMontant() + ", Type: " + payment.getType_Payment();
            try {
                // Générer le code QR pour ce paiement
                QRCodeGenerator.generateQRCode(qrContent, "C:/Users/LENOVO/OneDrive/Bureau/Esprit S2/QRCodePayment/QRPayment.png" + payment.getId_Payment() + ".png");
                System.out.println("Code QR généré pour le paiement avec l'ID : " + payment.getId_Payment());
            } catch (WriterException | IOException e) {
                System.err.println("Erreur lors de la génération du code QR pour le paiement avec l'ID : " + payment.getId_Payment());
                e.printStackTrace();
            }
        }
        showAlert("Success", "Codes QR générés avec succès.");
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
