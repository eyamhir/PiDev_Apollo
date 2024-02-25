package org.example.interfaces;

import java.io.IOException;

public interface Interface_Client_Chat {

    // Méthode pour démarrer le client de chat et se connecter au serveur
    void demarrerClient() throws IOException;

    // Méthode pour envoyer un message au serveur
    void envoyerMessage(String message) throws IOException;

    // Méthode pour recevoir un message du serveur
    String recevoirMessage() throws IOException;

    // Méthode pour fermer la connexion avec le serveur
    void fermerConnexion() throws IOException;
}
