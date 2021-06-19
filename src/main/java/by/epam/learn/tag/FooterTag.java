package by.epam.learn.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code FooterTag} class is the custom tag for footer
 * 
 * @author Ihar Klepcha
 * @see TagSupport
 */
public class FooterTag extends TagSupport {
	public static Logger log = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	private static final String FOOTER = "Do not develop my webapp";
	private static final String FOOTER_TAG_START = "<footer>";
	private static final String P_TAG_START = "<p>";
	private static final String FOOTER_TAG_END = "</footer>";
	private static final String P_TAG_END = "</p>";

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.write(FOOTER_TAG_START);
			out.write(P_TAG_START);
			out.write(FOOTER);
			out.write(P_TAG_END);
			out.write(FOOTER_TAG_END);
		} catch (IOException e) {
			log.error("I/O error occurs", e);
			throw new JspTagException("I/O error occurs", e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
