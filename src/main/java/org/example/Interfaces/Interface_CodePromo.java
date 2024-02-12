package org.example.Interfaces;

import org.example.Models.CodePromo;

import java.sql.SQLException;
import java.util.List;

public interface Interface_CodePromo {
    void creerCodePromo(CodePromo codePromo) throws SQLException;
    CodePromo getCodePromoParId(int id) throws SQLException;
    List<CodePromo> obtenirTousLesCodesPromo() throws SQLException;
    void mettreAJourCodePromo(CodePromo codePromo) throws SQLException;
    void supprimerCodePromo(int id) throws SQLException;
}
