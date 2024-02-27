package tn.esprit.controllers;

import com.google.zxing.WriterException;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private TextField searchField;



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
    void initialize2() throws SQLException {
        CommandeService commandeService = new CommandeService();
        List<Commande> allCommandes = commandeService.recuperer();
        Myliste.getItems().addAll(allCommandes);

        // Créer une FilteredList pour filtrer les commandes en fonction du texte de recherche
        FilteredList<Commande> filteredCommandes = new FilteredList<>(FXCollections.observableArrayList(allCommandes));

        // Associer la FilteredList à la ListView
        Myliste.setItems(filteredCommandes);

        // Ajouter un écouteur pour surveiller les changements dans le champ de recherche
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Nouvelle valeur de recherche : " + newValue); // Ajout d'un message de débogage
            filteredCommandes.setPredicate(commande -> {
                // Si le champ de recherche est vide, afficher toutes les commandes
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Convertir le texte de recherche en minuscules pour une recherche insensible à la casse
                String lowerCaseFilter = newValue.toLowerCase();

                // Vérifier si le texte de recherche correspond à l'ID, au total ou à la date de création de la commande
                if (String.valueOf(commande.getId_Commande()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Correspondance trouvée pour l'ID de la commande
                } else if (String.valueOf(commande.getPrix_total()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Correspondance trouvée pour le total de la commande
                } else if (commande.getDate_creation_commande().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Correspondance trouvée pour la date de création de la commande
                }
                return false; // Aucune correspondance trouvée
            });
        });
    }



    @FXML
    void genererCodesQR(ActionEvent event) {
        CommandeService commandeService = new CommandeService();
        List<Commande> commandes = Myliste.getItems();
        for (Commande commande : commandes) {
            String commandeData = "ID: " + commande.getId_Commande() + ", Total: " + commande.getPrix_total() + ", Date: " + commande.getDate_creation_commande();
            String filePath = "C:/Users/LENOVO/OneDrive/Bureau/Esprit S2/QRCodeCommande/QRCommande.png" + commande.getId_Commande() + ".png"; // Mettez ici le chemin approprié où vous souhaitez enregistrer les codes QR
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
