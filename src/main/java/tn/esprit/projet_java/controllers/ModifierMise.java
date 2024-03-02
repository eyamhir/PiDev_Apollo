package tn.esprit.projet_java.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.models.Mise;
import tn.esprit.projet_java.services.MiseService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.EventObject;
import java.util.List;
import java.util.Optional;

public class ModifierMise {
    private final MiseService miseService = new MiseService();
    @FXML
    private ListView<Mise> listview;
    private EventObject actionEvent;



    @FXML
    void initialize() {
        try {
            if (listview == null) {
                throw new IllegalStateException("ListView is null. Check your FXML file.");
            }

            List<Mise> miseList = miseService.fetchmise();

            listview.getItems().addAll(miseList);


        } catch (SQLException e) {
            showErrorAlert("Error", e.getMessage());
        } catch (IllegalStateException e) {
            showErrorAlert("Error", e.getMessage());
        }
    }



    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }
    /*private void initData() {
        try {
            System.out.println("Refreshing data...");
            List<Mise> miseList = miseService.fetchmise();
            ObservableList<Mise> observableList = FXCollections.observableArrayList(miseList);
            listview.setItems(observableList);
        } catch (SQLException e) {
            afficherMessageErreur("Erreur lors de l'initialisation des données.");
        }
    }*/
    private void initData() {
        try {
            System.out.println("Refreshing data...");
            List<Mise> miseList = miseService.fetchmise();

            // Trier la liste selon le montant maximal (maxMontant) de manière décroissante
            miseList.sort(Comparator.comparingDouble(Mise::getMax_montant).reversed());

            ObservableList<Mise> observableList = FXCollections.observableArrayList(miseList);
            listview.setItems(observableList);
        } catch (SQLException e) {
            afficherMessageErreur("Erreur lors de l'initialisation des données.");
        }
    }


    @FXML
    void afficher_mise(ActionEvent event) {

    }
    @FXML
    void modifier_mise(ActionEvent event) {

        Mise selectedMise = listview.getSelectionModel().getSelectedItem();
        if (selectedMise != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierPage.fxml"));
                Parent root = loader.load();
                ModifierPage controller = loader.getController();
                controller.initData(selectedMise);
                controller.setMise(selectedMise);

                // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification
                Stage window = (Stage) listview.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Aucun utilisateur sélectionné, affichez un message d'erreur
            afficherMessageErreur("Veuillez sélectionner un mise à modifier.");
        }
    }

    @FXML
    void supprimer_mise(ActionEvent event) {
        Mise selectedMise = listview.getSelectionModel().getSelectedItem();
        if (selectedMise != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Supprimer la mise ?");
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cet mise ?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // Implementer la logique pour supprimer l'encher sélectionné
                    miseService.supprimer(selectedMise.getId_mise());
                    // Rafraîchir les données après la suppression
                    initData();
                } catch (SQLException e) {
                    afficherMessageErreur("Erreur lors de la suppression de la mise.");
                }
            }
        } else {
            // Aucun utilisateur sélectionné, afficher un message d'erreur
            afficherMessageErreur("Veuillez sélectionner une mise à supprimer.");
        }
    }


}
