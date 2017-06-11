/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionTCP.EnvioMail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


/**
 *
 * @author rogas
 */
public  class EnvioMail {
    
 
    
    public static boolean sendMail(){
        String to ="rogasturias@gmail.com";
        String asunto="asunto del mensaje";
        String cuerpo="Cuerpo del mensaje";
        Authenticator auth;
        try
        {
            Properties props = System.getProperties();
              // -- configuracion de la cuenta y envio --
              props.put("mail.transport.protocol", "smtp" );
              props.put("mail.smtp.starttls.enable","true" );
              props.put("mail.smtp.host","smtp.gmail.com");
              props.put("mail.smtp.auth", "true" );
              auth = new SMTPAuthenticator();
              Session session = Session.getInstance(props, auth);
              
              // -- Creando un mensaje --
              // ojo la cuenta tiene que permitir accesos no seguroS
              Message msg = new MimeMessage(session);
              // -- poniendo el desde y para --
              msg.setFrom(new InternetAddress("proyectoDam2017@gmail.com"));
              msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
              msg.setSubject(asunto);
              msg.setText(cuerpo);
              
              // -- Send the message --
              Transport.send(msg);
              return true;
        }
        catch (Exception ex)
        {         
          return false;
        }
  }
    
     public static boolean sendMail(String to, String asunto, String cuerpo){        
        Authenticator auth;
        try
        {
            Properties props = System.getProperties();
              // -- configuracion de la cuenta y envio --
              props.put("mail.transport.protocol", "smtp" );
              props.put("mail.smtp.starttls.enable","true" );
              props.put("mail.smtp.host","smtp.gmail.com");
              props.put("mail.smtp.auth", "true" );
              auth = new SMTPAuthenticator();
              Session session = Session.getInstance(props, auth);
              
              // -- Creando un mensaje --
              // ojo la cuenta tiene que permitir accesos no seguros
              Message msg = new MimeMessage(session);
              // -- poniendo el desde y para --
              msg.setFrom(new InternetAddress("proyectoDam2017@gmail.com"));
              msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
              msg.setSubject(asunto);
              msg.setText(cuerpo);
              
              // -- Send the message --
              Transport.send(msg);
              return true;
        }
        catch (Exception ex)
        {         
          return false;
        }
  }

// clase interna para autenticacion

 private static class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username =  "proyectoDam2017@gmail.com";           
            String password = "@sturiaS2017";  
            return new PasswordAuthentication(username, password);
        }
  }

    
    
    
    
 
}
