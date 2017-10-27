/**
 * Copyright(C) 2017 Luvina
 * BaseDaoImpl.java Oct 23, 2017 minhhang
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroup;
import manageuser.entities.UserInfor;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller xử lý cho màn hình danh sách user ADM002
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
			HttpSession session = request.getSession();
			// Nếu chưa đăng nhập
			if (!Common.checkLogin(session)) {
				// Quay về màn hình ADM001
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM001);
				requestDispatcher.forward(request, response);
			} else {
				request.setCharacterEncoding("UTF-8");
				// Khởi tạo
				TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
				MstGroupLogicImpl groupLogicImpl = new MstGroupLogicImpl();
				List<UserInfor> listUser = new ArrayList<>();
				String type = request.getParameter("type");
				List<MstGroup> listGroup = groupLogicImpl.getAllGroups();
				String name = "";
				int group_id = 0;
				// Tìm kiếm
				if ("search".equals(type)) {
					name = request.getParameter("name");
					// group_id = Integer.parseInt(request.getParameter("group_id"));
					group_id = Common.tryParse(request.getParameter("group_id"));
					request.setAttribute("group_id", group_id);
					request.setAttribute("name", name);
				}
				listUser = tblUserLogicImpl.getListUser(-1, -1, group_id, name, "", "", "", "");
				request.setAttribute("listUser", listUser);
				request.setAttribute("listGroup", listGroup);
				// Forward đến ADM002
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
