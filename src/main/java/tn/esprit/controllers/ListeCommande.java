package tn.esprit.controllers;

import com.google.zxing.WriterException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tn.esprit.models.Commande;
import tn.esprit.sevices.CommandeService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    @FXML
    void genererCodesQR(ActionEvent event) {
        CommandeService commandeService = new CommandeService();
        List<Commande> commandes = Myliste.getItems();
        for (Commande commande : commandes) {
            String commandeData = "ID: " + commande.getId_Commande() + ", Total: " + commande.getPrix_total() + ", Date: " + commande.getDate_creation_commande();
            String filePath = "C:/Users/LENOVO/OneDrive/Bureau/Esprit S2/QR/QRCode.png" + commande.getId_Commande() + ".png"; // Mettez ici le chemin approprié où vous souhaitez enregistrer les codes QR
            try {
                QRCodeGenerator.generateQRCode(commandeData, filePath);
                System.out.println("Code QR généré pour la commande avec l'ID : " + commande.getId_Commande());
            } catch (WriterException | IOException e) {
                System.err.println("Erreur lors de la génération du code QR pour la commande avec l'ID : " + commande.getId_Commande());
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
