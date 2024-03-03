package tn.esprit.sevices;

import com.sun.javafx.collections.MappingChange;
import tn.esprit.models.Commande;
import tn.esprit.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandeService implements IService<Commande>{

    private Connection connection;

    public CommandeService(){
        connection = Mydatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Commande commande) throws SQLException {
        String req ="INSERT INTO commande (Prix_total,Date_Creation_Commande) VALUES ('"+commande.getPrix_total()+"','"+commande.getDate_creation_commande()+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(Commande commande) throws SQLException {

        String req ="UPDATE commande SET prix_total=?, date_creation_commande=? where id_Commande=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setFloat(1, commande.getPrix_total());
        ps.setString(2, commande.getDate_creation_commande());
        ps.setInt(3,commande.getId_Commande());
        ps.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req ="DELETE FROM commande WHERE id_Commande = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Commande> recuperer() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String req = "SELECT * FROM commande";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            Commande commande =new Commande(rs.getInt("id_Commande"),rs.getFloat("Prix_total"),rs.getString("date_creation_commande"));
            commandes.add(commande);
        }
        return commandes;
    }

    public Commande recupererParId(int id) throws SQLException {
        Commande commande = null;
        String req = "SELECT * FROM commande WHERE id_Commande = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            commande = new Commande(rs.getInt("id_Commande"), rs.getFloat("Prix_total"), rs.getString("date_creation_commande"));
        }

        return commande;
    }


    // Méthode pour récupérer le nombre de commandes par mois
    public Map<String, Integer> countCommandsByMonth() throws SQLException {
        Map<String, Integer> commandsByMonth = new HashMap<>();
        String req = "SELECT DATE_FORMAT(Date_Creation_Commande, '%Y-%m') AS Month, COUNT(*) AS Total FROM commande GROUP BY Month";
        PreparedStatement ps = connection.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String month = rs.getString("Month");
            int total = rs.getInt("Total");
            commandsByMonth.put(month, total);
        }
        return commandsByMonth;
    }
}



