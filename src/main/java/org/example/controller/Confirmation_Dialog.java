package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Confirmation_Dialog {
    @FXML
    private Button ConfirmationTF;
    @FXML
    private void ConfirmationDialog(ActionEvent event) {
        Stage stage = (Stage) ConfirmationTF.getScene().getWindow();
        stage.close();
    }
}
