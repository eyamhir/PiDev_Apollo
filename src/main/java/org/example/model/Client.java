package org.example.model;

public class Client {
    private int client_Id;


    // Constructeur
    public Client(int clientId) {
        this.client_Id = clientId;
    }
public Client(){

}
    // Getters et setters


    public int getClient_Id() {
        return client_Id;
    }

    public void setClient_Id(int client_Id) {
        this.client_Id = client_Id;
    }
    //  TOString

    @Override
    public String toString() {
        return "Client{" +
                "client_Id=" + client_Id +
                '}';
    }
}
