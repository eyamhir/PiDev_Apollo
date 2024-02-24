package org.example.Models;

import java.time.LocalDate;
import java.util.*;
    public class Utilisateur {
        private int id_utilisateur;
        private String nom;
        private String prenom;
        private String adresse_mail;
        private int num_tel;
        private LocalDate date_naissance;
        private LocalDate date_inscription;
        private String role;
        private String mot_passe;

        // Constructor
        public Utilisateur() {
        }

        public Utilisateur(String nom, String prenom, String adresse_mail, int num_tel, LocalDate date_naissance, LocalDate date_inscription, String role, String mot_passe) {
            this.nom = nom;
            this.prenom = prenom;
            this.adresse_mail = adresse_mail;
            this.num_tel = num_tel;
            this.date_naissance = date_naissance;
            this.date_inscription = date_inscription;
            this.role = role;
            this.mot_passe = mot_passe;
        }


        public Utilisateur(int id_utilisateur, String nom, String prenom, String adresse_mail, int num_tel, LocalDate date_naissance, LocalDate date_inscription, String role, String mot_passe) {
            this.id_utilisateur = id_utilisateur;
            this.nom = nom;
            this.prenom = prenom;
            this.adresse_mail = adresse_mail;
            this.num_tel = num_tel;
            this.date_naissance = date_naissance;
            this.date_inscription = date_inscription;
            this.role = role;
            this.mot_passe = mot_passe;
        }

        public int getId_utilisateur() {
            return id_utilisateur;
        }

        public void setId_utilisateur(int id_utilisateur) {
            this.id_utilisateur = id_utilisateur;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getAdresse_mail() {
            return adresse_mail;
        }

        public void setAdresse_mail(String adresse_mail) {
            this.adresse_mail = adresse_mail;
        }

        public int getNum_tel() {
            return num_tel;
        }

        public void setNum_tel(int num_tel) {
            this.num_tel = num_tel;
        }

        public LocalDate getDate_naissance() {
            return date_naissance;
        }

        public void setDate_naissance(LocalDate date_naissance) {
            this.date_naissance = date_naissance;
        }

        public LocalDate getDate_inscription() {
            return date_inscription;
        }

        public void setDate_inscription(LocalDate date_inscription) {
            this.date_inscription = date_inscription;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getMot_passe() {
            return mot_passe;
        }

        public void setMot_passe(String mot_passe) {
            this.mot_passe = mot_passe;
        }

        @Override
        public String toString() {
            return "Utilisateur{" +
                    "id_utilisateur=" + id_utilisateur +
                    ", nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", adresse_mail='" + adresse_mail + '\'' +
                    ", num_tel=" + num_tel +
                    ", date_naissance=" + date_naissance +
                    ", date_inscription=" + date_inscription +
                    ", role='" + role + '\'' +
                    ", mot_passe='" + mot_passe + '\'' +
                    '}';
        }
    }


