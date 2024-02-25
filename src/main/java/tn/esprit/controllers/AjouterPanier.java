package tn.esprit.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.models.Panier;
import tn.esprit.sevices.PanierService;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterPanier {


        private final PanierService panierService = new PanierService();

        @FXML
        private TextField Id_Panier1;

        @FXML
        private TextField Nb_Commande1;

        @FXML
        private TextField Id_Panier2;

        @FXML
        private TextField Nb_Commande2;

        @FXML
        private TextField Id_Panier3;

        @FXML
        private TextField Nb_Commande3;

        @FXML
        void Ajouter_premier_ouevre(ActionEvent event) {
            ajouterPanier(Id_Panier1, Nb_Commande1);
        }

        @FXML
        void Ajouter_deuxieme_ouevre(ActionEvent event) {
            ajouterPanier(Id_Panier2, Nb_Commande2);
        }

        @FXML
        void Ajouter_troisieme_ouevre(ActionEvent event) {
            ajouterPanier(Id_Panier3, Nb_Commande3);
        }

        @FXML
        void Modifier_premier_ouevre(ActionEvent event) {
            modifierPanier(Id_Panier1, Nb_Commande1);
        }

        @FXML
        void modifier_deuxieme_ouevre(ActionEvent event) {
            modifierPanier(Id_Panier2, Nb_Commande2);
        }

        @FXML
        void modifier_troisieme_ouevre(ActionEvent event) {
            modifierPanier(Id_Panier3, Nb_Commande3);
        }

        @FXML
        void supprimer_premier_ouevre(ActionEvent event) {
            supprimerPanier(Id_Panier1);
        }

        @FXML
        void supprimer_deuxieme_ouevre(ActionEvent event) {
            supprimerPanier(Id_Panier2);
        }

        @FXML
        void supprimer_troisieme_ouevre(ActionEvent event) {
            supprimerPanier(Id_Panier3);
        }

        private void ajouterPanier(TextField idPanierField, TextField nbCommandeField) {
            try {
                int idPanier = Integer.parseInt(idPanierField.getText());
                int nbCommande = Integer.parseInt(nbCommandeField.getText());
                panierService.ajouter(new Panier(idPanier, nbCommande));
                showAlert("Succès", "Panier ajouté avec succès.");
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer des valeurs valides pour l'identifiant du panier et le nombre de commandes.");
            } catch (SQLException e) {
                showAlert("Erreur", "Une erreur s'est produite lors de l'ajout du panier : " + e.getMessage());
            }
        }

        private void modifierPanier(TextField idPanierField, TextField nbCommandeField) {
            try {
                int idPanier = Integer.parseInt(idPanierField.getText());
                int nbCommande = Integer.parseInt(nbCommandeField.getText());
                panierService.modifier(new Panier(idPanier, nbCommande));
                showAlert("Succès", "Panier modifié avec succès.");
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer des valeurs valides pour l'identifiant du panier et le nombre de commandes.");
            } catch (SQLException e) {
                showAlert("Erreur", "Une erreur s'est produite lors de la modification du panier : " + e.getMessage());
            }
        }

        private void supprimerPanier(TextField idPanierField) {
            try {
                int idPanier = Integer.parseInt(idPanierField.getText());
                panierService.supprimer(idPanier);
                showAlert("Succès", "Panier supprimé avec succès.");
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer une valeur valide pour l'identifiant du panier.");
            } catch (SQLException e) {
                showAlert("Erreur", "Une erreur s'est produite lors de la suppression du panier : " + e.getMessage());
            }
        }

        private void showAlert(String titre, String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(titre);
            alert.setContentText(message);
            alert.showAndWait();
        }



    @FXML
    void versCommande(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterCommande.fxml");

    }

    @FXML
    void versPayment(ActionEvent event) throws IOException {
        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/AjouterPayment.fxml");

    }

    @FXML
    void ListePanier(ActionEvent event) throws IOException {

        MainFX main = new MainFX();
        main.changeStage("/tn.esprit/ListePanier.fxml");


    }




}
