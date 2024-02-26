package org.example.Models;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String recipient, String subject, String body) {
        // Set properties for the SMTP server
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Replace these with your Gmail credentials
        String adresse_mail = "eyamhir329@gmail.com";
        String mot_passe = "bpew zjor bwxx esvw";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(adresse_mail, mot_passe);
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set content type to "text/html"
            message.setContent(body, "text/html; charset=utf-8");

            // Set recipient
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            // Set email subject
            message.setSubject(subject);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully to " + recipient);
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

}
