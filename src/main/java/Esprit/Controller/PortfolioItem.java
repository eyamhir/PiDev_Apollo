package Esprit.Controller;

import Esprit.Models.Oeuvre;
import Esprit.Models.Portfolio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PortfolioItem {
    @FXML
    private Label bio;

    @FXML
    private ImageView image;

    @FXML
    private Label nom;
    public void setData(Portfolio portfolio) {
        nom.setText(portfolio.getNom_Artistique());
        bio.setText(portfolio.getBiographie());
        String imageUrl = "file:/"+ portfolio.getImageurl();
        try {
            Image image1 = new Image(imageUrl);
            image.setImage(image1);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
