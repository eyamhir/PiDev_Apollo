package tn.esprit.test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainQR extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("QR Code Reader");

        FileChooser fileChooser = new FileChooser();

        Button openButton = new Button("Open QR Code Image");
        openButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    BufferedImage image = ImageIO.read(new FileInputStream(file));
                    if (image != null) {
                        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
                        MultiFormatReader reader = new MultiFormatReader();
                        Result result = reader.decode(bitmap);
                        System.out.println("QR Code Data: " + result.getText());
                        // Faire quelque chose avec les données du code QR, comme afficher dans une interface utilisateur
                    }
                } catch (IOException | NotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        VBox vbox = new VBox(openButton);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
