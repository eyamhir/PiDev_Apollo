package org.example.test;
import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;
import org.example.model.Artiste;
import org.example.model.Client;
import org.example.model.Conversation;
import org.example.model.Participant;
import org.example.model.Message;
import org.example.services.Service_Artiste;
import org.example.services.Service_Client;
import org.example.services.Service_Conversation;
import org.example.services.Service_Message;
import org.example.services.Service_Participant;
import org.example.utils.MaConnexion;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        MaConnexion db = MaConnexion.getInstance();
        Service_Artiste serviceArtiste = new Service_Artiste();
        Service_Client serviceClient = new Service_Client();
        Service_Message serviceMessage = new Service_Message();
        Service_Conversation serviceConversation = new Service_Conversation();
Service_Participant serviceParticipant=new Service_Participant();
        // Création d'un artiste
     /*   Artiste artiste1 = new Artiste();
        artiste1.setSpecialite_Artistique("peintre");

        try {
            // Création d'un artiste
            serviceArtiste.creerArtiste(artiste1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
*/
        // Lecture des données artiste
     /*   Artiste artisteLu = serviceArtiste.lire_Donnée_Artiste(1);
        System.out.println("Artiste lu : " + artisteLu);*/

        // Mise à jour de l'artiste
     /*   artisteLu.setSpecialite_Artistique("Musique Classique");
        serviceArtiste.mettreAJourArtiste(artisteLu);
        System.out.println("Artiste mis à jour : " + artisteLu);*/

        // Suppression d'un artiste
      //  serviceArtiste.supprimerArtiste(1);

        // Création d'un client
     /*   Client client1 = new Client();

        try {
            // Création d'un client
            serviceClient.creerClient(client1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        // Lecture des données client

      /*  Client clientLu = serviceClient.lire_Donnée_Client(1);
        Client clientLu2 = serviceClient.lire_Donnée_Client(4);
        System.out.println("Client lu : " + clientLu);
        System.out.println("Client lu : " + clientLu2);*/

        // Mise à jour du client
      /*  clientLu2.setClient_Id(2);
        serviceClient.mettreAJourClient(clientLu2);
        System.out.println("Client mis à jour : " + clientLu2);*/

        // Suppression d'un client
      //  serviceClient.supprimerClient(4);

        // Création d'une conversation
  /*      Conversation conversation1 = new Conversation();
        conversation1.setSujet("l'art et la nature");
        conversation1.setTitre("l'art et la nature ");
        conversation1.setTypeConversation(Conversation_Type.PRIVATE); // Utilisation du setter correct
        conversation1.setDescription("la relation entre la nature et l'art et comment améliorer cette relation");
        conversation1.setVisibilite(Visibilite.PUBLIC);
        conversation1.setDateCreation(new Date()); // Utilisation de la date actuelle pour la création
        conversation1.setDateFin(new Date()); // La date de fin peut être nulle pour le moment

        try {
            // Création d'une conversation
            serviceConversation.creerConversation(conversation1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
*/
        // Lecture des données conversation
    /* Conversation conversationLu = serviceConversation.lireConversation(1);
        System.out.println("Conversation lue : " + conversationLu);

        // Mise à jour de la conversation
        conversationLu.setVisibilite(Visibilite.PRIVATE);
        serviceConversation.mettreAJourConversation(conversationLu); // Correction du nom de la méthode
        System.out.println("Conversation mise à jour : " + conversationLu);*/

        // Suppression d'une conversation
    //    serviceConversation.supprimerConversation(1);

        // Création d'entité participant
        /*Participant participant = new Participant();
        participant.setArtisteId(1);
        participant.setClientId(2);
        participant.setConversationId(1);
        participant.setArtisteId(1);*/
        // Création d'un participant
        //serviceParticipant.ajouterParticipant(participant);

        // Lecture des données du participant
     /*   Participant participantLu = serviceParticipant.getParticipantParID(1);
        System.out.println("participant lu : " + participantLu);*/

        // Mise à jour du entité participant
      /*  participantLu.setConversationId(2);
        serviceParticipant.modifierStatut(participant,"active");
        System.out.println("Message mis à jour : " + participantLu);*/

        // Suppression du participant
       // serviceParticipant.supprimerParticipant(1);




        // Création d'un Message
      /* Message message1 = new Message();
        message1.setIdDestinataire(1);
        message1.setIdExpediteur(1);
        message1.setDateEnvoi(new Date()); // Utilisation de la date actuelle pour l'envoi
        message1.setConversationId(1);
        message1.setContenu("le premier message");

        try {
            // Création d'un message
            serviceMessage.creerMessage(message1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        // Lecture des données du message
        /*Message messageLu = serviceMessage.lireMessage(1);
        System.out.println("Message lu : " + messageLu);*/

        // Mise à jour du message
     /*   messageLu.setContenu("Bonjour");
        serviceMessage.mettreAJourMessage(messageLu);
        System.out.println("Message mis à jour : " + messageLu);*/

        // Suppression du message
//        serviceMessage.supprimerMessage(1);

        // Liste des messages
   /*     List<Message> messages = serviceMessage.listerMessages();
        System.out.println("Liste des messages : ");
        for (Message message : messages) {
            System.out.println(message);
        }*/
    }



}
