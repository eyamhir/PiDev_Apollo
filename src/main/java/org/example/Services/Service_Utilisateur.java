package org.example.Services;

import org.example.Interfaces.Interface_Utilisateur;
import org.example.Models.Utilisateur;
import org.example.Utils.MaConnexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Service_Utilisateur implements Interface_Utilisateur {
    private Connection connection;

    public Service_Utilisateur() {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void creerUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateur (nom, prenom, adresse_mail, num_tel, date_naissance, date_inscription, specialite_artistique, adresse_locale, role, mot_passe, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, utilisateur.getNom());
        statement.setString(2, utilisateur.getPrenom());
        statement.setString(3, utilisateur.getAdresse_mail());
        statement.setInt(4, utilisateur.getNum_tel());
        statement.setDate(5, Date.valueOf(utilisateur.getDate_naissance()));
        statement.setDate(6, Date.valueOf(utilisateur.getDate_inscription()));
        statement.setString(7, utilisateur.getSpecialite_artistique());
        statement.setString(8, utilisateur.getAdresse_locale());
        statement.setString(9, utilisateur.getRole());
        //statement.setString(10, utilisateur.getMot_passe());
        statement.setString(10, HashUtil.doHashing(utilisateur.getMot_passe()));
        statement.setBoolean(11, utilisateur.getActive());
        statement.executeUpdate();
    }

    public void creerUtilisateurUI(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateur (nom, prenom, adresse_mail, num_tel, date_naissance, date_inscription, specialite_artistique, adresse_locale, role, mot_passe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, utilisateur.getNom());
        statement.setString(2, utilisateur.getPrenom());
        statement.setString(3, utilisateur.getAdresse_mail());
        statement.setInt(4, utilisateur.getNum_tel());
        statement.setDate(5, Date.valueOf(utilisateur.getDate_naissance()));
        statement.setDate(6, Date.valueOf(utilisateur.getDate_inscription()));
        statement.setString(7, utilisateur.getSpecialite_artistique());
        statement.setString(8, utilisateur.getAdresse_locale());
        statement.setString(9, utilisateur.getRole());
        //statement.setString(10, utilisateur.getMot_passe());
        statement.setString(10, HashUtil.doHashing(utilisateur.getMot_passe()));


        statement.executeUpdate();
    }

    @Override
    public Utilisateur getUtilisateurParId(int id) throws SQLException {
        String query = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Utilisateur utilisateur = null;

        if (resultSet.next()) {
            utilisateur = new Utilisateur();
            utilisateur.setId_utilisateur(resultSet.getInt("id_utilisateur"));
            utilisateur.setNom(resultSet.getString("nom"));
            utilisateur.setPrenom(resultSet.getString("prenom"));
            utilisateur.setAdresse_mail(resultSet.getString("adresse_mail"));
            utilisateur.setNum_tel(resultSet.getInt("num_tel"));
            utilisateur.setDate_naissance(resultSet.getDate("date_naissance").toLocalDate());
            utilisateur.setDate_inscription(resultSet.getDate("date_inscription").toLocalDate());
            utilisateur.setSpecialite_artistique(resultSet.getString("specialite_artistique"));
            utilisateur.setAdresse_locale(resultSet.getString("adresse_locale"));
            utilisateur.setRole(resultSet.getString("role"));
            utilisateur.setMot_passe(resultSet.getString("mot_passe"));
            utilisateur.setActive(resultSet.getBoolean("isActive"));
        }

        return utilisateur;
    }

    @Override
    public List<Utilisateur> obtenirTousLesUtilisateurs() throws SQLException {
        String query = "SELECT * FROM utilisateur";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<Utilisateur> utilisateurs = new ArrayList<>();

        while (resultSet.next()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId_utilisateur(resultSet.getInt("id_utilisateur"));
            utilisateur.setNom(resultSet.getString("nom"));
            utilisateur.setPrenom(resultSet.getString("prenom"));
            utilisateur.setAdresse_mail(resultSet.getString("adresse_mail"));
            utilisateur.setNum_tel(resultSet.getInt("num_tel"));
            utilisateur.setDate_naissance(resultSet.getDate("date_naissance").toLocalDate());
            utilisateur.setDate_inscription(resultSet.getDate("date_inscription").toLocalDate());
            utilisateur.setSpecialite_artistique(resultSet.getString("specialite_artistique"));
            utilisateur.setAdresse_locale(resultSet.getString("adresse_locale"));
            utilisateur.setRole(resultSet.getString("role"));
            utilisateur.setMot_passe(resultSet.getString("mot_passe"));
            utilisateur.setActive(resultSet.getBoolean("isActive"));

            utilisateurs.add(utilisateur);
        }

        return utilisateurs;
    }

    @Override
    public void mettreAJourUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "UPDATE utilisateur SET nom = ?, prenom = ?, adresse_mail = ?, num_tel = ?, date_naissance = ?, date_inscription = ?, specialite_artistique = ?, adresse_locale = ?, role = ?, mot_passe = ? WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, utilisateur.getNom());
        statement.setString(2, utilisateur.getPrenom());
        statement.setString(3, utilisateur.getAdresse_mail());
        statement.setInt(4, utilisateur.getNum_tel());
        statement.setDate(5, Date.valueOf(utilisateur.getDate_naissance()));
        statement.setDate(6, Date.valueOf(utilisateur.getDate_inscription()));
        statement.setString(7, utilisateur.getSpecialite_artistique());
        statement.setString(8, utilisateur.getAdresse_locale());
        statement.setString(9, utilisateur.getRole());
        statement.setString(10, utilisateur.getMot_passe());
        statement.setInt(11, utilisateur.getId_utilisateur());
        statement.executeUpdate();
    }

    @Override
    public void supprimerUtilisateur(int id) throws SQLException {
        String query = "DELETE FROM utilisateur WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public boolean connecter(String adresse_mail, String mot_passe) {
        try {
            String req = "SELECT * FROM `utilisateur` WHERE `adresse_mail` = ? AND `mot_passe` = ?";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, adresse_mail);
            pstmt.setString(2, mot_passe);
            ResultSet rs = pstmt.executeQuery();


            // If the result set has any rows, it means the user exists with the given email and password
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Return false if any exception occurs or if no user is found with the given credentials
        return false;
    }



    public List<Utilisateur> search(String keyword) {
        List<Utilisateur> userListView = new ArrayList<>();
        try {
            String req = "SELECT * FROM `utilisateur` WHERE `nom` LIKE ? OR `prenom` LIKE ?";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId_utilisateur(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setMot_passe(rs.getString(4));
                u.setAdresse_mail(rs.getString(5));
                u.setRole(rs.getString(6));

                userListView.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userListView;
    }

    public List<Utilisateur> filterByName(String keyword) {
        List<Utilisateur> userListView = new ArrayList<>();
        try {
            String req = "SELECT * FROM `utilisateur` WHERE `nom` LIKE ? OR `prenom` LIKE ?";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId_utilisateur(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setMot_passe(rs.getString(4));
                u.setAdresse_mail(rs.getString(5));
                u.setRole(rs.getString(6));

                userListView.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userListView;
    }

    public boolean resetPWD(String email) {
        // Implement logic to generate a temporary password
        String temporaryPassword = generateTemporaryPassword();

        // Implement logic to update the user's password in the database
        boolean passwordUpdated = updatePasswordInDatabase(email, temporaryPassword);

        if (passwordUpdated) {
            // Implement logic to send an email with the new password
            boolean emailSent = sendEmail(email, temporaryPassword);
            return emailSent;
        } else {
            return false;
        }
    }

    private String generateTemporaryPassword() {
        // Générer une chaîne aléatoire pour le mot de passe temporaire
        // Vous pouvez personnaliser la longueur ou les caractères utilisés selon vos besoins
        int length = 10; // Longueur du mot de passe temporaire
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // Caractères utilisés

        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        return password.toString();
    }

    private boolean updatePasswordInDatabase(String adresse_mail, String newPwd) {
        try {
            // Établir une connexion à la base de données
            Connection connection = MaConnexion.getInstance().getCnx();

            // Construire la requête SQL pour mettre à jour le mot de passe de l'utilisateur
            String query = "UPDATE utilisateur SET mot_passe = ? WHERE adresse_mail = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPwd);
            statement.setString(2, adresse_mail);

            // Exécuter la requête SQL de mise à jour
            int rowsUpdated = statement.executeUpdate();

            // Vérifier si la mise à jour a réussi
            if (rowsUpdated > 0) {
                // La mise à jour a réussi
                return true;
            } else {
                // La mise à jour a échoué
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // En cas d'erreur, retourner false
        }
    }


    private boolean sendEmail(String email, String newPassword) {
        // Implement logic to send an email with the new password
        // Use JavaMail API or any other email-sending library
        return true; // Assuming email sending was successful
    }

}
