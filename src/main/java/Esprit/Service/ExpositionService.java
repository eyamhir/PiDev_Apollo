package Esprit.Service;

import Esprit.Interfaces.Crud;
import Esprit.Models.Categories;
import Esprit.Models.Exposition;
import Esprit.Models.Type_Expose;
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
        String req = "INSERT INTO exposition(image_affiche,titre, description, date_debut, date_fin,type_expo,localisation) VALUES (?,?,?, ?, ?, ?,?)";

        PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,exposition.getImageAffiche());
            ps.setString(2, exposition.getTitre());
            ps.setString(3, exposition.getDescription());
            ps.setDate(4, Date.valueOf(exposition.getDate_debut()));
            ps.setDate(5, Date.valueOf(exposition.getDate_fin()));
            ps.setString(6, exposition.getTypeExpose().name());
            ps.setString(7, exposition.getLocalisation());

            ps.executeUpdate();
    }

    @Override
    public void modifier(Exposition exposition) throws SQLException {
        String req = "UPDATE exposition SET image_affiche=?, titre=?, description=?, date_debut=?, date_fin=? ,type_expo=?,localisation=? WHERE id_Exposition=?";

        PreparedStatement ps = connection.prepareStatement(req) ;
            ps.setString(1, exposition.getImageAffiche());
            ps.setString(2, exposition.getTitre());
            ps.setString(3, exposition.getDescription());
            ps.setDate(4,Date.valueOf(exposition.getDate_debut()));
            ps.setDate(5, Date.valueOf(exposition.getDate_fin()));
            ps.setString(6,exposition.getTypeExpose().name());
            ps.setString(7,exposition.getLocalisation());
            ps.setInt(8, exposition.getId_Exposition());
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
                        exposition.setImageAffiche(rs.getString("image_affiche"));
                        exposition.setTitre(rs.getString("titre"));
                        exposition.setDescription(rs.getString("description"));
                        exposition.setDate_debut(rs.getObject("date_debut", LocalDate.class));
                        exposition.setDate_fin(rs.getObject("date_fin",LocalDate.class));
                        String typeExpostr = rs.getString("type_expo");
                        Type_Expose typeExpose = Type_Expose.valueOf(typeExpostr.toUpperCase());
                        exposition.setTypeExpose(typeExpose);

                        exposition.setLocalisation(rs.getString("localisation"));

                        expositions.add(exposition);
                    }
                    return expositions;
                }

}
