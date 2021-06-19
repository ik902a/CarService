package by.epam.learn.util;

import java.util.List;

import by.epam.learn.entity.EmailMessage;
import by.epam.learn.entity.Price;
import by.epam.learn.entity.User;

/**
 * The {@code EmailMessageFactory} class creates sending messages
 * 
 * @author Ihar Klepcha
 */
public class EmailMessageFactory {
	private static final String ACTIVATE_SUBJECT = "Activate your account!";
	private static final String INVOICE_SUBJECT = "M-service, invoice";
	private static final String HREF_ATTRIBUTE = "href=\"http://localhost:4040/DemoCarService/controller?command=activation&login=";
	private static final String PLACEHOLDER = "\">click here to activate your account in M-service";
	private static final String INVOICE = "Your invoice";
	private static final String N = "N";
	private static final String OPERATION = "Operation";
	private static final String PRICE = "Price";
	private static final String TOTAL = "Total: ";
	private static final String H1_STARTING_TAG = "<h1>";
	private static final String H1_CLOSING_TAG = "</h1>";
	private static final String A_STARTING_TAG = "<a ";
	private static final String A_CLOSING_TAG = "</a>";
	private static final String P_STARTING_TAG = "<p>";
	private static final String P_CLOSING_TAG = "</p>";
	private static final String TABLE_STARTING_TAG = "<table border=\"1\">";
	private static final String TABLE_CLOSING_TAG = "</table>";
	private static final String TR_STARTING_TAG = "<tr>";
	private static final String TR_CLOSING_TAG = "</tr>";
	private static final String TH_STARTING_TAG = "<th>";
	private static final String TH_CLOSING_TAG = "</tn>";
	private static final String TD_STARTING_TAG = "<td>";
	private static final String TD_CLOSING_TAG = "</td>";

	
	/**
	 * Creates an email message when user signs up
	 * 
	 * @param user {@link User} user
	 * @return {@link EmailMessage} email message entity
	 */
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
		EmailMessage emailMessage = new EmailMessage(recipient, ACTIVATE_SUBJECT, message);
		return emailMessage;
	}
	
	/**
	 * Creates an email message when user work finished
	 * 
	 * @param prices {@link List} of {@link Price} price list
	 * @param recipient {@link String} recipient email address
	 * @return {@link EmailMessage} email message entity
	 */
	public static EmailMessage createInvoiceMessage(List<Price> prices, String recipient) {
		double sum = 0;
		int number = 1;
		StringBuilder builder = new StringBuilder();
		builder.append(H1_STARTING_TAG);
		builder.append(INVOICE);
		builder.append(H1_CLOSING_TAG);
		builder.append(TABLE_STARTING_TAG);
		builder.append(TR_STARTING_TAG);
		builder.append(TH_STARTING_TAG);
		builder.append(N);
		builder.append(TH_CLOSING_TAG);
		builder.append(TH_STARTING_TAG);
		builder.append(OPERATION);
		builder.append(TH_CLOSING_TAG);
		builder.append(TH_STARTING_TAG);
		builder.append(PRICE);
		builder.append(TH_CLOSING_TAG);
		builder.append(TR_CLOSING_TAG);
		for (Price price : prices) {
			builder.append(TR_STARTING_TAG);
			builder.append(TD_STARTING_TAG);
			builder.append(number++);
			builder.append(TD_CLOSING_TAG);
			builder.append(TD_STARTING_TAG);
			builder.append(price.getOperation());
			builder.append(TD_CLOSING_TAG);
			builder.append(TD_STARTING_TAG);
			builder.append(price.getPrice());
			builder.append(TD_CLOSING_TAG);
			builder.append(TR_CLOSING_TAG);
			sum += price.getPrice();
		}
		builder.append(TABLE_CLOSING_TAG);
		builder.append(P_STARTING_TAG);
		builder.append(TOTAL);
		builder.append(sum);
		builder.append(P_CLOSING_TAG);
		String message = builder.toString();
		EmailMessage emailMessage = new EmailMessage(recipient, INVOICE_SUBJECT, message);
		return emailMessage;
	}
}


