package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import model.command.Navigation;
import model.entity.User;

/**Filter for user registration*/
@WebFilter("/Airline")
public class RegistrationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String page = request.getParameter("page");
		String adminCode = request.getParameter("admin_code");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		if (page != null && adminCode != null && 
				page.equals("registration") && !adminCode.isEmpty() && !adminCode.equals(User.ADMIN_CODE)) {
			httpRequest.setAttribute("adminCodeError",
					"Admin code is incorrect. Try again or leave field empty and login as user.");
			httpRequest.getRequestDispatcher(Navigation.registrationUri).forward(httpRequest, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
