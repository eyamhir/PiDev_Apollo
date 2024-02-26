package Esprit.Models;


import java.sql.Date;
import java.time.LocalDate;

public class Portfolio {
    private int id_portfolio ;
    private String imageurl;
    private String nom_Artistique ;
    private String biographie ;
    private LocalDate debut_carrier ;
    private String reseau_sociaux ;



    public Portfolio() {
    }

    public Portfolio(String nom_Artistique,String imageurl,String biographie, LocalDate debut_carrier, String reseau_sociaux ) {
        this.nom_Artistique=nom_Artistique;
        this.imageurl=imageurl;
        this.biographie = biographie;
        this.debut_carrier = debut_carrier;
        this.reseau_sociaux = reseau_sociaux;

    }

    public Portfolio(String nom_Artistique,int id_portfolio, String biographie, LocalDate debut_carrier, String reseau_sociaux ) {
        this.nom_Artistique=nom_Artistique;
        this.id_portfolio = id_portfolio;
        this.biographie = biographie;
        this.debut_carrier = debut_carrier;
        this.reseau_sociaux = reseau_sociaux;

    }

    public String getNom_Artistique() { return nom_Artistique; }

    public void setNom_Artistique(String nom_Artistique) {this.nom_Artistique = nom_Artistique;}

    public int getId_portfolio() {
        return id_portfolio;
    }

    public void setId_portfolio(int id_portfolio) {
        this.id_portfolio = id_portfolio;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public LocalDate getDebut_carrier() {
        return  debut_carrier;
    }

    public void setDebut_carrier(LocalDate debut_carrier) {
        this.debut_carrier = debut_carrier;
    }

    public String getReseau_sociaux() {
        return reseau_sociaux;
    }
    public String getImageurl() { return imageurl;}

    public void setImageurl(String imageurl) {this.imageurl = imageurl;}

    @Override
    public String toString() {
        return "Portfolio{" +
                "id_portfolio=" + id_portfolio +
                ", biographie='" + biographie + '\'' +
                ", debut_carrier=" + debut_carrier +
                ", reseau_sociaux='" + reseau_sociaux + '\'' +
                ",nom_Artistique=" + nom_Artistique+
                '}';
    }

    public void setReseau_sociaux(String reseau_sociaux) {
        this.reseau_sociaux = reseau_sociaux;
    }
}
