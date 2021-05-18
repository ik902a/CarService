package by.epam.learn.util;

import by.epam.learn.entity.EmailMessage;
import by.epam.learn.entity.User;

public class EmailMessageFactory {
	private static final String SUBJECT = "Activate your account!";
	private static final String H1_STARTING_TAG = "<h1>";
	private static final String H1_CLOSING_TAG = "</h1>";
	private static final String A_STARTING_TAG = "<a ";
	private static final String A_CLOSING_TAG = "</a>";
	//TODO localchost исправить
	private static final String HREF_ATTRIBUTE = "href=\"http://localhost:4040/DemoCarService/controller?command=activation&login=";
	private static final String PLACEHOLDER = "\">click here to activate your account";
	
	public static EmailMessage createSignUpMessage(User user) {

		String recipient = user.getEmail();
		StringBuilder builder = new StringBuilder();

		builder.append(H1_STARTING_TAG);
		builder.append(A_STARTING_TAG);
		builder.append(HREF_ATTRIBUTE);
		builder.append(user.getLogin());
		builder.append(PLACEHOLDER);
		builder.append(A_CLOSING_TAG);
		builder.append(H1_CLOSING_TAG);
		String message = builder.toString();
		EmailMessage emailMessage = new EmailMessage(recipient, SUBJECT, message);
		return emailMessage;
	}

}
