package tn.esprit.sevices;

import tn.esprit.models.Commande;
import tn.esprit.models.Payment;
import tn.esprit.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentService implements IService<Payment> {

    private Connection connection;

    public PaymentService(){
        connection = Mydatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Payment payment) throws SQLException {
        String req ="INSERT INTO payment (Montant,type_Payment) VALUES ('"+payment.getMontant()+"','"+payment.getType_Payment()+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
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
}