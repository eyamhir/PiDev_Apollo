package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Interface_signin {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void signInAction() {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Votre logique de connexion ici
    }

    // Ajoutez d'autres méthodes de gestion des événements ici
}
