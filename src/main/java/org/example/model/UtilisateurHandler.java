package org.example.model;
import org.example.services.Service_Message;


import java.io.*;
import java.net.Socket;
import java.util.List;

public class UtilisateurHandler {

    private Service_Message serviceMessage;
    private Socket socket;
    private List<UtilisateurHandler> clients;
    private BufferedReader reader;
    private BufferedWriter writer;

    private boolean running = true;

    public UtilisateurHandler(Socket socket, List<UtilisateurHandler> clients) {
        try {
            this.socket = socket;
            this.clients = clients;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.serviceMessage = new Service_Message();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void envoyerMessage(String message) {
        try {
           writer.write(message); // Utilisation du BufferedWriter pour envoyer le message
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recevoirMessages() {
        new Thread(() -> {
            while (running && socket.isConnected()) {
                try {
                    String message =reader.readLine();
                    diffuserMessage(message);
                } catch (IOException e) {
                    System.out.println("Erreur lors de la réception du message : " + e.getMessage());
                    arreterCommunication();
                }
            }
        }).start();
    }

    public void diffuserMessage(String message) {
        for (UtilisateurHandler clientHandler : clients) {
            if (clientHandler != this) {
                try {
                    clientHandler.writer.write(message);
                    clientHandler.writer.newLine();
                    clientHandler.writer.flush();
                } catch (IOException e) {
                    System.out.println("Erreur lors de l'envoi du message au client : " + e.getMessage());
                }
            }
        }
    }

    public void arreterCommunication() {
        running = false;
        try {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }
}
