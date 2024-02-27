package tn.esprit.projet_java.services;
import tn.esprit.projet_java.services.IService;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.utils.MaConnecxion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnchersService implements IService<Enchers>{

    private Connection cnx;


    public EnchersService(){
        cnx = MaConnecxion.getInstance().getCnx();
    }




    @Override
    public void ajouter(Enchers enchers) throws SQLException {

           /*// try {
                String req = "INSERT INTO enchers (type_oeuvre, min_montant,max_montant,date_debut,date_fin,image) VALUES ('"+ enchers.getType_oeuvre() + "','" +enchers.getMin_montant()+ "','"+enchers.getDate_debut()+"','" +enchers.getDate_fin()+ "','" +enchers.getImage() +  "')";
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("encher Added successfully!");
          //  } catch (SQLException ex) {
           //    ex.printStackTrace();
           // }*/
        /*try {
            String req = "INSERT INTO enchers (id_utilisateur, type_oeuvre, min_montant, date_debut, date_fin, image) VALUES (?, ?, ?, ?, ?, ?)";

            // Using PreparedStatement to handle parameters and prevent SQL injection
            try (PreparedStatement pst = cnx.prepareStatement(req)) {
                // Assuming getId_utilisateur() is a method in your Enchers class
                pst.setInt(1, enchers.getId_utilisateur());
                pst.setString(2, enchers.getType_oeuvre());
                pst.setDouble(3, enchers.getMin_montant());
                pst.setDate(4, java.sql.Date.valueOf(enchers.getDate_debut()));
                pst.setDate(5, java.sql.Date.valueOf(enchers.getDate_fin()));
                pst.setString(6, enchers.getImage());

                pst.executeUpdate();
            }

            System.out.println("Encher Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/

        ////////////////////////
        try {


            String req = "INSERT INTO enchers (id_utilisateur, type_oeuvre, min_montant, date_debut, date_fin, image) VALUES (?, ?, ?, ?, ?, ?)";

            // Using try-with-resources to automatically close resources (PreparedStatement)
            try (PreparedStatement pst = cnx.prepareStatement(req)) {
                // Assuming getId_utilisateur() is a method in your Enchers class
                pst.setInt(1, enchers.getId_utilisateur());
                pst.setString(2, enchers.getType_oeuvre());
                pst.setDouble(3, enchers.getMin_montant());
                pst.setDate(4, java.sql.Date.valueOf(enchers.getDate_debut()));
                pst.setDate(5, java.sql.Date.valueOf(enchers.getDate_fin()));
                pst.setString(6, enchers.getImage());

                int rowsAffected = pst.executeUpdate();

                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    System.out.println("Encher added successfully!");
                } else {
                    System.out.println("Encher not added. No rows affected.");
                }
            } // The PreparedStatement will be closed automatically here

        } catch (SQLException ex) {
            // Log the exception or handle it more gracefully
            ex.printStackTrace();
            // Imprimer des informations supplémentaires sur l'exception
            System.out.println("SQL State: " + ex.getSQLState());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Message: " + ex.getMessage());
        }



    }





    @Override


    public void modifier(Enchers enchers) throws SQLException {
        try {
            String req = "UPDATE enchers SET  type_oeuvre = ?, min_montant = ?, date_debut = ?, date_fin = ?, image = ? WHERE id_enchers = ?";
            PreparedStatement es = cnx.prepareStatement(req);
            es.setString(1, enchers.getType_oeuvre());
            es.setDouble(2, enchers.getMin_montant());
            es.setDate(3, Date.valueOf(enchers.getDate_debut()));
            es.setDate(4, Date.valueOf(enchers.getDate_fin()));
            es.setString(5, enchers.getImage());
            es.setInt(6, enchers.getId_enchers());  // Ajout du paramètre id
            es.executeUpdate();
            System.out.println("encher updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void modifierUI(Enchers enchers) throws SQLException {
        try {
            String req = "UPDATE enchers SET  type_oeuvre = ?, min_montant = ?, date_debut = ?, date_fin = ?  WHERE id_enchers = ?";
            PreparedStatement es = cnx.prepareStatement(req);
            es.setString(1, enchers.getType_oeuvre());
            es.setDouble(2, enchers.getMin_montant());
            es.setDate(3, Date.valueOf(enchers.getDate_debut()));
            es.setDate(4, Date.valueOf(enchers.getDate_fin()));
           es.setInt(5, enchers.getId_enchers());  // Ajout du paramètre id
            es.executeUpdate();
            System.out.println("encher updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id_enchers) throws SQLException{
        System.out.println("Deleting Encher with ID: " + id_enchers);
        // Your deletion logic
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
               // enchers.setMax_montant(rs.getFloat("max_montant"));
                enchers.setDate_debut(rs.getObject("date_debut",LocalDate.class));

                enchers.setDate_fin(rs.getObject("date_fin",LocalDate.class));
                enchers.setImage(rs.getString("image"));
                enchers.setId_utilisateur(rs.getInt("id_utilisateur"));
                enchersList.add(enchers);
            }
        }

        return enchersList;
    }

}
