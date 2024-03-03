package tn.esprit.sevices;

import tn.esprit.models.Commande;
import tn.esprit.models.EmailSender;
import tn.esprit.models.Payment;
import tn.esprit.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentService implements IService<Payment> {

    private Connection connection;
    private EmailSender emailSender;

    public PaymentService(){
        connection = Mydatabase.getInstance().getConnection();
        // Initialisez l'objet EmailSender avec vos informations d'identification
        emailSender = new EmailSender("radhouanegrami329@gmail.com","cjsp gjjs czcc ktvl");
    }

    @Override
    public void ajouter(Payment payment) throws SQLException {
        String req ="INSERT INTO payment (Montant,type_Payment) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        ps.setFloat(1, payment.getMontant());
        ps.setString(2, payment.getType_Payment());
        ps.executeUpdate();

        // Récupérer l'adresse e-mail du destinataire depuis votre interface utilisateur ou d'autres sources
        String recipient = obtenirAdresseDestinataire(); // À remplacer par votre méthode ou source de données

        // Envoyer l'e-mail de facture après l'ajout du paiement
        envoyerFacture(payment, recipient);
    }

    // Méthode pour obtenir l'adresse e-mail du destinataire (à implémenter selon votre logique métier)
    private String obtenirAdresseDestinataire() {
        // Implémentez cette méthode pour obtenir dynamiquement l'adresse e-mail du destinataire
        // Vous pouvez obtenir cette adresse à partir d'un champ de texte dans votre interface utilisateur (FXML), d'une méthode de service, etc.
        return "adresse_destinataire@example.com";
    }

    // Méthode pour envoyer l'e-mail de facture
    private void envoyerFacture(Payment payment, String recipient) {
        String subject = "Facture de paiement";
        String body = "Montant : " + payment.getMontant() + "\nType de paiement : " + payment.getType_Payment();

        // Envoyer l'e-mail
        emailSender.sendEmail(recipient, subject, body);
    }


    @Override
    public void modifier(Payment payment) throws SQLException {
        String req ="UPDATE payment SET Montant=?, type_Payment=? where id_Payment=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setFloat(1, payment.getMontant());
        ps.setString(2, payment.getType_Payment());
        ps.setInt(3, payment.getId_Payment());
        ps.executeUpdate();

    }


    @Override
    public void supprimer(int id) throws SQLException {
        String req ="DELETE FROM payment WHERE id_Payment = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();

    }

    @Override
    public List<Payment> recuperer() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String req = "SELECT * FROM payment";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            Payment payment =new Payment(rs.getInt("id_Payment"),rs.getFloat("Montant"),rs.getString("type_Payment"));
            payments.add(payment);
        }
        return payments;
    }

    @Override
    public Payment recupererParId(int id) throws SQLException {
        Payment payment = null;
        String req = "SELECT * FROM payment WHERE id_Payment = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            payment = new Payment(rs.getInt("id_Payment"), rs.getFloat("Montant"), rs.getString("type_Payment"));
        }

        return payment;
    }


    public void ajouter(Payment payment, String recipient) {
        try {
            // Votre code existant pour insérer le paiement dans la base de données reste inchangé
            String req ="INSERT INTO payment (Montant,type_Payment) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, payment.getMontant());
            ps.setString(2, payment.getType_Payment());
            ps.executeUpdate();

            // Envoyer l'e-mail de facture après l'ajout du paiement
            envoyerFacture(payment, recipient);
        } catch (SQLException e) {
            // Gérer les exceptions SQL ici
            e.printStackTrace();
        }
    }

    public Map<String, Integer> countPaymentsByType() throws SQLException {
        Map<String, Integer> paymentsByType = new HashMap<>();
        String req = "SELECT type_Payment, COUNT(*) AS Total FROM payment GROUP BY type_Payment";
        PreparedStatement ps = connection.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String type = rs.getString("type_Payment");
            int total = rs.getInt("Total");
            paymentsByType.put(type, total);
        }
        return paymentsByType;
    }

    public int countTotalNumberOfPayments() throws SQLException {
        int total = 0;
        String req = "SELECT COUNT(*) AS Total FROM payment";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        if (rs.next()) {
            total = rs.getInt("Total");
        }
        return total;
    }

    public Map<String, Integer> countPaymentsByMonth() throws SQLException {
        Map<String, Integer> paymentsByMonth = new HashMap<>();
        String req = "SELECT DATE_FORMAT(Date_Payment, '%Y-%m') AS Month, COUNT(*) AS Total FROM payment GROUP BY Month";
        PreparedStatement ps = connection.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String month = rs.getString("Month");
            int total = rs.getInt("Total");
            paymentsByMonth.put(month, total);
        }
        return paymentsByMonth;
    }




}
