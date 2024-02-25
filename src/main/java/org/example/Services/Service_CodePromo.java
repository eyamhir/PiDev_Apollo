package org.example.Services;

import org.example.Interfaces.Interface_CodePromo;
import org.example.Models.CodePromo;
import org.example.Utils.MaConnexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service_CodePromo implements Interface_CodePromo {
    private Connection connection;

    public Service_CodePromo(Connection connection) {
        this.connection = connection;
    }
    public Service_CodePromo()
    {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void creerCodePromo(CodePromo codePromo) throws SQLException {
        String query = "INSERT INTO CodePromo (code, dateExpiration , id_utilisateur) VALUES (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, codePromo.getCode());
        statement.setDate(2, Date.valueOf(codePromo.getDateExpiration()));
        statement.setInt(3, codePromo.getId_utilisteur());

        statement.executeUpdate();
    }

    @Override
    public CodePromo getCodePromoParId(int id) throws SQLException {
        String query = "SELECT * FROM CodePromo WHERE id_CodePromo = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        CodePromo codePromo = null;

        if (resultSet.next()) {
            codePromo = new CodePromo();
            codePromo.setId_CodePromo(resultSet.getInt("id_CodePromo"));
            codePromo.setCode(resultSet.getString("code"));
            codePromo.setDateExpiration(resultSet.getDate("dateExpiration").toLocalDate());
        }

        return codePromo;
    }

    @Override
    public List<CodePromo> obtenirTousLesCodesPromo() throws SQLException {
        String query = "SELECT * FROM CodePromo";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<CodePromo> codesPromo = new ArrayList<>();

        while (resultSet.next()) {
            CodePromo codePromo = new CodePromo();
            codePromo.setId_CodePromo(resultSet.getInt("id_CodePromo"));
            codePromo.setCode(resultSet.getString("code"));
            codePromo.setDateExpiration(resultSet.getObject("dateExpiration", LocalDate.class));
            codesPromo.add(codePromo);
        }

        return codesPromo;
    }

    @Override
    public void mettreAJourCodePromo(CodePromo codePromo) throws SQLException {
        String query = "UPDATE CodePromo SET code = ?, dateExpiration = ? WHERE id_CodePromo = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, codePromo.getCode());
        statement.setDate(2, Date.valueOf(codePromo.getDateExpiration()));
        statement.setInt(3, codePromo.getId_CodePromo());
        statement.executeUpdate();
    }

    @Override
    public void supprimerCodePromo(int id) throws SQLException {
        String query = "DELETE FROM CodePromo WHERE id_CodePromo = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public List<CodePromo> search(String code) {
        return null;
    }


    public List<CodePromo> filterByName(String code) {
        return null;
    }

    @Override
    public boolean connecter(String adresse_mail, String mot_passe) {
        return false;
    }

}
