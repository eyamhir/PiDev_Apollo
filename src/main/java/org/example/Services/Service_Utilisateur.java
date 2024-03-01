package org.example.Services;

import javafx.scene.control.Alert;
import org.example.Interfaces.Interface_Utilisateur;
import org.example.Models.Utilisateur;
import org.example.Utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_Utilisateur implements Interface_Utilisateur {
    private Connection connection;

    public Service_Utilisateur() {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void creerUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateur (nom, prenom, adresse_mail, num_tel, date_naissance, date_inscription, specialite_artistique, adresse_locale, role, mot_passe, isActive ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        statement.setString(10, utilisateur.getMot_passe());
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
        statement.setString(10, utilisateur.getMot_passe());


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

    /*public boolean connecter(String adresse_mail, String mot_passe) {
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
    }*/
    public boolean connecter(String adresse_mail, String mot_passe) {
        try {
            String req = "SELECT * FROM `utilisateur` WHERE `adresse_mail` = ? AND `mot_passe` = ?";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, adresse_mail);
            pstmt.setString(2, mot_passe);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                boolean isActive = rs.getBoolean("isActive");
                boolean isBanned = rs.getBoolean("isBanned");

                if (isBanned) {
                    // Utilisateur banni, afficher une alerte
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Avertissement");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous avez été banni. Veuillez contacter l'administrateur pour plus d'informations.");
                    alert.showAndWait();

                    // Retourner false pour indiquer que la connexion a échoué en raison du bannissement
                    return false;
                }

                // Si l'utilisateur n'est pas banni et est actif, retourner true pour indiquer une connexion réussie
                return isActive;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Si aucune ligne n'est retournée, cela signifie que la connexion a échoué
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

    public boolean isAdmin(String adresse_mail) {
        try {
            String req = "SELECT * FROM `utilisateur` WHERE `adresse_mail` = ? AND `role` = 'Admin'";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, adresse_mail);
            ResultSet rs = pstmt.executeQuery();
            // result of user checked and Admin
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean emailExists(String adresse_mail) {
        try {
            String query = "SELECT COUNT(*) FROM `utilisateur` WHERE `adresse_mail` = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, adresse_mail);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean userExists(String nom, String prenom) {
        try {
            String query = "SELECT COUNT(*) FROM `utilisateur` WHERE `nom` = ? AND `prenom` = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public void updatePassword(int id_utilisateur, String newPassword) throws SQLException {
        try { String req = "UPDATE `utilisateur` SET `mot_passe`=? WHERE `id_utilisateur`=?";
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, id_utilisateur);
            pstmt.executeUpdate();
            System.out.println("Password updated successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    // Method to retrieve the user's ID by email

    public Utilisateur getUserByEmail(String adresse_mail) throws SQLException {
        Utilisateur utilisateur = null;
        PreparedStatement pstmt = null;
        try {
            String query = "SELECT * FROM utilisateur WHERE adresse_mail = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, adresse_mail);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setId_utilisateur(rs.getInt("id_utilisateur"));
                utilisateur.setAdresse_mail(rs.getString("adresse_mail"));
                // Définir les autres attributs de l'objet Utilisateur au besoin
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return utilisateur;
    }



    public int getUserIdByEmail(String adresse_mail) throws SQLException {
        int id_utilisateur = -1; // Initialize with a default value
        PreparedStatement pstmt = null;
        try {
            String query = "SELECT id_utilisateur FROM utilisateur WHERE adresse_mail = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, adresse_mail);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id_utilisateur = rs.getInt("id_utilisateur");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return id_utilisateur;
    }

    public boolean findEmail(String adresse_mail) throws SQLException {
        return getUserIdByEmail(adresse_mail) != -1;
    }

    public void banUtilisateur(int id_utilisateur) throws SQLException {
        String query = "UPDATE utilisateur SET isBanned = true WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_utilisateur);
        statement.executeUpdate();
    }

    public void debanUtilisateur(int id_utilisateur) throws SQLException {
        String query = "UPDATE utilisateur SET isBanned = false WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id_utilisateur);
        statement.executeUpdate();
    }


}
