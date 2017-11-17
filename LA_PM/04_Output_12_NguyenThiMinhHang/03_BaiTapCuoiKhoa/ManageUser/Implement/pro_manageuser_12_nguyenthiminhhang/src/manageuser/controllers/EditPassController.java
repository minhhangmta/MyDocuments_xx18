/**
 * Copyright(C) 2017 Luvina
 * EditPassController.java Oct 23, 2017 minhhang
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller xử lý các logic của màn hình ADM007
 * 
 * @author minhhang
 */
@WebServlet("/editPass.do")
public class EditPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditPassController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			int userId = Common.tryParseInt(request.getParameter("id"));
			// Neu user ton tai
			if (tblUserLogicImpl.existUserById(userId)) {
				request.setAttribute("userId", userId);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM007);
				requestDispatcher.forward(request, response);
			} else {// neu khong ton tai
				response.sendRedirect(
						request.getContextPath() + Constant.ERROR_SERVLET + "?error=" + Constant.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			int userId = Common.tryParseInt(request.getParameter("id"));
			request.setAttribute("userId", userId);
			// Neu user ton tai
			if (tblUserLogicImpl.existUserById(userId)) {
				String password = request.getParameter("password");
				String confirmPassword = request.getParameter("confirmPassword");
				ValidateUser validateUser = new ValidateUser();
				List<String> lstError = validateUser.validatePasswords(password, confirmPassword);
				// Neu khong co loi
				if (lstError.isEmpty()) {
					String salt = Common.createSaltString();
					if (tblUserLogicImpl.updatePass(password, salt, userId)) {
						response.sendRedirect(request.getContextPath() + Constant.SUCCESS_SERVLET + "?type="
								+ Constant.UPDATE_PASS_SUCCESS);
					} else {
						response.sendRedirect(request.getContextPath() + Constant.SUCCESS_SERVLET + "?type="
								+ Constant.UPDATE_PASS_FAIL);
					}
				} else {// Co loi
					request.setAttribute("lstError", lstError);
					// Forward đến ADM007
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM007);
					requestDispatcher.forward(request, response);
				}
			} else {// Neu user khong ton tai
				response.sendRedirect(
						request.getContextPath() + Constant.ERROR_SERVLET + "?error=" + Constant.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}
}
