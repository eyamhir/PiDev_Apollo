package Esprit.Models;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

public class Oeuvre {
    private int id_Oeuvre;
    private String titre ;
    private String image_oeuvre;
    private String description ;
    private LocalDate date_creation ;
    private float dimension ;
    private boolean disponibilite ;
    private Categories categories;
    private int id_portfolio ;
    private float prix ;
    private int quantite ;

    public Oeuvre() {
    }

    public int getId_portfolio() {
        return id_portfolio;
    }

    public void setId_portfolio(int id_portfolio) {
        this.id_portfolio = id_portfolio;
    }

    public Oeuvre(String titre, String  image_oeuvre, String description, LocalDate date_creation, float dimension,float prix, boolean disponibilite, int quantite, int id_portfolio, Categories categories) {
        this.titre = titre;
        this.image_oeuvre = image_oeuvre;
        this.description = description;
        this.date_creation = date_creation;
        this.dimension = dimension;
        this.disponibilite = disponibilite;
        this.categories = categories;
        this.id_portfolio = id_portfolio;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Oeuvre(int id_Oeuvre, String titre, String image_oeuvre, String description, LocalDate date_creation, float dimension, boolean disponibilite, float prix, int quantite, Categories categories , int id_portfolio) {
        this.id_Oeuvre = id_Oeuvre;
        this.categories=categories;
        this.titre = titre;
        this.image_oeuvre = image_oeuvre;
        this.description = description;
        this.date_creation = date_creation;
        this.dimension = dimension;
        this.disponibilite = disponibilite;
        this.prix = prix;
        this.id_portfolio=id_portfolio;
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
    public Categories getCategories() {return categories;}

    public void setCategories(Categories categories) {this.categories = categories;}

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage_oeuvre() {
        return image_oeuvre;
    }

    public void setImage_oeuvre(String image_oeuvre) {
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
                ",catégorie="+categories+
                ",portfolio="+id_portfolio+
                ", quantite=" + quantite +
                '}';
    }
}
