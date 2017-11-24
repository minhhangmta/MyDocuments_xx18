package manageuser.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import manageuser.utils.Constant;

/**
 * Filter xử lý lọc các controller tính năng encode
 */
@WebFilter(urlPatterns = { "*.do" })
public class EncodeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public EncodeFilter() {
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
		// encode cho request
		request.setCharacterEncoding(Constant.CHARSET);
		// endcode cho response
		response.setCharacterEncoding(Constant.CHARSET);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
