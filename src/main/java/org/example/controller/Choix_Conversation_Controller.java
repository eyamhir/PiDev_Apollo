package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;
import org.example.model.Conversation;
import org.example.model.Utilisateur;
import org.example.services.Service_Conversation;
import org.example.services.Service_Utilisateur;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Choix_Conversation_Controller {


    @FXML
    void deriger_conversation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Conversation.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Deriger_Liste_Amis() {
        try {
            // Créer une instance du service utilisateur
            Service_Utilisateur service_utilisateur = new Service_Utilisateur();

            // Récupérer l'utilisateur par son ID
            Utilisateur utilisateur = service_utilisateur.getUtilisateurParId(3);

            // Récupérer l'ID de l'utilisateur
            int utilisateur_id = utilisateur.getId_utilisateur();

            // Créer une nouvelle conversation
            Conversation conversation = new Conversation();
            conversation.setTitre(utilisateur.getNom());
            conversation.setSujet(utilisateur.getPrenom());
            conversation.setDescription("Description de la conversation");
            conversation.setDateCreation(Timestamp.valueOf(LocalDateTime.now()));
            conversation.setDateFin(Timestamp.valueOf(LocalDateTime.now())); // Date de fin non spécifiée pour le moment
            conversation.setTypeConversation(Conversation_Type.DUO);
            conversation.setVisibilite(Visibilite.PRIVATE);
            conversation.setUtilisateur_id(utilisateur_id); // Définir l'ID de l'utilisateur pour la conversation

            // Enregistrer la conversation dans la base de données
            Service_Conversation serviceConversation = new Service_Conversation();
            serviceConversation.creerConversation(conversation);

            // Charger la vue de la liste des participants du duo
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Liste_Participant_duo.fxml"));
            Parent root = loader.load();

            // Afficher la scène dans une nouvelle fenêtre
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException | SQLException e) {
            e.printStackTrace(); // Gérer les exceptions appropriées ici
        }
    }
public void Afficher_Liste_Conversation(ActionEvent event){
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Liste_Conversation.fxml"));
    try {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
