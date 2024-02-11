package org.example.services;

import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;
import org.example.interfaces.Interface_Conversation;
import org.example.model.Conversation;
import org.example.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_Conversation implements Interface_Conversation<Conversation> {
    private Connection connection;

    public Service_Conversation() {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void creerConversation(Conversation conversation) throws SQLException {
        String query = "INSERT INTO conversation (titre, sujet, description, date_creation, date_fin, conversation_type, visibilite) VALUES (?,?,?,?,?,?,?)";
      PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, conversation.getTitre());
        statement.setString(2, conversation.getSujet());
        statement.setString(3, conversation.getDescription());
        statement.setTimestamp(4, new java.sql.Timestamp(conversation.getDateCreation().getTime()));
        statement.setTimestamp(5, new java.sql.Timestamp(conversation.getDateFin().getTime()));
        statement.setString(6, conversation.getTypeConversation(Conversation_Type.PRIVATE).name());
        statement.setString(7, conversation.getVisibilite().name());
        statement.executeUpdate();
    }

    @Override
    public Conversation lireConversation(int id) throws SQLException {
        String query = "SELECT * FROM conversation WHERE conversation_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Conversation conversation = null;

        if (resultSet.next()) {
            conversation = new Conversation();
            conversation.setConversationId(resultSet.getInt("conversation_id"));
            conversation.setTitre(resultSet.getString("titre"));
            conversation.setSujet(resultSet.getString("sujet"));
            conversation.setDescription(resultSet.getString("description"));
            conversation.setDateCreation(resultSet.getTimestamp("date_creation"));
            conversation.setDateFin(resultSet.getTimestamp("date_fin"));
            String typeConversation = resultSet.getString("conversation_type");
            String visibilite = resultSet.getString("visibilite");
            conversation.setTypeConversation(Conversation_Type.valueOf(typeConversation));
            conversation.setVisibilite(Visibilite.valueOf(visibilite));
        }

        return conversation;
    }

    @Override
    public void mettreAJourConversation(Conversation conversation) throws SQLException {
        String query = "UPDATE conversation SET titre = ?, sujet = ?, description = ?, date_creation = ?, date_fin = ?, conversation_type = ?, visibilite = ? WHERE conversation_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, conversation.getTitre());
        statement.setString(2, conversation.getSujet());
        statement.setString(3, conversation.getDescription());
        statement.setTimestamp(4, new java.sql.Timestamp(conversation.getDateCreation().getTime()));
        statement.setTimestamp(5, new java.sql.Timestamp(conversation.getDateFin().getTime()));
        statement.setString(6, conversation.getTypeConversation(Conversation_Type.PRIVATE).name());
        statement.setString(7, conversation.getVisibilite().name());
        statement.setInt(8, conversation.getConversationId());
        statement.executeUpdate();
    }

    @Override
    public void supprimerConversation(int id) throws SQLException {
        String query = "DELETE FROM conversation WHERE conversation_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<Conversation> listerConversations() throws SQLException {
        List<Conversation> conversations = new ArrayList<>();
        String query = "SELECT * FROM conversation";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Conversation conversation = new Conversation();
            conversation.setConversationId(resultSet.getInt("conversation_id"));
            conversation.setTitre(resultSet.getString("titre"));
            conversation.setSujet(resultSet.getString("sujet"));
            conversation.setDescription(resultSet.getString("description"));
            conversation.setDateCreation(resultSet.getTimestamp("date_creation"));
            conversation.setDateFin(resultSet.getTimestamp("date_fin"));
            String typeConversation = resultSet.getString("type_conversation");
            String visibilite = resultSet.getString("visibilite");
            conversation.setTypeConversation(Conversation_Type.valueOf(typeConversation));
            conversation.setVisibilite(Visibilite.valueOf(visibilite));
            conversations.add(conversation);
        }

        return conversations;
    }


}
