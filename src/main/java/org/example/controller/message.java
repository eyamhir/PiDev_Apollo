package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.model.Message;
import org.example.model.Participant;
import org.example.services.Service_Message;
import org.example.services.Service_Participant;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class message{

    @FXML
    private ListView<String> participantListView;

    @FXML
    private ListView<String> messageListView;

    @FXML
    private TextField messageField;

    private Service_Message messageService;
    private Service_Participant participantService;

    public message() {
        messageService = new Service_Message();
        participantService = new Service_Participant();
    }

    @FXML
    public void initialize() throws SQLException {
        // Charge la liste des participants
       // afficherParticipants();
        // Charge la liste des messages
      //  afficherMessages();
    }

    @FXML
    private void sendMessage() {
        String messageText = messageField.getText();
        // Récupérer l'id de la conversation actuelle
        int conversationId = obtenirConversationIdActuelle(); // Vous devez implémenter cette méthode
        // Enregistrer le message dans la base de données
        try {
            messageService.creerMessage(new Message(1, 1, new Date(), messageText, conversationId)); // Remplacez les valeurs factices par les bonnes valeurs
            // Mettre à jour l'affichage des messages
            afficherMessages();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        messageField.clear();
    }

    @FXML
    void envoyer_message(MouseEvent event) {
        sendMessage();
    }

    // Méthode pour afficher les participants dans la ListView
    /*private void afficherParticipants() {
        // Récupérer l'id de la conversation actuelle
        int conversationId = obtenirConversationIdActuelle(); // Vous devez implémenter cette méthode
        // Récupérer la liste des participants pour cette conversation
        List<Participant> participants = participantService.getParticipants(conversationId); // Vous devez implémenter cette méthode dans Service_Participant
        // Ajouter les participants à la ListView
        participantListView.getItems().addAll(String.valueOf(participants));
    }
*/
    // Méthode pour afficher les messages dans la ListView
    private void afficherMessages() throws SQLException {
        // Récupérer l'id de la conversation actuelle
        int conversationId = obtenirConversationIdActuelle(); // Vous devez implémenter cette méthode
        // Récupérer la liste des messages pour cette conversation
        List<Message> messages = messageService.listerMessagesParConversation(conversationId);
        // Effacer la ListView des messages
        messageListView.getItems().clear();
        // Ajouter les messages à la ListView
        for (Message message : messages) {
            messageListView.getItems().add(message.toString());
        }
    }

    // Méthode factice pour obtenir l'ID de la conversation actuelle
    private int obtenirConversationIdActuelle() {
        // Implémentez la logique pour obtenir l'ID de la conversation actuelle
        return 1; // Valeur factice pour l'exemple
    }
}
