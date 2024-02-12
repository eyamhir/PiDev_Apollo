package Esprit.test;

import Esprit.Models.Exposition;
import Esprit.Service.ExpositionService;

import java.sql.SQLException;
import java.time.LocalDate;

public class ExpositionMain {
    public static void main(String[] args) throws SQLException {
        ExpositionService expo=new ExpositionService();
        Exposition newExposition = new Exposition("lisa","hhelo",LocalDate.of(2001,2,3),LocalDate.of(2002,2,3));
        expo.ajouter(newExposition);

    }

}
