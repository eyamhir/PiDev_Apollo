package tn.esprit.projet_java.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.services.EnchersService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AfficherEncheres {

    private final EnchersService enchersService = new EnchersService();

    @FXML
    private ListView<Enchers> ListView;

    @FXML
    void initialize() {
        try {
            if (ListView == null) {
                throw new IllegalStateException("ListView is null. Check your FXML file.");
            }

            List<Enchers> encheresList = enchersService.fetchenchers();

            ListView.getItems().addAll(encheresList);

           /* ListView.setCellFactory(new Callback<ListView<Enchers>, CustomListCell>() {
                @Override
                public CustomListCell call(ListView<Enchers> param) {
                    return new CustomListCell();
                }
            });*/

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


    @FXML
    void modifier(ActionEvent event) {
        // Code pour modifier l'utilisateur sélectionné dans la liste
        Enchers selectedEncher = ListView.getSelectionModel().getSelectedItem();
        if (selectedEncher != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEncheres.fxml"));
                Parent root = loader.load();
                ModifierEncheres controller = loader.getController();
                controller.initData(selectedEncher); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification
                Stage window = (Stage) ListView.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Aucun utilisateur sélectionné, affichez un message d'erreur
            afficherMessageErreur("Veuillez sélectionner un utilisateur à modifier.");
        }
    }




   @FXML
   void supprimer(ActionEvent event) {
       Enchers selectedUser = ListView.getSelectionModel().getSelectedItem();
       if (selectedUser != null) {
           Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
           confirmationAlert.setTitle("Confirmation de suppression");
           confirmationAlert.setHeaderText("Supprimer l'utilisateur ?");
           confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

           Optional<ButtonType> result = confirmationAlert.showAndWait();
           if (result.isPresent() && result.get() == ButtonType.OK) {
               try {
                   // Implementer la logique pour supprimer l'encher sélectionné
                   enchersService.supprimer(selectedUser.getId_enchers());
                   // Rafraîchir les données après la suppression
                   initData();
               } catch (SQLException e) {
                   afficherMessageErreur("Erreur lors de la suppression de l'encher.");
               }
           }
       } else {
           // Aucun utilisateur sélectionné, afficher un message d'erreur
           afficherMessageErreur("Veuillez sélectionner un encher à supprimer.");
       }
   }

    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }


    // Method to initialize data
    private void initData() {
        try {
            List<Enchers> encheresList = enchersService.fetchenchers();
            ObservableList<Enchers> observableList = FXCollections.observableArrayList(encheresList);
            ListView.setItems(observableList);
        } catch (SQLException e) {
            afficherMessageErreur("Erreur lors de l'initialisation des données.");
        }
    }



}
