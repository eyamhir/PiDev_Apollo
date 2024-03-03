package org.example.controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.example.enumarate.Conversation_Type;
import org.example.enumarate.EmojiPicker;
import org.example.model.*;
import org.example.services.Service_Conversation;
import org.example.services.Service_Message;
import org.example.services.Service_Utilisateur;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
public class message_expediteur implements Initializable {
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
    private Service_Message messageService;
    private Service_Conversation conversationService;
    private Service_Utilisateur utilisateurService;
    private Socket socket;

    private Server server;
    // Créer une liste de sockets pour les autres clients connectés
    private List<UtilisateurHandler> clients;
    private UtilisateurHandler utilisateurHandler;

    @FXML
    public Button emojiButton;
    @FXML
    public TextField SearchId;
    @FXML
    Text textId;
    private String nom_sender;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageService = new Service_Message();
        conversationService = new Service_Conversation();
        utilisateurService = new Service_Utilisateur();
        try {
            Conversation conversationa = conversationService.recupererConversationEnCours();
            if(conversationa.getTypeConversation()==Conversation_Type.DUO){
                afficherContenusMessages();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Utilisateur expediteur = utilisateurService.getUtilisateurParId(3);
            nom_sender = expediteur.getNom();
            textId.setText(nom_sender);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        afficherDestinatairesDansConversation();

        // Ajout d'un écouteur pour ajuster la valeur de défilement lorsque la hauteur de VBoxMessages change
        VboxMessages.heightProperty().addListener((observableValue, oldValue, newValue) ->
                sp_main.setVvalue(newValue.doubleValue()));

        // Démarrage du serveur en arrière-plan
      /*  new Thread(() -> {
            try {
                server = new Server(); // Créer une instance de Server
                socket = new Socket("localhost", 3003);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                clients = new ArrayList<>();
                utilisateurHandler = new UtilisateurHandler(socket, clients);
                utilisateurHandler.recevoirMessages();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            server.accepterConnexions();
        }).start();*/
        new Thread(this::startServer).start();
    }
    private void startServer() {
        try {
            server = new Server();
            socket = new Socket("localhost", 3003);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            clients = new ArrayList<>();
            utilisateurHandler = new UtilisateurHandler(socket, clients);
            utilisateurHandler.recevoirMessages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.accepterConnexions();
    }

   private void afficherContenusMessages() {
       try {
           // Récupérer l'utilisateur expéditeur
           Utilisateur expediteur = utilisateurService.getUtilisateurParId(3); // Remplacez 3 par l'ID de l'utilisateur connecté
           int id_expediteur = expediteur.getId_utilisateur();

           // Récupérer l'utilisateur destinataire
           Conversation conversation = conversationService.recupererConversationEnCours();

           if (conversation.getTypeConversation() == Conversation_Type.DUO) {
               // Récupérer les contenus des messages pour une conversation en duo
               Utilisateur destinataire = utilisateurService.getUtilisateurParId(Liste_Amis_Conversation_duo.getId_utilisateur_selectionnee());
               int destinataire_id = destinataire.getId_utilisateur();
               List<String> contenusMessages = messageService.recupererContenusMessagesParExpediteurEtDestinataire(id_expediteur, destinataire_id);

               // Effacer tout contenu précédent dans VBoxMessages
               VboxMessages.getChildren().clear();

               // Ajouter chaque contenu de message à VBoxMessages
               for (String contenu : contenusMessages) {
                   // Création de la HBox pour chaque message
                   HBox messageBox = new HBox();
                   messageBox.setAlignment(Pos.CENTER_RIGHT);
                   messageBox.setPrefHeight(10);
                   messageBox.setPadding(new Insets(5, 5, 0, 10));
                   // Création du texte du message avec le style spécifié
                   Text message = new Text(contenu);
                   message.setFont(Font.font("Arial", FontWeight.NORMAL, 14)); // Police normale et taille de police
                   message.setFill(Color.DARKBLUE); // Couleur du texte
                   // Création du TextFlow pour le message
                   TextFlow textFlow = new TextFlow(message);
                   textFlow.setStyle("-fx-background-color: #E8DED1; -fx-font-weight: bold; -fx-color: white; -fx-background-radius: 20px");
                   textFlow.setPadding(new Insets(5, 10, 5, 10));
                   // Ajout du TextFlow dans la HBox
                   messageBox.getChildren().add(textFlow);
                   // Ajout de la HBox pour le message dans le VBox des messages
                   VboxMessages.getChildren().add(messageBox);
               }
           } else if (conversation.getTypeConversation() == Conversation_Type.GROUP) {
               Utilisateur destinataire = utilisateurService.getUtilisateurParId(Liste_Amis_Conversation_Groupe.getId_utilisateur_selectionnee_group());
               int destinataire2 = destinataire.getId_utilisateur();
           }
       } catch (SQLException e) {
           e.printStackTrace();
           // Gérer l'exception SQLException
       }
   }
    public void attachedButtonOnAction(ActionEvent actionEvent) {
        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getDirectory() + dialog.getFile();
        dialog.dispose();
         sendImage(file);
        System.out.println(file + " chosen.");
    }
    private void sendImage(String msgToSend) {
        Image image = new Image(msgToSend);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        TextFlow textFlow = new TextFlow(imageView);
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5,5,5,10));
        hBox.getChildren().add(imageView);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        VboxMessages.getChildren().add(hBox);
        try {
            dataOutputStream.writeUTF(nom_sender + "-" +msgToSend);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean verif(String message){
        List<String> badWords = new ArrayList<>();
        badWords.add("Connard");
        badWords.add("Va te faire foutre");
        badWords.add("Ferme ta gueule");
        badWords.add("Bâtard");
        badWords.add("Trou du cul");
        badWords.add("Sac à merde");
        badWords.add("Casse-couilles");
        badWords.add("Enfoiré");
        badWords.add("Tête de bite");
        badWords.add("Pas le couteau le plus affûté du tiroir");
        badWords.add("Fini à la pisse");
        badWords.add("Pétasse");
        badWords.add("Abruti");
        badWords.add("Pas la lumière à tous les étages");
        badWords.add("Cn");
        badWords.add("Sale merde");
        badWords.add("Tocard");
        badWords.add("Sous-merde");
        badWords.add("Mange-merde");
        badWords.add("Pouffiasse");
        badWords.add("Va te faire cuire le cul");
        badWords.add("Bercé un peu trop près du mur");
        badWords.add("Petite bite");
        badWords.add("Bouffon");
        badWords.add("Branleur");
        badWords.add("Grognasse");
        badWords.add("Couille molle");
        badWords.add("Branquignole");
        badWords.add("Fils de chien");
        badWords.add("Salaud");
        badWords.add("cul");
        badWords.add("fuck");
        badWords.add("pute");
        badWords.add("ass");
        badWords.add("bite");
        badWords.add("cnne");

        for (String badWord : badWords) {
            if (message.toLowerCase().contains(badWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void SendMessage() {
        String messageText = message_id.getText().trim();

        // Vérifier si le message contient des mots interdits
        if (verif(messageText)) {
            // Afficher un dialogue indiquant que le message contient des mots interdits
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Message Contient des Mots Interdits");
            alert.setHeaderText(null);
            alert.setContentText("Votre message contient des mots interdits. Veuillez modifier votre message.");
            alert.showAndWait();
            return; // Ne pas envoyer le message si des mots interdits sont détectés
        }

        //  service_message_firebase.sendMessage(messageText);
        if (!messageText.isEmpty()) {
            try {
                Utilisateur sender = utilisateurService.getUtilisateurParId(3);
                Utilisateur receiver = utilisateurService.getUtilisateurParId(Liste_Amis_Conversation_duo.getId_utilisateur_selectionnee());
                Conversation conversation = conversationService.recupererConversationEnCours();

                if (conversation.getTypeConversation() == Conversation_Type.GROUP && utilisateurHandler != null) {
                    utilisateurHandler.diffuserMessage(messageText);
                } else if (conversation.getTypeConversation() == Conversation_Type.DUO && utilisateurHandler != null) {
                    utilisateurHandler.envoyerMessage(messageText);
                }
                int conversationId = conversation.getConversationId();
                if (conversation.getTypeConversation() == Conversation_Type.DUO) {
                    messageService.creerMessage(new Message(new Date(), messageText, conversationId, sender.getId_utilisateur(), receiver.getId_utilisateur()));
                } else if (conversation.getTypeConversation() == Conversation_Type.GROUP) {
                    messageService.creerMessage(new Message(new Date(), messageText, conversationId, sender.getId_utilisateur(), 0));
                }
                addMessageToView(sender.getNom(), messageText);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle SQLException
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
        message.setFont(Font.font("Arial", FontWeight.MEDIUM, 16)); // Police en gras et taille de la police
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

    // Ajouter la méthode recevoirMessage dans la classe MessageController
   private void recevoirMessage(String message) {
        // Écouter en continu les messages du serveur et les afficher dans l'interface utilisateur
        new Thread(() -> {
            while (true) {
                utilisateurHandler.recevoirMessages();
                if (message != null && !message.isEmpty()) {
                    // Traiter le message reçu, par exemple, l'afficher dans l'interface utilisateur
                    Platform.runLater(() -> addMessageToView("Serveur", message));
                }
            }
        }).start();
    }
    public static void receiveMessage(String msg, VBox vBox) throws IOException {
        if (msg.matches(".*\\.(png|jpe?g|gif)$")){
            HBox hBoxName = new HBox();
            hBoxName.setAlignment(Pos.CENTER_LEFT);
            Text textName = new Text(msg.split("[-]")[0]);
            TextFlow textFlowName = new TextFlow(textName);
            hBoxName.getChildren().add(textFlowName);

            Image image = new Image(msg.split("[-]")[1]);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5,5,5,10));
            hBox.getChildren().add(imageView);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    vBox.getChildren().add(hBoxName);
                    vBox.getChildren().add(hBox);
                }
            });

        }else {
            String name = msg.split("-")[0];
            String msgFromServer = msg.split("-")[1];

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5,5,5,10));

            HBox hBoxName = new HBox();
            hBoxName.setAlignment(Pos.CENTER_LEFT);
            Text textName = new Text(name);
            TextFlow textFlowName = new TextFlow(textName);
            hBoxName.getChildren().add(textFlowName);

            Text text = new Text(msgFromServer);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle("-fx-background-color: #abb8c3; -fx-font-weight: bold; -fx-background-radius: 20px");
            textFlow.setPadding(new Insets(5,10,5,10));
            text.setFill(Color.color(0,0,0));

            hBox.getChildren().add(textFlow);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    vBox.getChildren().add(hBoxName);
                    vBox.getChildren().add(hBox);
                }
            });
        }
    }
    @FXML
    public void Chercher_Destinataire(ActionEvent event) {
        // Vérifie si l'événement provient du bouton
        if (event.getSource() instanceof Button) {
            String nomPrenom = SearchId.getText().trim();

            try {
                List<String> nomsPrenomsDestinataires = messageService.chercherDestinataireParNomPrenom(nomPrenom);
                ListeParticipant.getItems().clear();

                if (!nomsPrenomsDestinataires.isEmpty()) {
                    ListeParticipant.getItems().addAll(nomsPrenomsDestinataires);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Aucun Destinataire Trouvé");
                    alert.setHeaderText(null);
                    alert.setContentText("Aucun destinataire trouvé avec le nom ou prénom : " + nomPrenom);
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Affichage d'une boîte de dialogue d'erreur en cas d'erreur SQL
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur dans la recherche ");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur s'est produite lors de la recherche des destinataires.");
                alert.showAndWait();
            }
        }
    }
    public void closeServer() throws SQLException {
        utilisateurHandler.arreterCommunication();
        server.arreterServeur();

        // Mettre à jour la date de fin de la conversation en cours
        try {
            java.util.Date dateFin = new java.util.Date(); // Date actuelle
            conversationService.mettreAJourDateFinConversationEnCours(dateFin);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la date de fin de la conversation : " + e.getMessage());
        }
        // Afficher une boîte de dialogue de confirmation pour fermer le chat
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Fermeture du chat");
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir fermer le chat ?");

        // Attendre la réponse de l'utilisateur
        confirmationDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Fermeture confirmée, vous pouvez insérer ici d'autres actions à effectuer avant la fermeture du chat
                System.exit(0); // Par exemple, fermer l'application
            }
        });
    }
}
