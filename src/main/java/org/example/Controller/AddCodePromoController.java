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
import org.example.Models.CodePromo;
import org.example.Models.Utilisateur;
import org.example.Services.Service_CodePromo;
import org.example.Services.Service_Utilisateur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddCodePromoController {
    @FXML
    private TextField codeTF;

    @FXML
    private DatePicker dateExpirationPicker;

    @FXML
    private ChoiceBox<Utilisateur> boxFT;
    private Button backBT;

    private final Service_CodePromo serviceCodePromo = new Service_CodePromo();
    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();

    @FXML
    void initialize() throws SQLException {
        List<Utilisateur> userList = serviceUtilisateur.obtenirTousLesUtilisateurs();
        ObservableList<Utilisateur> observableList = FXCollections.observableList(userList);
        boxFT.setItems(observableList);
    }


        // Méthode de validation pour vérifier si tous les champs sont remplis
        private boolean allFieldsFilled() {
            if (codeTF.getText().isEmpty() || dateExpirationPicker.getValue() == null) {
                // Afficher une boîte de dialogue d'erreur indiquant les champs requis
                displayErrorDialog("Veuillez remplir tous les champs requis.");
                return false;
            }
            return true;
        }

        // Méthode pour afficher une boîte de dialogue d'erreur avec un message personnalisé
        private void displayErrorDialog(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    @FXML

    public void createCodePromo(javafx.event.ActionEvent actionEvent) {
        try {
            if (allFieldsFilled()) {

                // Créer un nouvel objet CodePromo;
                CodePromo nouveauCodePromo = new CodePromo(codeTF.getText(), dateExpirationPicker.getValue(),boxFT.getValue().getId_utilisateur());

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
            displayErrorDialog("Une erreur SQL s'est produite lors de la création du code promo.");
        } catch (IllegalArgumentException e) {
            // Gérer les exceptions d'argument illégal
            displayErrorDialog("Une erreur s'est produite lors de la création du code promo.");
        }
    }
   /* @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userSigninInterface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }*/
}
