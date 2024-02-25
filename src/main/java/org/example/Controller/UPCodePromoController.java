package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.Models.CodePromo;
import org.example.Services.Service_CodePromo;

import java.sql.SQLException;
import java.time.LocalDate;

public class UPCodePromoController {
    private final Service_CodePromo serviceCodePromo = new Service_CodePromo();

    private CodePromo codePromo;

    @FXML
    private TextField codeField;

    @FXML
    private DatePicker expirationDateField;

    @FXML
    void initialize() {
        // Initialisez les données de l'interface si nécessaire
    }

    @FXML
    void modifyCodePromo(ActionEvent event) {
        try {
            if (allFieldsFilled()) {
                // Créer un objet CodePromo avec les données saisies
                CodePromo updatedCodePromo = new CodePromo(
                        codePromo.getId_CodePromo(), // Utilisez l'identifiant existant
                        codeField.getText(),
                        expirationDateField.getValue()
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
        if (codeField.getText().isEmpty() || expirationDateField.getValue() == null) {
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
        this.codepromo = codePromo;
        if (codePromo != null) {
            codeField.setText(codePromo.getCode());
            if (codePromo.getDateExpiration() != null) {
                LocalDate dateExpiration = codePromo.getDateExpiration();
                expirationDateField.setValue(dateExpiration);
            }
        }
    }
}
