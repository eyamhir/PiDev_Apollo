package org.example.services;

import org.example.model.Utilisateur;
import org.example.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_Amies {

    private Connection connection;

    public Service_Amies() {
        connection = MaConnexion.getInstance().getCnx();
    }

    public List<Utilisateur> getAllArtistsAndClients() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        String query = "SELECT utilisateur_id, role, nom, prenom FROM utilisateur where  IsActive=1 ";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Récupérer les informations de l'utilisateur depuis le résultat de la requête
                int utilisateurId = resultSet.getInt("utilisateur_id");
                String role = resultSet.getString("role");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");

                // Créer un nouvel objet Utilisateur avec les informations récupérées
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId_utilisateur(utilisateurId);
                utilisateur.setRole(role);
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);

                // Ajouter l'utilisateur à la liste
                utilisateurs.add(utilisateur);
            }
        }

        return utilisateurs;
    }


    // Méthode pour rechercher les utilisateurs par leur nom
    public List<Utilisateur> rechercher_par_nom(String nomRecherche) throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        String query = "SELECT   nom, prenom FROM utilisateur WHERE nom LIKE ? AND IsActive=1";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Paramétrer le paramètre nomRecherche dans la requête SQL
            statement.setString(1, "%" + nomRecherche + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Récupérer les informations de l'utilisateur depuis le résultat de la requêt
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");

                    // Créer un nouvel objet Utilisateur avec les informations récupérées
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setNom(nom);
                    utilisateur.setPrenom(prenom);

                    // Ajouter l'utilisateur à la liste
                    utilisateurs.add(utilisateur);
                }
            }
        }

        return utilisateurs;
    }
}

