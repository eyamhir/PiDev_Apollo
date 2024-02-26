package Esprit.Controller;

import Esprit.Models.Oeuvre;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class OeuvreController {



    @FXML
    private Label dimensiontx;

    @FXML
    private ImageView imagetx;

    @FXML
    private Label prixTx;

    @FXML
    private Label titretx;
    public void setData(Oeuvre oeuvre) {
        titretx.setText(oeuvre.getTitre());
        dimensiontx.setText(String.valueOf(oeuvre.getDimension()));
        prixTx.setText(String.valueOf(oeuvre.getPrix())+"DT");
        String imageUrl = oeuvre.getImage_oeuvre();
        try {
            Image image = new Image(imageUrl);
            imagetx.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
