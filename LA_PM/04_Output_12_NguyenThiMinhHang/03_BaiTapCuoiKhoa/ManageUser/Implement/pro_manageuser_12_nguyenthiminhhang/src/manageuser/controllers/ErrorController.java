/**
 * Copyright(C) 2017 Luvina
 * ErrorController.java Oct 23, 2017 minhhang
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
import manageuser.utils.Constant;

/**
 * Controller xử lý các logic của màn hình Error
 * 
 * @author minhhang
 */
@WebServlet("/error.do")
public class ErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ErrorController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String error = request.getParameter("error");
			String errorNotice;
			if (Constant.NOT_FOUND.equals(error)) {
				errorNotice = MessageErrorProperties.getData("ER013");
			} else {
				errorNotice = MessageErrorProperties.getData("ERROR_SYSTEM");
			}
			request.setAttribute("error", errorNotice);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
