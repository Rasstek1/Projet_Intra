package com.martin.projet_intra.models;


import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service  // Indique que la classe est un service Spring
public class EmailService {

    private GreenMail greenMail;  // Instance de GreenMail pour le service de messagerie

    public EmailService() {
        this.greenMail = new GreenMail(ServerSetupTest.SMTP);  // Initialise GreenMail avec le protocole SMTP
        greenMail.start();  // Démarre GreenMail
    }

    public void sendEmail(String to, String subject, String body) {
        Properties properties = new Properties();  // Propriétés pour la session de messagerie
        properties.put("mail.smtp.host", "localhost");  // Hôte SMTP
        properties.put("mail.smtp.port", "3025");  // Port SMTP de GreenMail

        Session session = Session.getInstance(properties);  // Crée une session de messagerie avec les propriétés

        try {
            MimeMessage message = new MimeMessage(session);  // Crée un nouveau message
            message.setFrom("noreply@example.com");  // Définit l'expéditeur
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  // Ajoute un destinataire
            message.setSubject(subject);  // Définit le sujet du message
            message.setText(body);  // Définit le corps du message

            Transport.send(message);  // Envoie le message via GreenMail
        } catch (MessagingException e) {
            e.printStackTrace();  // Imprime la trace de la pile si une exception se produit
        }
    }

    // Méthode pour arrêter GreenMail
    public void stopService() {
        greenMail.stop();  // Arrête GreenMail
    }
}

