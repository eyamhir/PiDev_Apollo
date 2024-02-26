package tn.esprit.controllers;

import com.google.zxing.WriterException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Commande;
import tn.esprit.sevices.CommandeService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AjouterCommande {

    private Stage commandeStage;

    public Stage getCommandeStage() {
        return commandeStage;
    }

    public void setCommandeStage(Stage commandeStage) {
        this.commandeStage = commandeStage;
    }

    @FXML
    private TextField ID_Commande;


    @FXML
    private DatePicker date;

    @FXML
    private TextField Prix_total;

    private final CommandeService ps = new CommandeService();

    @FXML
    void AjouterC(ActionEvent event) {
        // Récupérer la date sélectionnée à partir du DatePicker
        LocalDate selectedDate = date.getValue();

        // Vérifier si une date a été sélectionnée et si le champ Prix_total contient une valeur
        if (selectedDate != null && !Prix_total.getText().isEmpty()) {
            // Convertir la LocalDate en String
            String dateString = selectedDate.toString();

            try {
                // Ajouter la commande avec la date sélectionnée
                ps.ajouter(new Commande(Float.parseFloat(Prix_total.getText()), dateString));
                // Afficher un message de succès
                showAlert("Success", "Command added successfully.");
            } catch (SQLException e) {
                // Afficher un message d'erreur en cas d'échec
                showAlert("Error", e.getMessage());
            }
        } else {
            // Afficher un message d'erreur si aucune date n'a été sélectionnée ou si le champ Prix_total est vide
            showAlert("Error", "Please select a date and enter a valid price.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }






    @FXML
    void supprimerc(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/SupprimerCommande.fxml");

    }

    @FXML
    void ListeC(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("/tn.esprit/ListeCommande.fxml"));
//        Parent root = loader.load();
//        MyList myList = loader.getController();
//        this.commandeStage = new Stage();
//        commandeStage.setTitle("liste");
//        commandeStage.setScene(new Scene(root));
//        myList.setMyStage(commandeStage);
//        commandeStage.show();
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/ListeCommande.fxml");

    }

    @FXML
    void VersPanier(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPanier.fxml");

    }


    @FXML
    void VersPAyment(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPayment.fxml");
    }

    @FXML
    void page_Modifier_Commande(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/ModifierCommande.fxml");

    }

    @FXML
    void Afficher_CodeQR(ActionEvent event) {
        int idCommande = Integer.parseInt(ID_Commande.getText());
        Commande commande;
        try {
            commande = ps.recupererParId(idCommande);
            if (commande != null) {
                String commandeData = "ID: " + commande.getId_Commande() + ", Total: " + commande.getPrix_total() + ", Date: " + commande.getDate_creation_commande();
                QRCodeGenerator.generateQRCode(commandeData, "C:/Users/LENOVO/OneDrive/Bureau/Esprit S2/QR/QRCode.png");
                showAlert("Succès", "Le code QR a été généré avec succès.");
            } else {
                showAlert("Erreur", "Aucune commande trouvée avec cet ID.");
            }
        } catch (SQLException | WriterException | IOException e) {
            showAlert("Erreur", "Une erreur est survenue lors de la génération du code QR.");
            e.printStackTrace();
        }
    }





}
