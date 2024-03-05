package tn.esprit.apollogui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.services.EvenementService;

import java.sql.SQLException;
import java.util.List;

public class Participation {


    private final EvenementService evenementService = new EvenementService();

    @FXML
    private Button joinEvent;

    @FXML
    private Button viewParticipants;


    @FXML
    private ListView<evenement> ListView;

    @FXML
    void initialize() throws SQLException {
        try {
            List<evenement> evenementList = evenementService.recuperer();

            ListView.getItems().addAll(evenementList);

            // Custom cell factory to display user details
            ListView.setCellFactory(new Callback<ListView<evenement>, ListCell<evenement>>() {
                @Override
                public ListCell<evenement> call(ListView<evenement> param) {
                    return new ListCell<evenement>() {
                        @Override
                        protected void updateItem(evenement evenement, boolean empty) {
                            super.updateItem(evenement, empty);
                            if (empty || evenement == null) {
                                setText(null);
                            } else {
                                // Set the text of the cell to display only the desired fields
                                setText("Nom: " + evenement.getNom() + "\n" +
                                        "Type: " + evenement.getType() + "\n" +
                                        "Description: " + evenement.getDescription() + "\n" +
                                        "Date début:" + evenement.getDate_debut() + "\n" +
                                        "Date fin: " + evenement.getDate_fin()
                                );
                            }
                        }
                    };
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML

    void viewParticipants(ActionEvent event) {
        evenement selectedEvent = ListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            System.out.println("Viewing participants for: " + selectedEvent.getNom());
            // Implement logic to show participants for the selected event
        }
    }

    @FXML
    void joinEvent(ActionEvent event) {
        evenement selectedEvent = ListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            // Assuming you have the userID after user login
            int userID = 123; // Replace with the actual userID

            // Update the database to associate the user with the selected event
            try {
                evenementService.joinEvent(userID, selectedEvent.getId());
                System.out.println("User with ID " + userID + " joined event: " + selectedEvent.getNom());

                // Show a confirmation alert in French
                showConfirmationAlert("Événement Rejoint", "Vous avez rejoint avec succès l'événement : " + selectedEvent.getNom());

            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }

    // Method to show a confirmation alert in French
    private void showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Customize the OK button text and set the button data
        ButtonType okButton = new ButtonType("D'accord", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        alert.showAndWait();
    }

    }


