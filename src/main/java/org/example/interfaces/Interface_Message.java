package org.example.interfaces;

import org.example.model.Artiste;
import org.example.model.Message;

import java.sql.SQLException;
import java.util.List;

public interface Interface_Message<T> {
    //CRUD Message
    void creerMessage(T t) throws SQLException;

    Message lireMessage(int id) throws SQLException;

    void mettreAJourMessage(T t) throws SQLException;

    void supprimerMessage(int id) throws SQLException;

    // Methode avancées

    List<T> listerMessages() throws SQLException;

    List<T> listerMessagesParConversation(int idConversation) throws SQLException;

    int compterMessagesParArtiste(int idArtiste) throws SQLException;

}
