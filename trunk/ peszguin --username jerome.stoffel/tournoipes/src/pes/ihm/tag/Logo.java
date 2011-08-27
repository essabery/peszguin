package pes.ihm.tag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * Tag permettant d'afficher le logo en haut à gauche de la page, avec lien vers
 * la page d'accueil et affichage de la date au format jj/mm/aaaa
 * 
 * @author Aude Mulliez
 * @author applimodele
 * @author Insee 2009
 * @version 1.0
 * @since 1.0
 */
public class Logo extends AbstractTag {

	/**
	 * les attributs du tag
	 * 
	 */
	private String datePattern;

	/**
	 * La méthode appellée lors de l'ouverture de ce tag. <br>
	 */
	@Override
	public int doStartTag() throws JspTagException {
		HttpServletRequest requete = (HttpServletRequest) pc.getRequest();
		String urlAccueil = requete.getContextPath() + "/accueil.action";

		try {
			JspWriter out = pc.getOut();

			/* mise en forme de la date du jour */
			String dateFormatee;
			try {
				dateFormatee = DateFormatUtils.format(new Date(), datePattern);
			} catch (IllegalArgumentException iae) {
				dateFormatee = "";
			}
			out.println("<a id=logo href=\"" + urlAccueil + "\">");
			out.println(dateFormatee);
			out.println("</a>");
		} catch (IOException ex) {
			throw new JspTagException("An IOException occurred");
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
}
