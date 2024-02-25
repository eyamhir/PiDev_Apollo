package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.sevices.CommandeService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;

public class SupprimerCommande {
    private final CommandeService ps = new CommandeService();

    @FXML
    private TextField ID_supp_com;

    @FXML
    void Supprimer_Co(ActionEvent event) {
        // Vérifier si le champ ID_supp_com est vide
        if (ID_supp_com.getText().isEmpty()) {
            showAlert("Error", "Please enter an ID.");
            return; // Sortir de la méthode car l'ID est vide
        }

        // Récupérer l'ID de la commande à partir du champ ID_supp_com
        int id = Integer.parseInt(ID_supp_com.getText());

        // Vérifier si l'ID est valide
        if (id > 0) {
            try {
                // Supprimer la commande avec l'ID spécifié
                ps.supprimer(id);
                // Afficher un message de succès
                showAlert("Success", "Command deleted successfully.");
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
    void page_Listecomm(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/ListeCommande.fxml");
    }

    @FXML
    void page_ajou_com(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterCommande.fxml");
    }

    @FXML
    void page_modif_com(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/ModifierCommande.fxml");
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
