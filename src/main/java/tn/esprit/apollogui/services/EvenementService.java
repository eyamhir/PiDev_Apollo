package tn.esprit.apollogui.services;

import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.utils.MyDatabase;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EvenementService implements IService<evenement> {

    private Connection connection;

    public EvenementService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    /*public void ajouter(evenement evenement) throws SQLException {
        String dateFormatPattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);

        String req = "INSERT INTO evenement(Nom, Date_debut, Date_fin, Description, Type) VALUES('"
                + evenement.getNom() + "','" + dateFormat.format(evenement.getDate_debut()) + "','"
                + dateFormat.format(evenement.getDate_fin()) + "','" + evenement.getDescription() + "','"
                + evenement.getType() + "')";
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(req);
            System.out.println("Event added!");
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            throw ex;
        }
    }*/

    public void ajouter(evenement evenement) throws SQLException {


        try {


            String req = "INSERT INTO evenement (Id,Nom,Type,Description, Date_debut, Date_fin) VALUES (?, ?, ?, ?, ?,?)";


            // Using try-with-resources to automatically close resources (PreparedStatement)
            try (PreparedStatement pst = connection.prepareStatement(req)) {
                // Assuming getId_utilisateur() is a method in your Enchers class
                pst.setInt(1, evenement.getId());
                pst.setString(2, evenement.getNom());
                pst.setString(3, evenement.getType());
                pst.setString(4, evenement.getDescription());
                pst.setDate(5, java.sql.Date.valueOf(evenement.getDate_debut()));
                pst.setDate(6, java.sql.Date.valueOf(evenement.getDate_fin()));

                int rowsAffected = pst.executeUpdate();

                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    System.out.println("Event added succesfully!");
                } else {
                    System.out.println("Event was not added");
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

    public void modifier(evenement evenement) throws SQLException {
        String req = "UPDATE evenement SET Nom=?, Date_debut=?, Date_fin=?, Description=?, Type=? WHERE Id=?";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, evenement.getNom());
            ps.setDate(2, java.sql.Date.valueOf(evenement.getDate_debut()));
            ps.setDate(3, java.sql.Date.valueOf(evenement.getDate_fin()));
            ps.setString(4, evenement.getDescription());
            ps.setString(5, evenement.getType());
            ps.setInt(6, evenement.getId());

            ps.executeUpdate();
            System.out.println("Event Updated !");
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            throw ex;
        }
    }


    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM evenement WHERE id=?";
        PreparedStatement ps= connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();


    }

    @Override
    public  List<evenement> recuperer() throws SQLException {
        List<evenement> evenements = new ArrayList<>();
        String req= "SELECT * FROM evenement";
        Statement st= connection.createStatement();
        ResultSet rs=st.executeQuery(req);

        while (rs.next()){
            evenement evenement= new evenement();
            evenement.setId(rs.getInt("Id"));
            evenement.setDate_debut(rs.getObject("Date_debut", LocalDate.class));
            evenement.setDate_fin(rs.getObject("Date_fin", LocalDate.class));
            evenement.setDescription(rs.getString("Description"));
            evenement.setType(rs.getString("Type"));
            
            
            evenements.add(evenement);
        }
        return evenements;






    }
}
