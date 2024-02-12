package Esprit.test;

import Esprit.Models.Portfolio;
import Esprit.Service.PortfolioService;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;


public class PortfolioMain {
    public static void main(String[] args){
        PortfolioService ps= new PortfolioService();
        try {
            Portfolio portfolio=new Portfolio("eya",LocalDate.of(2000,10,1),"eya.fb");
            ps.ajouter(portfolio);
            System.out.println(ps.reupere_tout());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
