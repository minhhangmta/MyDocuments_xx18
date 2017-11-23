/**
 * Copyright(C) 2017 Luvina
 * DeleteUserController.java Oct 23, 2017 minhhang
 */

package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller xử lý logic xóa user của màn hình ADM005
 *
 * @author minhhang
 */
@WebServlet("/deleteUser.do")
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserController() {
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
			int userId = Common.tryParseInt(request.getParameter("id"));
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			// Neu user ton tai
			if (tblUserLogicImpl.existUserById(userId)) {
				// Delete User thanh cong
				if (tblUserLogicImpl.deleteUser(userId)) {
					response.sendRedirect(
							request.getContextPath() + Constant.SUCCESS_SERVLET + "?type=" + Constant.DELETE_SUCCESS);
				} else {// Delete khong thanh cong
					response.sendRedirect(
							request.getContextPath() + Constant.SUCCESS_SERVLET + "?type=" + Constant.DELETE_FAIL);
				}
			} else {// User khong ton tai
				response.sendRedirect(
						request.getContextPath() + Constant.ERROR_SERVLET + "?error=" + Constant.NOT_FOUND);
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}