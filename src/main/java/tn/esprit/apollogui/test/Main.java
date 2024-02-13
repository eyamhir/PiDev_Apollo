package tn.esprit.apollogui.test;

import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.services.EvenementService;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EvenementService es = new EvenementService();
        try {
            es.ajouter(new evenement("Elyes", "desevent", "tevent", new Date(), new Date()));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}