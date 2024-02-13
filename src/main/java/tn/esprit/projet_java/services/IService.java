package tn.esprit.projet_java.services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T>{
    //add
     void ajouter(T t) throws SQLException;
    void modifier(T t) throws SQLException;
    void supprimer(int id_enchers) throws SQLException;


    //list : select
     List<T> fetchenchers() throws SQLException;

    //affectation
  //  public void affecterartiste(artiste a, enchers t);
}
