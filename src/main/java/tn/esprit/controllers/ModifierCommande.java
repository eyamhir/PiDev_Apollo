package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Commande;
import tn.esprit.sevices.CommandeService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ModifierCommande {

    private final CommandeService ps = new CommandeService();

    @FXML
    private DatePicker ID_Com_Date;

    @FXML
    private TextField ID_Com_Mod;

    @FXML
    private TextField ID_Prix_Mod;

    @FXML
    void Modifier_Com(ActionEvent event) {
        // Vérifier si les champs sont vides
        if (ID_Com_Mod.getText().isEmpty() || ID_Prix_Mod.getText().isEmpty() || ID_Com_Date.getValue() == null) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        // Récupérer l'ID de la commande à partir du champ ID_Com_Mod
        int id = Integer.parseInt(ID_Com_Mod.getText());

        // Vérifier si l'ID est valide
        if (id > 0) {
            // Récupérer la date sélectionnée à partir du DatePicker ID_Com_Date
            LocalDate selectedDate = ID_Com_Date.getValue();

            // Convertir la LocalDate en String
            String dateString = selectedDate.toString();

            try {
                // Mettre à jour la commande avec la date sélectionnée et le prix modifié
                ps.modifier(new Commande(id, Float.parseFloat(ID_Prix_Mod.getText()), dateString));
                // Afficher un message de succès
                showAlert("Success", "Command updated successfully.");
            } catch (SQLException e) {
                // Afficher un message d'erreur en cas d'échec
                showAlert("Error", e.getMessage());
            }
        } else {
            // Afficher un message d'erreur si l'ID n'est pas valide
            showAlert("Error", "Please enter a valid ID.");
        }
    }

    @FXML
    void Page_Ajouer_Com(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterCommande.fxml");
    }

    @FXML
    void Page_Liste_Com(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/ListeCommande.fxml");
    }

    @FXML
    void Page_Supprimer_Com(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/SupprimerCommande.fxml");
    }

    @FXML
    void vers_page_panier(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPanier.fxml");

    }

    @FXML
    void vers_page_payment(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPayment.fxml");

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
