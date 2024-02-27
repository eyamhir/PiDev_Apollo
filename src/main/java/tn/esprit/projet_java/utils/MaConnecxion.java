package tn.esprit.projet_java.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnecxion {
    //DB PARAM
   private final String URL ="jdbc:mysql://localhost:3306/Chat_Apollo";
   // private final String URL ="jdbc:mysql://localhost:3306/pidev";
    private final String USER ="root";
    private  final String PASSWORD ="";

    //var
    private Connection cnx;
    //1
    private static MaConnecxion instance;

    //const
    //2
    private  MaConnecxion(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connection established");
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }


    public Connection getCnx() {

        return cnx;
    }

    //3
    public static MaConnecxion getInstance() {
        if(instance == null)
            instance = new MaConnecxion();

        return instance;
    }




}
