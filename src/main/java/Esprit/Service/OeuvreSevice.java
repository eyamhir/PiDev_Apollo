package Esprit.Service;

import Esprit.Interfaces.Crud;
import Esprit.Models.Categories;
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
        String req = "INSERT INTO `oeuvre` (titre, image_oeuvre, description, date_creation, dimension, prix, disponibilite, quantite, categorie , id_portfolio) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, oeuvre.getTitre());
            ps.setString(2, oeuvre.getImage_oeuvre());
            ps.setString(3, oeuvre.getDescription());
            ps.setDate(4, Date.valueOf(oeuvre.getDate_creation()));
            ps.setFloat(5, oeuvre.getDimension());
            ps.setFloat(6, oeuvre.getPrix());
            ps.setBoolean(7, oeuvre.isDisponibilite());
            ps.setInt(8, oeuvre.getQuantite());
            ps.setString(9, oeuvre.getCategories().name());
            ps.setInt(10, oeuvre.getId_portfolio());
            ps.executeUpdate();

        } catch (SQLException e) {
            // Handle the SQLException, e.g., log or print an error message
            e.printStackTrace();
        }
    }
    public void modifier(Oeuvre oeuvre) throws SQLException {
        String req = "UPDATE oeuvre SET titre=?, image_oeuvre=?, description=?, date_creation=?, " +
                    "dimension=?, disponibilite=?, prix=?, quantite=? , categorie=?  WHERE id_Oeuvre=?";

            PreparedStatement ps = connection.prepareStatement(req) ;
                ps.setString(1, oeuvre.getTitre());
                ps.setString(2, oeuvre.getImage_oeuvre());
                ps.setString(3, oeuvre.getDescription());
                ps.setDate(4, Date.valueOf(oeuvre.getDate_creation()));
                ps.setFloat(5, oeuvre.getDimension());
                ps.setBoolean(6, oeuvre.isDisponibilite());
                ps.setFloat(7, oeuvre.getPrix());
                ps.setInt(8, oeuvre.getQuantite());
                ps.setString(9, oeuvre.getCategories().name());
                ps.setInt(10, oeuvre.getId_Oeuvre());
                ps.executeUpdate();
            }

    @Override
    public void supprimer(Oeuvre oeuvre) throws SQLException {
        int idOeuvre = oeuvre.getId_Oeuvre();
        String req = "DELETE FROM oeuvre WHERE id_Oeuvre=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, idOeuvre);
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
                        oeuvre.setImage_oeuvre(rs.getString("image_oeuvre"));
                        oeuvre.setDescription(rs.getString("description"));
                        oeuvre.setDate_creation(rs.getObject("date_creation",LocalDate.class));
                        oeuvre.setDimension(rs.getFloat("dimension"));
                        // Récupérer la catégorie depuis la colonne "categorie"
                        String categorieStr = rs.getString("categorie");
                        // Convertir la chaîne en enum Categories
                        Categories categorie = Categories.valueOf(categorieStr.toUpperCase());
                        oeuvre.setCategories(categorie);
                        oeuvre.setDisponibilite(rs.getBoolean("disponibilite"));
                        oeuvre.setPrix(rs.getFloat("prix"));
                        oeuvre.setQuantite(rs.getInt("quantite"));
                        oeuvres.add(oeuvre);
    }
                    return oeuvres;}
    public Oeuvre getOeuvreById(int id) throws SQLException {
        ResultSet rs = null;
        Oeuvre oeuvre = null;
        PreparedStatement ps = null;
        try {
            String req = "SELECT * FROM oeuvre WHERE id_Oeuvre = ?";
            ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            rs = ps.executeQuery(req);
            if (rs.next()) {
                oeuvre = new Oeuvre();
                oeuvre.setId_Oeuvre(rs.getInt("id_Oeuvre"));
                oeuvre.setTitre(rs.getString("titre"));
                oeuvre.setImage_oeuvre(rs.getString("image_oeuvre"));
                oeuvre.setDescription(rs.getString("description"));
                oeuvre.setDate_creation(rs.getDate("date_creation").toLocalDate());
                oeuvre.setDimension(rs.getFloat("dimension"));
                oeuvre.setDisponibilite(rs.getBoolean("disponibilite"));
                oeuvre.setPrix(rs.getFloat("prix"));
                oeuvre.setQuantite(rs.getInt("quantite"));
                oeuvre.setCategories(Categories.valueOf(rs.getString("categorie")));
                oeuvre.setId_portfolio(rs.getInt("id_portfolio"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            // Ne fermez pas la connexion ici, car elle doit être gérée ailleurs
        }
        return oeuvre;
    }



    }



