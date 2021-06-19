package by.epam.learn.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code PasswordEncryptor} utility is responsible for encrypting passwords
 * 
 * @author Ihar Klepcha
 */
public class PasswordEncryptor {
	public static Logger log = LogManager.getLogger();
	private static final String MESSAGE_DIGEST_ALGORITHM = "SHA-1";
	private static final String ENCODING = "utf8";

	private PasswordEncryptor() {
	}

	/**
	 * Encrypts password
	 * 
	 * @param password {@link String} password
	 * @return {@link String} encrypted password
	 */
	public static String encrypt(String password) {
		String encryptedPassword;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);
			messageDigest.update(password.getBytes(ENCODING));
			byte[] bytesEncoded = messageDigest.digest();
			BigInteger bigInt = new BigInteger(1, bytesEncoded);
			encryptedPassword = bigInt.toString(16);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			log.error("incorrect algorithm or encoding impossible", e);
			encryptedPassword = String.valueOf(password.hashCode());
		}
		return encryptedPassword;
	}
	
	/**
	 * Checks password
	 * 
	 * @param password {@link String} password
	 * @param hashedPassword {@link String} encrypted password
	 * @return boolean true if the password is correct, else false
	 */
	public static boolean checkPassword(String password, String hashedPassword) {
		String checkedPassword = encrypt(password);
		return checkedPassword.equals(hashedPassword);
	}
}
