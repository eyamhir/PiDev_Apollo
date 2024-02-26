package tn.esprit.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

    public class QRCodeReader {

        public String readQRCode(File qrCodeImage) throws IOException, NotFoundException, ChecksumException, FormatException {
            BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(bufferedImage)));
            Reader reader = new com.google.zxing.qrcode.QRCodeReader();
            Result result = reader.decode(binaryBitmap);
            return result.getText();
        }
    }


