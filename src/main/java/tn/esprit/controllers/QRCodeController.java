package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeController {

    @FXML
    private void openButton() {
        // Logique pour ouvrir le code QR
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open QR Code Image");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                BufferedImage image = ImageIO.read(file);
                if (image != null) {
                    BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
                    MultiFormatReader reader = new MultiFormatReader();
                    Result result = reader.decode(binaryBitmap);
                    String qrCodeData = result.getText();
                    // Traiter les données du code QR, comme afficher dans l'interface utilisateur
                    showAlert("QR Code Data", qrCodeData);
                } else {
                    showAlert("Error", "Failed to read image file.");
                }
            } catch (IOException | NotFoundException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to read QR Code.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
