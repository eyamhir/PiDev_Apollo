package tn.esprit.projet_java.models;

public class Mise {
    private int id_mise;
    private double max_montant;
    private int id_enchers;
    private int id_utilisateur;

    public Mise(double maxMontant) {
    }

    public Mise(int id_mise, double max_montant, int id_enchers, int id_utilisateur) {
        this.id_mise = id_mise;
        this.max_montant = max_montant;
        this.id_enchers = id_enchers;
        this.id_utilisateur = id_utilisateur;
    }

    public Mise(int id_mise, double max_montant) {
        this.id_mise = id_mise;
        this.max_montant = max_montant;
    }

    public Mise(double max_montant, int id_enchers, int id_utilisateur) {
        this.max_montant = max_montant;
        this.id_enchers = id_enchers;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_mise() {
        return id_mise;
    }

    public void setId_mise(int id_mise) {
        this.id_mise = id_mise;
    }

    public double getMax_montant() {
        return max_montant;
    }

    public void setMax_montant(float max_montant) {
        this.max_montant = max_montant;
    }

    public int getId_enchers() {
        return id_enchers;
    }

    public void setId_enchers(int id_enchers) {
        this.id_enchers = id_enchers;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Mise{" +
               // "id_mise=" + id_mise +
                "max_montant=" + max_montant +
               // ", id_enchers=" + id_enchers +
                //", id_utilisateur=" + id_utilisateur +
                '}';
    }
}
