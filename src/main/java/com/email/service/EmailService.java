package com.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public boolean sendEmail(String subject , String message, String to){
        String from="putridjackal@gmail.com";
        boolean f = false;
        //variable for gmail
    	String host = "smtp.gmail.com";
    	
    	//get the system properties
    	Properties properties =  System.getProperties(); 
    	System.out.println("PROPERTIES "+properties);
    	
    	//setting important info to properties object
    	properties.put("mail.smtp.host", host);
    	properties.put("mail.smtp.port", "465");
    	properties.put("mail.smtp.ssl.enable", "true");
    	properties.put("mail.smtp.auth", "true");
    	
    	//get the session object
    	Session session =  Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("putridjackal@gmail.com", "rvzu jmtb pelt kffr");
				
			}
    		
    		
    		
		});
    	
    	//compose the message
    	
    	session.setDebug(true);
    	
    	MimeMessage m =  new MimeMessage(session);
    	//from email
    	try {
    		
    		//from email
			m.setFrom(from);
			
			//adding recipient
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);
			
			//Send message using Tranpost class
			Transport.send(m);
			
			System.out.println("Send success");
			f = true;
			
			
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return f;
    }
}
