package org.example.interfaces;

import org.example.model.Conversation;

import java.sql.SQLException;

public interface Interface_Client <T>{
    void creerClient(T t) throws SQLException;

    T lire_Donnée_Client(int id) throws SQLException;

    void mettreAJourClient(T t) throws SQLException;

    void supprimerClient(int id) throws SQLException;
}
