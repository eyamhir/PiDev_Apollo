package org.example.services;

import org.example.interfaces.Interface_Client_Chat;
import org.example.model.Message;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class Service_Client_Chat implements Interface_Client_Chat {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private List<Socket> otherClients; // Liste des sockets des autres utilisateurs

    public Service_Client_Chat(List<Socket> otherClients) {
        this.otherClients = otherClients;
    }

    @Override
    public void demarrerClient() throws IOException {
        // Établir une connexion avec le serveur de chat

        socket = new Socket("localhost", 2222); // Remplacez "localhost" par l'adresse IP ou le nom d'hôte du serveur

        // Initialiser les flux d'entrée/sortie une seule fois
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        System.out.println("Client de chat démarré.");

        // Démarrer un thread pour recevoir les messages du serveur et des autres utilisateurs en continu
        new Thread(this::recevoirMessage).start();
    }

    @Override
    public void envoyerMessage(String message) throws IOException {
        // Vérifier si le flux d'écriture est null
        if (writer != null) {
            // Envoyer un message au serveur
            writer.write(message);
            writer.newLine(); // Ajouter un saut de ligne
            writer.flush(); // Assurer que le message est envoyé

            // Envoyer le message à tous les autres utilisateurs connectés
            for (Socket client : otherClients) {
                BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                clientWriter.write(message);
                clientWriter.newLine();
                clientWriter.flush();
            }
        } else {
            throw new IOException("Le flux d'écriture n'est pas initialisé.");
        }
    }

    @Override
    public String recevoirMessage() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Message reçu : " + message);
                // Traiter le message reçu, par exemple, l'afficher à l'utilisateur
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Retourner une chaîne vide si une exception se produit pour éviter le blocage de la méthode
        return "";
    }


    // Méthode pour recevoir les messages du serveur et des autres utilisateurs en continu


    @Override
    public void fermerConnexion() throws IOException {
        // Fermer les flux et la connexion au serveur
        reader.close();
        writer.close();
        socket.close();
        System.out.println("Connexion au serveur fermée.");
    }
}


/*package org.example.services;

import org.example.interfaces.Interface_Client_Chat;
import org.example.model.Message;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class Service_Client_Chat implements Interface_Client_Chat {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    @Override
    public void demarrerClient() throws IOException {
        // Établir une connexion avec le serveur de chat
        socket = new Socket("localhost", 2222); // Remplacez "localhost" par l'adresse IP ou le nom d'hôte du serveur

        // Initialiser les flux d'entrée/sortie une seule fois
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        System.out.println("Client de chat démarré.");
    }

    @Override
    public void envoyerMessage(String message) throws IOException {
        // Vérifier si le flux d'écriture est null
        if (writer != null) {
            // Envoyer un message au serveur
            writer.write(message);
            writer.newLine(); // Ajouter un saut de ligne
            writer.flush(); // Assurer que le message est envoyé
        } else {
            throw new IOException("Le flux d'écriture n'est pas initialisé.");
        }
    }
    @Override
    public String recevoirMessage() throws IOException {
        // Recevoir un message du serveur
        return reader.readLine();
    }

    @Override
    public void fermerConnexion() throws IOException {
        // Fermer les flux et la connexion au serveur
        reader.close();
        writer.close();
        socket.close();
        System.out.println("Connexion au serveur fermée.");
    }
}
*/