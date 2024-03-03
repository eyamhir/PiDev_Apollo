package tn.esprit.apollogui.test;

import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.services.EvenementService;

import java.text.ParseException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;



public class Main {
    public static void main(String[] args) throws SQLException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date Date_debut = sdf.parse("2024-02-13");
        Date Date_fin = sdf.parse("2024-02-12");
            EvenementService es = new EvenementService();
            try {

                es.ajouter(new evenement("datech", "datechack", "datechack", Date_debut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), Date_fin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));




                //evenement eventToUpdate = new evenement("test2", "wdescrsevent", "tevent", new Date(), new Date());
                //  eventToUpdate.setId(4);
                // es.modifier(eventToUpdate);


                es.supprimer(4);
                System.out.println(es.recuperer());
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
