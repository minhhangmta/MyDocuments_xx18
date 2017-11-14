package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.properties.MessageErrorProperties;
import manageuser.properties.MessageProperties;
import manageuser.utils.Constant;

/**
 * Controller xử lý các logic của màn hình ADM006
 */
@WebServlet("/success.do")
public class SuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuccessController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String type = request.getParameter("type");
			HttpSession session = request.getSession();
			if (Constant.INSERT_FAIL.equals(type) || Constant.UPDATE_FAIL.equals(type)
					|| Constant.UPDATE_PASS_FAIL.equals(type)) {
				request.setAttribute("content", MessageErrorProperties.getData("ER015"));
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM006);
				requestDispatcher.forward(request, response);
			} else {
				if (Constant.INSERT_SUCCESS.equals(type) || Constant.UPDATE_SUCCESS.equals(type)) {
					String keySession = request.getParameter("keySession").toString();
					request.setAttribute("keySession", keySession);
					// xoa keySession
					session.removeAttribute(keySession);
					if (Constant.INSERT_SUCCESS.equals(type)) {
						request.setAttribute("content", MessageProperties.getData("MSG001"));
					} else if (Constant.UPDATE_SUCCESS.equals(type)) {
						request.setAttribute("content", MessageProperties.getData("MSG002"));
					}
				}
				if (Constant.UPDATE_PASS_SUCCESS.equals(type)) {
					request.setAttribute("content", MessageProperties.getData("MSG002"));
				}
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM006);
				requestDispatcher.forward(request, response);
			}

		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}
}
