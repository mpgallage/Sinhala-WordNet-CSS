package org.sinhala.wordnet.css.common.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHandler {
	
	public static void sendMail(String to, String from, String subject, String content) {

		// Assuming you are sending email from localhost
		String host = "wordnet.lk";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject(subject);
			
			Multipart mp = new MimeMultipart("alternative");
			
			MimeBodyPart html = new MimeBodyPart();
			html.setContent(content, "text/html");
			
			mp.addBodyPart(html);
			

			// Now set the actual message
			message.setContent(mp);

			// Send message
			Transport.send(message);

		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}
}