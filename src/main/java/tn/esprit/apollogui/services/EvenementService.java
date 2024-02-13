package tn.esprit.apollogui.services;

import tn.esprit.apollogui.utils.MyDatabase;

import java.sql.Connection;

public class EvenementService implements IService<evenement>{


    private Connection connection;

    public EvenementService(){
        connection= MyDatabase.getInstance().getConnection();
    }
    public void ajouter(evenement evenement){
        String req = "INSERT INTO evenement(Nom,Date_debut,Date_fin,Description,Type) VALUES('"+evenement.getNom()++")"

    }
    public void modifier(evenement evenement){

    }
    public void supprimer(evenement evenement) {

    }
    public List<evenement> recupere() {
        return null;
    }
}
