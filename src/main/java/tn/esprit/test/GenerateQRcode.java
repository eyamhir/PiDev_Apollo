package tn.esprit.test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Paths;

public class GenerateQRcode {
    public static void main(String[] args) throws WriterException, IOException {
        String data = "C:/Users/LENOVO/IdeaProjects/ApplicationArt/src/main/java/tn/esprit/controllers/AjouterCommande";
        String path = "C:/Users/LENOVO/OneDrive/Bureau/Esprit S2/QR.jpg";

        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        System.out.println("QR code successfully generated at: " + path);
    }
}
