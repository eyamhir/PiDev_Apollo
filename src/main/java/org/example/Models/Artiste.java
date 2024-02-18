package org.example.Models;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

public class Artiste extends Utilisateur{
    private int id_Artiste;
    private String Specialite_Artistique;

    public Artiste(int id_Artiste, String specialite_Artistique) {
        this.id_Artiste = id_Artiste;
        Specialite_Artistique = specialite_Artistique;
    }

    public Artiste(int id_utilisateur, String nom, String prenom, String adresse_mail, int num_tel, LocalDate date_naissance, LocalDate date_inscription, Blob profile_image, String role, String mot_passe, int id_Artiste, String specialite_Artistique) {
        super(id_utilisateur, nom, prenom, adresse_mail, num_tel, date_naissance, date_inscription, role, mot_passe);
        this.id_Artiste = id_Artiste;
        Specialite_Artistique = specialite_Artistique;
    }

    public Artiste() {
    }

    public int getId_Artiste() {
        return id_Artiste;
    }

    public void setId_Artiste(int id_Artiste) {
        this.id_Artiste = id_Artiste;
    }

    public String getSpecialite_Artistique() {
        return Specialite_Artistique;
    }

    public void setSpecialite_Artistique(String specialite_Artistique) {
        Specialite_Artistique = specialite_Artistique;
    }

    @Override
    public String toString() {
        return "Artiste{" +
                "id_Artiste=" + id_Artiste +
                ", Specialite_Artistique='" + Specialite_Artistique + '\'' +
                '}';
    }
}
