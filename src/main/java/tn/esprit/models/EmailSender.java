package tn.esprit.models;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    private final String username;
    private final String password;
    private final String SMTP_HOST = "smtp.gmail.com";
    private final String SMTP_PORT = "587";

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String recipient, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        try {
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("E-mail envoyé avec succès !");
        } catch (AddressException e) {
            System.err.println("Adresse e-mail invalide : " + e.getMessage());
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }

    public void sendInvoice(String recipient, String subject, String body) {
        sendEmail(recipient, subject, body);
    }

    public static void main(String[] args) {
        String username = "radhouanegrami329@gmail.com";
        String password = "cjsp gjjs czcc ktvl";

        EmailSender emailSender = new EmailSender(username, password);

        emailSender.sendEmail("Exempleemail@gmail.com", "Test Email", "Ceci est un e-mail de test.");
    }
}
