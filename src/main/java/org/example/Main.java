package org.example;

import org.example.Models.Client;
import org.example.Models.CodePromo;
import org.example.Models.Utilisateur;
import org.example.Services.Service_Client;
import org.example.Services.Service_CodePromo;
import org.example.Services.Service_Utilisateur;
import org.example.Utils.MaConnexion;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        MaConnexion cnx = MaConnexion.getInstance();
        //Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();
        //********************************TEST CRUD UTILISATEUR************************//
        // Création d'un utilisateur
        //Utilisateur utilisateur1 = new Utilisateur();
        //utilisateur1.setNom("Mhir");
        //utilisateur1.setPrenom("Eya");
        //utilisateur1.setAdresse_mail("eya.mhir@esprit.tn");
        //utilisateur1.setNum_tel(50859320);
        //utilisateur1.setDate_naissance(new Date());
        //utilisateur1.setDate_inscription(new Date());
        //utilisateur1.setRole("Admin");
        //utilisateur1.setMot_passe("eyamhir123");
        //try {
        // Création d'un utilisateur
        //serviceUtilisateur.creerUtilisateur(utilisateur1);
        //} catch (SQLException e) {
        //    throw new RuntimeException(e);
        //}

        // Lecture des données utilisateur
        // Utilisateur utilisateurLu = serviceUtilisateur.getUtilisateurParId(1);
        // System.out.println("utilisateur lu : " + utilisateurLu);

        // Mise à jour de l'utilisateur
        //utilisateurLu.setAdresse_mail("eyamhir329@gmail.com");
        //serviceUtilisateur.mettreAJourUtilisateur(utilisateurLu);
        //System.out.println("Utilisateur mis à jour : " + utilisateurLu);

        // Suppression d'un utilisateur
        // serviceUtilisateur.supprimerUtilisateur(1);
        //********************************TEST CRUD CLIENT************************//




        //********************************TEST CRUD ARTISTE************************//




        //********************************TEST CRUD CODE-PROMO************************//
        //Service_CodePromo serviceCodePromo = new Service_CodePromo();

        // Création d'un code promo
        //CodePromo codePromo = new CodePromo();
        //codePromo.setCode("CODESPECIALE0800");
        //codePromo.setDateExpiration(new Date());

        //try {
            // Création d'un code promo
        //    serviceCodePromo.creerCodePromo(codePromo);
        //} catch (SQLException e) {
        //    throw new RuntimeException(e);
        //}

        // Lecture des données du code promo
        //CodePromo codePromoLu = serviceCodePromo.getCodePromoParId(1);
        //System.out.println("Code promo lu : " + codePromoLu);

        // Mise à jour du code promo
        //codePromoLu.setCode("oeuvreRARE0608");
        //serviceCodePromo.mettreAJourCodePromo(codePromoLu);
        //System.out.println("Code promo mis à jour : " + codePromoLu);

        // Suppression du code promo
        //serviceCodePromo.supprimerCodePromo(3);
        //serviceCodePromo.supprimerCodePromo(4);

    }
}