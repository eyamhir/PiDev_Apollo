/*package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;
import org.example.model.Conversation;
import org.example.services.Service_Conversation;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Modifier_Conversation implements Initializable {

        @FXML
        private TextField titreTF;

        @FXML
        private TextField sujetTF;

        @FXML
        private TextArea descriptionTA;

        @FXML
        private MenuButton TypeTF;

        @FXML
        private MenuButton VisibiliteTF;

        private Service_Conversation serviceConversation = new Service_Conversation();

        private Conversation conversation;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                // Initialisation des champs avec les données de la conversation à modifier
                try {
                        conversation = serviceConversation.lireConversationunique(1); // Méthode à implémenter dans Service_Conversation pour récupérer la conversation à modifier
                        titreTF.setText(conversation.getTitre());
                        sujetTF.setText(conversation.getSujet());
                        descriptionTA.setText(conversation.getDescription());
                        TypeTF.setText(conversation.getTypeConversation(Conversation_Type.PRIVATE).toString());
                        VisibiliteTF.setText(conversation.getVisibilite().toString());
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
        @FXML
        public void ModifierConversation(javafx.event.ActionEvent actionEvent) {
                try {
                        Conversation_Type conversationType = Conversation_Type.valueOf(TypeTF.getText());
                        Visibilite visibilite = Visibilite.valueOf(VisibiliteTF.getText());

                        conversation.setTitre(titreTF.getText());
                        conversation.setSujet(sujetTF.getText());
                        conversation.setDescription(descriptionTA.getText());
                        conversation.setTypeConversation(conversationType);
                        conversation.setVisibilite(visibilite);

                        try {
                                serviceConversation.mettreAJourConversation(conversation);

                                // Affichage de l'alerte de confirmation
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Confirmation");
                                alert.setHeaderText(null);
                                alert.setContentText("La conversation a été modifiée avec succès.");
                                alert.showAndWait();

                        } catch (SQLException e) {
                                // Gestion des erreurs
                                e.printStackTrace();
                        }
                } catch (IllegalArgumentException e) {
                        throw new RuntimeException(e);
                }
        }
}
*/

package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;
import org.example.model.Conversation;
import org.example.services.Service_Conversation;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class Modifier_Conversation implements Initializable {

    @FXML
    private TextField titreTF;

    @FXML
    private TextField sujetTF;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private MenuButton TypeTF;

    @FXML
    private MenuButton VisibiliteTF;

    private Service_Conversation serviceConversation = new Service_Conversation();

    private Conversation conversation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ne pas initialiser les champs ici, ils seront initialisés à l'aide de la méthode initData()
    }

    // Méthode pour initialiser les données de la conversation
    public void initData(int conversationId) {
        try {
            conversation = serviceConversation.lireConversationunique(conversationId);
            titreTF.setText(conversation.getTitre());
            sujetTF.setText(conversation.getSujet());
            descriptionTA.setText(conversation.getDescription());
            TypeTF.setText(conversation.getTypeConversation().toString());
            VisibiliteTF.setText(conversation.getVisibilite().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ModifierConversation(javafx.event.ActionEvent actionEvent) {
        try {
            // Récupérer les valeurs des champs et les convertir en types énumérés
            Conversation_Type conversationType = Conversation_Type.valueOf(TypeTF.getText());
            Visibilite visibilite = Visibilite.valueOf(VisibiliteTF.getText());

            // Mettre à jour les informations de la conversation
            conversation.setTitre(titreTF.getText());
            conversation.setSujet(sujetTF.getText());
            conversation.setDescription(descriptionTA.getText());
            conversation.setTypeConversation(conversationType);
            conversation.setVisibilite(visibilite);
            conversation.setDateCreation(null);
            conversation.setDateCreation(null);

            // Appeler la méthode de service pour mettre à jour la conversation dans la base de données
            try {
                serviceConversation.mettreAJourConversationUI(conversation);

                // Afficher une alerte de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("La conversation a été modifiée avec succès.");
                alert.showAndWait();

            } catch (SQLException e) {
                // Gestion des erreurs de mise à jour
                e.printStackTrace();
                // Vous pouvez également afficher une alerte pour informer l'utilisateur de l'échec de la mise à jour
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Erreur lors de la modification de la conversation. Veuillez réessayer.");
                alert.showAndWait();
            }
        } catch (IllegalArgumentException e) {
            // Gestion des erreurs de conversion
            e.printStackTrace();
            // Vous pouvez également afficher une alerte pour informer l'utilisateur de l'erreur de saisie
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur de saisie. Veuillez vérifier les données saisies.");
            alert.showAndWait();
        }
    }

}


