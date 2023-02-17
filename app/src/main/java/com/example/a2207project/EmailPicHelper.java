package com.example.a2207project;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailPicHelper {
    public static void sendEmail(String recipient, String subject, String message, List<File> images)
    {
        new EmailPicHelper.SendPicTask().execute(recipient,subject,message,images);
    }

    private static class SendPicTask extends AsyncTask<Object, Void, Void>
    {
        @Override
        protected Void doInBackground(Object... params)
        {
            Log.d("Message","String message task");
            String recipient = (String) params[0];
            String subject = (String) params[1];
            String message = (String) params[2];
            List<File> images = (List<File>) params[3];
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

                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(message);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                for (File image : images) {
                    if (image.exists() && image.canRead()) {
                        BodyPart messageBodyPartPic = new MimeBodyPart();
                        DataSource source = new FileDataSource(image);
                        messageBodyPartPic.setDataHandler(new DataHandler(source));
                        messageBodyPartPic.setFileName(image.getName());
                        messageBodyPartPic.setDisposition(MimeBodyPart.ATTACHMENT);
                        multipart.addBodyPart(messageBodyPartPic);
                    } else {
                        Log.d("Can't Read", image.toString());
                    }
                }

                emailMessage.setContent(multipart);

                Transport.send(emailMessage);
                Log.d("Message","I think has been sent");
            }
            catch (AddressException e) {
                Log.d("Exception", "Address");
                e.printStackTrace();
            }
            catch (MessagingException e) {
                Log.d("Exception", "Messaging");
                e.printStackTrace();
            }

            return null;
        }
    }
}
