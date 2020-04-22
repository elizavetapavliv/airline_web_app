package controller.tag;

import java.io.IOException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import model.entity.Brigade;
import model.entity.Stewardess;
import model.entity.User;

/**Tag for printing brigade*/
public class BrigadeTag extends TagSupport {

	/**Serial version UID*/
	private static final long serialVersionUID = 1L;
	
	/**Flag for administrator*/
	private boolean isAdmin;

	@Override
	public int doStartTag() throws JspTagException {
		try {
			User user = (User) pageContext.getAttribute("user", PageContext.SESSION_SCOPE);

			Brigade brigade = (Brigade) pageContext.getAttribute("brigade", PageContext.REQUEST_SCOPE);
			isAdmin = user.getType().equals("admin");

			pageContext.getOut().write("<table id=\"brigade_table\" class=\"vertical_table\">");
			pageContext.getOut().write("<tr><th>Id</th><td id=\"bId\">" + brigade.getId() + "</td>");
			if(isAdmin) {
				pageContext.getOut().write("<td></td>");
			}
			pageContext.getOut().write("</tr>");
			
			pageContext.getOut().write("<tr><th>Pilot 1</th><td id=\"pilot1\">" + brigade.getPilot1() + "</td>");
			writeAdminColumn("ipilot1");

			pageContext.getOut().write("<tr><th>Pilot 2</th><td id=\"pilot2\">" + brigade.getPilot2() + "</td>");
			writeAdminColumn("ipilot2");

			pageContext.getOut().write("<tr><th>Navigator</th><td id=\"navigator\">" + brigade.getNavigator() + "</td>");
			writeAdminColumn("inavigator");

			pageContext.getOut().write("<tr><th>Radio operator</th><td id=\"operator\">" + brigade.getRadioOperator() + "</td>");
			writeAdminColumn("ioperator");
 
			pageContext.getOut().write("<tr><th>Stewardesses</th><td>"
					+ "<table class=\"table\"><thead><tr><th>Id</th><th>Name</th></tr></thead><tbody>");

			for (Stewardess stewardess : brigade.getStewardesses()) {
				pageContext.getOut()
						.write("<tr><td>" + stewardess.getId() + "</td><td>" + stewardess.getName() + "</td></tr>");
			}
			pageContext.getOut().write("</tbody></table></td>");
			if(isAdmin) {
				pageContext.getOut().write("<td></td>");
			}
			pageContext.getOut().write("</tr></table>");
		} 
		catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

	/**
	 * Write column for editing data
	 * @param id - input id
	 * @throws IOException - for printing to pageContext
	 */
	private void writeAdminColumn(String id) throws IOException {
		if (isAdmin) {
			pageContext.getOut()
					.write("<td><form action=\"Airline\" method=\"POST\">"
							+ "<input name=\"page\" type=\"hidden\" value=\"brigade\">"
							+ "<input id=" + id + " name=\"update\" type=\"submit\" value=\"Update\" style=\"margin-left:0px\"></form></td>");
		}
		pageContext.getOut().write("</tr>");
	}
}
