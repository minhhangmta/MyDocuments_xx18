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
 * Filter xử lý lọc các controller thông qua login
 */
@WebFilter(urlPatterns = { "*.do" })
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
		// Nếu là trang login.do
		if (path.contains("login.do")) {
			if (Common.checkLogin(session)) {
				res.sendRedirect(req.getContextPath() + Constant.LISTUSER_SERVLET);
			} else {// Chưa đăng nhập
				chain.doFilter(req, res);
			}
		} else {// Các trang .do khác
			// đã đăng nhập
			if (Common.checkLogin(session)) {
				chain.doFilter(req, res);
			} else {// chưa đăng nhập
				res.sendRedirect(req.getContextPath() + Constant.LOGIN_SERVLET);
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
