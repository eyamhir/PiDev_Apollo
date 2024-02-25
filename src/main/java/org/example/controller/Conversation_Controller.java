package org.example.controller;

import com.mysql.cj.xdevapi.JsonArray;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.example.model.Conversation;
import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;
import org.example.model.Utilisateur;
import org.example.services.Service_Conversation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.services.Service_Utilisateur;

public class Conversation_Controller {

    // Widgets
    @FXML
    private TextField titreTF;
    @FXML
    private TextField sujetTF;
    @FXML
    private TextArea descriptionTA;
    @FXML
    private MenuButton VisibiliteTF;
    @FXML
    private MenuButton TypeTF;

    private Service_Conversation serviceConversation = new Service_Conversation();
    private Service_Utilisateur service_utilisateur = new Service_Utilisateur();
    private int idUtilisateur = 2; // Identifiant de l'utilisateur

    @FXML
    void addConversation(ActionEvent event) {
        // Vérifier si les champs obligatoires sont vides
        if (titreTF.getText().isEmpty() || sujetTF.getText().isEmpty() || descriptionTA.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs obligatoires.");
            alert.showAndWait();
            return; // Arrêter l'exécution de la méthode si les champs obligatoires sont vides
        }

        try {
            // Récupérer la visibilité sélectionnée
            String visibiliteString = (String) VisibiliteTF.getItems().get(1).getUserData();
            Visibilite visibilite = Visibilite.valueOf(visibiliteString);

            // Récupérer le type sélectionné
          /*  String typeString = (String) TypeTF.getItems().get(0).getUserData();
            Conversation_Type type = Conversation_Type.valueOf(typeString);
*/
            // Créer la conversation avec les données saisies
            Conversation conversation = new Conversation(
                    titreTF.getText(),
                    sujetTF.getText(),
                    descriptionTA.getText(),
                    new Date(),
                    new Date(),
                    Conversation_Type.GROUP,
                    visibilite,
                    idUtilisateur, // Utiliser l'identifiant de l'utilisateur
                    new ArrayList<>()
            );

            try {
                serviceConversation.creerConversation(conversation);

                // Affichage de l'alerte de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("La conversation a été ajoutée avec succès.");
                alert.showAndWait();

                // Fermer la fenêtre actuelle
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

                // Charger la nouvelle page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Liste_Conversation.fxml"));
                Parent root = loader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();

            } catch (SQLException e) {
                // Gestion des erreurs SQL
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalArgumentException e) {
            // Gestion des erreurs d'argument
            throw new RuntimeException(e);
        }
    }
}
