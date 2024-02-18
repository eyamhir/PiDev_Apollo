package org.example.test;

import org.example.Models.Artiste;
import org.example.Models.Client;
import org.example.Models.CodePromo;
import org.example.Models.Utilisateur;
import org.example.Services.Service_Artiste;
import org.example.Services.Service_Client;
import org.example.Services.Service_CodePromo;
import org.example.Services.Service_Utilisateur;
import org.example.Utils.MaConnexion;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        //MaConnexion cnx = MaConnexion.getInstance();
        //Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();
        //********************************TEST CRUD UTILISATEUR************************//
        // Création d'un utilisateur
        /*Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("Mhir");
        utilisateur.setPrenom("Zaineb");
        utilisateur.setAdresse_mail("zainebmhir@gmail.com");
        utilisateur.setNum_tel(22264665);
        utilisateur.setDate_naissance(new Date());
        utilisateur.setDate_inscription(new Date());
        utilisateur.setRole("Client");
        utilisateur.setMot_passe("zaineb123");
        try {
         //Création d'un utilisateur
        serviceUtilisateur.creerUtilisateur(utilisateur);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        // Lecture des données utilisateur
         //Utilisateur utilisateurLu = serviceUtilisateur.getUtilisateurParId(2);
         //System.out.println("utilisateur lu : " + utilisateurLu);

        // Mise à jour de l'utilisateur
        //utilisateurLu.setNom("eya");
        //serviceUtilisateur.mettreAJourUtilisateur(utilisateurLu);
        //System.out.println("Utilisateur mis à jour : " + utilisateurLu);

        // Suppression d'un utilisateur
        // serviceUtilisateur.supprimerUtilisateur(2);

        //********************************TEST CRUD CLIENT************************//
        // Création d'un client
        //Service_Client serviceClient = new Service_Client();
        //Client client = new Client();
        //client.setId_utilisateur(2);


        //try {
        // Création d'un client
        //    serviceClient.creerClient(client);
        //} catch (SQLException e) {
        //    throw new RuntimeException(e);
        //}

        // Lecture des données client
        //Client clientLu = serviceClient.getClientParId(1);
        //System.out.println("Client lu : " + clientLu);


        // Suppression d'un artiste
        // serviceClient.supprimerClient(2);



        //********************************TEST CRUD ARTISTE************************//
        // Création d'un artiste
        //Service_Artiste serviceArtiste = new Service_Artiste();
        //Artiste artiste1 = new Artiste();
        //artiste1.setSpecialite_Artistique("peintre");
        //artiste1.setId_utilisateur(1);


        //try {
            // Création d'un artiste
        //    serviceArtiste.creerArtiste(artiste1);
        //} catch (SQLException e) {
        //    throw new RuntimeException(e);
        //}

        // Lecture des données artiste
        //Artiste artisteLu = serviceArtiste.getArtisteParId(1);
        //System.out.println("Artiste lu : " + artisteLu);

        // Mise à jour de l'artiste
     /*   artisteLu.setSpecialite_Artistique("Musique Classique");
        serviceArtiste.mettreAJourArtiste(artisteLu);
        System.out.println("Artiste mis à jour : " + artisteLu);*/

        // Suppression d'un artiste
        //  serviceArtiste.supprimerArtiste(1);



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