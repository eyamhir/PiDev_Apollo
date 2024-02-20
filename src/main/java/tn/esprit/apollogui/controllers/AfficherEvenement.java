package tn.esprit.apollogui.controllers;


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
import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.services.EvenementService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AfficherEvenement {

    private final EvenementService evenementService = new EvenementService();

    @FXML
    private ListView<evenement> ListView;

    @FXML
    void initialize() {
        try {
            if (ListView == null) {
                throw new IllegalStateException("ListView is null. Check your FXML file.");
            }

            //List<evenement> evenementList = EvenementService.recuperer();
            List<evenement>   evenementList=evenementService.recuperer();


            ListView.getItems().addAll(evenementList);

           /* ListView.setCellFactory(new Callback<ListView<evenement>, CustomListCell>() {
                @Override
                public CustomListCell call(ListView<evenement> param) {
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

/*
    @FXML
    void modifier(ActionEvent event) {

        evenement selectedevenement = ListView.getSelectionModel().getSelectedItem();
        if (selectedevenement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Modifierevenement.fxml"));
                Parent root = loader.load();
                Modifierevenement controller = loader.getController();
                controller.initData(selectedevenement);
                Stage window = (Stage) ListView.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {

            afficherMessageErreur("Veuillez sélectionner un evenement à modifier.");
        }
    }

*/


    @FXML
    void Supprimer(ActionEvent event) {
        evenement selectedUser = ListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm ");
            confirmationAlert.setHeaderText("delete event ?");
            confirmationAlert.setContentText("Are you sure you want to delete?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {


                    evenementService.supprimer(selectedUser.getId());

                    initData();
                } catch (SQLException e) {
                    afficherMessageErreur("Erreur .");
                }
            }
        } else {

            afficherMessageErreur("Please select an event to delete.");
        }
    }

    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }



    private void initData() {
        try {
            List<evenement> evenementList = evenementService.recuperer();
            ObservableList<evenement> observableList = FXCollections.observableArrayList(evenementList);
            ListView.setItems(observableList);
        } catch (SQLException e) {
            afficherMessageErreur("Erreur lors de l'initialisation des données.");
        }
    }


    public void goback(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/apollogui/AjouterEvenement.fxml"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}