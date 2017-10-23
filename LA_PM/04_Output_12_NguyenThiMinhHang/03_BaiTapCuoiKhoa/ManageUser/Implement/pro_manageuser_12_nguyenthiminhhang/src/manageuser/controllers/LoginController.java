package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.validates.ValidateUser;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("loginId");
		String password = request.getParameter("password");
		ValidateUser validateUser = new ValidateUser();
		if (validateUser.validateLogin(username, password) == null) {
			response.sendRedirect("view/jsp/ADM002.jsp");
		} else {
			ArrayList<String> errMassages = validateUser.validateLogin(username, password);
			request.setAttribute("errMassages", errMassages);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/jsp/ADM001.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
