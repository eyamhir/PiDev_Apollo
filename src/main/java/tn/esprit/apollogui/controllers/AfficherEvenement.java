package tn.esprit.apollogui.controllers;


import com.google.zxing.WriterException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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


    @FXML
    void modifier(ActionEvent event) {

        evenement selectedevenement = ListView.getSelectionModel().getSelectedItem();
        if (selectedevenement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/apollogui/ModifierEvenement.fxml"));
                Parent root = loader.load();
                ModifierEvenement controller = loader.getController();
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
            afficherMessageErreur("Error");
        }
    }



    public void goback(ActionEvent event) throws IOException{
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/tn/esprit/apollogui/AjouterEvenement.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }


    @FXML
    void goModifier(ActionEvent event) {
        // Code pour modifier l'utilisateur sélectionné dans la liste
        evenement selectedevenement = ListView.getSelectionModel().getSelectedItem();
        if (selectedevenement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/apollogui/ModifierEvenement.fxml"));
                Parent root = loader.load();
                ModifierEvenement controller = loader.getController();
                controller.initData(selectedevenement);
                controller.setEvenement(selectedevenement);
                // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification
                Stage window = (Stage) ListView.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Aucun utilisateur sélectionné, affichez un message d'erreur
            afficherMessageErreur("Veuillez sélectionner un encher à modifier.");
        }
    }

    @FXML
    void generQRCodeE(ActionEvent event) {
        EvenementService evenementService = new EvenementService(); // Create an instance
        ObservableList<evenement> evenementList;

        try {
            evenementList = FXCollections.observableArrayList(evenementService.recuperer()); // Call the instance method
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            afficherMessageErreur("Error retrieving evenements.");
            return; // Exit the method if an error occurs
        }

        for (evenement currentEvenement : evenementList) {
            String qrContent = "evenement ID: " + currentEvenement.getId() +
                    ", Nom: " + currentEvenement.getNom() +
                    ", Type: " + currentEvenement.getType() +
                    ", Description: " + currentEvenement.getDescription() +
                    ", Date début: " + currentEvenement.getDate_debut() +
                    ", Date fin: " + currentEvenement.getDate_fin();

            try {
                String filePath = "C:/Users/elyes/OneDrive/Bureau/Qrcode" +
                        currentEvenement.getId() + ".png";
                QRCodeGeneratorE.generateQRCode(qrContent, filePath);
                System.out.println("Code QR généré pour le panier avec l'ID : " + currentEvenement.getId());
            } catch (WriterException | IOException e) {
                System.err.println("Erreur lors de la génération du code QR pour le panier avec l'ID : " +
                        currentEvenement.getId());
                e.printStackTrace();
            }
        }

        afficherMessageErreur("QRCode generated with success!.");
    }

   /* public void goModifier(ActionEvent event) {
        evenement selectedevenement = ListView.getSelectionModel().getSelectedItem();
        if (selectedevenement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/apollogui/ModifierEvenement.fxml"));
                Parent root = loader.load();
                ModifierEvenement controller = loader.getController();

                controller.initData(selectedevenement);
                controller.setEvenement(selectedevenement);
                // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification
                Stage window = (Stage) ListView.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Aucun utilisateur sélectionné, affichez un message d'erreur
            afficherMessageErreur("Please select evenement you want tp update");
        }
    }

  */
   /* private void initData() {
        try {
            System.out.println("Refreshing data...");
            List<evenement> evenementList = EvenementService.recuperer();
            ObservableList<evenement> observableList = FXCollections.observableArrayList(evenementList);
            ListView.setItems(observableList);
        } catch (SQLException e) {
            afficherMessageErreur("Error");
        }
    }

    */
}