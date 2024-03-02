package org.example.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.model.Utilisateur;
import org.example.services.Service_Amies;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Liste_Amis_Conversation_Groupe implements Initializable {
    @FXML
    private ListView<Utilisateur> AmiesListView;

    private List<Utilisateur> utilisateursSelectionnes; // Liste des utilisateurs sélectionnés

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        utilisateursSelectionnes = new ArrayList<>(); // Initialisation de la liste des utilisateurs sélectionnés

        // Initialise la ListView avec les données des participants
        Service_Amies service_amies = new Service_Amies();
        try {
            List<Utilisateur> utilisateurs = service_amies.getAllArtistsAndClients();
            AmiesListView.getItems().addAll(utilisateurs);

            // Définir comment afficher chaque élément dans la ListView
            AmiesListView.setCellFactory(new Callback<>() {
                @Override
                public ListCell<Utilisateur> call(ListView<Utilisateur> listView) {
                    return new ListCell<>() {
                        @Override
                        protected void updateItem(Utilisateur utilisateur, boolean empty) {
                            super.updateItem(utilisateur, empty);
                            if (empty || utilisateur == null) {
                                setText(null);
                            } else {
                                String roleText = "";
                                if (utilisateur.getRole().equals(("Artiste"))) {
                                    roleText = "Artiste";
                                } else if (utilisateur.getRole().equals("Client")) {
                                    roleText = "Client";
                                }
                                setText("Nom: " + utilisateur.getNom() + "\nPrénom: " + utilisateur.getPrenom() + "\nRôle: " + roleText);
                            }

                        }
                    };
                }
            });

            // Autoriser la sélection multiple dans la liste
            AmiesListView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void Demarrer_Chat_Groupe() throws IOException {
        List<Utilisateur> utilisateursSelectionnes = AmiesListView.getSelectionModel().getSelectedItems(); // Obtenir les utilisateurs sélectionnés

        if (!utilisateursSelectionnes.isEmpty()) {

            // Ajoutez ici le code pour démarrer une conversation avec les amis sélectionnés dans la liste.
            System.out.println("Démarrer une conversation avec : ");
            for (Utilisateur utilisateur : utilisateursSelectionnes) {
                System.out.println("- " + utilisateur.getNom());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucun amies est séléctionnée");
            alert.setHeaderText(null);
            alert.setContentText("Attention aucun amies a été sélectionnée");
            alert.showAndWait();
        }
        Utilisateur utilisateurSelectionne = AmiesListView.getSelectionModel().getSelectedItem();

        if (utilisateurSelectionne != null) {
            int utilisateurSelectionneId = utilisateurSelectionne.getId_utilisateur();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/message.fxml"));
            Parent root = loader.load();
            message_expediteur messagecontroller = loader.getController();
         //   messagecontroller.initialize(utilisateurSelectionneId);
           /* message messageController = new message(utilisateurSelectionneId);
           loader.setController(messageController);*/
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        }
    }
}
