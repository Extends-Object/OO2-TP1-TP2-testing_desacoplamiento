package concurso.utilidades.notificadores;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class NotificadorEmail implements Notificador {

    final String username = "d84e36c103e3f5";
    final String password = "4cec2de3b26f70";
    String host = "sandbox.smtp.mailtrap.io";

    @Override
    public void notificar(Notificacion notificacion) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");            // Desactivando startTLS porque no reconoce el certificado del servidor de Mailtrap cuando intenta usar startTLS
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");                          //Cambiando 587 por 25 --> puerto sin TLS


        Session session = Session.getInstance(props,                //Hace una instancia de la sesion con las prop configuradas
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        try {

            Message message = new MimeMessage(session);  //Contenedor del mail que tiene todas las cosas del mail
            message.setFrom(new InternetAddress(notificacion.getEmisor()));  //Crea un objeto dir de internet que recibe el mail
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(notificacion.getDestinatario())); //Es quien soy
            message.setSubject(notificacion.getAsunto()); //Asunto
            message.setText(notificacion.getCuerpo());  //Cuerpo del mail

            Transport.send(message); //Envia el mail
            System.out.println("El mensaje se envió correctamente");

        } catch (MessagingException e) {  //Por si falla el envio
            throw new RuntimeException("No se pudo enviar el mensaje", e);
        }
    }
}

