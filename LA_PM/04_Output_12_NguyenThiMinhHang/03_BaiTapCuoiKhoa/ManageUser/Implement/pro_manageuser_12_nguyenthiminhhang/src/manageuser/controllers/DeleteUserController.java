package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller xử lý logic xóa user của màn hình ADM005
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
			if (tblUserLogicImpl.existUserById(userId)) {
				if (tblUserLogicImpl.deleteUser(userId)) {
					response.sendRedirect(request.getContextPath() + Constant.SUCCESS_SERVLET + "?type="
							+ Constant.DELETE_SUCCESS);
				} else {
					response.sendRedirect(request.getContextPath() + Constant.SUCCESS_SERVLET + "?type="
							+ Constant.DELETE_FAIL);
				}
			} else {
				String errorSystem = MessageErrorProperties.getData("ER013");
				request.setAttribute("error", errorSystem);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
				requestDispatcher.forward(request, response);
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
