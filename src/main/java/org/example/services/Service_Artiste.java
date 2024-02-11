package org.example.services;

import org.example.interfaces.Interface_Artiste;
import org.example.model.Artiste;
import org.example.utils.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service_Artiste implements Interface_Artiste<Artiste> {

    private Connection connection;

    public Service_Artiste() {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void creerArtiste(Artiste artiste) throws SQLException {
        String query = "INSERT INTO artiste (specialite_artistique) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, artiste.getSpecialite_Artistique());
        statement.executeUpdate();
    }

    @Override
    public Artiste lire_Donnée_Artiste(int id) throws SQLException {
        String query = "SELECT * FROM artiste WHERE artiste_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Artiste artiste = null;

        if (resultSet.next()) {
            artiste = new Artiste();
            artiste.setArtisteId(resultSet.getInt("artiste_id"));
            artiste.setSpecialite_Artistique(resultSet.getString("specialite_artistique"));
        }

        return artiste;
    }

    @Override
    public void mettreAJourArtiste(Artiste artiste) throws SQLException {
        String query = "UPDATE artiste SET specialite_artistique = ? WHERE artiste_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, artiste.getSpecialite_Artistique());
        statement.setInt(2, artiste.getArtisteId());
        statement.executeUpdate();
    }

    @Override
    public void supprimerArtiste(int id) throws SQLException {
        String query = "DELETE FROM artiste WHERE artiste_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
