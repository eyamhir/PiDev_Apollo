package Esprit.Service;

import Esprit.Interfaces.Crud;
import Esprit.Models.Oeuvre;
import Esprit.Models.Portfolio;
import Esprit.utils.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OeuvreSevice implements Crud<Oeuvre> {
    private Connection connection ;
    public OeuvreSevice(){
        connection= Database.getInstance().getConnection();
    }
    @Override
    public void ajouter(Oeuvre oeuvre) {
        String req = "INSERT INTO `oeuvre` (titre, image_oeuvre, description, date_creation, dimension, prix, disponibilite, quantite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, oeuvre.getTitre());
            ps.setBlob(2, oeuvre.getImage_oeuvre());
            ps.setString(3, oeuvre.getDescription());
            ps.setDate(4, Date.valueOf(oeuvre.getDate_creation()));
            ps.setFloat(5, oeuvre.getDimension());
            ps.setFloat(6, oeuvre.getPrix());
            ps.setBoolean(7, oeuvre.isDisponibilite());
            ps.setInt(8, oeuvre.getQuantite());
            ps.executeUpdate();

        } catch (SQLException e) {
            // Handle the SQLException, e.g., log or print an error message
            e.printStackTrace();
        }
    }
    public void modifier(Oeuvre oeuvre) throws SQLException {
        String req = "UPDATE oeuvre SET titre=?, image_oeuvre=?, description=?, date_creation=?, " +
                    "dimension=?, disponibilite=?, prix=?, quantite=? WHERE id_Oeuvre=?";

            PreparedStatement ps = connection.prepareStatement(req) ;
                ps.setString(1, oeuvre.getTitre());
                ps.setBlob(2, oeuvre.getImage_oeuvre());
                ps.setString(3, oeuvre.getDescription());
                ps.setDate(4, Date.valueOf(oeuvre.getDate_creation()));
                ps.setFloat(5, oeuvre.getDimension());
                ps.setBoolean(6, oeuvre.isDisponibilite());
                ps.setFloat(7, oeuvre.getPrix());
                ps.setInt(8, oeuvre.getQuantite());
                ps.setInt(9, oeuvre.getId_Oeuvre());
                ps.executeUpdate();
            }

    @Override
    public void supprimer(int id) throws SQLException {
            String req = "DELETE FROM oeuvre WHERE id_Oeuvre=?";
            PreparedStatement ps = connection.prepareStatement(req) ;
            ps.setInt(1, id);
            ps.executeUpdate();


            }

    @Override
    public List<Oeuvre> reupere_tout() throws SQLException {
        List<Oeuvre> oeuvres = new ArrayList<>();
        String req = "SELECT * FROM oeuvre";
        Statement st = connection.createStatement();
        ResultSet rs=st.executeQuery(req);

                    while (rs.next()) {
                        Oeuvre oeuvre = new Oeuvre();
                        oeuvre.setId_Oeuvre(rs.getInt("id_Oeuvre"));
                        oeuvre.setTitre(rs.getString("titre"));
                        oeuvre.setImage_oeuvre(rs.getBlob("image_oeuvre"));
                        oeuvre.setDescription(rs.getString("description"));
                        oeuvre.setDate_creation(rs.getObject("date_creation",LocalDate.class));
                        oeuvre.setDimension(rs.getFloat("dimension"));
                        oeuvre.setDisponibilite(rs.getBoolean("disponibilite"));
                        oeuvre.setPrix(rs.getFloat("prix"));
                        oeuvre.setQuantite(rs.getInt("quantite"));
                        oeuvres.add(oeuvre);
    }
                    return oeuvres;}
}
