package org.example.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
public class VerifCode {
    private int code;
    private String  adresse_mail;
    @FXML
    private TextField codeTF;
    private String verificationCode;
    @FXML
    private Button confirmBT; // Add this button to the FXML file as well

    private boolean validateCode(String inputCode) {
        // Check if the input code matches the verification code
        return inputCode.equals(verificationCode);
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/CheckMailInterface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void confirmCode(ActionEvent event) throws IOException {
        // Get the user's input code from the codeTF field
        String inputCode = codeTF.getText();

        // Validate the input code using the validateCode method
        if (validateCode(inputCode)) {
            // Show another scene where the user can enter their new password
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML_files/newPassword.fxml"));
            Parent root = loader.load();
            newPassword controller = loader.getController();
            controller.initData(adresse_mail);
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } else {
            // Show an alert that the code is incorrect and ask the user to try again
            showAlert(Alert.AlertType.ERROR, "Invalid Code", "The code you entered is incorrect. Please try again.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void initCode(int code, String adresse_mail) {
        this.verificationCode = String.valueOf(code);
        this.adresse_mail = adresse_mail;
    }
}