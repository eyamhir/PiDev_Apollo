package tn.esprit.apollogui.models;

import java.util.Date;

public class evenement {

    private int id;
    private String Nom,Description,Type;
    private Date Date_debut,Date_fin;


    public evenement(int id, String nom, String description, String type, Date date_debut, Date date_fin) {
        this.id = id;
        Nom = nom;
        Description = description;
        Type = type;
        Date_debut = date_debut;
        Date_fin = date_fin;

    }

    public evenement(String nom, String description, String type, Date date_debut, Date date_fin) {
        Nom = nom;
        Description = description;
        Type = type;
        Date_debut = date_debut;
        Date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Date getDate_debut() {
        return Date_debut;
    }

    public void setDate_debut(Date date_debut) {
        Date_debut = date_debut;
    }

    public Date getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(Date date_fin) {
        Date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "evenement{" +
                "id=" + id +
                ", Nom='" + Nom + '\'' +
                ", Description='" + Description + '\'' +
                ", Type='" + Type + '\'' +
                ", Date_debut=" + Date_debut +
                ", Date_fin=" + Date_fin +
                '}';
    }
}
