/**
 * Copyright(C) 2017 Luvina
 * DetailUserController.java Oct 23, 2017 minhhang
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller xử lý các logic của màn hình ADM005
 * 
 * @author minhhang
 */
@WebServlet("/detailUser.do")
public class DetailUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// click link id từ adm002
		try {
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			int userId = Common.tryParseInt(request.getParameter("id"));
			// neu user ko ton tai
			if (!tblUserLogicImpl.existUserById(userId)) {
				response.sendRedirect(
						request.getContextPath() + Constant.ERROR_SERVLET + "?error=" + Constant.NOT_FOUND);
			} else {
				UserInfor userInfor = tblUserLogicImpl.getUserById(userId);
				request.setAttribute("userInfor", userInfor);
				request.setAttribute("userId", userId);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM005);
				requestDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}

}
