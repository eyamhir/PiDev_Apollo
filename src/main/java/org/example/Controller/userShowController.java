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
import org.example.Models.Utilisateur;
import org.example.Services.Service_Utilisateur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class userShowController {

    private final Service_Utilisateur serviceUtilisateur = new Service_Utilisateur();

    @FXML
    private ListView<Utilisateur> userListView;
    @FXML
    private Button modifierButton;

    @FXML
    private Button supprimerButton;
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;
    @FXML
    private Button back;


    @FXML
    void initialize() {
        try {
        List<Utilisateur> userList = serviceUtilisateur.obtenirTousLesUtilisateurs();

        userListView.getItems().addAll(userList);

        // Custom cell factory to display user details
        userListView.setCellFactory(new Callback<ListView<Utilisateur>, ListCell<Utilisateur>>() {
            @Override
            public ListCell<Utilisateur> call(ListView<Utilisateur> param) {
                return new ListCell<Utilisateur>() {
                    @Override
                    protected void updateItem(Utilisateur utilisateur, boolean empty) {
                        super.updateItem(utilisateur, empty);
                        if (empty || utilisateur == null) {
                            setText(null);
                        } else {
                            // Set the text of the cell to display only the desired fields
                            setText("Nom: " + utilisateur.getNom() + "\n" +
                                    "Prenom: " + utilisateur.getPrenom() + "\n" +
                                    "Role: " + utilisateur.getRole());
                        }
                    }
                };
            }
        });

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/BackSigninInterface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }

    /*@FXML
    void modifierUtilisateur(ActionEvent event) {
        Utilisateur selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userModifyInterface.fxml"));
                Parent root = loader.load();
                userModifController controller = loader.getController();
                controller.initData(selectedUser); // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification
                Stage window = (Stage) userListView.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            afficherMessageErreur("Veuillez sélectionner un utilisateur à modifier.");
        }
    }*/

    @FXML
    void supprimerUtilisateur(ActionEvent event) {
        Utilisateur selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Supprimer l'utilisateur ?");
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // Implémenter la logique pour supprimer l'utilisateur sélectionné
                    serviceUtilisateur.supprimerUtilisateur(selectedUser.getId_utilisateur());
                    // Rafraîchir les données après la suppression
                    initData();
                } catch (SQLException e) {
                    afficherMessageErreur("Erreur lors de la suppression de l'utilisateur.");
                }
            }
        } else {
            afficherMessageErreur("Veuillez sélectionner un utilisateur à supprimer.");
        }
    }

    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void initData() {
        try {
            List<Utilisateur> userList = serviceUtilisateur.obtenirTousLesUtilisateurs();
            ObservableList<Utilisateur> observableList = FXCollections.observableArrayList(userList);
            userListView.setItems(observableList);
        } catch (SQLException e) {
            afficherMessageErreur("Erreur lors de l'initialisation des données.");
        }
    }

    @FXML
    void search(ActionEvent event) {
        String code = searchTextField.getText();
        List<Utilisateur> searchResults = serviceUtilisateur.search(code);
        ObservableList<Utilisateur> observableSearchResults = FXCollections.observableArrayList(searchResults);
        userListView.setItems(observableSearchResults);
    }

    @FXML
    void afficherDetails(ActionEvent event) {
        Utilisateur selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/userDetailsInterface.fxml"));
                Parent root = loader.load();
                userDetailsController controller = loader.getController();
                controller.initData(selectedUser); // Passez l'utilisateur sélectionné au contrôleur des détails
                Stage window = new Stage(); // Créez une nouvelle fenêtre pour afficher les détails
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException e) {
                afficherMessageErreur("Erreur lors de l'ouverture de la fenêtre des détails.");
            }
        } else {
            afficherMessageErreur("Veuillez sélectionner un utilisateur pour afficher les détails.");
        }
    }

    @FXML
    void toggleBannissement(ActionEvent event) {
        Utilisateur selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                if (selectedUser.getIsBanned()) {
                    serviceUtilisateur.debanUtilisateur(selectedUser.getId_utilisateur());
                    selectedUser.setIsBanned(false);
                    afficherMessageErreur("L'utilisateur a été débanni avec succès.");
                } else {
                    serviceUtilisateur.banUtilisateur(selectedUser.getId_utilisateur());
                    selectedUser.setIsBanned(true);
                    afficherMessageErreur("L'utilisateur a été banni avec succès.");
                }
            } catch (SQLException e) {
                afficherMessageErreur("Erreur lors de la modification du statut de l'utilisateur.");
            }
        } else {
            afficherMessageErreur("Veuillez sélectionner un utilisateur.");
        }
    }




}
