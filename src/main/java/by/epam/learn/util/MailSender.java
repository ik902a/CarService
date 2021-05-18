package by.epam.learn.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.EmailMessage;
import by.epam.learn.exception.MailException;

public class MailSender {
	public static Logger log = LogManager.getLogger();
	
	private static final String MAIL_PROPERTIES = "property/mail.properties";
	private static final String MAIL_USER_LOGIN = "mail.user.login";
	private static final String MAIL_USER_PASSWORD = "mail.user.password";
	private static final String TYPE = "text/html; charset=utf-8";
	private static final Properties properties = new Properties();

	static {
		ClassLoader classLoader = MailSender.class.getClassLoader();
		try (InputStream resourceAsStream = classLoader.getResourceAsStream(MAIL_PROPERTIES)) {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			log.error("Error uploading " + MAIL_PROPERTIES, e);
			throw new RuntimeException("Error uploading a file" + MAIL_PROPERTIES, e);
		}
	}

	private MailSender() {
	}

	public static void send(EmailMessage emailMessage) throws MessagingException {
		Session mailSession = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty(MAIL_USER_LOGIN),
						properties.getProperty(MAIL_USER_PASSWORD));
			}
		});
		
		String sendToEmail = emailMessage.getRecipient();
//		try {
			Message message = new MimeMessage(mailSession);
			//message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendToEmail));
			message.setSubject(emailMessage.getSubject());
			message.setContent(emailMessage.getMessage(), TYPE);
			log.debug("Email {} try sent", emailMessage);
			Transport.send(message);
			log.info("Email {} sent successfully...", emailMessage);
			//DEPLICATE
//			log.info("Email {} sent successfully...", emailMessage);
//		} catch (AddressException e) {
//			log.error("Invalid address: " + sendToEmail + " " + e);
//			throw new MailException("Invalid address: " + sendToEmail, e);
//		} catch (MessagingException e) {
//			log.error("Error generating or sending message: " + e);
//			throw new MailException("Error generating or sending message: ", e);
//		}
	}
}
