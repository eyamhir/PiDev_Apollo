package Esprit.Controller;
import Esprit.Models.Exposition;
import Esprit.Models.Oeuvre;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class ExpositionItem {




        @FXML
        private Label Titreid;

        @FXML
        private Label debutid;

        @FXML
        private Label finid;

        @FXML
        private ImageView imgAffiche;


    public void setData(Exposition exposition) {
        Titreid.setText(exposition.getTitre());
        debutid.setText(String.valueOf(exposition.getDate_debut()));
        finid.setText(String.valueOf(exposition.getDate_fin()));
        String imageUrl = exposition.getImageAffiche();
        try {
            Image image = new Image(imageUrl);
            imgAffiche.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    }



