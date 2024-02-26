package Esprit.Service;

import Esprit.Interfaces.Crud;
import Esprit.Models.Exposition;
import Esprit.utils.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpositionService implements Crud<Exposition> {
    private Connection connection ;
    public ExpositionService(){
        // Obtention de la connexion à la base de données
        connection= Database.getInstance().getConnection();

    }
    @Override
    public void ajouter(Exposition exposition) throws SQLException {
        String req = "INSERT INTO exposition(titre, description, date_debut, date_fin) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, exposition.getTitre());
            ps.setString(2, exposition.getDescription());
            ps.setDate(3, Date.valueOf(exposition.getDate_debut()));
            ps.setDate(4, Date.valueOf(exposition.getDate_fin()));
            ps.executeUpdate();
    }

    @Override
    public void modifier(Exposition exposition) throws SQLException {
        String req = "UPDATE exposition SET titre=?, description=?, date_debut=?, date_fin=? WHERE id_Exposition=?";

        PreparedStatement ps = connection.prepareStatement(req) ;
            ps.setString(1, exposition.getTitre());
            ps.setString(2, exposition.getDescription());
            ps.setDate(3,Date.valueOf(exposition.getDate_debut()));
            ps.setDate(4, Date.valueOf(exposition.getDate_fin()));
            ps.setInt(5, exposition.getId_Exposition());
            ps.executeUpdate();
        }



    @Override
    public void supprimer(Exposition exposition) throws SQLException {
        int idExposition = exposition.getId_Exposition();
        String req = "DELETE FROM exposition WHERE id_Exposition=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, idExposition);
        ps.executeUpdate();
    }

    @Override
    public List<Exposition> reupere_tout() throws SQLException {
        List<Exposition> expositions = new ArrayList<>();


            String req = "SELECT * FROM exposition";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Exposition exposition = new Exposition();
                        exposition.setId_Exposition(rs.getInt("id_Exposition"));
                        exposition.setTitre(rs.getString("titre"));
                        exposition.setDescription(rs.getString("description"));
                        exposition.setDate_debut(rs.getObject("date_debut", LocalDate.class));
                        exposition.setDate_fin(rs.getObject("date_fin",LocalDate.class));

                        expositions.add(exposition);
                    }
                    return expositions;
                }

}
