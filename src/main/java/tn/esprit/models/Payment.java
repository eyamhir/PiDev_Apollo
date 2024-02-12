package tn.esprit.models;

public class Payment {
    private int id_Payment;
    private float Montant;
    private String type_Payment;

    public Payment() {
    }

    public Payment(int id_Payment, float montant, String type_Payment) {
        this.id_Payment = id_Payment;
        Montant = montant;
        this.type_Payment = type_Payment;
    }

    public Payment(float montant, String type_Payment) {
        Montant = montant;
        this.type_Payment = type_Payment;
    }

    public int getId_Payment() {
        return id_Payment;
    }

    public void setId_Payment(int id_Payment) {
        this.id_Payment = id_Payment;
    }

    public float getMontant() {
        return Montant;
    }

    public void setMontant(float montant) {
        Montant = montant;
    }

    public String getType_Payment() {
        return type_Payment;
    }

    public void setType_Payment(String type_Payment) {
        this.type_Payment = type_Payment;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id_Payment=" + id_Payment +
                ", Montant=" + Montant +
                ", type_Payment='" + type_Payment + '\'' +
                '}';
    }
}
