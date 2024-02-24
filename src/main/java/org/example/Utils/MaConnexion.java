package org.example.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {
    // init
    final String URL = "jdbc:mysql://localhost:3306/PiDev_3A14_Apollo";
    final String USR = "root";
    final String PWD = "";
    private static MaConnexion instance;
    // var

    private Connection cnx;

    public MaConnexion() {
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static MaConnexion getInstance(){
        if (instance == null){
            instance = new MaConnexion();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}