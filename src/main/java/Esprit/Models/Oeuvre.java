package Esprit.Models;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

public class Oeuvre {
    private int id_Oeuvre;
    private String titre ;
    private Blob image_oeuvre;
    private String description ;
    private LocalDate date_creation ;
    private float dimension ;
    private boolean disponibilite ;
    private float prix ;
    private int quantite ;

    public Oeuvre() {
    }

    public Oeuvre(int id_Oeuvre, String titre, Blob image_oeuvre, String description, LocalDate date_creation, float dimension, boolean disponibilite, float prix, int quantite) {
        this.id_Oeuvre = id_Oeuvre;
        this.titre = titre;
        this.image_oeuvre = image_oeuvre;
        this.description = description;
        this.date_creation = date_creation;
        this.dimension = dimension;
        this.disponibilite = disponibilite;
        this.prix = prix;
        this.quantite = quantite;

    }

    public int getId_Oeuvre() {
        return id_Oeuvre;
    }

    public void setId_Oeuvre(int id_Oeuvre) {
        this.id_Oeuvre = id_Oeuvre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Blob getImage_oeuvre() {
        return image_oeuvre;
    }

    public void setImage_oeuvre(Blob image_oeuvre) {
        this.image_oeuvre = image_oeuvre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public float getDimension() {
        return dimension;
    }

    public void setDimension(float dimension) {
        this.dimension = dimension;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Oeuvre{" +
                "id_Oeuvre=" + id_Oeuvre +
                ", titre='" + titre + '\'' +
                ", image_oeuvre=" + image_oeuvre +
                ", description='" + description + '\'' +
                ", date_creation=" + date_creation +
                ", dimension=" + dimension +
                ", disponibilite=" + disponibilite +
                ", prix=" + prix +
                ", quantite=" + quantite +
                '}';
    }
}
