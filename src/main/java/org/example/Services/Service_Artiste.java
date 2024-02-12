package org.example.Services;

import org.example.Interfaces.Interface_Artiste;
import org.example.Models.Artiste;
import org.example.Utils.MaConnexion;

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
    public Service_Artiste()
    {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void creerArtiste(Artiste artiste) throws SQLException {
        String query = "INSERT INTO artiste (specialite_artistique) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, artiste.getSpecialite_Artistique());

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
            artiste.setSpecialite_Artistique(resultSet.getString("specialite_artistique"));
            artistes.add(artiste);
        }

        return artistes;
    }

    @Override
    public void mettreAJourArtiste(Artiste artiste) throws SQLException {
        String query = "UPDATE artiste SET specialite_artistique = ? WHERE id_Artiste = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, artiste.getId_Artiste());
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