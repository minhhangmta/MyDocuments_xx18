/**
 * Copyright(C) 2017 Luvina
 * LoginController.java Oct 23, 2017 minhhang
 */
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

import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller xử lý cho màn hình login
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.ADM001);
			requestDispatcher.forward(req, resp);
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_SERVLET);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Lấy username và password từ jsp
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// Khởi tạo lớp ValidateUser
			ValidateUser validateUser = new ValidateUser();
			ArrayList<String> errMassages = validateUser.validateLogin(username, password);
			// nếu validateLogin trả về null/không có lỗi
			if (errMassages.isEmpty()) {
				// Lưu username vào session
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				// điều hướng đến ADM002
				response.sendRedirect(request.getContextPath() + Constant.LISTUSER_SERVLET);
			} else {
				// lưu list đó vào request
				request.setAttribute("errMassages", errMassages);
				// lưu username vừa nhập vào request
				request.setAttribute("username", username);
				// getRequestDispatcher tới view
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM001);
				// foward tới requestDispatcher đó
				requestDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}
}
