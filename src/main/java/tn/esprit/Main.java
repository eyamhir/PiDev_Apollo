package tn.esprit;

import tn.esprit.models.Commande;
import tn.esprit.models.Panier;
import tn.esprit.models.Payment;
import tn.esprit.sevices.CommandeService;
import tn.esprit.sevices.PanierService;
import tn.esprit.sevices.PaymentService;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        //PaymentService paymentService = new PaymentService();
        //LocalDateTime datetime = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        //String time = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(datetime);
        //paymentService.ajouter(new Payment(20,"carteBancaire"));
       //paymentService.modifier(new Payment(1,18500,"carte Visa"));
        //paymentService.supprimer(1);
       //List<Payment> payments = paymentService.recuperer();
        //System.out.println(payments);

        PanierService panierService = new PanierService();
        //panierService.ajouter(new Panier(50));
        //panierService.modifier(new Panier(1,250));
        //panierService.supprimer(1);
        List<Panier> paniers = panierService.recuperer();
        System.out.println(paniers);
        //Test Commit
    }
}