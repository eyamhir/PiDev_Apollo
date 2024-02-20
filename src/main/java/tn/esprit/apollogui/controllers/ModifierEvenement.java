/*package tn.esprit.apollogui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.esprit.apollogui.models.evenement;
import tn.esprit.apollogui.services.EvenementService;

import java.sql.SQLException;
import java.util.Date;

public class ModifierEvenement {


    private TextField Date_debutTF;

    @FXML
    private TextField Date_finTF;

    @FXML
    private TextField DescriptionTF;

    @FXML
    private TextField NomTF;

    @FXML
    private TextField TypeTF;


    EvenementService es = new EvenementService();

        evenement eventToUpdate = new evenement(NomTF, DescriptionTF, TypeTF, new Date(), new Date());
        eventToUpdate.setId(4);
        es.modifier(eventToUpdate);




}

/*
 */