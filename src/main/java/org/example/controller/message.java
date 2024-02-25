package org.example.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.example.model.Conversation;
import org.example.model.Message;
import org.example.model.Utilisateur;
import org.example.services.*;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class message implements Initializable {
    @FXML
    public BorderPane pane;
    @FXML
    private TextField message_id;
    @FXML
    private ListView<String> ListeParticipant;
    @FXML
    private VBox VboxMessages;
    @FXML
    private ScrollPane sp_main;
    @FXML
    public Button emojibutton;

    private Service_Message messageService;
    private Service_Conversation conversationService;
    private Service_Utilisateur utilisateurService;
    private Service_Serveur_Socket serviceServeurSocket;
    private Service_Client_Chat serviceClientChat;
    private Service_Amies service_amies;
    private int utilisateurSelectionneId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageService = new Service_Message();
        conversationService = new Service_Conversation();
        utilisateurService = new Service_Utilisateur();
        serviceServeurSocket = new Service_Serveur_Socket();
        service_amies=new Service_Amies();

        // Créer une liste de sockets pour les autres clients connectés
        List<Socket> autresClients = new ArrayList<>();
        serviceClientChat = new Service_Client_Chat(autresClients);

        afficherDestinatairesDansConversation();

        // Démarrage du serveur en arrière-plan
        new Thread(() -> {
            try {
                serviceServeurSocket.demarrerServeur();
                //serviceServeurSocket.gererConnexions();  -> Ne pas appeler ici, sera géré par l'envoi de message
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Ajout d'un écouteur pour ajuster la valeur de défilement lorsque la hauteur de VBoxMessages change
        VboxMessages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });
    }

    @FXML
    private void SendMessage() {
        String messageText = message_id.getText().trim();
        if (!messageText.isEmpty()) {
            try {
                Utilisateur utilisateur = utilisateurService.getUtilisateurParId(3); // Remplacer 2 par l'ID de l'utilisateur connecté
                Utilisateur utilisateur1 = utilisateurService.getUtilisateurParId(utilisateurSelectionneId);
                Conversation conversation = conversationService.recupererConversationEnCours();

                // Démarrer le client chat avant d'envoyer le message
                serviceClientChat.demarrerClient();
               // serviceServeurSocket.gererConnexions();
                serviceClientChat.envoyerMessage(messageText);

                int conversationId = conversation.getConversationId();
                messageService.creerMessage(new Message(new Date(), messageText, conversationId, utilisateur.getId_utilisateur(), utilisateur1.getId_utilisateur()));
                addMessageToView(utilisateur.getNom(), messageText);

            } catch (SQLException | IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de l'envoi du message.", e);
            }
            message_id.clear();
        }
    }

    private void addMessageToView(String senderName, String messageText) {
        HBox messageBox = new HBox();
        messageBox.setAlignment(Pos.CENTER_RIGHT);
        messageBox.setPrefHeight(10);
        messageBox.setPadding(new Insets(5, 5, 0, 10));

        // Création du texte du message avec le style spécifié
        Text message = new Text(senderName + ": " + messageText);
        message.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));// Police en gras et taille de la police
        message.setFill(Color.DARKBLUE); // Couleur du texte

        // Création du TextFlow pour le message
        TextFlow textFlow = new TextFlow(message);
        textFlow.setStyle("-fx-background-color: #E8DED1; -fx-font-weight: bold; -fx-color: white; -fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        // Ajout du TextFlow dans la HBox
        messageBox.getChildren().add(textFlow);

        // Création de la HBox pour l'heure du message
        HBox hBoxTime = new HBox();
        hBoxTime.setAlignment(Pos.CENTER_RIGHT);
        hBoxTime.setPadding(new Insets(0, 5, 5, 10));

        // Récupération de l'heure actuelle
        String stringTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        Text time = new Text(stringTime);
        time.setStyle("-fx-font-size: 8");

        // Ajout de l'heure dans la HBox
        hBoxTime.getChildren().add(time);

        // Ajout des HBox au VBox des messages
        VboxMessages.getChildren().addAll(messageBox, hBoxTime);

    }

    private void afficherDestinatairesDansConversation() {
        Conversation conversation;
        try {
            conversation = conversationService.recupererConversationEnCours();
            List<String> destinataires = messageService.recupererNomsPrenomsDestinatairesDansConversation(conversation.getConversationId());
            ListeParticipant.getItems().addAll(destinataires);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'affichage des destinataires dans la conversation.", e);
        }
    }


    public void initialize(int utilisateurSelectionneId) {
        this.utilisateurSelectionneId = utilisateurSelectionneId;
    }
}
