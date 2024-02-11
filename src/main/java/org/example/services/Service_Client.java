package org.example.services;

import org.example.interfaces.Interface_Client;
import org.example.model.Client;
import org.example.utils.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service_Client implements Interface_Client<Client> {
    private Connection connection;

    public Service_Client() {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void creerClient(Client client) throws SQLException {
        String query = "INSERT INTO client (client_id) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, client.getClient_Id());
        statement.executeUpdate();
    }

    @Override
    public Client lire_Donnée_Client(int id) throws SQLException {
        String query = "SELECT * FROM client WHERE client_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Client client = null;

        if (resultSet.next()) {
            client = new Client();
            client.setClient_Id(resultSet.getInt("client_id"));
        }

        return client;
    }

    @Override
    public void mettreAJourClient(Client client) throws SQLException {
        String query = "UPDATE client SET client_id = ? WHERE client_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, client.getClient_Id());
        // Si vous avez d'autres champs à mettre à jour, vous pouvez les ajouter ici
        statement.setInt(2, client.getClient_Id()); // Condition pour l'identifiant du client à mettre à jour
        statement.executeUpdate();
    }
    @Override
    public void supprimerClient(int id) throws SQLException {
        String query = "DELETE FROM client WHERE client_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
