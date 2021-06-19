package by.epam.learn.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.EmailMessage;
import by.epam.learn.entity.Order;
import by.epam.learn.entity.Price;
import by.epam.learn.exception.DaoException;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.dao.impl.InvoiceDaoImpl;
import by.epam.learn.model.dao.impl.PriceDaoImpl;
import by.epam.learn.model.service.InvoiceService;
import by.epam.learn.util.EmailMessageFactory;
import by.epam.learn.util.MailSender;

/**
 * The {@code InvoiceServiceImpl} class is responsible for operations with the invoice
 * 
 * @author Ihar Klepcha
 * @see InvoiceService
 */
public class InvoiceServiceImpl implements InvoiceService {
	public static Logger log = LogManager.getLogger();

	@Override
	public boolean addInvoice(String[] operations, Order order) throws ServiceException {
		boolean isAdded = false;
		List<Price> prices = new ArrayList<>();
		long clientId = order.getCar().getUser().getUserId();
		String recipient = order.getCar().getUser().getEmail();
		try {
			for (String priceIdValue : operations) {
				long priceId = Long.parseLong(priceIdValue);
				Price price = PriceDaoImpl.getInstance().findEntityById(priceId);
				if (price != null) {
					prices.add(price);
				}
				isAdded = InvoiceDaoImpl.getInstance().addInvoice(clientId, priceId);
			}
			if (isAdded) {
				EmailMessage emailMessage = EmailMessageFactory.createInvoiceMessage(prices, recipient);
				MailSender.send(emailMessage);
			}
		} catch (MessagingException | DaoException e) {
			log.error("invoice creation error", e);
			throw new ServiceException("invoice creation error", e);
		}
		return isAdded;
	}
}
