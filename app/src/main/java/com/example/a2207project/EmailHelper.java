package com.example.a2207project;

//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class EmailHelper {
    public static void sendEmail(String recipient, String subject, String message)
    {
        new SendEmailTask().execute(recipient,subject,message);
    }
    private static class SendEmailTask extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... params)
        {
            Log.d("Message","String message task");
            String recipient = params[0];
            String subject = params[1];
            String message = params[2];
            final String username = "ict1004p2grp4@gmail.com";
            final String password = "wokreajkgpizsoeo";

            Properties props = new Properties();
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","587");
            Session session = Session.getInstance(props, new Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(username,password);
                }
            });

            try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            emailMessage.setSubject(subject);
            emailMessage.setText(message);

            Transport.send(emailMessage);
            Log.d("Message","I think has beensent");
        } catch (AddressException e)
        {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
            return null;
        }
    }
}
