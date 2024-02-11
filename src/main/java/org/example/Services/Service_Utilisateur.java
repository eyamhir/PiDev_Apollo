package org.example.Services;

import org.example.Interfaces.Interface_Utilisateur;
import org.example.Models.Utilisateur;
import org.example.Utils.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_Utilisateur implements Interface_Utilisateur {
    private Connection connection;

    public Service_Utilisateur() {
        connection = MaConnexion.getInstance().getCnx();
    }
    @Override
    public void creerUtilisateur(Utilisateur utilisateur) throws SQLException {
        // Requête SQL pour insérer un nouvel utilisateur dans la base de données
        String query = "INSERT INTO utilisateur (nom, prenom, adresse_mail, num_tel, date_naissance, date_inscription, role, mot_passe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, utilisateur.getNom());
        statement.setString(2, utilisateur.getPrenom());
        statement.setString(3, utilisateur.getAdresse_mail());
        statement.setInt(4, utilisateur.getNum_tel());
        statement.setDate(5, new java.sql.Date(utilisateur.getDate_naissance().getTime()));
        statement.setDate(6, new java.sql.Date(utilisateur.getDate_inscription().getTime()));
        //statement.setBlob(7, utilisateur.getProfile_image());
        statement.setString(7, utilisateur.getRole());
        statement.setString(8, utilisateur.getMot_passe());
        statement.executeUpdate();
    }

    @Override
    public Utilisateur getUtilisateurParId(int id) throws SQLException {
        // Requête SQL pour sélectionner un utilisateur par son identifiant
        String query = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Utilisateur utilisateur = null; // Initialise l'utilisateur à null pour le cas où aucun résultat n'est trouvé

        if (resultSet.next()) {
            // Crée un nouvel objet Utilisateur et définit ses attributs à partir des données de la requête
            utilisateur = new Utilisateur();
            utilisateur.setId_utilisateur(resultSet.getInt("id_utilisateur"));
            utilisateur.setNom(resultSet.getString("nom"));
            utilisateur.setPrenom(resultSet.getString("prenom"));
            utilisateur.setAdresse_mail(resultSet.getString("adresse_mail"));
            utilisateur.setNum_tel(resultSet.getInt("num_tel"));
            utilisateur.setDate_naissance(resultSet.getDate("date_naissance"));
            utilisateur.setDate_inscription(resultSet.getDate("date_inscription"));
            //utilisateur.setProfile_image(resultSet.getBlob("profile_image"));
            utilisateur.setRole(resultSet.getString("role"));
            utilisateur.setMot_passe(resultSet.getString("mot_passe"));
        }

        // Retourne l'utilisateur (peut être null si aucun utilisateur correspondant n'est trouvé)
        return utilisateur;
    }

    @Override
    public List<Utilisateur> obtenirTousLesUtilisateurs() throws SQLException {
        // Requête SQL pour sélectionner tous les utilisateurs dans la base de données
        String query = "SELECT * FROM utilisateur";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<Utilisateur> utilisateurs = new ArrayList<>();

        // Parcourt tous les résultats et crée des objets Utilisateur pour chaque enregistrement
        while (resultSet.next()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId_utilisateur(resultSet.getInt("id_utilisateur"));
            utilisateur.setNom(resultSet.getString("nom"));
            utilisateur.setPrenom(resultSet.getString("prenom"));
            utilisateur.setAdresse_mail(resultSet.getString("adresse_mail"));
            utilisateur.setNum_tel(resultSet.getInt("num_tel"));
            utilisateur.setDate_naissance(resultSet.getDate("date_naissance"));
            utilisateur.setDate_inscription(resultSet.getDate("date_inscription"));
            //utilisateur.setProfile_image(resultSet.getBlob("profile_image"));
            utilisateur.setRole(resultSet.getString("role"));
            utilisateur.setMot_passe(resultSet.getString("mot_passe"));

            utilisateurs.add(utilisateur);
        }

        // Retourne la liste des utilisateurs
        return utilisateurs;
    }

    @Override
    public void mettreAJourUtilisateur(Utilisateur utilisateur) throws SQLException {
        // Requête SQL pour mettre à jour les informations d'un utilisateur dans la base de données
        String query = "UPDATE utilisateur SET nom = ?, prenom = ?, adresse_mail = ?, num_tel = ?, date_naissance = ?, date_inscription = ?, role = ?, mot_passe = ? WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, utilisateur.getNom());
        statement.setString(2, utilisateur.getPrenom());
        statement.setString(3, utilisateur.getAdresse_mail());
        statement.setInt(4, utilisateur.getNum_tel());
        statement.setDate(5, new java.sql.Date(utilisateur.getDate_naissance().getTime()));
        statement.setDate(6, new java.sql.Date(utilisateur.getDate_inscription().getTime()));
        //statement.setBlob(7, utilisateur.getProfile_image());
        statement.setString(7, utilisateur.getRole());
        statement.setString(8, utilisateur.getMot_passe());
        statement.setInt(9, utilisateur.getId_utilisateur());
        statement.executeUpdate();
    }

    @Override
    public void supprimerUtilisateur(int id) throws SQLException {
        // Requête SQL pour supprimer un utilisateur de la base de données en fonction de son identifiant
        String query = "DELETE FROM utilisateur WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
