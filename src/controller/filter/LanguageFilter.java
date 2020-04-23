package controller.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

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
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		if(language != null) {
			session.setAttribute("locale", new Locale(language));
		}
		else {
			if(session.getAttribute("locale") == null){
				session.setAttribute("locale", request.getLocale());
			}
		}
		Map<String, String[]> parameters = request.getParameterMap();
		for(Map.Entry<String, String[]> entry: parameters.entrySet()) {
			httpRequest.setAttribute(entry.getKey(), entry.getValue());
		}
		chain.doFilter(request, response);		
	}

	@Override
	public void destroy() {}

}
