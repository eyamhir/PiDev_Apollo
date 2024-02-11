package org.example.services;

import org.example.interfaces.Interface_Participant;
import org.example.model.Conversation;
import org.example.model.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_Participant  implements Interface_Participant<Participant> {

    private Connection connection; // Connexion à la base de données

    // Constructeur prenant la connexion en paramètre
    public Service_Participant(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void ajouterParticipant(Participant participant) {
        String query = "INSERT INTO participants (conversation_id, artiste_id, client_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, participant.getConversationId());
            statement.setInt(2, participant.getArtisteId());
            statement.setInt(3, participant.getClientId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerParticipant(int id) {
        String query = "DELETE FROM participants WHERE participant_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Participant> getParticipants(int conversationId) {
        List<Participant> participants = new ArrayList<>();
        String query = "SELECT * FROM participants WHERE conversation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, conversationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int participantId = resultSet.getInt("participant_id");
                int artisteId = resultSet.getInt("artiste_id");
                int clientId = resultSet.getInt("client_id");
                participants.add(new Participant(participantId, conversationId, artisteId, clientId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    @Override
    public List<Participant> getParticipantsActifs(int conversationId) {
        return null;
    }

    @Override
    public Participant getParticipantParNom(String nom) {
        return null;
    }

    @Override
    public Participant getParticipantParID(int participantId) {
        return null;
    }

    @Override
    public void modifierStatut(Participant participant, String statut) {

    }

    @Override
    public List<Conversation> getConversationsParticipant(Participant participant) {
        return null;
    }

    @Override
    public List<Participant> getParticipantsConversation(Conversation conversation) {
        return null;
    }

    @Override
    public List<Participant> getParticipantsParRole(String role) {
        return null;
    }
}
