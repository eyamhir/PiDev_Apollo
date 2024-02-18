package org.example.Services;

import org.example.Interfaces.Interface_Client;
import org.example.Models.Client;
import org.example.Utils.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_Client implements Interface_Client {
    private Connection connection;


    public Service_Client()
    {
        connection = MaConnexion.getInstance().getCnx();
    }


    @Override
    public void creerClient(Client client) throws SQLException {
        String query = "INSERT INTO client (Adresse, id_utilisateur) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, client.getAdresse());
        statement.setInt(2, client.getId_utilisateur());
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
            client.setAdresse(resultSet.getString("Adresse"));
            client.setId_utilisateur(resultSet.getInt("id_utilisateur"));


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
            client.setAdresse(resultSet.getString("Adresse"));
            client.setId_utilisateur(resultSet.getInt("id_utilisateur"));
            clients.add(client);
        }

        return clients;
    }

    @Override
    public void mettreAJourClient(Client client) throws SQLException {
        String query = "UPDATE client SET id_utilisateur = ? WHERE id_Client = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, client.getId_utilisateur());
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
