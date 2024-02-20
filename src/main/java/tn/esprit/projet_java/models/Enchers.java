package tn.esprit.projet_java.models;

import java.time.LocalDate;
import java.util.Date;

public class Enchers {
    private int id_enchers;
    private  String type_oeuvre;
    private float min_montant;
    private float max_montant;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private String artiste_associe_de_oeuvre;

   // private LocalDate dateDebut;
    //private LocalDate dateFin;
    public Enchers() {
    }
   /* public Enchers(float minMontant, LocalDate dateDebut, LocalDate dateFin, String type) {

        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

    }*/
    public Enchers(float min_montant, LocalDate date_debut, LocalDate date_fin, String type_oeuvre) {
        //this.id_enchers = id_enchers;
        this.type_oeuvre = type_oeuvre;
        this.min_montant = min_montant;
       // this.max_montant = max_montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
       // this.artiste_associe_de_oeuvre = artiste_associe_de_oeuvre;
    }
    public Enchers(int id_enchers, String type_oeuvre, float min_montant, float max_montant, LocalDate date_debut, LocalDate date_fin, String artiste_associe_de_oeuvre) {
        this.id_enchers = id_enchers;
        this.type_oeuvre = type_oeuvre;
        this.min_montant = min_montant;
        this.max_montant = max_montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.artiste_associe_de_oeuvre = artiste_associe_de_oeuvre;
    }

    public Enchers(String type_oeuvre, float min_montant, float max_montant, LocalDate date_debut, LocalDate date_fin, String artiste_associe_de_oeuvre) {
        this.type_oeuvre = type_oeuvre;
        this.min_montant = min_montant;
        this.max_montant = max_montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.artiste_associe_de_oeuvre = artiste_associe_de_oeuvre;
    }

    public Enchers(int idEnchers, float parseFloat, LocalDate vadate_debut, LocalDate date_fin, String type_oeuvre) {
    }


    public int getId_enchers() {
        return id_enchers;
    }

    public void setId_enchers(int id_enchers) {
        this.id_enchers = id_enchers;
    }

    public String getType_oeuvre() {
        return type_oeuvre;
    }

    public void setType_oeuvre(String type_oeuvre) {
        this.type_oeuvre = type_oeuvre;
    }

    public  float getMin_montant() {
        return min_montant;
    }

    public void setMin_montant(float min_montant) {
        this.min_montant = min_montant;
    }

    public float getMax_montant() {
        return max_montant;
    }

    public void setMax_montant(float max_montant) {
        this.max_montant = max_montant;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate  date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate  date_fin) {
        this.date_fin = date_fin;
    }

    public String getArtiste_associe_de_oeuvre() {
        return artiste_associe_de_oeuvre;
    }

    public void setArtiste_associe_de_oeuvre(String artiste_associe_de_oeuvre) {
        this.artiste_associe_de_oeuvre = artiste_associe_de_oeuvre;
    }


    @Override
    public String toString() {
        return "Enchers{" +
                "id_enchers=" + id_enchers +
                ", type_oeuvre='" + type_oeuvre + '\'' +
                ", min_montant=" + min_montant +
                ", max_montant=" + max_montant +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", artiste_associe_de_oeuvre='" + artiste_associe_de_oeuvre + '\'' +
                '}';
    }


}
