package com.martin.projet_intra.models;


import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    private GreenMail greenMail;

    public EmailService() {
        this.greenMail = new GreenMail(ServerSetupTest.SMTP);
        greenMail.start();
    }

    public void sendEmail(String to, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "3025");  // Port SMTP de GreenMail

        Session session = Session.getInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom("noreply@example.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);  // Cela enverra le mail via GreenMail
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour arrêter GreenMail
    public void stopService() {
        greenMail.stop();
    }
}

