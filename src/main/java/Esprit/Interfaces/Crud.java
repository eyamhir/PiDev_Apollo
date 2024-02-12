package Esprit.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Crud<T> {
    void ajouter(T t ) throws SQLException;
    void  modifier(T t) throws SQLException;
    void supprimer (int id) throws SQLException;
    List<T> reupere_tout() throws SQLException;
}
