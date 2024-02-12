package Esprit.Models;


import java.sql.Date;
import java.time.LocalDate;

public class Portfolio {
    private int id_portfolio ;
    private String biographie ;
    private LocalDate debut_carrier ;
    private String reseau_sociaux ;

    public Portfolio() {
    }

    public Portfolio(String biographie, LocalDate debut_carrier, String reseau_sociaux) {
        this.biographie = biographie;
        this.debut_carrier = debut_carrier;
        this.reseau_sociaux = reseau_sociaux;
    }

    public Portfolio(int id_portfolio, String biographie, LocalDate debut_carrier, String reseau_sociaux) {
        this.id_portfolio = id_portfolio;
        this.biographie = biographie;
        this.debut_carrier = debut_carrier;
        this.reseau_sociaux = reseau_sociaux;
    }

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

    @Override
    public String toString() {
        return "Portfolio{" +
                "id_portfolio=" + id_portfolio +
                ", biographie='" + biographie + '\'' +
                ", debut_carrier=" + debut_carrier +
                ", reseau_sociaux='" + reseau_sociaux + '\'' +
                '}';
    }

    public void setReseau_sociaux(String reseau_sociaux) {
        this.reseau_sociaux = reseau_sociaux;
    }
}
