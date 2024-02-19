package org.example.controller;

import javafx.scene.control.Alert;
import org.example.model.Conversation;
import org.example.services.Service_Conversation;

import java.sql.SQLException;

public class Supprimer_Conversation {

    private Service_Conversation serviceConversation = new Service_Conversation();

    public void supprimerConversation(Conversation conversation) {
        try {
            serviceConversation.supprimerConversation(conversation.getConversationId());
            // Afficher une alerte de confirmation après la suppression
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("La conversation a été supprimée avec succès.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            // Afficher une alerte en cas d'erreur lors de la suppression
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Une erreur s'est produite lors de la suppression de la conversation.");
            errorAlert.showAndWait();
        }
    }
}
