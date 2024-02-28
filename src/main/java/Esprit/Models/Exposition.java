package Esprit.Models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Exposition {
    private  int id_Exposition ;
    private String titre ;
    private String description ;
    private Type_Expose typeExpose;
    private LocalDate date_debut;
    private LocalDate date_fin ;
    private String localisation;
    private String imageAffiche;
    private int id_portfolio;

    public Exposition() {
    }

    public Exposition(String imageAffiche,String titre, String description, LocalDate date_debut, LocalDate date_fin ,int id_portfolio,Type_Expose typeExpose,String localisation) {
        this.imageAffiche=imageAffiche;
        this.titre = titre;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_portfolio=id_portfolio;
        this.typeExpose=typeExpose;
        this.localisation=localisation;
    }



    public Exposition(int id_Exposition,String imageAffiche , String titre, String description, LocalDate date_debut, LocalDate date_fin, int id_portfolio,Type_Expose typeExpose,String localisation) {
        this.id_Exposition = id_Exposition;
        this.imageAffiche=imageAffiche;
        this.titre = titre;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_portfolio=id_portfolio;
        this.typeExpose=typeExpose;
        this.localisation=localisation;

    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getImageAffiche() {
        return imageAffiche;
    }

    public void setImageAffiche(String imageAffiche) {
        this.imageAffiche = imageAffiche;
    }

    public Type_Expose getTypeExpose() {
        return typeExpose;
    }

    public void setTypeExpose(Type_Expose typeExpose) {
        this.typeExpose = typeExpose;
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
    public int getId_portfolio() {
        return id_portfolio;
    }

    public void setId_portfolio(int id_portfolio) {
        this.id_portfolio = id_portfolio;
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id_Exposition=" + id_Exposition +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", id_portfolio="+id_portfolio+
                '}';
    }

}
