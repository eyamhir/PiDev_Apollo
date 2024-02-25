package org.example.services;

import org.example.interfaces.Interface_Message;
import org.example.model.Message;
import org.example.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_Message implements Interface_Message<Message> {
    private Connection connection;

    public Service_Message() {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void creerMessage(Message message) throws SQLException {
        String query = "INSERT INTO message (date_envoi, contenu, conversation_id, expediteur_id,destinataire_id) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, new Timestamp(message.getDateEnvoi().getTime()));
            statement.setString(2, message.getContenu());
            statement.setInt(3, message.getConversationId());
            statement.setInt(4, message.getExpediteur_id());
            statement.setInt(5, message.getDestinataire_id());
            // Utiliser l'ID de l'expéditeur
            statement.executeUpdate();
        }
    }

    @Override
    public Message lireMessage(int id) throws SQLException {
        String query = "SELECT * FROM message WHERE message_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Message message = new Message();
                message.setMessageId(resultSet.getInt("message_id"));
                message.setDateEnvoi(resultSet.getTimestamp("date_envoi"));
                message.setContenu(resultSet.getString("contenu"));
                message.setConversationId(resultSet.getInt("conversation_id"));
                message.setExpediteur_id(resultSet.getInt("expediteur_id"));
                message.setDestinataire_id(resultSet.getInt("destinataire_id"));

                return message;
            }
        }
        return null;
    }
    @Override
    public void mettreAJourMessage(Message message) throws SQLException {
        String query = "UPDATE message SET contenu = ?, date_envoi = NOW() WHERE message_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, message.getContenu());
            statement.setInt(2, message.getMessageId());
            statement.executeUpdate();
        }
    }

    @Override
    public void supprimerMessage(int id) throws SQLException {
        String query = "DELETE FROM message WHERE message_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Message> listerMessages() throws SQLException {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM message";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Message message = new Message();
                message.setMessageId(resultSet.getInt("message_id"));
                message.setDateEnvoi(resultSet.getTimestamp("date_envoi"));
                message.setContenu(resultSet.getString("contenu"));
                message.setConversationId(resultSet.getInt("conversation_id"));
                message.setExpediteur_id(resultSet.getInt("expediteur_id"));
                message.setDestinataire_id(resultSet.getInt("destinataire_id"));
                messages.add(message);
            }
        }
        return messages;
    }

    @Override
    public List<Message> listerMessagesParConversation(int idConversation) throws SQLException {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM message WHERE conversation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idConversation);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setMessageId(resultSet.getInt("message_id"));
                message.setDateEnvoi(resultSet.getTimestamp("date_envoi"));
                message.setContenu(resultSet.getString("contenu"));
                message.setConversationId(resultSet.getInt("conversation_id"));
                message.setExpediteur_id(resultSet.getInt("expediteur_id"));
                message.setDestinataire_id(resultSet.getInt("destinataire_id"));

                messages.add(message);
            }
        }
        return messages;
    }
    public List<String> recupererNomsPrenomsDestinatairesDansConversation(int idConversation) throws SQLException {
        List<String> nomsPrenomsDestinataires = new ArrayList<>();
        String query = "SELECT DISTINCT CONCAT(u.nom, ' ', u.prenom) AS nom_complet FROM utilisateur u " +
                "JOIN message m ON u.utilisateur_id = m.destinataire_id " +
                "JOIN conversation c ON m.conversation_id = c.conversation_id " +
                "WHERE c.conversation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idConversation);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nomPrenomDestinataire = resultSet.getString("nom_complet");
                    nomsPrenomsDestinataires.add(nomPrenomDestinataire);
                }
            }
        }
        return nomsPrenomsDestinataires;
    }
    public String recupererNomExpediteurDansConversation(int idConversation) throws SQLException {
        String nomExpediteur = null;
        String query = "SELECT DISTINCT CONCAT(u.nom, ' ', u.prenom) AS nom_complet FROM utilisateur u " +
                "JOIN message m ON u.utilisateur_id = m.expediteur_id " +
                "JOIN conversation c ON m.conversation_id = c.conversation_id " +
                "WHERE c.conversation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idConversation);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nomExpediteur = resultSet.getString("nom_complet");
                }
            }
        }
        return nomExpediteur;
    }

}
