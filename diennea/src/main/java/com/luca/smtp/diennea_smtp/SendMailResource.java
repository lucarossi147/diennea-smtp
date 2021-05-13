package com.luca.smtp.diennea_smtp;

import com.luca.smtp.diennea_smtp.util.EmailUtil;
import com.luca.smtp.diennea_smtp.util.Mail;
import com.luca.smtp.diennea_smtp.util.User;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Properties;

@Path("/mail")
public class SendMailResource {

    @POST
    @Path("/send-mail")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    //@Produces({MediaType.APPLICATION_JSON})
    public Response sendMail(@FormParam("token")String token, @FormParam("from")String from, @FormParam("to") String to, @FormParam("subject") String subject, @FormParam("body") String body){

        FakeDBSingleton fakeDBSingleton = FakeDBSingleton.getInstance();
        if (!fakeDBSingleton.isLoggedIn(from, token)){
            User user =fakeDBSingleton.fakeDB.get(from);
         return Response.status(401, user.getEmail() + " "+ user.getToken() ).build();
        }
        final String fromEmail = "diennea.smtp@gmail.com"; //requires valid gmail id
        final String password = "smtp.diennea"; // correct password for gmail id
        final String toEmail = "lucarossi147@gmail.com"; // can be any email id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, from, to,subject, body);
        return Response.ok(from+ " "+ to + " "+ subject + " "+ body).build();
    }

}
