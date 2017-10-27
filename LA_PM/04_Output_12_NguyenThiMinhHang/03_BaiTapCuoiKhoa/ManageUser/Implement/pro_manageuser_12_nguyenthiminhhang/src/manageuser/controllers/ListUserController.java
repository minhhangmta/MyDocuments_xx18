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
				String sortType = "";
				int offset = 0;
				String sortByFullName = Constant.ASCENDING;
				String sortByCodeLevel = Constant.ASCENDING;
				String sortByEndDate = Constant.DECREASE;
				// Tìm kiếm
				if ("search".equals(type)) {
					name = request.getParameter("name");
					group_id = Common.tryParseInt(request.getParameter("group_id"));
					request.setAttribute("group_id", group_id);
					request.setAttribute("name", name);
				}
				if ("sort".equals(type)) {
					sortType = request.getParameter("typeSort");
					switch (sortType) {
					case Constant.FULL_NAME:
						sortByFullName = Common.convertSort(Constant.ASCENDING);
						break;
					case Constant.CODE_LEVEL:
						sortByCodeLevel = Common.convertSort(Constant.ASCENDING);
						break;
					case Constant.END_DATE:
						sortByEndDate = Common.convertSort(Constant.DECREASE);
						break;
					default:
						break;
					}
				}
				listUser = tblUserLogicImpl.getListUsers(offset, Constant.LIMIT_PAGE, group_id, name, sortType,
						sortByFullName, sortByCodeLevel, sortByEndDate);
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
