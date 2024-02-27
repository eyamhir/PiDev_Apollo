package tn.esprit.sevices;

import tn.esprit.models.Commande;
import tn.esprit.models.Panier;
import tn.esprit.utils.Mydatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanierService implements IService <Panier> {

    private Connection connection;

    public PanierService() {
        connection = Mydatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Panier panier) throws SQLException {
        String req ="INSERT INTO panier (Nbr_Commande) VALUES ('"+panier.getNbr_Commande()+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Panier panier) throws SQLException {
        String req ="UPDATE panier SET Nbr_Commande=? where id_Panier=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, panier.getNbr_Commande());
        ps.setInt(2, panier.getId_Panier());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req ="DELETE FROM panier WHERE id_Panier = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Panier> recuperer() throws SQLException {
        List<Panier> paniers = new ArrayList<>();
        String req = "SELECT * FROM panier";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()){
            Panier panier =new Panier(rs.getInt("id_Panier"),rs.getInt("Nbr_Commande"));
            paniers.add(panier);
        }
        return paniers;
    }

    @Override
    public Panier recupererParId(int id) throws SQLException {
        Panier panier = null;
        String req = "SELECT * FROM panier WHERE id_Panier = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            panier = new Panier(rs.getInt("id_Panier"), rs.getInt("Nbr_Commande"));
        }
        return panier;
    }
}
