package org.example.services;

import org.example.interfaces.Interface_Serveur_Socket;
import org.example.model.Conversation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;

public class Service_Serveur_Socket implements Interface_Serveur_Socket {
    private ServerSocket serverSocket;
    private Service_Conversation serviceConversation = new Service_Conversation();

    @Override
    public void demarrerServeur() throws IOException {
        // Créer le socket serveur et démarrer le serveur
        serverSocket = new ServerSocket(2222);
        System.out.println("Serveur de chat démarré sur le port " + serverSocket);
    }

    @Override
    public void arreterServeur(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) throws IOException {
        try {
            Conversation service_conversation = serviceConversation.recupererConversationEnCours();
            if (service_conversation != null) {
                // Définir la date de fin comme la date actuelle
                service_conversation.setDateFin(new Date());
                // Mettre à jour la conversation avec la nouvelle date de fin
                serviceConversation.mettreAJourConversation(service_conversation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Fermer les flux et le socket
            bufferedReader.close();
            bufferedWriter.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void gererConnexions() throws IOException {
        while (!serverSocket.isClosed()) {
            // Accepter une connexion entrante
            Socket clientSocket = serverSocket.accept();

            // Créer un thread pour gérer la connexion client
            Thread clientThread = new Thread(() -> {
                try {
                    // Initialiser les flux d'entrée/sortie pour la communication avec le client
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    // Traiter les messages du client, par exemple, lire les messages et y répondre
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Message reçu du client : " + message);
                        // Diffuser le message à tous les autres clients connectés
                        diffuserMessage(message, writer);
                    }
                    // Une fois que la communication est terminée, fermer les flux et le socket client
                    reader.close();
                    writer.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            // Démarrer le thread client
            clientThread.start();
        }
    }
    private synchronized void diffuserMessage(String message, BufferedWriter sender) throws IOException {
        for (Thread clientThread : Thread.getAllStackTraces().keySet()) {
            if (clientThread.getName().startsWith("Thread-")) {
                Object clientObject = clientThread.getContextClassLoader();
                if (clientObject instanceof Socket) {
                    Socket clientSocket = (Socket) clientObject;
                    // Envoyer le message à tous les autres clients connectés
                    BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    if (clientWriter != sender) {
                        clientWriter.write(message);
                        clientWriter.newLine();
                        clientWriter.flush();
                    }
                }
            }
        }
    }
        }
