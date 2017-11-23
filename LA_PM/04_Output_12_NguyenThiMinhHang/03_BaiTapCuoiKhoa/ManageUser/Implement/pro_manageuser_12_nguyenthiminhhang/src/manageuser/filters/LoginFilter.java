package manageuser.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = { "*.do", "*.jsp" })
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Start fix bug ID 49 – NguyenThiMinhHang 2017/11/1
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		String path = req.getServletPath();
		if (path.contains("login.do") || path.contains("logout.do")) {
			if (path.contains("login.do") && Common.checkLogin(session)) {
				res.sendRedirect(req.getContextPath() + Constant.LISTUSER_SERVLET);
			} else {
				chain.doFilter(req, res);
			}
		} else if (path.contains("ADM001.jsp") && Common.checkLogin(session)) {
			res.sendRedirect(req.getContextPath() + Constant.LISTUSER_SERVLET);
		} else {
			if (Common.checkLogin(session)) {
				chain.doFilter(req, res);
			} else {
				res.sendRedirect(req.getContextPath());
			}
		}
		// End fix bug ID 49 – NguyenThiMinhHang 2017/11/1
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
