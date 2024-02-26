package org.example.Services;
import org.example.Utils.MaConnexion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HashUtil {

    private Connection connection;

    public HashUtil() {
        connection = MaConnexion.getInstance().getCnx();
    }
    // Hashage MD5
    public static String doHashing(String mot_passe) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(mot_passe.getBytes());
            byte[] bytes = messageDigest.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
