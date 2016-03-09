package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  request1 = (HttpServletRequest )request;
		HttpServletResponse response2 =(HttpServletResponse)response;
		String name=(String)request1.getSession().getAttribute("name");
		if(name!=null){
			chain.doFilter(request, response);
		}else{
			response2.sendRedirect("logout.jsp");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
