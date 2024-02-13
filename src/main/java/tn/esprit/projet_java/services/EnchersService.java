package tn.esprit.projet_java.services;

import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.utils.MaConnecxion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnchersService implements IService<Enchers>{

    private Connection cnx;

    public EnchersService(){
        cnx = MaConnecxion.getInstance().getCnx();
    }
    @Override
    public void ajouter(Enchers enchers) throws SQLException {
           // try {
                String req = "INSERT INTO enchers (type_oeuvre, min_montant,max_montant,date_debut,date_fin,artiste_associe_de_oeuvre) VALUES ('"+ enchers.getType_oeuvre() + "','" +enchers.getMin_montant()+ "','"+enchers.getMax_montant()+"','"+enchers.getDate_debut()+"','" +enchers.getDate_fin()+ "','" +enchers.getArtiste_associe_de_oeuvre() +  "')";
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("encher Added successfully!");
          //  } catch (SQLException ex) {
           //    ex.printStackTrace();
           // }
}




    @Override


    public void modifier(Enchers enchers) throws SQLException {
        try {
            String req = "UPDATE enchers SET type_oeuvre = ?, min_montant = ?, max_montant = ?, date_debut = ?, date_fin = ?, artiste_associe_de_oeuvre = ? WHERE id_enchers = ?";
            PreparedStatement es = cnx.prepareStatement(req);
            es.setString(1, enchers.getType_oeuvre());
            es.setFloat(2, enchers.getMin_montant());
            es.setFloat(3, enchers.getMax_montant());
            es.setDate(4, (Date) enchers.getDate_debut());
            es.setDate(5, (Date) enchers.getDate_fin());
            es.setString(6, enchers.getArtiste_associe_de_oeuvre());
            es.setInt(7, enchers.getId_enchers());  // Ajout du paramètre id
            es.executeUpdate();
            System.out.println("encher updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void supprimer(int id_enchers) throws SQLException{
        String req = "DELETE from enchers WHERE id_enchers =?";
        PreparedStatement es = cnx.prepareStatement(req);
        es.setInt(1,id_enchers);
        es.executeUpdate();
        System.out.println("encher deleted successfully!");
    }



  @Override
    public List<Enchers> fetchenchers() throws SQLException {
        List<Enchers> enchersList = new ArrayList<>();
        String req = "SELECT * FROM enchers";

        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Enchers enchers = new Enchers();
                enchers.setId_enchers(rs.getInt("id_enchers"));
                enchers.setType_oeuvre(rs.getString("type_oeuvre"));
                enchers.setMin_montant(rs.getFloat("min_montant"));
                enchers.setMax_montant(rs.getFloat("max_montant"));
                enchers.setDate_debut(rs.getDate("date_debut"));
                enchers.setDate_fin(rs.getDate("date_fin"));
                enchers.setArtiste_associe_de_oeuvre(rs.getString("artiste_associe_de_oeuvre"));

                enchersList.add(enchers);
            }
        }

        return enchersList;
    }

}
