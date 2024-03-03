package tn.esprit.apollogui.models;

import java.time.LocalDate;
import java.util.Date;

public class evenement {

    private int id;
    private String Nom,Description,Type;
    private LocalDate Date_debut,Date_fin;


    public evenement() {
        this.id = id;
        Nom = Nom;
        Description = Description;
        Type = Type;
        Date_debut = Date_debut;
        Date_fin = Date_fin;

    }
    public evenement(int id, String nom, String description, String type, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.Nom = nom;
        this.Description = description;
        this.Type = type;
        this.Date_debut = dateDebut;
        this.Date_fin = dateFin;
    }
    public evenement(String nom, String description, String type, LocalDate date_debut, LocalDate date_fin) {
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

    public LocalDate getDate_debut() {
        return Date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        Date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
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
