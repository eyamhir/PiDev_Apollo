package Esprit.Service;

import Esprit.Interfaces.Crud;
import Esprit.Models.Portfolio;
import Esprit.utils.Database;

import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PortfolioService implements Crud<Portfolio> {
    private Connection connection ;
    public PortfolioService(){
        connection = Database.getInstance().getConnection();
    }
    @Override
    public void ajouter(Portfolio portfolio) throws SQLException {
        String req = "INSERT INTO portfolio(biographie, debut_carriere, reseau_sociaux) VALUES (?, ?, ?)";

        PreparedStatement st = connection.prepareStatement(req) ;
            st.setString(1, portfolio.getBiographie());
            st.setDate(2, Date.valueOf(portfolio.getDebut_carrier()));
            st.setString(3, portfolio.getReseau_sociaux());

            st.executeUpdate();}




    @Override
    public void modifier(Portfolio portfolio) throws SQLException {
        String req="UPDATE portfolio SET biographie = ?,debut_carriere = ?,reseau_sociaux = ? WHERE id_portfolio=?";
        PreparedStatement ps=connection.prepareStatement(req);
        ps.setString(1, portfolio.getBiographie());
        ps.setDate(2, Date.valueOf(portfolio.getDebut_carrier()));
        ps.setString(3, portfolio.getReseau_sociaux());
        ps.setInt(4,portfolio.getId_portfolio());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `portfolio` WHERE id_portfolio = ?";
        PreparedStatement ps = connection.prepareStatement(req) ;
            ps.setInt(1, id);
            ps.executeUpdate();
    }

    @Override
    public List<Portfolio> reupere_tout() throws SQLException {
        List<Portfolio> portfolios=new ArrayList<>();
        String req="SELECT * FROM portfolio " ;
        Statement st= connection.createStatement();
        ResultSet rs=st.executeQuery(req);

        while (rs.next()){
            Portfolio portfolio =new Portfolio();
            portfolio.setId_portfolio(rs.getInt("id_portfolio"));
            portfolio.setBiographie(rs.getString(1));
            portfolio.setDebut_carrier(rs.getObject("debut_carriere", LocalDate.class));
            portfolio.setReseau_sociaux(rs.getString("reseau_sociaux"));
            portfolios.add(portfolio);
        }
        return portfolios;

    }
}
