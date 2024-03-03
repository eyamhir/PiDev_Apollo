package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.example.model.Conversation;
import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;
import org.example.services.Service_Conversation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import org.example.services.Service_Utilisateur;

public class Conversation_Controller  {

    // Widgets
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
    private Service_Utilisateur service_utilisateur = new Service_Utilisateur();
    private int idUtilisateur =3; // Identifiant de l'utilisateur


   /* @FXML
    void addConversation(ActionEvent event) {
        String typeValue = type.getValue(); // Récupérer la valeur sélectionnée dans le ComboBox
        Conversation_Type typeEnum = Conversation_Type.valueOf(typeValue.toUpperCase());
        String vesValue = ves.getValue(); // Récupérer la valeur sélectionnée dans le ComboBox
        Visibilite vesEnum = Visibilite.valueOf(vesValue.toUpperCase());
        // Vérifier si les champs obligatoires sont videss
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
            // Créer la conversation avec les données saisies
            Conversation conversation = new Conversation(
                    titreTF.getText(),
                    sujetTF.getText(),
                    descriptionTA.getText(),
                    new Timestamp(new Date().getTime()),
                    new Timestamp(new Date().getTime()),
                    typeEnum,
                    vesEnum,
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Liste_Participant_groupe.fxml"));
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
*/
   @FXML
   void addConversation(ActionEvent event) {
       // Récupération des valeurs sélectionnées dans les ComboBox
       String typeValue = type.getValue();
       Conversation_Type typeEnum = Conversation_Type.valueOf(typeValue.toUpperCase());
       String vesValue = ves.getValue();
       Visibilite vesEnum = Visibilite.valueOf(vesValue.toUpperCase());

       // Vérification si les champs obligatoires sont vides
       if (titreTF.getText().isEmpty() || sujetTF.getText().isEmpty() || descriptionTA.getText().isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Erreur de saisie");
           alert.setHeaderText(null);
           alert.setContentText("Veuillez remplir tous les champs obligatoires.");
           alert.showAndWait();
           return; // Arrêter l'exécution de la méthode si les champs obligatoires sont vides
       }

       // Vérification si la date de fin est postérieure à la date de début
       Date dateDebut = new Date();
       Date dateFin = new Date();
       if (dateFin.before(dateDebut)) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Erreur de date");
           alert.setHeaderText(null);
           alert.setContentText("La date de fin doit être postérieure à la date de début.");
           alert.showAndWait();
           return; // Arrêter l'exécution de la méthode si la date de fin est antérieure à la date de début
       }

       try {
           // Création de la conversation avec les données saisies
           Conversation conversation = new Conversation(
                   titreTF.getText(),
                   sujetTF.getText(),
                   descriptionTA.getText(),
                   new Timestamp(dateDebut.getTime()),
                   null,
                   typeEnum,
                   vesEnum,
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
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/Liste_Participant_groupe.fxml"));
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


    public void initialize (){
        ObservableList<String> types= FXCollections.observableArrayList(
                "GROUP","DUO");
        type.setItems(types);
        ObservableList<String> visibilite= FXCollections.observableArrayList(
                "PRIVATE","PUBLIC");
        ves.setItems(visibilite);

    }
}
