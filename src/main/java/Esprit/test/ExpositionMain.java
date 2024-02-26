package Esprit.test;

import Esprit.Models.Exposition;
import Esprit.Service.ExpositionService;

import java.sql.SQLException;
import java.time.LocalDate;

public class ExpositionMain {
    public static void main(String[] args) throws SQLException {
        ExpositionService expo=new ExpositionService();
        //Exposition newExposition = new Exposition("sarra","amira",LocalDate.of(2023,9,4),LocalDate.of(2024,6,2));
        //newExposition.setId_Exposition(3);
       // expo.supprimer(3);


       // Exposition exposition=new Exposition();
        //exposition.setTitre("Test Exposition");
        //exposition.setDescription("Test Description");
       // exposition.setDate_debut(LocalDate.of(2022, 1, 1));
       // exposition.setDate_fin(LocalDate.of(2022, 2, 1));
        //expo.ajouter(exposition);

        // Modify the added Exposition

        //exposition.setTitre("new expo");
        //exposition.setDescription("Description");
        //exposition.setDate_debut(LocalDate.of(2022, 3, 1));
        //exposition.setDate_fin(LocalDate.of(2022, 4, 1));
        // Call the modifier method
        //expo.modifier(exposition);

        // Display all Expositions after modification
        //System.out.println("Expositions after modification:");
        System.out.println(expo.reupere_tout());



    }

}
