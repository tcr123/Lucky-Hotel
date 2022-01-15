
package com.example.fop_hotel_gui;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    public static com.example.fop_hotel_gui.SendEmail SendEmail;

    public static void sendEmail(String recepient) throws MessagingException {
        System.out.println("Preparing sending email");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myEmail = "worldpopi57@gmail.com";
        String password = "Watterhelp2222.";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });
        
        Message message = prepareMessage(session, myEmail, recepient);
        Transport.send(message);
        System.out.println("message sent successfully");
    }
            
    private static Message prepareMessage(Session session, String myEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My First Email From Jave App");
            message.setText("Hey Thre, \n look my email");
            return message;
        }
        catch (Exception ex) {
//            Logger.getLogger(SendEmail.class.getName()).log(Level.DEBUG, null, ex);
            System.out.println("Nothing");
        }
        return null;
    }  
}
