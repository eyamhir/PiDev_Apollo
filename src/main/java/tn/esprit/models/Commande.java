package tn.esprit.models;

public class Commande {
    private int id_Commande;
    private float prix_total;
    private String date_creation_commande;

    public Commande(int id_Commande, Float prix_total, String date_creation_commande) {
        this.id_Commande = id_Commande;
        this.prix_total = prix_total;
        this.date_creation_commande = date_creation_commande;
    }

    public Commande() {
    }
    public Commande(float prix,String date){
        prix_total =prix;
        date_creation_commande = date;
    }

    public int getId_Commande() {
        return id_Commande;
    }

    public void setId_Commande(int id_Commande) {
        this.id_Commande = id_Commande;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public String getDate_creation_commande() {
        return date_creation_commande;
    }

    public void setDate_creation_commande(String date_creation_commande) {
        this.date_creation_commande = date_creation_commande;
    }



    @Override
    public String toString() {
        return "Commande{" +
                "id_Commande=" + id_Commande +
                ", prix_total=" + prix_total +
                ", date_creation_commande=" + date_creation_commande +
                '}';
    }
}
