package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;
import org.example.model.Conversation;
import org.example.services.Service_Conversation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Modifier_Conversation  {

    @FXML
    private TextField titreTF;

    @FXML
    private TextField sujetTF;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<String> ves;

    private Service_Conversation serviceConversation = new Service_Conversation();

    private Conversation conversation;


    public void initialize() {
        ObservableList<String> types= FXCollections.observableArrayList(
                "GROUP","DUO");
        type.setItems(types);
        ObservableList<String> visibilite= FXCollections.observableArrayList(
                "PRIVATE","PUBLIC");
        ves.setItems(visibilite);

    }
    // Méthode pour initialiser les données de la conversation
    public void initData(int conversationId) {
        ObservableList<String> types= FXCollections.observableArrayList(
                "GROUP","DUO");
        type.setItems(types);
        ObservableList<String> visibilite= FXCollections.observableArrayList(
                "PRIVATE","PUBLIC");
        ves.setItems(visibilite);
        try {
            conversation = serviceConversation.lireConversationunique(conversationId);
            if (conversation != null) {
                titreTF.setText(conversation.getTitre());
                sujetTF.setText(conversation.getSujet());
                descriptionTA.setText(conversation.getDescription());

                // Setting the selected item for TypeTF and VisibiliteTF
                type.setItems(FXCollections.observableArrayList(types));
                type.getSelectionModel().select(conversationId);
                ves.setItems(FXCollections.observableArrayList(visibilite));
                ves.getSelectionModel().select(conversationId);

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
        String typeValue = type.getValue(); // Récupérer la valeur sélectionnée dans le ComboBox
        Conversation_Type typeEnum = Conversation_Type.valueOf(typeValue.toUpperCase());
        String vesValue = ves.getValue(); // Récupérer la valeur sélectionnée dans le ComboBox
        Visibilite vesEnum = Visibilite.valueOf(vesValue.toUpperCase());
        try {
            // Vérification des champs
         if(allFieldsFilled()) {
                // Récupération des valeurs des champs et conversion en types énumérés
                Conversation_Type conversationType = Conversation_Type.valueOf(type.getValue());
                Visibilite visibilite = Visibilite.valueOf(ves.getValue());
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
                type.getValue().isEmpty() ||
                ves.getValue().isEmpty()) {

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
