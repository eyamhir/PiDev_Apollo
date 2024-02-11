package org.example.model;

public class Participant {
    private int participantId;
    private int conversationId;
    private int artisteId;
    private int clientId;

    // Constructeur paramétré avec identifiant
    public Participant(int participantId, int conversationId, int artisteId, int clientId) {
        this.participantId = participantId;
        this.conversationId = conversationId;
        this.artisteId = artisteId;
        this.clientId = clientId;
    }
    // Constructeur paramétré sans identifiant
    public Participant( int conversationId, int artisteId, int clientId) {
        this.conversationId = conversationId;
        this.artisteId = artisteId;
        this.clientId = clientId;
    }

    // Constructeur par défaut
    public Participant() {
    }
    //Gettter and Setter


    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getArtisteId() {
        return artisteId;
    }

    public void setArtisteId(int artisteId) {
        this.artisteId = artisteId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    //ToString

    @Override
    public String toString() {
        return "Participant{" +
                "participantId=" + participantId +
                ", conversationId=" + conversationId +
                ", artisteId=" + artisteId +
                ", clientId=" + clientId +
                '}';
    }
}
