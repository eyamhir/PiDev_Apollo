package org.example.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.model.Conversation;
import org.example.services.Service_Conversation;
import org.example.controller.Supprimer_Conversation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Afficher_Conversation implements Initializable {

    @FXML
    private ListView<Conversation> conversationListView;

    private Service_Conversation serviceConversation = new Service_Conversation();
    private Supprimer_Conversation supprimerConversationController = new Supprimer_Conversation();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialise la ListView avec les données des conversations
        try {
            List<Conversation> conversations = serviceConversation.listerConversations();
            conversationListView.getItems().addAll(conversations);

            // Définir comment afficher chaque élément dans la ListView
            conversationListView.setCellFactory(new Callback<ListView<Conversation>, javafx.scene.control.ListCell<Conversation>>() {
                @Override
                public ListCell<Conversation> call(ListView<Conversation> conversationListView) {
                    return new ListCell<Conversation>() {
                        @Override
                        protected void updateItem(Conversation conversation, boolean empty) {
                            super.updateItem(conversation, empty);
                            if (conversation != null) {
                                setText(conversation.getConversationId() + " | " + conversation.getTitre() + " | " + conversation.getSujet() + " | " + conversation.getDescription() + " | " + conversation.getDateCreation() + " | " + conversation.getDateFin() + " | " + conversation.getTypeConversation() + " | " + conversation.getVisibilite());
                            } else {
                                setText(null);
                            }
                        }
                    };
                }
            });


// Ajouter un gestionnaire d'événements pour gérer les clics sur les éléments de la ListView
            conversationListView.setOnMouseClicked(event -> {
                // Récupérer l'élément sélectionné
                Conversation selectedConversation = conversationListView.getSelectionModel().getSelectedItem();
                if (selectedConversation != null) {
                    try {
                        // Charger la nouvelle page avec les détails de la conversation sélectionnée
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifier_conversation.fxml"));
                        Parent root = loader.load();

                        // Passer les données de la conversation à la nouvelle fenêtre
                        Modifier_Conversation controller = loader.getController();
                        controller.initData(selectedConversation.getConversationId());

                        // Ajouter un gestionnaire d'événements pour détecter lorsque la fenêtre de modification est fermée
                        Stage newStage = new Stage();
                        newStage.setScene(new Scene(root));
                        newStage.showAndWait();

                        // Rafraîchir la liste des conversations après la modification
                        List<Conversation> updatedConversations = serviceConversation.listerConversations(); // Récupérer la liste mise à jour
                        conversationListView.getItems().setAll(updatedConversations); // Mettre à jour les conversations dans la ListView

                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            });




            // Ajouter un gestionnaire d'événements pour gérer les clics sur les éléments de la ListView
         /*   conversationListView.setOnMouseClicked(event -> {
                // Récupérer l'élément sélectionné
                Conversation selectedConversation = conversationListView.getSelectionModel().getSelectedItem();
                if (selectedConversation != null) {
                    try {
                        // Charger la nouvelle page avec les détails de la conversation sélectionnée
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifier_conversation.fxml"));
                        Parent root = loader.load();

                        // Passer les données de la conversation à la nouvelle fenêtre
                        Modifier_Conversation controller = loader.getController();
                        controller.initData(selectedConversation.getConversationId());

                        // Ajouter un gestionnaire d'événements pour détecter lorsque la fenêtre de modification est fermée
                        Stage newStage = new Stage();
                        newStage.setScene(new Scene(root));
                        newStage.showAndWait();

                        // Charger la nouvelle page avec les détails de la conversation sélectionnée
                        FXMLLoader loadera = new FXMLLoader(getClass().getResource("/Liste_Conversation.fxml"));
                        Parent roota = loader.load();
                        // Rafraîchir la liste des conversations après la modification
                        conversationListView.getItems().clear(); // Effacer la liste existante
                        List<Conversation> updatedConversations = serviceConversation.listerConversations(); // Récupérer la liste mise à jour
                        conversationListView.getItems().addAll(updatedConversations); // Ajouter les conversations mises à jour à la ListView

                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

*/



            // l'evéneement pour supprimer la liste
       /*     conversationListView.setOnMouseClicked(event -> {
                // Récupérer l'élément sélectionné
                Conversation selectedConversation = conversationListView.getSelectionModel().getSelectedItem();
                if (selectedConversation != null) {
                    // Demander une confirmation avant de supprimer la conversation
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation de suppression");
                    alert.setHeaderText(null);
                    alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // Supprimer la conversation si l'utilisateur confirme
                        supprimerConversationController.supprimerConversation(selectedConversation);
                        // Rafraîchir la liste des conversations après la suppression
                        conversationListView.getItems().remove(selectedConversation);
                    }
                }
            });
*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
