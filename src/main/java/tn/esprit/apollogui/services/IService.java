package tn.esprit.apollogui.services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    void ajouter(T t) throws SQLException, SQLException;

    void modifier(T t) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<T> recuperer() throws SQLException;

}
