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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.EmailMessage;

/**
 * The {@code MailSender} utility is responsible for sending messages
 * 
 * @author Ihar Klepcha
 */
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

	/**
	 * Sends message
	 * 
	 * @param emailMessage {@link EmailMessage} email message entity
	 * @throws MessagingException
	 */
	public static void send(EmailMessage emailMessage) throws MessagingException {
		Session mailSession = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty(MAIL_USER_LOGIN),
						properties.getProperty(MAIL_USER_PASSWORD));
			}
		});
		String sendToEmail = emailMessage.getRecipient();
			Message message = new MimeMessage(mailSession);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendToEmail));
			message.setSubject(emailMessage.getSubject());
			message.setContent(emailMessage.getMessage(), TYPE);
			log.debug("Email {} try sent", emailMessage);
			Transport.send(message);
			log.info("Email {} sent successfully...", emailMessage);
	}
}
