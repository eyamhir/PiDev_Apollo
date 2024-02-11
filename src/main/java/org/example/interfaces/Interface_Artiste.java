package org.example.interfaces;

import org.example.model.Conversation;
import org.example.model.Message;

import java.sql.SQLException;

public interface Interface_Artiste<T> {
    void creerArtiste(T t) throws SQLException;

    T lire_Donnée_Artiste(int id) throws SQLException;

    void mettreAJourArtiste(T t) throws SQLException;

    void supprimerArtiste(int id) throws SQLException;
}
