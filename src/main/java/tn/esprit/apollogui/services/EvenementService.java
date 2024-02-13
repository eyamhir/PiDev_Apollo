package tn.esprit.apollogui.services;

import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.utils.MyDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
            throw ex; // Rethrow the exception
        }
    }


    public void modifier(evenement evenement) {
        // Implement logic to update evenement record
    }

    public void supprimer(int id) {
        // Implement logic to delete evenement record
    }

    @Override
    public List<evenement> recuperer() {
        // Implement logic to retrieve evenement records
        return null;
    }
}
