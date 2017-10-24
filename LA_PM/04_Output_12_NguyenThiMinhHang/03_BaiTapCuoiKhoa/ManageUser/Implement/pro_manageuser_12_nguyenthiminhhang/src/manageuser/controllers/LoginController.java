package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		// Lấy username và password từ jsp
		String username = request.getParameter("loginId");
		String password = request.getParameter("password");
		// Khởi tạo lớp ValidateUser
		ValidateUser validateUser = new ValidateUser();
		// nếu validateLogin trả về null/không có lỗi
		if (validateUser.validateLogin(username, password).isEmpty()) {
			// Lưu session
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			// điều hướng đến ADM002
			response.sendRedirect("view/jsp/ADM002.jsp");
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/jsp/ADM002.jsp");
//			requestDispatcher.forward(request, response);
		} else {
			// Lấy list thông báo lỗi từ validateLogin
			ArrayList<String> errMassages = validateUser.validateLogin(username, password);
			// lưu list đó vào request
			request.setAttribute("errMassages", errMassages);
			// getRequestDispatcher tới view
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/jsp/ADM001.jsp");
			// foward tới requestDispatcher đó
			requestDispatcher.forward(request, response);
		}
	}
}
