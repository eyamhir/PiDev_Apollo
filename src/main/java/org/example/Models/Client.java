package org.example.Models;

import java.sql.Blob;
import java.util.Date;

public class Client extends Utilisateur{
    private int id_Client;
    public Client() {
    }


    public Client(int id_Client) {
        this.id_Client = id_Client;
    }

    public Client(int id_utilisateur, String nom, String prenom, String adresse_mail, int num_tel, Date date_naissance, Date date_inscription, Blob profile_image, String role, String mot_passe, int id_Client) {
        super(id_utilisateur, nom, prenom, adresse_mail, num_tel, date_naissance, date_inscription, profile_image, role, mot_passe);
        this.id_Client = id_Client;
    }

    public int getId_Client() {
        return id_Client;
    }

    public void setId_Client(int id_Client) {
        this.id_Client = id_Client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_Client=" + id_Client +
                '}';
    }
}
