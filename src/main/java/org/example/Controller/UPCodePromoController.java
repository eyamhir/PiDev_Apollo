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
import java.time.LocalDate;
import java.util.List;

public class UPCodePromoController {
    private final Service_CodePromo serviceCodePromo = new Service_CodePromo();
    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();


    private CodePromo codePromo;

    @FXML
    private TextField codeTF;

    @FXML
    private DatePicker dateExpirationPicker;
    @FXML
    private ChoiceBox<Utilisateur> utilisateurChoiceBox;

    @FXML
    void initialize() throws SQLException {
        // Initialisez les données de l'interface si nécessaire
        List<Utilisateur> userListView =serviceUtilisateur.obtenirTousLesUtilisateurs();
        ObservableList<Utilisateur> observableList = FXCollections.observableList(userListView);
        utilisateurChoiceBox.setItems(observableList);

    }

    @FXML
    void modifyCodePromo(ActionEvent event) {
        try {
            if (allFieldsFilled()) {
                // Vérifier la longueur du code
                if (codeTF.getText().length() < 5) {
                    displayErrorDialog("Le code promo doit contenir au moins 5 caractères.");
                    return;
                }

                // Vérifier la date d'expiration
                LocalDate expirationDate = dateExpirationPicker.getValue();
                if (expirationDate != null && expirationDate.isBefore(LocalDate.now())) {
                    displayErrorDialog("La date d'expiration doit être aujourd'hui ou ultérieure.");
                    return;
                }

                // Créer un objet CodePromo avec les données saisies
                CodePromo updatedCodePromo = new CodePromo(
                        codePromo.getId_CodePromo(), // Utilisez l'identifiant existant
                        codeTF.getText(),
                        dateExpirationPicker.getValue()
                );

                // Appeler la méthode du service pour modifier le code promo
                serviceCodePromo.mettreAJourCodePromo(updatedCodePromo);

                // Afficher une boîte de dialogue de confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Le code promo a été modifié avec succès.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            e.printStackTrace();
            displayErrorDialog("Une erreur s'est produite lors de la modification du code promo.");
        }
    }

    // Méthode de validation pour vérifier si tous les champs sont remplis
    private boolean allFieldsFilled() {
        if (codeTF.getText().isEmpty() || dateExpirationPicker.getValue() == null) {
            // Afficher une boîte de dialogue d'erreur indiquant les champs requis
            displayErrorDialog("Veuillez remplir tous les champs obligatoires.");
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

    // Méthode pour initialiser les données du code promo dans l'interface
    private CodePromo codepromo;

    public void initData(CodePromo codePromo) {
        this.codePromo = codePromo;
        if (codePromo != null) {
            // Afficher les détails du code promo dans les champs de texte et le choix de l'utilisateur
            codeTF.setText(codePromo.getCode());
            if (codePromo.getDateExpiration() != null) {
                dateExpirationPicker.setValue(codePromo.getDateExpiration());
            }

            // Charger la liste des utilisateurs et sélectionner l'utilisateur associé au code promo
            try {
                List<Utilisateur> utilisateurs = serviceUtilisateur.obtenirTousLesUtilisateurs();
                utilisateurChoiceBox.setItems(FXCollections.observableArrayList(utilisateurs));
                utilisateurChoiceBox.getSelectionModel().select(codePromo.getId_utilisteur());
            } catch (SQLException e) {
                e.printStackTrace(); // Gérer l'exception de manière appropriée (par exemple, la journalisation)
            }
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/AFFinterfaceCodePromo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }
}
