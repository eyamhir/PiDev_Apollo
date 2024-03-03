package org.example.services;

import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;
import org.example.interfaces.Interface_Conversation;
import org.example.model.Conversation;
import org.example.utils.MaConnexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Service_Conversation implements Interface_Conversation<Conversation> {
    private Connection connection;

    public Service_Conversation() {
        connection = MaConnexion.getInstance().getCnx();
    }


    @Override
    public void creerConversation(Conversation conversation) throws SQLException {
        String query = "INSERT INTO conversation (titre, sujet, description, date_creation, date_fin, conversation_type, visibilite, utilisateur_id) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, conversation.getTitre());
            statement.setString(2, conversation.getSujet());
            statement.setString(3, conversation.getDescription());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now())); // Utiliser la date actuelle
            statement.setTimestamp(5, null); // Définir la date de fin comme '9999-12-31'
            statement.setString(6, conversation.getTypeConversation().name());
            statement.setString(7, conversation.getVisibilite().name());
            statement.setInt(8, conversation.getUtilisateur_id());
            statement.executeUpdate();
        }
    }

    @Override
    public Conversation lireConversation(int id) throws SQLException {
        String query = "SELECT * FROM conversation WHERE conversation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Conversation conversation = new Conversation();
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
                    return conversation;
                }
            }
        }
        return null;
    }

    @Override
    public Conversation lireConversationunique(int id) throws SQLException {
        String query = "SELECT conversation_id,titre,sujet,description,conversation_type,visibilite FROM conversation WHERE conversation_id = ?";
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
            // conversation.setDateCreation(resultSet.getTimestamp("date_creation"));
            // conversation.setDateFin(resultSet.getTimestamp("date_fin"));
            String typeConversation = resultSet.getString("conversation_type");
            String visibilite = resultSet.getString("visibilite");
            conversation.setTypeConversation(Conversation_Type.valueOf(typeConversation));
            conversation.setVisibilite(Visibilite.valueOf(visibilite));
        }

        return conversation;
    }

    @Override
    public void mettreAJourConversation(Conversation conversation) throws SQLException {
        String query = "UPDATE conversation SET titre = ?, sujet = ?, description = ?,  conversation_type = ?, visibilite = ? WHERE conversation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, conversation.getTitre());
            statement.setString(2, conversation.getSujet());
            statement.setString(3, conversation.getDescription());
         //   statement.setTimestamp(4, new java.sql.Timestamp(conversation.getDateCreation().getTime()));
        //    statement.setTimestamp(5, new java.sql.Timestamp(conversation.getDateFin().getTime()));
            statement.setString(4, conversation.getTypeConversation().name());
            statement.setString(5, conversation.getVisibilite().name());
            statement.setInt(8, conversation.getConversationId());
            statement.executeUpdate();
        }
    }
    //@Override
    public void mettreAJourConversationUI(Conversation conversation) throws SQLException {
        String query = "UPDATE conversation SET titre = ?, sujet = ?, description = ?,conversation_type = ?, visibilite = ? WHERE conversation_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, conversation.getTitre());
        statement.setString(2, conversation.getSujet());
        statement.setString(3, conversation.getDescription());
        // statement.setTimestamp(4, new java.sql.Timestamp(conversation.getDateCreation().getTime()));
        // statement.setTimestamp(5, new java.sql.Timestamp(conversation.getDateFin().getTime()));
        statement.setString(4, conversation.getTypeConversation().toString());
        statement.setString(5, conversation.getVisibilite().toString());
        statement.setInt(6, conversation.getConversationId());
        statement.executeUpdate();
    }

    @Override
    public void supprimerConversation(int id) throws SQLException {
        String query = "DELETE FROM conversation WHERE conversation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Conversation> listerConversations() throws SQLException {
        List<Conversation> conversations = new ArrayList<>();
        String query = "SELECT * FROM conversation";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Conversation conversation = new Conversation();
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
                conversations.add(conversation);
            }
        }
        return conversations;
    }
    @Override
    public List<Conversation> rechercherConversationsParSujet(String sujet) throws SQLException {
        String query = "SELECT * FROM conversation WHERE sujet LIKE ?";
        List<Conversation> conversations = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + sujet + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Conversation conversation = new Conversation();
                    conversation.setConversationId(resultSet.getInt("conversation_id"));
                    conversation.setTitre(resultSet.getString("titre"));
                    conversation.setSujet(resultSet.getString("sujet"));
                    conversation.setDescription(resultSet.getString("description"));
                    conversation.setDateCreation(resultSet.getTimestamp("date_creation"));
                    conversation.setDateFin(resultSet.getTimestamp("date_fin"));

                    // Assurez-vous que les valeurs récupérées pour les énumérations sont non null avant de les convertir
                    String typeConversation = resultSet.getString("conversation_type");
                    if (typeConversation != null) {
                        conversation.setTypeConversation(Conversation_Type.valueOf(typeConversation));
                    }

                    String visibilite = resultSet.getString("visibilite");
                    if (visibilite != null) {
                        conversation.setVisibilite(Visibilite.valueOf(visibilite));
                    }

                    conversations.add(conversation);
                }
            }
        }
        return conversations;
    }
    public Conversation recupererConversationEnCours() throws SQLException {
        LocalDateTime dateActuelle = LocalDateTime.now();
        String query = "SELECT * FROM conversation WHERE date_creation <= ? AND (date_fin IS NULL OR date_fin >= ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, Timestamp.valueOf(dateActuelle));
            statement.setTimestamp(2, Timestamp.valueOf(dateActuelle));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Conversation conversation = new Conversation();
                    conversation.setConversationId(resultSet.getInt("conversation_id"));
                    conversation.setTitre(resultSet.getString("titre"));
                    conversation.setSujet(resultSet.getString("sujet"));
                    conversation.setDescription(resultSet.getString("description"));
                    conversation.setDateCreation(resultSet.getTimestamp("date_creation"));
                    conversation.setDateFin(resultSet.getTimestamp("date_fin")); // Assurez-vous de récupérer la date de fin
                    String typeConversation = resultSet.getString("conversation_type");
                    String visibilite = resultSet.getString("visibilite");
                    conversation.setTypeConversation(Conversation_Type.valueOf(typeConversation));
                    conversation.setVisibilite(Visibilite.valueOf(visibilite));
                    return conversation;
                }
            }
        }
        return null;
    }
 /*   public void mettreAJourDateFinConversationEnCours(Date dateFin) throws SQLException {
        Conversation conversationEnCours = recupererConversationEnCours(); // Récupérer la conversation en cours

        if (conversationEnCours = null) { // Vérifier si une conversation est en cours
            String query = "UPDATE conversation SET date_fin = ? WHERE conversation_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setTimestamp(1, new java.sql.Timestamp(conversationEnCours.getDateFin().getTime()));
                 statement.setInt(2, conversationEnCours.getConversationId());
                statement.executeUpdate();
            }
        } else {
            System.out.println("Aucune conversation en cours."); // Gérer le cas où aucune conversation n'est en cours
        }
    }*/
 public void mettreAJourDateFinConversationEnCours(java.util.Date dateFin) throws SQLException {
     Conversation conversationEnCours = recupererConversationEnCours(); // Récupérer la conversation en cours
     if (conversationEnCours != null) { // Vérifier si une conversation est en cours
         if (conversationEnCours.getDateFin() == null) { // Vérifier si la date de fin est null
             String query = "UPDATE conversation SET date_fin = ? WHERE conversation_id = ?";
             try (PreparedStatement statement = connection.prepareStatement(query)) {
                 statement.setTimestamp(1, new Timestamp(dateFin.getTime()));
                 statement.setInt(2, conversationEnCours.getConversationId());
                 statement.executeUpdate();
             }
         } else {
             System.out.println("La date de fin de la conversation n'est pas null.");
         }
     } else {
         System.out.println("Aucune conversation en cours."); // Gérer le cas où aucune conversation n'est en cours
     }
 }

}