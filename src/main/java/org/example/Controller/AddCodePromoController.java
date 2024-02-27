package org.example.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.example.Models.CodePromo;
import org.example.Models.Utilisateur;
import org.example.Services.Service_CodePromo;
import org.example.Services.Service_Utilisateur;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AddCodePromoController {
    @FXML
    private TextField codeTF;

    @FXML
    private DatePicker dateExpirationPicker;

    @FXML
    private Button backBT;

    @FXML
    private ChoiceBox<Utilisateur> utilisateurChoiceBox; // Choice box for selecting the user


    private final Service_CodePromo serviceCodePromo = new Service_CodePromo();
    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();

    // Hashage MD5
    public static String doHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*@FXML
    void initialize() throws SQLException {
        List<Utilisateur> userList = serviceUtilisateur.obtenirTousLesUtilisateurs();
        ObservableList<Utilisateur> observableUsers = FXCollections.observableList(userList);

        utilisateurChoiceBox.setItems(observableUsers);

        // Définir un StringConverter pour afficher le nom et le prénom de chaque utilisateur dans le ChoiceBox
        utilisateurChoiceBox.setConverter(new StringConverter<Utilisateur>() {
            @Override
            public String toString(Utilisateur utilisateur) {
                // Retourne le nom et le prénom de l'utilisateur
                return utilisateur.getNom() + " " + utilisateur.getPrenom();
            }

            @Override
            public Utilisateur fromString(String string) {
                // Cette méthode peut être ignorée pour un ChoiceBox non-éditable
                return null;
            }
        });
    }*/


   @FXML
    void initialize() throws SQLException {

        List<Utilisateur> userListView = serviceUtilisateur.obtenirTousLesUtilisateurs();
        ObservableList<Utilisateur> observableList = FXCollections.observableList(userListView);
        utilisateurChoiceBox.setItems(observableList);
    }


        // Méthode de validation pour vérifier si tous les champs sont remplis
        private boolean allFieldsFilled () {
        if (codeTF.getText().isEmpty() || dateExpirationPicker.getValue() == null) {
            // Afficher une boîte de dialogue d'erreur indiquant les champs requis
            displayErrorDialog("Veuillez remplir tous les champs requis.");
            return false;
        }
        return true;
    }
     // Méthode pour afficher une boîte de dialogue d'erreur avec un message personnalisé
        private void displayErrorDialog (String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void createCodePromo(ActionEvent actionEvent) {
        try {
            if (allFieldsFilled()) {
                // Vérifier si la longueur du code est inférieure à 5 caractères
                if (codeTF.getText().length() < 5) {
                    displayErrorDialog("Le code promo doit contenir au moins 5 caractères.");
                    return; // Sortir de la méthode si la condition n'est pas remplie
                }

                // Vérifier si la date d'expiration est aujourd'hui ou ultérieure
                LocalDate expirationDate = dateExpirationPicker.getValue();
                if (expirationDate.isBefore(LocalDate.now())) {
                    displayErrorDialog("La date d'expiration doit être aujourd'hui ou ultérieure.");
                    return; // Sortir de la méthode si la condition n'est pas remplie
                }

                System.out.println("id "+utilisateurChoiceBox.getValue().getId_utilisateur());

                // Créer un nouvel objet CodePromo
                CodePromo nouveauCodePromo = new CodePromo(codeTF.getText(), expirationDate, utilisateurChoiceBox.getValue().getId_utilisateur());
                System.out.println(nouveauCodePromo);

                // Utiliser le service pour ajouter le code promo
                serviceCodePromo.creerCodePromo(nouveauCodePromo);

                // Afficher une alerte de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Le code promo a été créé avec succès.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Gérer les exceptions SQL
            displayErrorDialog("Une erreur SQL s'est produite lors de la création du code promo: " + e.getMessage());
            e.printStackTrace(); // Afficher la trace de la pile pour un débogage ultérieur
        } catch (IllegalArgumentException e) {
            // Gérer les exceptions d'argument illégal
            displayErrorDialog("Une erreur s'est produite lors de la création du code promo.");
        }
    }



    @FXML
    void back(ActionEvent event) throws IOException{
            try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML_files/AFFinterfaceCodePromo.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            }catch(IOException e){
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
            }
    }

}


