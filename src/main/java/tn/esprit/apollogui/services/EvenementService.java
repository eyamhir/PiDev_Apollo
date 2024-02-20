package tn.esprit.apollogui.services;

import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.utils.MyDatabase;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EvenementService implements IService<evenement> {

    private Connection connection;

    public EvenementService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    public void ajouter(evenement evenement) throws SQLException {
        String dateFormatPattern = "yyyy-MM-dd"; // Define the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);

        String req = "INSERT INTO evenement(Nom, Date_debut, Date_fin, Description, Type) VALUES('"
                + evenement.getNom() + "','" + dateFormat.format(evenement.getDate_debut()) + "','"
                + dateFormat.format(evenement.getDate_fin()) + "','" + evenement.getDescription() + "','"
                + evenement.getType() + "')";
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(req);
            System.out.println("Evenement ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout de l'événement: " + ex.getMessage());
            throw ex;
        }
    }


    public void modifier(evenement evenement) throws SQLException {
        String req = "UPDATE evenement SET Nom=?, Date_debut=?, Date_fin=?, Description=?, Type=? WHERE Id=?";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, evenement.getNom());
            ps.setDate(2, new java.sql.Date(evenement.getDate_debut().getTime()));
            ps.setDate(3, new java.sql.Date(evenement.getDate_fin().getTime()));
            ps.setString(4, evenement.getDescription());
            ps.setString(5, evenement.getType());
            ps.setInt(6, evenement.getId());

            ps.executeUpdate();
            System.out.println("Evenement modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de l'événement: " + ex.getMessage());
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
            evenement.setDate_debut(rs.getDate("Date_debut"));
            evenement.setDate_fin(rs.getDate("Date_fin"));
            evenement.setDescription(rs.getString("Description"));
            evenement.setType(rs.getString("Type"));
            
            
            evenements.add(evenement);
        }
        return evenements;






    }
}
