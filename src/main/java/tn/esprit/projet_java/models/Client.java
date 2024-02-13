package tn.esprit.projet_java.models;

public class Client {
    private int id_client;
    private float montant_proposee_parclient;
    private String statut_enchers_client;

    public Client() {
    }

    public Client(int id_client, float montant_proposee_parclient, String statut_enchers_client) {
        this.id_client = id_client;
        this.montant_proposee_parclient = montant_proposee_parclient;
        this.statut_enchers_client = statut_enchers_client;
    }

    public Client( float montant_proposee_parclient, String statut_enchers_client) {
        this.montant_proposee_parclient = montant_proposee_parclient;
        this.statut_enchers_client = statut_enchers_client;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public float getMontant_proposee_parclient() {
        return montant_proposee_parclient;
    }

    public void setMontant_proposee_parclient(float montant_proposee_parclient) {
        this.montant_proposee_parclient = montant_proposee_parclient;
    }

    public String getStatut_enchers_client() {
        return statut_enchers_client;
    }

    public void setStatut_enchers_client(String statut_enchers_client) {
        this.statut_enchers_client = statut_enchers_client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", montant_proposee_parclient=" + montant_proposee_parclient +
                ", statut_enchers_client='" + statut_enchers_client + '\'' +
                '}';
    }
}
