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
import javax.servlet.http.HttpSession;

import model.command.Navigation;
import model.entity.User;

/**Filter for checking if user logged in*/
@WebFilter("/Airline")
public class UserSessionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) session.getAttribute("user");
		String page = request.getParameter("page");
		if (user == null && (page == null || page != null && 
				!(page.equals("login") || page.equals("registration")) || page.equals("logout"))) {
			request.getRequestDispatcher(Navigation.loginUri).forward(request, response);
		//	return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
