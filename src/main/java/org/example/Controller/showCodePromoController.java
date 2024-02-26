package org.example.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Models.CodePromo;
import org.example.Services.Service_CodePromo;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

public class showCodePromoController {
    private final Service_CodePromo serviceCodePromo = new Service_CodePromo();

    @FXML
    private ListView<CodePromo> codePromoListView;

    @FXML
    void initialize() {
        initData();
        codePromoListView.setCellFactory(new Callback<ListView<CodePromo>, ListCell<CodePromo>>() {
            @Override
            public ListCell<CodePromo> call(ListView<CodePromo> param) {
                return new ListCell<CodePromo>() {
                    @Override
                    protected void updateItem(CodePromo codePromo, boolean empty) {
                        super.updateItem(codePromo, empty);
                        if (empty || codePromo == null) {
                            setText(null);
                        } else {
                            setText("Code: " + codePromo.getCode() +
                                    ", Date d'expiration: " + codePromo.getDateExpiration());
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void ajouterCodePromo(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/ADDinterfaceCodePromo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }


    @FXML
    void supprimerCodePromo(ActionEvent event) {
        CodePromo selectedCodePromo = codePromoListView.getSelectionModel().getSelectedItem();
        if (selectedCodePromo != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Supprimer le code promo ?");
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce code promo ?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    serviceCodePromo.supprimerCodePromo(selectedCodePromo.getId_CodePromo());
                    initData();
                } catch (SQLException e) {
                    afficherMessageErreur("Erreur lors de la suppression du code promo : " + e.getMessage());
                }
            }
        } else {
            afficherMessageErreur("Veuillez sélectionner un code promo à supprimer.");
        }
    }

    private void initData() {
        try {
            List<CodePromo> codePromos = serviceCodePromo.obtenirTousLesCodesPromo();
            ObservableList<CodePromo> observableCodePromos = FXCollections.observableArrayList(codePromos);
            codePromoListView.setItems(observableCodePromos);
        } catch (SQLException e) {
            afficherMessageErreur("Erreur lors de l'initialisation des données des codes promo : " + e.getMessage());
        }
    }

    @FXML
    void modifierCodePromo(ActionEvent event) {
        // Code pour modifier le code promo sélectionné dans la liste
        CodePromo selectedCodePromo = codePromoListView.getSelectionModel().getSelectedItem();
        if (selectedCodePromo != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/UPinterfaceCodePromo.fxml"));
                Parent root = loader.load();
                UPCodePromoController controller = loader.getController();
                controller.initData(selectedCodePromo); // Passer le code promo sélectionné au contrôleur de modification
                Stage window = (Stage) codePromoListView.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Aucun code promo sélectionné, affichez un message d'erreur
            afficherMessageErreur("Veuillez sélectionner un code promo à modifier.");
        }
    }

}

