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
@WebFilter("*.do")
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
		 HttpServletResponse res = (HttpServletResponse) response;
		 HttpSession session = req.getSession();
		 if (req.getContextPath().contains("listUser.do")) {
		 if (Common.checkLogin(session)) {
		 // Cho phép request vượt qua Filter
		 chain.doFilter(request, response);
		 } else {
		 res.sendRedirect(req.getContextPath() + Constant.LOGIN_SERVLET);
		 }
		 } else {
		 chain.doFilter(request, response);
		 }
	/*	HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("loginName");
		String reqUrl = req.getRequestURL().toString();
		boolean bLogin = reqUrl.contains(Constant.LOGIN_SERVLET);
		boolean bADM = reqUrl.contains("/" + Constant.ADM001);
		if (username == null && ((!bADM && !bLogin) || (bLogin && req.getParameter("username") == null))) {
			// if not login yet
			// case 1: enter any url except ADM001 and LoginController
			// case 2: enter url LoginController
			res.sendRedirect(req.getContextPath() + "/" + Constant.ADM001);
		} else if (username != null && bLogin) {
			// if logged, enter url LoginController
			res.sendRedirect(req.getContextPath() + Constant.LISTUSER_SERVLET);
		} else {
			chain.doFilter(request, response);
		}*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
