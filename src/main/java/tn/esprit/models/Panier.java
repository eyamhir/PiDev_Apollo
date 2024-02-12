package tn.esprit.models;

public class Panier {
    private int id_Panier;
    private int Nbr_Commande;

    public Panier() {
    }

    public Panier(int id_Panier, int Nbr_Commande) {
        this.id_Panier = id_Panier;
        this.Nbr_Commande = Nbr_Commande;
    }


    public Panier(int nbr_Commande) {
        Nbr_Commande = nbr_Commande;
    }

    public int getId_Panier() {
        return id_Panier;
    }

    public void setId_Panier(int id_Panier) {
        this.id_Panier = id_Panier;
    }

    public int getNbr_Commande() {
        return Nbr_Commande;
    }

    public void setNbr_Commande(int nbr_Commande) {
        Nbr_Commande = nbr_Commande;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_Panier=" + id_Panier +
                ", Nbr_Commande=" + Nbr_Commande +
                '}';
    }
}
