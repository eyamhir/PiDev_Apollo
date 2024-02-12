package tn.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mydatabase {

    private static Mydatabase instance;
    private final String url="jdbc:mysql://localhost:3306/pidevg3";
    private final String user="root";
    private final String password="";
    private Connection connection;

    private Mydatabase() {
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("successfully connected");

        }catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Mydatabase getInstance(){
        if (instance==null)
            instance = new Mydatabase();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
