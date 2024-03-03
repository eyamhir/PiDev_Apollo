package tn.esprit.sevices;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{
    void ajouter(T t) throws SQLException;
    void modifier(T t) throws SQLException;

    void supprimer(int id) throws SQLException;
    List<T> recuperer() throws SQLException;

    T recupererParId(int id) throws SQLException; // Ajout de la méthode recupererParId
}