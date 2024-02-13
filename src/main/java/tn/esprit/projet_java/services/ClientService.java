package tn.esprit.projet_java.services;
import tn.esprit.projet_java.models.Client;
import tn.esprit.projet_java.utils.MaConnecxion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ClientService implements CService<Client>{

    private Connection cnx;

    public ClientService(){
        cnx = MaConnecxion.getInstance().getCnx();
    }
    @Override
    public void ajouter(Client client) throws SQLException {
        String req = "INSERT INTO client (montant_proposee_parclient,statut_enchers_client) VALUES ('"+ client.getMontant_proposee_parclient() + "','" +client.getStatut_enchers_client()+  "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("client Added successfully!");
    }

    @Override
    public void modifier(Client client) throws SQLException {
        try {
            String req = "UPDATE enchers SET montant_proposee_parclient = ?, statut_enchers_client = ?, WHERE id_client = ?";
            PreparedStatement es = cnx.prepareStatement(req);
            es.setFloat(1, client.getMontant_proposee_parclient());
            es.setString(2, client.getStatut_enchers_client());

            es.setInt(3, client.getId_client());  // Ajout du paramètre id
            es.executeUpdate();
            System.out.println("client updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id_client) throws SQLException {
        String req = "DELETE from client WHERE id_client =?";
        PreparedStatement es = cnx.prepareStatement(req);
        es.setInt(1,id_client);
        es.executeUpdate();
        System.out.println("client deleted successfully!");
    }

    @Override
    public List<Client> fetchclient() throws SQLException {
        List<Client> clientList = new ArrayList<>();
        String req = "SELECT * FROM client";

        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Client client = new Client();
                client.setId_client(rs.getInt("id_client"));
                client.setMontant_proposee_parclient(rs.getFloat("montant_proposee_parclient"));
                client.setStatut_enchers_client(rs.getString("statut_enchers_client"));


                clientList.add(client);
            }
        }

        return clientList;
    }


}
