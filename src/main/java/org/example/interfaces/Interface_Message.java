package org.example.interfaces;

import org.example.model.Message;

import java.sql.SQLException;
import java.util.List;

public interface Interface_Message<T> {
    //CRUD Message
    void creerMessage(T t) throws SQLException;

    Message lireMessage(int id) throws SQLException;

    void mettreAJourMessage(T t) throws SQLException;

    void supprimerMessage(int id) throws SQLException;


    List<T> listerMessages() throws SQLException;


    // Methode avancées
    List<T> listerMessagesParConversation(int idConversation) throws SQLException;


}
