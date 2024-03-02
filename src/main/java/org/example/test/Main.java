package org.example.test;
import org.example.services.Service_Conversation;
import org.example.services.Service_Message;
import org.example.utils.MaConnexion;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        MaConnexion db = MaConnexion.getInstance();
        Service_Message serviceMessage = new Service_Message();
        Service_Conversation serviceConversation = new Service_Conversation();
/*        try {
            // Démarrer le premier serveur
            serveur1.demarrerServeur();
            System.out.println("Serveur 1 démarré sur le port 9000.");

            // Attendre quelques instants pour que le premier serveur soit actif
            Thread.sleep(2000); // Attendre 2 secondes

            // Deuxième serveur sur un port différent, par exemple, 9090
            Service_Serveur_Socket_Conversation serveur2 = new Service_Serveur_Socket_Conversation();
            // Démarrer le deuxième serveur
            serveur2.demarrerServeur2();
            System.out.println("Serveur 2 démarré sur le port 9090.");

            // Gérer les connexions entrantes pour les deux serveurs
            Thread threadServeur1 = new Thread(() -> {
                try {
                    serveur1.gererConnexions();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threadServeur1.start();

            Thread threadServeur2 = new Thread(() -> {
                try {
                    serveur2.gererConnexions();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threadServeur2.start();

            // Attendre que les deux serveurs soient actifs
            threadServeur1.join();
            threadServeur2.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

*/
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
    /*    Conversation conversation1 = new Conversation();
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
   /* Conversation conversationLu = serviceConversation.lireConversation(2);
        System.out.println("Conversation lue : " + conversationLu);

        // Mise à jour de la conversation
        conversationLu.setVisibilite(Visibilite.PRIVATE);
        conversationLu.setTitre("deuxiéme conversation");
        serviceConversation.mettreAJourConversation(conversationLu); // Correction du nom de la méthode
        System.out.println("Conversation mise à jour : " + conversationLu);*/

        // Suppression d'une conversation
    //       serviceConversation.supprimerConversation(2);


        // Création d'un Message
   /*    Message message1 = new Message();
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
        }
*/
        // Lecture des données du message
/*        Message messageLu = serviceMessage.lireMessage(1);
       System.out.println("Message lu : " + messageLu.getContenu());*/

       //Mise à jour du message
  /*     messageLu.setContenu("Bonjour");
        serviceMessage.mettreAJourMessage(messageLu);
        System.out.println("Message mis à jour : " + messageLu); */

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