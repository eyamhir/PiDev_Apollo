package org.example.services;

import org.example.enumarate.Conversation_Type;
import org.example.interfaces.Interface_Serveur_Socket;
import org.example.model.Conversation;
import org.example.model.Message;
import org.example.services.Service_Conversation;
import org.example.services.Service_Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service_Serveur_Socket_Conversation implements Interface_Serveur_Socket {
    private ServerSocket serverSocket;
    private List<Message> messages;
    private Service_Message serviceMessage; // Service pour gérer les messages
    private Service_Conversation serviceConversation; // Service pour gérer les conversations

    public Service_Serveur_Socket_Conversation() {
        messages = new ArrayList<>();
        serviceMessage = new Service_Message(); // Initialisation du service des messages
        serviceConversation = new Service_Conversation(); // Initialisation du service des conversations
    }

    @Override
    public void demarrerServeur() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 9000));
            System.out.println("Le serveur est actif et en écoute sur le port 9000.");
        } catch (IOException e) {
            System.err.println("Erreur lors du démarrage du serveur : " + e.getMessage());
        }
    }

    public void demarrerServeur2() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 9090));
            System.out.println("Le serveur est actif et en écoute sur le port 9090.");
        } catch (IOException e) {
            System.err.println("Erreur lors du démarrage du serveur : " + e.getMessage());
        }
    }

    @Override
    public void arreterServeur() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'arrêt du serveur : " + e.getMessage());
        }
    }

    @Override
    public void gererConnexions() {
        try {
            while (true) {
                Socket socketClient = this.serverSocket.accept();
                new Thread(() -> {
                    try {
                        gererMessage(socketClient);
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la gestion des connexions : " + e.getMessage());
        }
    }

    private void gererMessage(Socket socketClient) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        PrintWriter writer = new PrintWriter(socketClient.getOutputStream(), true);

        // Lecture du message envoyé par le client
        String contenuMessage = reader.readLine();

        // Création de la date d'envoi du message
        Date dateEnvoi = new Date(); // Utilisation de la date actuelle

        // Extrait de l'identifiant de la conversation du message
        int conversationId = Integer.parseInt(contenuMessage.split(":")[0]);

        // Extrait le contenu du message
        String contenu = contenuMessage.substring(contenuMessage.indexOf(":") + 1);

        // Création de l'objet Message
        Message message = new Message(1, 2, dateEnvoi, contenu, conversationId); // Utilisez des valeurs temporaires pour l'expéditeur et le destinataire, ajustez en conséquence

        // Ajout du message à la liste des messages
        messages.add(message);

        // Enregistrement du message dans la base de données
        try {
            serviceMessage.creerMessage(message);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création du message : " + e.getMessage());
        }

        // Vérifie s'il s'agit d'une conversation entre deux personnes ou un groupe
        Conversation conversation = serviceConversation.lireConversation(conversationId);
        if (conversation != null) {
            Conversation_Type typeConversation = conversation.getTypeConversation();
            switch (typeConversation) {
                case PRIVATE:
                    // Si la conversation est entre deux personnes
                    // Gérer le message comme d'habitude
                    // Réponse au client
                    writer.println("Message reçu avec succès !");
                    break;
                case GROUP:
                    // Si la conversation est un groupe
                    // Traitez le message en conséquence pour le groupe
                    // Réponse au client
                    writer.println("Message reçu avec succès pour le groupe !");
                    break;
                default:
                    // Si le type de conversation n'est ni privé ni groupe, informez le client
                    writer.println("Type de conversation non pris en charge !");
            }
        } else {
            // Si la conversation n'existe pas, informez le client
            writer.println("Conversation non trouvée !");
        }

        // Vérifie si le message est destiné à un autre serveur
        if (contenuMessage.startsWith("SERVER:")) {
            // Extrait l'adresse IP et le port du serveur de destination
            String[] parts = contenuMessage.split(":");
            String adresseServeur = parts[1];
            int portServeur = Integer.parseInt(parts[2]);

            // Extrait le contenu du message
            contenu = parts[3];

            // Envoie le message à l'autre serveur
            envoyerMessage(contenu, adresseServeur, portServeur);
        }
    }

    // Méthode pour envoyer un message à un autre serveur
    // Ajoutez cette méthode à votre classe Service_Serveur_Socket_Conversation

    public void envoyerMessage(String contenuMessage, String adresseDestinataire, int portDestinataire) {
        try {
            // Création d'une connexion au serveur de destination
            Socket socket = new Socket(adresseDestinataire, portDestinataire);

            // Création des flux de sortie pour envoyer le message au serveur
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Envoi du message au serveur
            writer.println(contenuMessage);

            // Fermeture de la connexion
            socket.close();

            System.out.println("Message envoyé avec succès au serveur : " + adresseDestinataire + ":" + portDestinataire);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'envoi du message au serveur : " + e.getMessage());
        }
    }

}
