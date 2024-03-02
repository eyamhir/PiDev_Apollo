package org.example.model;

import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Conversation {
    // Attributs
    private int conversationId;
    private String titre;
    private String sujet;
    private String description;
    private Timestamp dateCreation;
    private Timestamp dateFin;
    private Conversation_Type typeConversation;
    private Visibilite visibilite;
    private int utilisateur_id;
    private List<Message> messages;


    // Constructeur paramétré avec identifiant

    public Conversation(int conversationId, String titre, String sujet, String description, Timestamp dateCreation, Timestamp dateFin, Conversation_Type typeConversation, Visibilite visibilite, int utilisateur_id, List<Message> messages) {
        this.conversationId = conversationId;
        this.titre = titre;
        this.sujet = sujet;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateFin = dateFin;
        this.typeConversation = typeConversation;
        this.visibilite = visibilite;
        this.utilisateur_id = utilisateur_id;
        this.messages = messages;
    }

    // Constructeur paramétré sans identifiant
    public Conversation(String titre, String sujet, String description, Timestamp dateCreation, Timestamp dateFin, Conversation_Type typeConversation, Visibilite visibilite, int utilisateur_id, List<Message> messages) {

        this.titre = titre;
        this.sujet = sujet;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateFin = dateFin;
        this.typeConversation = typeConversation;
        this.visibilite = visibilite;
        this.utilisateur_id = utilisateur_id;
        this.messages = messages;
    }

    // Constructeur par défaut
    public Conversation() {

    }

    // Getters et setters

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public Conversation_Type getTypeConversation() {
        return typeConversation;
    }

    public void setTypeConversation(Conversation_Type typeConversation) {
        this.typeConversation = typeConversation;
    }

    public Visibilite getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(Visibilite visibilite) {
        this.visibilite = visibilite;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateCreation = (dateCreation != null) ? sdf.format(dateCreation) : "N/A";
        String formattedDateFin = (dateFin != null) ? sdf.format(dateFin) : "N/A";

        return "Conversation{" +
                "conversationId=" + conversationId +
                ", sujet='" + sujet + '\'' +
                ", dateCreation=" + formattedDateCreation +
                ", dateFin=" + formattedDateFin +
                ", messages=" + messages +
                '}';
    }
}
