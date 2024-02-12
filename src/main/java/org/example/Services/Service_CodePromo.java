package org.example.Services;

import org.example.Interfaces.Interface_CodePromo;
import org.example.Models.CodePromo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service_CodePromo implements Interface_CodePromo {
    private Connection connection;

    public Service_CodePromo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void creerCodePromo(CodePromo codePromo) throws SQLException {
        String query = "INSERT INTO code_promo (code, date_expiration) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, codePromo.getCode());
        statement.setDate(2, new java.sql.Date(codePromo.getDateExpiration().getTime()));
        statement.executeUpdate();
    }

    @Override
    public CodePromo getCodePromoParId(int id) throws SQLException {
        String query = "SELECT * FROM code_promo WHERE id_CodePromo = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        CodePromo codePromo = null;

        if (resultSet.next()) {
            codePromo = new CodePromo();
            codePromo.setId_CodePromo(resultSet.getInt("id_CodePromo"));
            codePromo.setCode(resultSet.getString("code"));
            codePromo.setDateExpiration(resultSet.getDate("date_expiration"));
        }

        return codePromo;
    }

    @Override
    public List<CodePromo> obtenirTousLesCodesPromo() throws SQLException {
        String query = "SELECT * FROM code_promo";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<CodePromo> codesPromo = new ArrayList<>();

        while (resultSet.next()) {
            CodePromo codePromo = new CodePromo();
            codePromo.setId_CodePromo(resultSet.getInt("id_CodePromo"));
            codePromo.setCode(resultSet.getString("code"));
            codePromo.setDateExpiration(resultSet.getDate("date_expiration"));
            codesPromo.add(codePromo);
        }

        return codesPromo;
    }

    @Override
    public void mettreAJourCodePromo(CodePromo codePromo) throws SQLException {
        String query = "UPDATE code_promo SET code = ?, date_expiration = ? WHERE id_CodePromo = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, codePromo.getCode());
        statement.setDate(2, new java.sql.Date(codePromo.getDateExpiration().getTime()));
        statement.setInt(3, codePromo.getId_CodePromo());
        statement.executeUpdate();
    }

    @Override
    public void supprimerCodePromo(int id) throws SQLException {
        String query = "DELETE FROM code_promo WHERE id_CodePromo = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
