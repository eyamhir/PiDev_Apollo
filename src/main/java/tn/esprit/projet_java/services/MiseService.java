package tn.esprit.projet_java.services;

import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.models.Mise;
import tn.esprit.projet_java.utils.MaConnecxion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MiseService implements MService<Mise>{

    private Connection cnx;

    public MiseService(){
        cnx = MaConnecxion.getInstance().getCnx();
    }


  /*  @Override
    public void ajouter(Mise mise) throws SQLException {
        String req = "INSERT INTO mise (max_montant) VALUES ("+mise.getMax_montant() +"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("encher Added successfully!");
    }*/
  @Override

  public void ajouter(Mise mise) throws SQLException {
      String req = "INSERT INTO mise (max_montant, id_enchers, id_utilisateur) VALUES (?, ?, ?)";

      try (PreparedStatement pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
          pst.setDouble(1, mise.getMax_montant());
          pst.setInt(2, mise.getId_enchers());  // Replace with the appropriate method to get id_encheres
          pst.setInt(3, mise.getId_utilisateur()); // Replace with the appropriate method to get id_utilisateur

          int rowsAffected = pst.executeUpdate();

          if (rowsAffected > 0) {
              // Retrieve the auto-generated id_mise value
              try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                  if (generatedKeys.next()) {
                      int generatedId = generatedKeys.getInt(1);
                      // Set the generated id_mise to the Mise object
                      mise.setId_mise(generatedId);
                      System.out.println("Mise added successfully with id_mise: " + generatedId);
                  }
              }
          } else {
              System.out.println("Failed to add Mise.");
          }
      } catch (SQLException e) {
          e.printStackTrace(); // Handle the exception appropriately
      }
  }





    @Override
    public void modifier(Mise mise) throws SQLException {
        String req = "UPDATE mise SET max_montant = ? WHERE id_mise = ?";

        try (PreparedStatement pst = cnx.prepareStatement(req)) {
            pst.setDouble(1, mise.getMax_montant());
            pst.setInt(2, mise.getId_mise());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mise updated successfully!");
            } else {
                System.out.println("No Mise found with the specified id_mise.");
                // You may want to handle this case more appropriately, e.g., throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    @Override
    public void supprimer(int id_mise) throws SQLException {
        String req = "DELETE FROM mise WHERE id_mise = ?";

        try (PreparedStatement pst = cnx.prepareStatement(req)) {
            pst.setInt(1, id_mise);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mise deleted successfully!");
            } else {
                System.out.println("No Mise found with the specified id_mise.");
                // You may want to handle this case more appropriately, e.g., throw an exception
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }



    @Override
    public List<Mise> fetchmise() throws SQLException {
        List<Mise> miseList = new ArrayList<>();
        String req = "SELECT * FROM mise";

        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                int id_mise = rs.getInt("id_mise");
                double max_montant = rs.getDouble("max_montant");
                int id_enchers = rs.getInt("id_enchers");
                int id_utilisateur = rs.getInt("id_utilisateur");

                Mise mise = new Mise(id_mise, max_montant, id_enchers, id_utilisateur);
                miseList.add(mise);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return miseList;
    }



}
