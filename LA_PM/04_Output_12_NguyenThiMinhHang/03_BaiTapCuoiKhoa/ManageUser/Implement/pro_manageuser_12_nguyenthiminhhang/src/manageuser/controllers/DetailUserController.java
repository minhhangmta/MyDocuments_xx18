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
import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Servlet implementation class DetailUserController
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
		RequestDispatcher requestDispatcher;
		try {
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			int userId = Common.tryParseInt(request.getParameter("id"));
			// neu user ko ton tai
			if (!tblUserLogicImpl.existUserById(userId)) {
				String errorSystem = MessageErrorProperties.getData("ER013");
				request.setAttribute("error", errorSystem);
				requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
			} else {
				UserInfor userInfor = tblUserLogicImpl.getUserById(userId);
				request.setAttribute("userInfor", userInfor);
				request.setAttribute("userId", userId);
				requestDispatcher = request.getRequestDispatcher(Constant.ADM005);
			}
			requestDispatcher.forward(request, response);
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
