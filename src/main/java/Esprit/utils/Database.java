package Esprit.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String URL="jdbc:mysql://localhost:3306/appolo";
    private final String USER="root";
    private final String PWD="";
    private Connection connection ;
    private static Database instance;

    private Database() {
        try {
            connection= DriverManager.getConnection(URL,USER,PWD);
            System.out.println("conncexion réussie");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Database getInstance(){
        if (instance==null) instance=new Database();
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
}
