package tn.esprit.apollogui.controllers;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Paths;

/*public class QRCodeGeneratorE {
    public static void generateQRCode(String data, String filePath) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
        MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(filePath));
    }*/
public class QRCodeGeneratorE {
    public static void generateQRCode(String data, String filePath) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 250, 250);
        MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(filePath));
    }


}