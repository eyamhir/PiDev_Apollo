package org.example.services;

import org.example.interfaces.Interface_Participant;
import org.example.model.Conversation;
import org.example.model.Participant;
import org.example.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_Participant  implements Interface_Participant<Participant> {

    private Connection connection; // Connexion à la base de données

    // Constructeur prenant la connexion en paramètre
    public Service_Participant() {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouterParticipant(Participant participant) {
        String query = "INSERT INTO participant (conversation_id, artiste_id, client_id) VALUES (?, ?, ?)";
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
        String query = "DELETE FROM participant WHERE participant_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mettreAjourParticipant(Participant participant) throws SQLException {
        String query = "UPDATE participant p " +
                "JOIN conversation c ON p.conversation_id = c.conversation_id " +
                "JOIN artiste a ON p.artiste_id = a.artiste_id " +
                "JOIN client cl ON p.client_id = cl.client_id " +
                "SET p.conversation_id = ?, p.artiste_id = ?, p.client_id = ? " +
                "WHERE p.participant_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, participant.getConversationId());
            statement.setInt(2, participant.getArtisteId());
            statement.setInt(3, participant.getClientId());
            statement.setInt(4, participant.getParticipantId());
            statement.executeUpdate();
        }
    }


    @Override
    public List<Participant> getParticipants(int conversationId) {
        List<Participant> participants = new ArrayList<>();
        String query = "SELECT * FROM participant WHERE conversation_id = ?";
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
    public Participant getParticipantParID(int participantId) {
        String query = "SELECT * FROM participant WHERE participant_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, participantId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int conversationId = resultSet.getInt("conversation_id");
                int artisteId = resultSet.getInt("artiste_id");
                int clientId = resultSet.getInt("client_id");
                return new Participant(participantId, conversationId, artisteId, clientId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if participant not found
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
