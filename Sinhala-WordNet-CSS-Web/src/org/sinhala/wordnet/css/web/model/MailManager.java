package org.sinhala.wordnet.css.web.model;
import org.sinhala.wordnet.wordnetDB.model.User;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailManager {
	

	    private MailSender mailSender;
	    private SimpleMailMessage templateMessage;

	    public void setMailSender(MailSender mailSender) {
	        this.mailSender = mailSender;
	    }

	    public void setTemplateMessage(SimpleMailMessage templateMessage) {
	        this.templateMessage = templateMessage;
	    }

	    public void placeOrder(User user) {

	        // Do the business calculations...

	        // Call the collaborators to persist the order...

	        // Create a thread safe "copy" of the template message and customize it
	        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
	        msg.setTo(user.getEmail());
	        msg.setText(
	            "Dear " + user.getFirstName()
	                + user.getLastName()
	                + ", thank you for registering. Your user name is "
	                + user.getUsername());
	        try{
	        	System.out.println("sending mail");
	            this.mailSender.send(msg);
	        }
	        catch(MailException ex) {
	            // simply log it and go on...
	            System.err.println(ex.getMessage());            
	        }
	    }
	}