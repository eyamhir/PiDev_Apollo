package tn.esprit.projet_java.test;
import tn.esprit.projet_java.models.Enchers;
import tn.esprit.projet_java.models.Mise;
import tn.esprit.projet_java.services.EnchersService;
import tn.esprit.projet_java.services.MiseService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
       // MaConnecxion db = MaConnecxion.getInstance();
        //MaConnecxion db2 = MaConnecxion.getInstance();
        //System.out.println(db);
        //System.out.println(db2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       // Date dateDebut = new Date(sdf.parse("2024-02-11").getTime());
        //Date dateFin = new Date(sdf.parse("2024-02-10").getTime());
        java.util.Date dateDebut = sdf.parse("2024-02-13");
        java.util.Date dateFin = sdf.parse("2024-02-12");
        int id_utilisateur=3;
        int id_enchers=1;



        EnchersService  es = new EnchersService();
        //ClientService cs = new ClientService();
        MiseService ms = new MiseService();



//////////////test_enchere///////////////////////////

      //  try {


             // es.ajouter(new Enchers(123.56,dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "peinture","oumayma",id_utilisateur));
            //es.modifier(new Enchers(15,"Peint",1284.50,dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),"ouma"));
           // es.supprimer(57);
           //System.out.println(es.fetchenchers());
      //  } catch (SQLException e) {
          //  System.err.println(e.getMessage());
       // }
///////////////////////test_mise////////////////////////////////
       try {
            // ms.ajouter(new Mise(1500,id_enchers,id_utilisateur));
          //  ms.modifier(new Mise(18,1800.50,77,77));
           // ms.supprimer(3);
            System.out.println(ms.fetchmise());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
}}

