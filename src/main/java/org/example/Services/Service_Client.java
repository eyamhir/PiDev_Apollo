package org.example.Services;

import org.example.Interfaces.Interface_Client;
import org.example.Models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_Client implements Interface_Client {
    private Connection connection;

    public Service_Client(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void creerClient(Client client) throws SQLException {
        String query = "INSERT INTO client (nom, prenom, adresse_mail, num_tel, date_naissance, date_inscription, role, mot_passe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, client.getNom());
        statement.setString(2, client.getPrenom());
        statement.setString(3, client.getAdresse_mail());
        statement.setInt(4, client.getNum_tel());
        statement.setDate(5, new java.sql.Date(client.getDate_naissance().getTime()));
        statement.setDate(6, new java.sql.Date(client.getDate_inscription().getTime()));
        //statement.setBytes(8, client.getProfile_image());
        statement.setString(7, client.getRole());
        statement.setString(8, client.getMot_passe());
        statement.executeUpdate();
    }

    @Override
    public Client getClientParId(int id) throws SQLException {
        String query = "SELECT * FROM client WHERE id_Client = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Client client = null;

        if (resultSet.next()) {
            client = new Client();
            client.setId_Client(resultSet.getInt("id_Client"));
            client.setNom(resultSet.getString("nom"));
            client.setPrenom(resultSet.getString("prenom"));
            client.setAdresse_mail(resultSet.getString("adresse_mail"));
            client.setNum_tel(resultSet.getInt("num_tel"));
            client.setDate_naissance(resultSet.getDate("date_naissance"));
            client.setDate_inscription(resultSet.getDate("date_inscription"));
            //client.setProfile_image(resultSet.getBlob("profile"));
            client.setRole(resultSet.getString("role"));
            client.setMot_passe(resultSet.getString("mot_passe"));
        }

        return client;
    }

    @Override
    public List<Client> obtenirTousLesClients() throws SQLException {
        String query = "SELECT * FROM client";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<Client> clients = new ArrayList<>();

        while (resultSet.next()) {
            Client client = new Client();
            client.setId_Client(resultSet.getInt("id_Client"));
            client.setNom(resultSet.getString("nom"));
            client.setPrenom(resultSet.getString("prenom"));
            client.setAdresse_mail(resultSet.getString("adresse_mail"));
            client.setNum_tel(resultSet.getInt("num_tel"));
            client.setDate_naissance(resultSet.getDate("date_naissance"));
            client.setDate_inscription(resultSet.getDate("date_inscription"));
            //client.setProfile_image(resultSet.getBlob("profile"));
            client.setRole(resultSet.getString("role"));
            client.setMot_passe(resultSet.getString("mot_passe"));
            clients.add(client);
        }

        return clients;
    }

    @Override
    public void mettreAJourClient(Client client) throws SQLException {
        String query = "UPDATE client SET nom = ?, prenom = ?, adresse_mail = ?, num_tel = ?, date_naissance = ?, date_inscription = ?, role = ?, mot_passe = ? WHERE id_Client = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, client.getNom());
        statement.setString(2, client.getPrenom());
        statement.setString(3, client.getAdresse_mail());
        statement.setInt(4, client.getNum_tel());
        statement.setDate(5, new java.sql.Date(client.getDate_naissance().getTime()));
        statement.setDate(6, new java.sql.Date(client.getDate_inscription().getTime()));
        //statement.setBlob(7, client.getProfile_image());
        statement.setString(7, client.getRole());
        statement.setString(8, client.getMot_passe());
        statement.setInt(9, client.getId_Client());
        statement.executeUpdate();
    }

    @Override
    public void supprimerClient(int id) throws SQLException {
        String query = "DELETE FROM client WHERE id_Client = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
