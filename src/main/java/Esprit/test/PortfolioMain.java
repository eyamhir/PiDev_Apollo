package Esprit.test;

import Esprit.Models.Categories;
import Esprit.Models.Oeuvre;
import Esprit.Models.Portfolio;
import Esprit.Service.OeuvreSevice;
import Esprit.Service.PortfolioService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;


public class PortfolioMain {
    public static void main(String[] args) {
        PortfolioService ps = new PortfolioService();
        OeuvreSevice os = new OeuvreSevice();
        try {
            // Création et ajout d'un portfolio
//           Portfolio portfolio = new Portfolio("picaso", "hello jai beduter mon carrier ", LocalDate.of(2000, 10, 1), "picaco.fb");
//           ps.ajouter(portfolio);
//
//            // Récupération de l'ID du portfolio ajouté
//            List<Portfolio> portfolios = ps.reupere_tout();
//            int idPortfolio = portfolios.get(portfolios.size() - 1).getId_portfolio(); // Récupération de l'ID du dernier portfolio ajouté
//
//            // Création d'une œuvre
//            File imageFile = new File("C:/Users/sarra/Desktop/sarra.png");
//            byte[] blobData = Files.readAllBytes(imageFile.toPath());
//            Blob sampleBlob = new javax.sql.rowset.serial.SerialBlob(blobData);
//
//            Oeuvre sampleOeuvre = new Oeuvre();
//            sampleOeuvre.setTitre("Title");
//            sampleOeuvre.setImage_oeuvre(sampleBlob);
//            sampleOeuvre.setDescription("Sample Description");
//            sampleOeuvre.setDate_creation(LocalDate.of(2001, 9, 4));
//            sampleOeuvre.setDimension(10.5f);
//            sampleOeuvre.setPrix(99.99f);
//            sampleOeuvre.setDisponibilite(true);
//            sampleOeuvre.setQuantite(5);
//
//            // Assurez-vous que la méthode setCategories() accepte un argument de type Categories et définisse correctement la catégorie
//            sampleOeuvre.setCategories(Categories.PEINTURE); //
//
//            // Ajout de l'œuvre dans le portfolio
//            sampleOeuvre.setId_portfolio(idPortfolio); // Définition de l'ID du portfolio dans l'œuvre
//            os.ajouter(sampleOeuvre);
          // System.out.println( ps.recupererOeuvresPortfolio(15));
           //ps.supprimer1(15);
           System.out.println( ps.recupererOeuvresPortfolio(14));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
