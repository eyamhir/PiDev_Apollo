package org.example.interfaces;

import org.example.model.Conversation;
import org.example.model.Message;

import java.sql.SQLException;
import java.util.List;

public interface Interface_Conversation<T> {
    // CRUD Conversation
    void creerConversation(T conversation) throws SQLException;

    Conversation lireConversation(int id) throws SQLException;

    Conversation lireConversationunique(int id) throws SQLException;

    void mettreAJourConversation(T conversation) throws SQLException;

    void supprimerConversation(int id) throws SQLException;

    // Méthodes avancées
    List<T> listerConversations() throws SQLException;

   /* List<T> listerConversationsActivesParArtiste(int idArtiste) throws SQLException;

    int calculerDureeTotaleConversationsClient(int idClient) throws SQLException;

    // Nouvelles méthodes
    void archiverConversation(int idConversation) throws SQLException;

    List<T> rechercherConversationsParSujet(String sujet) throws SQLException;

    List<Message> listerMessagesRecents(int idConversation, int nombreMessages) throws SQLException;

    int compterMessagesDansConversation(int idConversation) throws SQLException;

    boolean estConversationActive(int idConversation) throws SQLException;

    void marquerConversationImportante(int idConversation) throws SQLException;
    List<T> listerParticipantsConversationGroupe(int idConversation) throws SQLException;

    // Nouvelle méthode pour compter le nombre de clients dans une conversation de groupe
    int compterClientsConversationGroupe(int idConversation) throws SQLException;

    // Nouvelle méthode pour compter le nombre d'artistes dans une conversation de groupe
    int compterArtistesConversationGroupe(int idConversation) throws SQLException;*/
}
