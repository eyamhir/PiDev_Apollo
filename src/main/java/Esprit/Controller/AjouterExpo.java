package Esprit.Controller;

import Esprit.Models.Exposition;
import Esprit.Service.ExpositionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AjouterExpo {

    @FXML
    private Button ajoutid;

    @FXML
    private DatePicker debutid;

    @FXML
    private TextArea descriptionid;

    @FXML
    private DatePicker finid;

    @FXML
    private TextField titreid;

    @FXML
    void Ajouterexpo(ActionEvent event) throws SQLException {
        ExpositionService expositionService = new ExpositionService();
        Exposition exposition=new Exposition(titreid.getText(),descriptionid.getText(),debutid.getValue(),finid.getValue(),14);
        expositionService.ajouter(exposition);

    }


}
