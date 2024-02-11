package org.example.Interfaces;

import org.example.Models.Client;

import java.sql.SQLException;
import java.util.List;

public interface Interface_Client {
    // Créer
    void creerClient(Client client) throws SQLException;

    // Lire
    Client getClientParId(int id) throws SQLException;
    List<Client> obtenirTousLesClients() throws SQLException;


    // Mettre à jour
    void mettreAJourClient(Client client) throws SQLException;

    // Supprimer
    void supprimerClient(int id) throws SQLException;
}
