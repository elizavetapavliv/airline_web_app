package controller.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**Filter for choosing language*/
@WebFilter("/*")
public class LanguageFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String language = request.getParameter("language");
		HttpSession session = ((HttpServletRequest) request).getSession();
		session.setAttribute("locale", language != null ? new Locale(language) : request.getLocale());
		request.getServletContext().log("Locale: " +  request.getLocale());
		chain.doFilter(request, response);		
	}

	@Override
	public void destroy() {}

}
