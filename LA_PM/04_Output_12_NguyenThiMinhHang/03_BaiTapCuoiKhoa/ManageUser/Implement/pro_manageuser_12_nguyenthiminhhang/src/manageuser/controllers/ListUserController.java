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

import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Servlet implementation class ListUserController Controller xử lý cho màn hình
 * danh sách user ADM002
 */
@WebServlet("/listUser")
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Hiển thị màn hình danh sách user ADM002
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	/**
	 * Hàm xử lý lấy data để hiển thị màn hình danh sách user ADM002
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			Common common = new Common();
			HttpSession session = request.getSession();
			// Nếu chưa đăng nhập
			if (!common.checkLogin(session)) {
				// Quay về màn hình ADM001
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM001);
				requestDispatcher.forward(request, response);
			} else {
				TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
				ArrayList<UserInfor> listUserInfor = tblUserLogicImpl.getListUser(1, 1, 1, "", "", "", "", "");
				request.setAttribute("listUserInfor", listUserInfor);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM002);
				requestDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
			try {
				requestDispatcher.forward(request, response);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
