package Esprit.Service;

import Esprit.Interfaces.Crud;
import Esprit.Models.Categories;
import Esprit.Models.Exposition;
import Esprit.Models.Oeuvre;
import Esprit.Models.Portfolio;
import Esprit.utils.Database;

import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PortfolioService implements Crud<Portfolio> {
    private Connection connection ;
    public PortfolioService(){
        connection = Database.getInstance().getConnection();
    }
    @Override
    public void ajouter(Portfolio portfolio) throws SQLException {
        String req = "INSERT INTO portfolio(nom_Artistique, imageUrl, biographie, debut_carriere, reseau_sociaux) VALUES (?,?,?, ?, ?)";

        PreparedStatement st = connection.prepareStatement(req) ;
            st.setString(1,portfolio.getNom_Artistique());
            st.setString(2, portfolio.getImageurl());
            st.setString(3, portfolio.getBiographie());
            st.setDate(4, Date.valueOf(portfolio.getDebut_carrier()));
            st.setString(5, portfolio.getReseau_sociaux());

            st.executeUpdate();}




    @Override
    public void modifier(Portfolio portfolio) throws SQLException {
        String req="UPDATE portfolio SET nom_Artistique =?, imageUrl=?, biographie = ?,debut_carriere = ?,reseau_sociaux = ? WHERE id_portfolio=?";
        PreparedStatement ps=connection.prepareStatement(req);
        ps.setString(1,portfolio.getNom_Artistique());
        ps.setString(2, portfolio.getImageurl());
        ps.setString(3, portfolio.getBiographie());
        ps.setDate(4, Date.valueOf(portfolio.getDebut_carrier()));
        ps.setString(5, portfolio.getReseau_sociaux());
        ps.setInt(6,portfolio.getId_portfolio());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Portfolio portfolio) throws SQLException {
        int idPortfolio = portfolio.getId_portfolio();
        // Supprimer toutes les expositions liées au portfolio
        String reqSupprimerExpositions = "DELETE FROM exposition WHERE id_portfolio=?";
        PreparedStatement psSupprimerExpositions = connection.prepareStatement(reqSupprimerExpositions);
        psSupprimerExpositions.setInt(1, idPortfolio);
        psSupprimerExpositions.executeUpdate();

        // Supprimer toutes les œuvres liées au portfolio
        String reqSupprimerOeuvres = "DELETE FROM oeuvre WHERE id_portfolio=?";
        PreparedStatement psSupprimerOeuvres = connection.prepareStatement(reqSupprimerOeuvres);
        psSupprimerOeuvres.setInt(1, idPortfolio);
        psSupprimerOeuvres.executeUpdate();

        // Supprimer le portfolio lui-même
        String reqSupprimerPortfolio = "DELETE FROM portfolio WHERE id_portfolio=?";
        PreparedStatement psSupprimerPortfolio = connection.prepareStatement(reqSupprimerPortfolio);
        psSupprimerPortfolio.setInt(1, idPortfolio);
        psSupprimerPortfolio.executeUpdate();
    }
    public void supprimer1(int idPortfolio) throws SQLException {
        // Supprimer toutes les expositions liées au portfolio
        String reqSupprimerExpositions = "DELETE FROM exposition WHERE id_portfolio=?";
        try (PreparedStatement psSupprimerExpositions = connection.prepareStatement(reqSupprimerExpositions)) {
            psSupprimerExpositions.setInt(1, idPortfolio);
            psSupprimerExpositions.executeUpdate();
        }

        // Supprimer toutes les œuvres liées au portfolio
        String reqSupprimerOeuvres = "DELETE FROM oeuvre WHERE id_portfolio=?";
        try (PreparedStatement psSupprimerOeuvres = connection.prepareStatement(reqSupprimerOeuvres)) {
            psSupprimerOeuvres.setInt(1, idPortfolio);
            psSupprimerOeuvres.executeUpdate();
        }

        // Supprimer le portfolio lui-même
        String reqSupprimerPortfolio = "DELETE FROM portfolio WHERE id_portfolio=?";
        try (PreparedStatement psSupprimerPortfolio = connection.prepareStatement(reqSupprimerPortfolio)) {
            psSupprimerPortfolio.setInt(1, idPortfolio);
            psSupprimerPortfolio.executeUpdate();
        }
    }


    @Override
    public List<Portfolio> reupere_tout() throws SQLException {
        List<Portfolio> portfolios=new ArrayList<>();
        String req="SELECT * FROM portfolio " ;
        Statement st= connection.createStatement();
        ResultSet rs=st.executeQuery(req);

        while (rs.next()){
            Portfolio portfolio =new Portfolio();
            portfolio.setId_portfolio(rs.getInt("id_portfolio"));
            portfolio.setNom_Artistique(rs.getString("nom_Artistique"));
            portfolio.setImageurl(rs.getString("imageUrl"));
            portfolio.setBiographie(rs.getString("biographie"));
            portfolio.setDebut_carrier(rs.getObject("debut_carriere", LocalDate.class));
            portfolio.setReseau_sociaux(rs.getString("reseau_sociaux"));
            portfolios.add(portfolio);
        }
        return portfolios;

    }
    public List<Oeuvre> recupererOeuvresPortfolio(int idPortfolio) throws SQLException {
        List<Oeuvre> oeuvres = new ArrayList<>();
        String req = "SELECT * FROM oeuvre WHERE id_portfolio = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, idPortfolio);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Oeuvre oeuvre = new Oeuvre();
            oeuvre.setId_Oeuvre(rs.getInt("id_Oeuvre"));
            oeuvre.setTitre(rs.getString("titre"));
            oeuvre.setImage_oeuvre(rs.getString("image_oeuvre"));
            oeuvre.setDescription(rs.getString("description"));
            oeuvre.setDate_creation(rs.getObject("date_creation", LocalDate.class));
            oeuvre.setDimension(rs.getFloat("dimension"));
            oeuvre.setPrix(rs.getFloat("prix"));
            oeuvre.setDisponibilite(rs.getBoolean("disponibilite"));
            oeuvre.setQuantite(rs.getInt("quantite"));
            oeuvre.setCategories(Categories.valueOf(rs.getString("categorie")));
            oeuvre.setId_portfolio(idPortfolio); // Ajout de l'identifiant du portfolio
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }
    public List<Exposition> getExpositionsByPortfolio(int idPortfolio) throws SQLException {
        List<Exposition> expositions = new ArrayList<>();
        String req = "SELECT * FROM exposition WHERE id_portfolio=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, idPortfolio);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Exposition exposition = new Exposition();
            exposition.setId_Exposition(rs.getInt("id_Exposition"));
            exposition.setTitre(rs.getString("titre"));
            exposition.setDescription(rs.getString("description"));
            exposition.setDate_debut(rs.getObject("date_debut", LocalDate.class));
            exposition.setDate_fin(rs.getObject("date_fin", LocalDate.class));
            // Vous pouvez ajouter d'autres attributs d'exposition si nécessaire
            expositions.add(exposition);
        }
        return expositions;
    }
    public List<Oeuvre> getOeuvresByCategorie(Categories categorie) throws SQLException {
        List<Oeuvre> oeuvres = new ArrayList<>();
        String req = "SELECT * FROM oeuvre WHERE id_categorie=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, categorie.ordinal() + 1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Oeuvre oeuvre = new Oeuvre();
            oeuvre.setId_Oeuvre(rs.getInt("id_Oeuvre"));
            oeuvre.setTitre(rs.getString("titre"));
            oeuvre.setImage_oeuvre(rs.getString("image_oeuvre"));
            oeuvre.setDescription(rs.getString("description"));
            oeuvre.setDate_creation(rs.getObject("date_creation", LocalDate.class));
            oeuvre.setDimension(rs.getFloat("dimension"));
            oeuvre.setPrix(rs.getFloat("prix"));
            oeuvre.setDisponibilite(rs.getBoolean("disponibilite"));
            oeuvre.setQuantite(rs.getInt("quantite"));
            // Vous pouvez ajouter d'autres attributs d'œuvre si nécessaire
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }
    public List<Oeuvre> recupererOeuvresParCategorie(int idPortfolio, Categories categorie) throws SQLException {
        List<Oeuvre> oeuvres = new ArrayList<>();
        String req = "SELECT * FROM oeuvre WHERE id_portfolio = ? AND categories = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, idPortfolio);
        ps.setString(2, categorie.name()); // Utiliser le nom de la catégorie
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Oeuvre oeuvre = new Oeuvre();
            oeuvre.setId_Oeuvre(rs.getInt("id_Oeuvre"));
            oeuvre.setTitre(rs.getString("titre"));
            oeuvre.setImage_oeuvre(rs.getString("image_oeuvre"));
            oeuvre.setDescription(rs.getString("description"));
            oeuvre.setDate_creation(rs.getObject("date_creation", LocalDate.class));
            oeuvre.setDimension(rs.getFloat("dimension"));
            oeuvre.setPrix(rs.getFloat("prix"));
            oeuvre.setDisponibilite(rs.getBoolean("disponibilite"));
            oeuvre.setQuantite(rs.getInt("quantite"));
            oeuvre.setCategories(Categories.valueOf(rs.getString("categories")));
            oeuvre.setId_portfolio(idPortfolio);
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }


}
