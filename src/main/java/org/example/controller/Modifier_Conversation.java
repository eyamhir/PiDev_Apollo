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
            conversation.getConversationId();
            if (conversation != null) {

                titreTF.setText(conversation.getTitre());
                sujetTF.setText(conversation.getSujet());
                descriptionTA.setText(conversation.getDescription());
                TypeTF.setText(conversation.getTypeConversation().toString());
                VisibiliteTF.setText(conversation.getVisibilite().toString());
            } else {
                displayErrorDialog("Conversation introuvable. Veuillez réessayer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            displayErrorDialog("Erreur lors de la récupération des données de la conversation. Veuillez réessayer.");
        }
    }

    @FXML
    public void ModifierConversation(javafx.event.ActionEvent actionEvent) {
        try {
            // Vérification des champs


         if(allFieldsFilled()) {
                // Récupération des valeurs des champs et conversion en types énumérés
                Conversation_Type conversationType = Conversation_Type.valueOf(TypeTF.getText());
                Visibilite visibilite = Visibilite.valueOf(VisibiliteTF.getText());

                // Mise à jour des informations de la conversation

                conversation.setTitre(titreTF.getText());
                conversation.setSujet(sujetTF.getText());
                conversation.setDescription(descriptionTA.getText());
                conversation.setTypeConversation(conversationType);
                conversation.setVisibilite(visibilite);

                // Appel de la méthode de service pour mettre à jour la conversation dans la base de données
                try {
                    serviceConversation.mettreAJourConversationUI(conversation);

                    // Affichage d'une alerte de confirmation
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("La conversation a été modifiée avec succès.");
                    alert.showAndWait();

                } catch (SQLException e) {
                    // Gestion des erreurs de mise à jour
                    e.printStackTrace();
                    // Affichage d'une alerte d'erreur
                    displayErrorDialog("Erreur lors de la modification de la conversation. Veuillez réessayer.");
                }
            }
        } catch (IllegalArgumentException e) {
            // Gestion des erreurs de conversion
            e.printStackTrace();
            // Affichage d'une alerte d'erreur
            displayErrorDialog("Erreur de saisie. Veuillez vérifier les données saisies.");
        }
    }

    // Méthode de validation pour vérifier si tous les champs sont remplis
    private boolean allFieldsFilled() {
        if (titreTF.getText().isEmpty() ||
                sujetTF.getText().isEmpty() ||
                descriptionTA.getText().isEmpty() ||
                TypeTF.getText().isEmpty() ||
                VisibiliteTF.getText().isEmpty()) {

            // Affichage d'une alerte d'erreur indiquant les champs requis
            displayErrorDialog("Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        return true;
    }

    // Méthode pour afficher une boîte de dialogue d'erreur
    private void displayErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
