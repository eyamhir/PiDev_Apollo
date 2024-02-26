//package Esprit.test;
//
//import Esprit.Models.Oeuvre;
//import Esprit.Service.OeuvreSevice;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.nio.charset.StandardCharsets;
//import java.sql.Blob;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.time.LocalDate;
//
//public class OeuvreMain {
//    public static void main(String[] args) {
//        OeuvreSevice oeuvreSevice=new OeuvreSevice();
//
//        try {
//            File imageFile=new File("Desktop/sarah jouini .png");
//            byte[] blobData = new byte[(int) imageFile.length()];
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(blobData);
//            Blob sampleBlob = new javax.sql.rowset.serial.SerialBlob(blobData);
//
//            // Create a sample Oeuvre object
//            Oeuvre sampleOeuvre = new Oeuvre();
//            sampleOeuvre.setTitre("Sample Title");
//            sampleOeuvre.setImage_oeuvre(sampleBlob);
//            sampleOeuvre.setDescription("Sample Description");
//            sampleOeuvre.setDate_creation(LocalDate.of(2001,9,4));
//            sampleOeuvre.setDimension(10.5f);
//            sampleOeuvre.setPrix(99.99f);
//            sampleOeuvre.setDisponibilite(true);
//            sampleOeuvre.setQuantite(5);
//
//            // Call the ajouter method to add the Oeuvre to the database
//            //oeuvreSevice.ajouter(sampleOeuvre);
//
//            // Print the Oeuvre object after insertion (ID should be updated if auto-incremented)
//            System.out.println("Inserted Oeuvre: " + sampleOeuvre);
//        } catch (SQLException e) {
//            // Handle the SQLException, e.g., log or print an error message
//            e.printStackTrace();
//        }
//    }
//}
