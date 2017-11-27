/**
 * Copyright(C) 2017 Luvina
 * SuccessController.java Oct 23, 2017 minhhang
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.properties.MessageErrorProperties;
import manageuser.properties.MessageProperties;
import manageuser.utils.Constant;

/**
 * Controller xử lý các logic của màn hình ADM006
 */
@WebServlet(urlPatterns = "/success.do")
public class SuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuccessController() {
		super();
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
			RequestDispatcher requestDispatcher;
			String type = request.getParameter("type");
			// Các trường hợp fail
			if (Constant.INSERT_FAIL.equals(type) || Constant.UPDATE_FAIL.equals(type)
					|| Constant.UPDATE_PASS_FAIL.equals(type) || Constant.DELETE_FAIL.equals(type)) {
				request.setAttribute("content", MessageErrorProperties.getData("ER015"));
				requestDispatcher = request.getRequestDispatcher(Constant.ADM006);
			} else {// Các trường hợp success
				if (Constant.INSERT_SUCCESS.equals(type) || Constant.UPDATE_SUCCESS.equals(type)
						|| Constant.UPDATE_PASS_SUCCESS.equals(type)) {
					if (Constant.INSERT_SUCCESS.equals(type)) {
						request.setAttribute("content", MessageProperties.getData("MSG001"));
					} else {
						request.setAttribute("content", MessageProperties.getData("MSG002"));
					}
				}
				if (Constant.DELETE_SUCCESS.equals(type)) {
					request.setAttribute("content", MessageProperties.getData("MSG003"));
				}
				requestDispatcher = request.getRequestDispatcher(Constant.ADM006);
			}
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}
}
