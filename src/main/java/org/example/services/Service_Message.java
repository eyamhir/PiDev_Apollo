package org.example.services;

import org.example.interfaces.Interface_Message;
import org.example.model.Artiste;
import org.example.model.Message;
import org.example.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_Message implements Interface_Message<Message> {
    Connection connection;

    public Service_Message() {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void creerMessage(Message message) throws SQLException {
        String query = "INSERT INTO message (expediteur_id,destinataire_id, date_envoi, contenu, conversation_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, message.getIdExpediteur());
        statement.setInt(2, message.getIdDestinataire());
        statement.setTimestamp(3, new Timestamp(message.getDateEnvoi().getTime()));
        statement.setString(4, message.getContenu());
        statement.setInt(5, message.getConversationId());
        statement.executeUpdate();
    }

    @Override
    public Message lireMessage(int id) throws SQLException {
        String query = "SELECT * FROM message WHERE message_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Message message = null;

        if (resultSet.next()) {
            message = new Message();
            message.setMessageId(resultSet.getInt("message_id"));
            message.setIdExpediteur(resultSet.getInt("expediteur_id"));
            message.setIdDestinataire(resultSet.getInt("destinataire_id"));
            message.setDateEnvoi(resultSet.getTimestamp("date_envoi"));
            message.setContenu(resultSet.getString("contenu"));
            message.setConversationId(resultSet.getInt("conversation_id"));
        }
        if (message == null) {
            System.out.println("Le message ne contient rien ");
        }
        return message;
    }



    @Override
    public void mettreAJourMessage(Message message) throws SQLException {
        String query = "UPDATE message SET contenu = ?, date_envoi = NOW() WHERE message_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, message.getContenu());
        statement.setInt(2, message.getMessageId());
        statement.executeUpdate();
    }


    @Override
    public void supprimerMessage(int id) throws SQLException {
        String query = "DELETE FROM message WHERE message_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<Message> listerMessages() throws SQLException {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM message";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Message message = new Message();
            message.setMessageId(resultSet.getInt("message_id"));
            message.setIdExpediteur(resultSet.getInt("artiste_id"));
            message.setIdDestinataire(resultSet.getInt("client_id"));
            message.setDateEnvoi(resultSet.getTimestamp("date_envoi"));
            message.setContenu(resultSet.getString("contenu"));
            message.setConversationId(resultSet.getInt("conversation_id"));
            messages.add(message);
        }

        return messages;
    }

    @Override
    public List<Message> listerMessagesParConversation(int idConversation) throws SQLException {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM message WHERE conversation_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idConversation);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Message message = new Message();
            message.setMessageId(resultSet.getInt("message_id"));
            message.setIdExpediteur(resultSet.getInt("artiste_id"));
            message.setIdDestinataire(resultSet.getInt("client_id"));
            message.setDateEnvoi(resultSet.getTimestamp("date_envoi"));
            message.setContenu(resultSet.getString("contenu"));
            message.setConversationId(resultSet.getInt("conversation_id"));
            messages.add(message);
        }

        return messages;
    }

    @Override
    public int compterMessagesParArtiste(int idArtiste) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM message WHERE artiste_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idArtiste);
        ResultSet resultSet = statement.executeQuery();

        int count = 0;

        if (resultSet.next()) {
            count = resultSet.getInt("count");
        }

        return count;
    }
}
