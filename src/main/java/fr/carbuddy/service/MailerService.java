package fr.carbuddy.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailerService {

	public void sendAutomaticMail(
		String from,
		String to,
		String mailhost,
		String cc,
		String bcc,
		String subject,
		BufferedReader in,
		File attachment,
		String mailer
	) {

	    /*
	     * Initialize the JavaMail Session.
	     */
	    Properties props = System.getProperties();
	    // XXX - could use Session.getTransport() and Transport.connect()
	    // XXX - assume we're using SMTP
	    if (mailhost != null)
		props.put("mail.smtp.host", mailhost);

	    // Get a Session object
	    Session session = Session.getInstance(props, null);

	    /*
	     * Construct the message and send it.
	     */
	    Message msg = new MimeMessage(session);
	    try {
		    if (from != null) {
		    	msg.setFrom(new InternetAddress(from));
		    } else {
		    	msg.setFrom();
		    }
		    
		    msg.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to, false));
		    if (cc != null)
			msg.setRecipients(Message.RecipientType.CC,
						InternetAddress.parse(cc, false));
		    if (bcc != null)
			msg.setRecipients(Message.RecipientType.BCC,
						InternetAddress.parse(bcc, false));
	
		    msg.setSubject(subject);
	
		    String text = collect(in);
	
		    if (attachment != null) {
			// Attach the specified file.
			// We need a multipart message to hold the attachment.
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(text);
			MimeBodyPart mbp2 = new MimeBodyPart();
			mbp2.attachFile(attachment);
			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);
			msg.setContent(mp);
		    } else {
			// If the desired charset is known, you can use
			// setText(text, charset)
			msg.setText(text);
		    }
	
		    msg.setHeader("X-Mailer", mailer);
		    msg.setSentDate(new Date());
	
		    // send the thing off
		    Transport.send(msg);
	
		    System.out.println("\nMail was sent successfully.");
	    } catch(Exception e) {
	    	System.err.println("\nMail failed to be sent.");
	    }
	}
	
	 /**
     * Read the body of the message until EOF.
     */
    public String collect(BufferedReader in) throws IOException {
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = in.readLine()) != null) {
		    sb.append(line);
		    sb.append("\n");
		}
		return sb.toString();
    }
}
