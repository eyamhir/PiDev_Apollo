package org.example.model;

import org.example.enumarate.Conversation_Type;
import org.example.enumarate.Visibilite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Conversation {
    // Attributs
    private int conversationId;
    private String titre;
    private String sujet;
    private String description;
    private Date dateCreation;
    private Date dateFin;
    private Conversation_Type typeConversation;
    private Visibilite visibilite;
    private List<Message> messages;


    // Constructeur paramétré avec identifiant


    public Conversation(int conversationId, String titre, String sujet, String description, Date dateCreation, Date dateFin, Conversation_Type typeConversation, Visibilite visibilite, List<Message> messages) {
        this.conversationId = conversationId;
        this.titre = titre;
        this.sujet = sujet;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateFin = dateFin;
        this.typeConversation = typeConversation;
        this.visibilite = visibilite;
        this.messages = messages;
    }

    // Constructeur paramétré sans identifiant
    public Conversation( String titre, String sujet, String description, Date dateCreation, Date dateFin, Conversation_Type typeConversation, Visibilite visibilite, List<Message> messages) {

        this.titre = titre;
        this.sujet = sujet;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateFin = dateFin;
        this.typeConversation = typeConversation;
        this.visibilite = visibilite;
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

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Conversation_Type getTypeConversation(Conversation_Type aPrivate) {
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


    // Autres getters et setters pour les autres attributs
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateCreation = sdf.format(dateCreation);
        String formattedDateFin = sdf.format(dateFin);

        return "Conversation{" +
                "conversationId=" + conversationId +
                ", sujet='" + sujet + '\'' +
                ", dateCreation=" + formattedDateCreation +
                ", dateFin=" + formattedDateFin +
                ", messages=" + messages +
                '}';
    }

}
