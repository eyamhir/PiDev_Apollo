package tn.esprit.projet_java.test;
import tn.esprit.projet_java.models.Client;
import tn.esprit.projet_java.services.ClientService;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.services.EnchersService;
import tn.esprit.projet_java.utils.MaConnecxion;

import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
       // MaConnecxion db = MaConnecxion.getInstance();
        //MaConnecxion db2 = MaConnecxion.getInstance();
        //System.out.println(db);
        //System.out.println(db2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDebut = new Date(sdf.parse("2024-02-11").getTime());
        Date dateFin = new Date(sdf.parse("2024-02-10").getTime());
        EnchersService  es = new EnchersService();
        ClientService cs = new ClientService();
        /*try {
            es.ajouter(new Enchers("Peinture ",10000,230000,dateDebut, dateFin,"ouma"));
        } catch (SQLException e) {
           // throw new RuntimeException(e);
            System.err.println(e.getMessage());
        }*/
       /* try {
        es.modifier(new Enchers(1,"Peinture ",10000,230000,dateDebut, dateFin,"ouma"));
        } catch (SQLException e) {
           // throw new RuntimeException(e);
            System.err.println(e.getMessage());
    }*/

//////////////////////test_enchers///////////////////////////////////////////////////////
        try {
           // es.ajouter(new Enchers("peint ",10000,230000,dateDebut, dateFin,"oumayma"));
            es.modifier(new Enchers(4,"Peinture ",10000,230000,dateDebut, dateFin,"ouma"));
            //es.supprimer(2);
            System.out.println(es.fetchenchers());
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

//////////////test_client///////////////////////////
       /* try {
             cs.ajouter(new Client(10000,"ouma"));
           // cs.modifier(new Client(12,"oumayma"));
            //cs.supprimer(12);
            //System.out.println(cs.fetchclient());
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }*/
}}
// test commit et push
