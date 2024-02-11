package org.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private int messageId;
    private int idExpediteur; // Id de l'expéditeur (artiste ou client)
    private int idDestinataire; // Id du destinataire (artiste ou client)
    private Date dateEnvoi;
    private String contenu;
    private int conversationId;

    // Constructeur paramétré avec identifiant
    public Message(int messageId, int idExpediteur, int idDestinataire, Date dateEnvoi, String contenu, int conversationId) {
        this.messageId = messageId;
        this.idExpediteur = idExpediteur;
        this.idDestinataire = idDestinataire;
        this.dateEnvoi = dateEnvoi;
        this.contenu = contenu;
        this.conversationId = conversationId;
    }

    // Constructeur paramétré sans identifiant
    public Message(int idExpediteur, int idDestinataire, Date dateEnvoi, String contenu, int conversationId) {
        this.idExpediteur = idExpediteur;
        this.idDestinataire = idDestinataire;
        this.dateEnvoi = dateEnvoi;
        this.contenu = contenu;
        this.conversationId = conversationId;
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

    public int getIdExpediteur() {
        return idExpediteur;
    }

    public void setIdExpediteur(int idExpediteur) {
        this.idExpediteur = idExpediteur;
    }

    public int getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(int idDestinataire) {
        this.idDestinataire = idDestinataire;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(dateEnvoi);

        return "Message{" +
                "messageId=" + messageId +
                ", idExpediteur=" + idExpediteur +
                ", idDestinataire=" + idDestinataire +
                ", dateEnvoi=" + formattedDate +
                ", contenu='" + contenu + '\'' +
                ", conversationId=" + conversationId +
                '}';
    }

}
