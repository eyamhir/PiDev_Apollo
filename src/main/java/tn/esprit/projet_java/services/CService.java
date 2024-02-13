package tn.esprit.projet_java.services;

import java.sql.SQLException;
import java.util.List;
public interface CService<M> {
    void ajouter(M m) throws SQLException;
    void modifier(M m) throws SQLException;
    void supprimer(int id_client) throws SQLException;


    //list : select
    List<M> fetchclient() throws SQLException;
}
