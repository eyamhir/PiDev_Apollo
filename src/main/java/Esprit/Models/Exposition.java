package Esprit.Models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Exposition {
    private  int id_Exposition ;
    private String titre ;
    private String description ;
    private LocalDate date_debut;
    private LocalDate date_fin ;

    public Exposition() {
    }

    public Exposition(String titre, String description, LocalDate date_debut, LocalDate date_fin) {
        this.titre = titre;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Exposition(int id_Exposition, String titre, String description, LocalDate date_debut, LocalDate date_fin) {
        this.id_Exposition = id_Exposition;
        this.titre = titre;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId_Exposition() {
        return id_Exposition;
    }

    public void setId_Exposition(int id_Exposition) {
        this.id_Exposition = id_Exposition;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id_Exposition=" + id_Exposition +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                '}';
    }

}
