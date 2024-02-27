package tn.esprit.projet_java.models;

import java.time.LocalDate;
import java.util.Date;

public class Enchers {
    private int id_enchers;
    private  String type_oeuvre;
    private double min_montant;
   // private float max_montant;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private String image;
    private int id_utilisateur;
   // private LocalDate dateDebut;
    //private LocalDate dateFin;
    public Enchers() {
    }
   /* public Enchers(float minMontant, LocalDate dateDebut, LocalDate dateFin, String type) {

        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

    }*/

    public Enchers(String type_oeuvre, double min_montant, LocalDate date_debut, LocalDate date_fin,String image) {
        this.type_oeuvre = type_oeuvre;
        this.min_montant = min_montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
       this.image = image;
    }

    public Enchers(double min_montant, LocalDate date_debut, LocalDate date_fin, String type_oeuvre, String image, int id_utilisateur) {
        //this.id_enchers = id_enchers;
        this.type_oeuvre = type_oeuvre;
        this.min_montant = min_montant;
       // this.max_montant = max_montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.id_utilisateur = id_utilisateur;
    }
    public Enchers(double min_montant, LocalDate date_debut, LocalDate date_fin, String type_oeuvre,String image) {
        //this.id_enchers = id_enchers;
        this.type_oeuvre = type_oeuvre;
        this.min_montant = min_montant;
        // this.max_montant = max_montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;

    }


    public Enchers(int id_enchers, String type_oeuvre, double min_montant,  LocalDate date_debut, LocalDate date_fin, String image,int id_utilisateur) {
        this.id_enchers = id_enchers;
        this.type_oeuvre = type_oeuvre;
        this.min_montant = min_montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.id_utilisateur=id_utilisateur;
    }
    public Enchers(int id_enchers, String type_oeuvre, double min_montant,  LocalDate date_debut, LocalDate date_fin, String image) {
        this.id_enchers = id_enchers;
        this.type_oeuvre = type_oeuvre;
        this.min_montant = min_montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;

    }

    public Enchers(String type_oeuvre, double min_montant, LocalDate date_debut, LocalDate date_fin, String image,int id_utilisateur) {
        this.type_oeuvre = type_oeuvre;
        this.min_montant = min_montant;
       // this.max_montant = max_montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.id_utilisateur = id_utilisateur;
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

    public  double getMin_montant() {
        return min_montant;
    }

    public void setMin_montant(double min_montant) {
        this.min_montant = min_montant;
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


    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Enchers{" +
                "id_enchers=" + id_enchers +
                ", type_oeuvre='" + type_oeuvre + '\'' +
                ", min_montant=" + min_montant +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", image='" + image + '\'' +
                ", id_utilisateur=" + id_utilisateur +
                '}';
    }
}
