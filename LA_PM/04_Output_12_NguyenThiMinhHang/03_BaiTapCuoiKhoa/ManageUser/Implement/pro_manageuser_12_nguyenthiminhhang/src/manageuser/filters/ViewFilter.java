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
 * Filter xử lý lọc các jsp
 */
@WebFilter(urlPatterns = { "*.jsp" })
public class ViewFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ViewFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		// Đã login
		if (Common.checkLogin(session)) {
			res.sendRedirect(req.getContextPath() + Constant.LISTUSER_SERVLET);
		} else {// Chưa login
			res.sendRedirect(req.getContextPath() + Constant.LOGIN_SERVLET);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
