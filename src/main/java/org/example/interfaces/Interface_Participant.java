package org.example.interfaces;

import org.example.model.Participant;
import org.example.model.Conversation;

import java.sql.SQLException;
import java.util.List;

public interface Interface_Participant<T> {
    // Ajoute un participant à une conversation
    void ajouterParticipant(T t);

    // Supprime un participant d'une conversation
    void supprimerParticipant(int id);

    // Mettre a jour participant
    void mettreAjourParticipant(T t) throws SQLException;

    // Renvoie la liste des participants associés à une conversation donnée
    List<T> getParticipants(int conversationId);

    // Recherche un participant par son identifiant unique et renvoie l'objet Participant correspondant
    T getParticipantParID(int participantId);

    // Methode avancées

    // Renvoie uniquement les participants actifs pour une conversation spécifique
    List<T> getParticipantsActifs(int conversationId);

    // Recherche un participant par son nom et renvoie l'objet Participant correspondant
    T getParticipantParNom(String nom);
    // Modifie le statut d'un participant (par exemple, "en ligne", "hors ligne", "occupé", etc.)
    void modifierStatut(T t, String statut);

    // Renvoie la liste des conversations auxquelles un participant participe
    List<Conversation> getConversationsParticipant(T t);

    // Renvoie la liste des participants impliqués dans une conversation donnée
    List<T> getParticipantsConversation(Conversation conversation);

    // Renvoie tous les participants ayant un rôle particulier (par exemple, "artiste", "client", "modérateur")
    List<T> getParticipantsParRole(String role);
}
