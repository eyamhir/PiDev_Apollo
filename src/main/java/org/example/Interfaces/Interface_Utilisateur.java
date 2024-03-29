package org.example.Interfaces;

import org.example.Models.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface Interface_Utilisateur {
    // Créer
    void creerUtilisateur(Utilisateur utilisateur) throws SQLException;

    // Lire
    Utilisateur getUtilisateurParId(int id) throws SQLException;
    List<Utilisateur> obtenirTousLesUtilisateurs() throws SQLException;

    // Mettre à jour
    void mettreAJourUtilisateur(Utilisateur utilisateur) throws SQLException;

    // Supprimer
    void supprimerUtilisateur(int id) throws SQLException;

    public List<Utilisateur> search(String code);

    public List<Utilisateur> filterByName(String code);
    public boolean connecter(String adresse_mail, String mot_passe);

}
