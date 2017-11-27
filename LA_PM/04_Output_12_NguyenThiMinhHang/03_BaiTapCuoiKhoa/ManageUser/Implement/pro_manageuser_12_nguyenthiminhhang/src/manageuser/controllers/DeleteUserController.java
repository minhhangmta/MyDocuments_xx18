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
@WebServlet(urlPatterns = "/deleteUser.do")
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// click delete từ adm005
		try {
			String url = "";
			int userId = Common.tryParseInt(request.getParameter("id"));
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			// Neu user ton tai
			if (tblUserLogicImpl.existUserById(userId)) {
				// Delete User thanh cong
				if (tblUserLogicImpl.deleteUser(userId)) {
					url = Constant.SUCCESS_SERVLET + "?type=" + Constant.DELETE_SUCCESS;
				} else {// Delete khong thanh cong
					url = Constant.SUCCESS_SERVLET + "?type=" + Constant.DELETE_FAIL;
				}
			} else {// User khong ton tai
				url = Constant.ERROR_SERVLET + "?error=" + Constant.NOT_FOUND;
			}
			response.sendRedirect(request.getContextPath() + url);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}

}
