package tn.esprit.controllers;

import com.google.zxing.WriterException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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




    @FXML
    void generQRCode(ActionEvent event) {
        ObservableList<Panier> paniers = ListePAnier.getItems();
        for (Panier panier : paniers) {
            String qrContent = "Panier ID: " + panier.getId_Panier() + ", Nombre de Commandes: " + panier.getNbr_Commande();
            try {
                QRCodeGenerator.generateQRCode(qrContent, "C:/Users/LENOVO/OneDrive/Bureau/Esprit S2/QRCodePanier/QRPanier.png" + panier.getId_Panier() + ".png");
                System.out.println("Code QR généré pour le panier avec l'ID : " + panier.getId_Panier());
            } catch (WriterException | IOException e) {
                System.err.println("Erreur lors de la génération du code QR pour le panier avec l'ID : " + panier.getId_Panier());
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
