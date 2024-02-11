package org.example.Services;

import org.example.Interfaces.Interface_Artiste;
import org.example.Models.Artiste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_Artiste implements Interface_Artiste {
    private Connection connection;

    public Service_Artiste(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void creerArtiste(Artiste artiste) throws SQLException {
        String query = "INSERT INTO artiste (nom, prenom, adresse_mail, num_tel, date_naissance, date_inscription, role, mot_passe, specialite_artistique) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, artiste.getNom());
        statement.setString(2, artiste.getPrenom());
        statement.setString(3, artiste.getAdresse_mail());
        statement.setInt(4, artiste.getNum_tel());
        statement.setDate(5, new java.sql.Date(artiste.getDate_naissance().getTime()));
        statement.setDate(6, new java.sql.Date(artiste.getDate_inscription().getTime()));
        //statement.setBytes(7, artiste.getProfile_image());
        statement.setString(7, artiste.getRole());
        statement.setString(8, artiste.getMot_passe());
        statement.setString(9, artiste.getSpecialite_Artistique());
        statement.executeUpdate();
    }

    @Override
    public Artiste getArtisteParId(int id) throws SQLException {
        String query = "SELECT * FROM artiste WHERE id_Artiste = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Artiste artiste = null;

        if (resultSet.next()) {
            artiste = new Artiste();
            artiste.setId_Artiste(resultSet.getInt("id_Artiste"));
            artiste.setNom(resultSet.getString("nom"));
            artiste.setPrenom(resultSet.getString("prenom"));
            artiste.setAdresse_mail(resultSet.getString("adresse_mail"));
            artiste.setNum_tel(resultSet.getInt("num_tel"));
            artiste.setDate_naissance(resultSet.getDate("date_naissance"));
            artiste.setDate_inscription(resultSet.getDate("date_inscription"));
            //artiste.setProfile_image(resultSet.getBlob("profile"));
            artiste.setRole(resultSet.getString("role"));
            artiste.setMot_passe(resultSet.getString("mot_passe"));
            artiste.setSpecialite_Artistique(resultSet.getString("specialite_artistique"));
        }

        return artiste;
    }

    @Override
    public List<Artiste> obtenirTousLesArtistes() throws SQLException {
        String query = "SELECT * FROM artiste";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<Artiste> artistes = new ArrayList<>();

        while (resultSet.next()) {
            Artiste artiste = new Artiste();
            artiste.setId_Artiste(resultSet.getInt("id_Artiste"));
            artiste.setNom(resultSet.getString("nom"));
            artiste.setPrenom(resultSet.getString("prenom"));
            artiste.setAdresse_mail(resultSet.getString("adresse_mail"));
            artiste.setNum_tel(resultSet.getInt("num_tel"));
            artiste.setDate_naissance(resultSet.getDate("date_naissance"));
            artiste.setDate_inscription(resultSet.getDate("date_inscription"));
            //artiste.setProfile_image(resultSet.getBlob("profile"));
            artiste.setRole(resultSet.getString("role"));
            artiste.setMot_passe(resultSet.getString("mot_passe"));
            artiste.setSpecialite_Artistique(resultSet.getString("specialite_artistique"));
            artistes.add(artiste);
        }

        return artistes;
    }

    @Override
    public void mettreAJourArtiste(Artiste artiste) throws SQLException {
        String query = "UPDATE artiste SET nom = ?, prenom = ?, adresse_mail = ?, num_tel = ?, date_naissance = ?, date_inscription = ?, role = ?, mot_passe = ?, specialite_artistique = ? WHERE id_Artiste = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, artiste.getNom());
        statement.setString(2, artiste.getPrenom());
        statement.setString(3, artiste.getAdresse_mail());
        statement.setInt(4, artiste.getNum_tel());
        statement.setDate(5, new java.sql.Date(artiste.getDate_naissance().getTime()));
        statement.setDate(6, new java.sql.Date(artiste.getDate_inscription().getTime()));
        //statement.setBlob(7, artiste.getProfile_image());
        statement.setString(7, artiste.getRole());
        statement.setString(8, artiste.getMot_passe());
        statement.setString(9, artiste.getSpecialite_Artistique());
        statement.setInt(10, artiste.getId_Artiste());
        statement.executeUpdate();
    }

    @Override
    public void supprimerArtiste(int id) throws SQLException {
        String query = "DELETE FROM artiste WHERE id_Artiste = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}