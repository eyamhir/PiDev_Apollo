package org.example.model;

import java.util.Date;

public class Message {
    private int messageId;
    private Date dateEnvoi;
    private String contenu;
    private int conversationId;
    private int expediteur_id;
    private int destinataire_id;

    // Constructeur paramétré avec identifiant
    public Message(int messageId,  Date dateEnvoi, String contenu, int conversationId,int expediteur_id,int destinataire_id) {
        this.messageId = messageId;
        this.dateEnvoi = dateEnvoi;
        this.contenu = contenu;
        this.conversationId = conversationId;
        this.expediteur_id=expediteur_id;
        this.destinataire_id=destinataire_id;
    }

    // Constructeur paramétré sans identifiant
    public Message( Date dateEnvoi, String contenu, int conversationId,int expediteur_id,int destinataire_id) {
        this.dateEnvoi = dateEnvoi;
        this.contenu = contenu;
        this.conversationId = conversationId;
        this.expediteur_id=expediteur_id;
        this.destinataire_id=destinataire_id;
    }

    // Constructeur par défaut
    public Message() {

    }

    // Getters et setters

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getExpediteur_id() {
        return expediteur_id;
    }

    public void setExpediteur_id
            (int expediteur_id) {
        this.expediteur_id = expediteur_id;
    }

    public int getDestinataire_id() {
        return destinataire_id;
    }

    public void setDestinataire_id
            (int utilisateurId) {
        this.destinataire_id = destinataire_id;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", expediteur_id=" + expediteur_id +
                ", destinataire_id"+destinataire_id+
                ", dateEnvoi=" + dateEnvoi +
                ", contenu='" + contenu + '\'' +
                ", conversationId=" + conversationId +
                '}';
    }
}
