package org.example.Interfaces;

import org.example.Models.Artiste;

import java.sql.SQLException;
import java.util.List;

public interface Interface_Artiste {
    // Créer
    void creerArtiste(Artiste artiste) throws SQLException;

    // Lire
    Artiste getArtisteParId(int id) throws SQLException;
    List<Artiste> obtenirTousLesArtistes() throws SQLException;

    // Mettre à jour
    void mettreAJourArtiste(Artiste artiste) throws SQLException;

    // Supprimer
    void supprimerArtiste(int id) throws SQLException;
}
