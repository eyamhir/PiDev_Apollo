/*package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.model.Conversation;
import org.example.services.Service_Conversation;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Afficher_Conversation implements Initializable {

    @FXML
    private ListView<Conversation> conversationListView;
    @FXML
    private TextField searchField; // Ajoutez le champ de recherche dans votre fichier FXML et injectez-le ici

    private Service_Conversation serviceConversation = new Service_Conversation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Conversation> conversations = serviceConversation.listerConversations();
            conversationListView.getItems().addAll(conversations);

            conversationListView.setCellFactory(param -> new ListCell<Conversation>() {
                @Override
                protected void updateItem(Conversation conversation, boolean empty) {
                    super.updateItem(conversation, empty);
                    if (empty || conversation == null) {
                        setText(null);
                    } else {
                        setText(conversation.getConversationId() + " | " + conversation.getTitre() + " | " +
                                conversation.getSujet() + " | " + conversation.getDescription() + " | " +
                                conversation.getDateCreation() + " | " + conversation.getDateFin() + " | " +
                                conversation.getTypeConversation() + " | " + conversation.getVisibilite());
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Supprimer_Conversation(ActionEvent event) {
        Conversation selectedConversation = conversationListView.getSelectionModel().getSelectedItem();
        if (selectedConversation != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    int conversationId = selectedConversation.getConversationId();
                    serviceConversation.supprimerConversation(conversationId);
                    conversationListView.getItems().remove(selectedConversation);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @FXML
    private void Modifier_Conversation(ActionEvent event) {
        Conversation selectedConversation = conversationListView.getSelectionModel().getSelectedItem();
        if (selectedConversation != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifier_conversation.fxml"));
                Parent root = loader.load();
                Modifier_Conversation controller = loader.getController();
                controller.initData(selectedConversation.getConversationId());
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.showAndWait();

                List<Conversation> updatedConversations = serviceConversation.listerConversations();
                conversationListView.getItems().setAll(updatedConversations);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void Chercher_Conversation(ActionEvent event) {
        String sujet = searchField.getText().trim(); // Obtenez le sujet à rechercher depuis le champ de recherche

        try {
            List<Conversation> conversations = serviceConversation.rechercherConversationsParSujet(sujet); // Recherchez les conversations par sujet
            conversationListView.getItems().clear(); // Effacez la liste existante

            if (!conversations.isEmpty()) {
                conversationListView.getItems().addAll(conversations); // Ajoutez les conversations trouvées à la ListView
            } else {
                // Aucune conversation trouvée, affichez un message à l'utilisateur
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aucune Conversation Trouvée");
                alert.setHeaderText(null);
                alert.setContentText("Aucune conversation trouvée avec le sujet: " + sujet);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
*/

package org.example.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.model.Conversation;
import org.example.services.Service_Conversation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Afficher_Conversation implements Initializable {

    @FXML
    private ListView<Conversation> conversationListView;

    @FXML
    private TextField searchField;

    private Service_Conversation serviceConversation = new Service_Conversation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Conversation> conversations = serviceConversation.listerConversations();
            conversationListView.getItems().addAll(conversations);

            conversationListView.setCellFactory(param -> new ListCell<Conversation>() {
                @Override
                protected void updateItem(Conversation conversation, boolean empty) {
                    super.updateItem(conversation, empty);
                    if (empty || conversation == null) {
                        setText(null);
                    } else {
                        setText(conversation.getConversationId() + " | " + conversation.getTitre() + " | " +
                                conversation.getSujet() + " | " + conversation.getDescription() + " | " +
                                conversation.getDateCreation() + " | " + conversation.getDateFin() + " | " +
                                conversation.getTypeConversation() + " | " + conversation.getVisibilite());
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Supprimer_Conversation(ActionEvent event) {
        Conversation selectedConversation = conversationListView.getSelectionModel().getSelectedItem();
        if (selectedConversation != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette conversation ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    int conversationId = selectedConversation.getConversationId();
                    serviceConversation.supprimerConversation(conversationId);
                    conversationListView.getItems().remove(selectedConversation);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void Modifier_Conversation(ActionEvent event) {
        Conversation selectedConversation = conversationListView.getSelectionModel().getSelectedItem();
        if (selectedConversation != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifier_conversation.fxml"));
                Parent root = loader.load();
                Modifier_Conversation controller = loader.getController();
                controller.initData(selectedConversation.getConversationId());
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.showAndWait();

                // Retourner à l'interface utilisateur principale avec la liste des conversations
                FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/Liste_Conversation.fxml"));
                Parent mainRoot = mainLoader.load();
                 Afficher_Conversation mainController = mainLoader.getController();
                Stage window = (Stage) conversationListView.getScene().getWindow();
                window.setScene(new Scene(mainRoot));
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void Chercher_Conversation(ActionEvent event) {
        String sujet = searchField.getText().trim();

        try {
            List<Conversation> conversations = serviceConversation.rechercherConversationsParSujet(sujet);
            conversationListView.getItems().clear();

            if (!conversations.isEmpty()) {
                conversationListView.getItems().addAll(conversations);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aucune Conversation Trouvée");
                alert.setHeaderText(null);
                alert.setContentText("Aucune conversation trouvée avec le sujet: " + sujet);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}











