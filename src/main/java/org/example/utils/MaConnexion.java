package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {
    // init
   private  final String URL = "jdbc:mysql://localhost:3306/Chat_Apollo";
   private  final String USR = "root";
   private  final String PWD = "";
    private static MaConnexion instance;
    // var

   private  Connection cnx;


    private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("connection established");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static MaConnexion getInstance(){
        if(instance==null){
            instance=new MaConnexion();

        }
        return  instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
