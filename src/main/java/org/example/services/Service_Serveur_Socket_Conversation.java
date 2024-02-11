package org.example.services;

import org.example.interfaces.Interface_Serveur_Socket;
import org.example.model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service_Serveur_Socket_Conversation implements Interface_Serveur_Socket {
    private ServerSocket serverSocket;
    private List<Message> messages;

    public Service_Serveur_Socket_Conversation() {
        messages = new ArrayList<>();
    }

    @Override
    public void demarrerServeur() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 8080)); // Utilisez le port que vous souhaitez
    }

    @Override
    public void arreterServeur() throws IOException {
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
    }

    @Override
    public void gererConnexions() throws IOException {
        while (true) {
            Socket socketClient = serverSocket.accept();
            new Thread(() -> {
                try {
                    gererMessage(socketClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void gererMessage(Socket socketClient) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        PrintWriter writer = new PrintWriter(socketClient.getOutputStream(), true);

        // Lecture du message envoyé par le client
        String contenuMessage = reader.readLine();

        // Création de la date d'envoi du message
        Date dateEnvoi = new Date(); // Utilisation de la date actuelle

        // Création du message
        Message message = new Message(1, 2, dateEnvoi, contenuMessage, 3);

        // Ajout du message à la liste des messages
        messages.add(message);

        // Affichage du message reçu côté serveur
        System.out.println("Nouveau message reçu : " + contenuMessage);

        // Réponse au client
        writer.println("Message reçu avec succès !");
    }
}
