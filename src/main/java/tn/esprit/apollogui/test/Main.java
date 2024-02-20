package tn.esprit.apollogui.test;

import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.services.EvenementService;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EvenementService es = new EvenementService();
        try {

            es.ajouter(new evenement("test", "descrsevent", "tevent", new Date(), new Date()));


          //evenement eventToUpdate = new evenement("test2", "wdescrsevent", "tevent", new Date(), new Date());
          //  eventToUpdate.setId(4);
           // es.modifier(eventToUpdate);


            //es.supprimer(4);
            //System.out.println(es.recuperer());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

