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

import org.omg.CORBA.COMM_FAILURE;

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
				List<Integer> listPaging = new ArrayList<>();
				String name = "";
				int group_id = 0;
				String sortType = "";
				int currentPage = 1;
				int limit = Common.getLimit();
				int offset = Common.getOffset(currentPage, limit);
				String sortByFullName = "";
				String sortByCodeLevel = "";
				String sortByEndDate = "";
				int totalPage = 0;
				int nextPage = 0;
				int previousPage = 0;
				// get type from jsp
				String type = request.getParameter("type");
				// get listGroup
				List<MstGroup> listGroup = groupLogicImpl.getAllGroups();
				// get totalRecord
				int totalRecord = tblUserLogicImpl.getTotalUsers(group_id, name);
				// get totalPage
				totalPage = Common.getTotalPage(totalRecord, limit);

				// Lần đầu hiển thị
				sortByFullName = Constant.ASCENDING;
				sortByCodeLevel = Constant.ASCENDING;
				sortByEndDate = Constant.DECREASE;
				request.setAttribute("sortByFullname", Constant.DECREASE);
				request.setAttribute("sortByCodeLevel", Constant.DECREASE);
				request.setAttribute("sortByEndDate", Constant.ASCENDING);

				// Chọn tìm kiếm
				if ("search".equals(type)) {
					name = request.getParameter("name");
					group_id = Common.tryParseInt(request.getParameter("group_id"));
					request.setAttribute("group_id", group_id);
					request.setAttribute("name", name);
				}
				// Chọn sắp xếp
				if ("sort".equals(type)) {
					sortType = request.getParameter("typeSort");
					switch (sortType) {
					case Constant.FULL_NAME:
						sortByFullName = request.getParameter("sortByFullName");
						if (sortByFullName.equals(Constant.DECREASE)) {
							request.setAttribute("sortByFullname", Constant.ASCENDING);
						} else if (sortByFullName.equals(Constant.ASCENDING)) {
							request.setAttribute("sortByFullname", Constant.DECREASE);
						}
						break;
					case Constant.CODE_LEVEL:
						sortByCodeLevel = request.getParameter("sortByCodeLevel");
						if (sortByCodeLevel.equals(Constant.DECREASE)) {
							request.setAttribute("sortByCodeLevel", Constant.ASCENDING);
						} else if (sortByFullName.equals(Constant.ASCENDING)) {
							request.setAttribute("sortByCodeLevel", Constant.DECREASE);
						}
						break;
					case Constant.END_DATE:
						sortByEndDate = request.getParameter("sortByEndDate");
						if (sortByEndDate.equals(Constant.DECREASE)) {
							request.setAttribute("sortByEndDate", Constant.ASCENDING);
						} else if (sortByEndDate.equals(Constant.ASCENDING)) {
							request.setAttribute("sortByEndDate", Constant.DECREASE);
						}
						break;
					default:
						break;
					}
				}
				if ("paging".equals(type)) {
					currentPage = Common.tryParseInt(request.getParameter("page"));
					offset = Common.getOffset(currentPage, limit);
				}

				// get listPaging
				listPaging = Common.getListPaging(totalRecord, limit, currentPage);
				request.setAttribute("listPaging", listPaging);
				request.setAttribute("totalPage", totalPage);
				// Tinh nextPage
				nextPage = Common.getNextPage(listPaging, currentPage);
				request.setAttribute("nextPage", nextPage);
				// Tinh previousPage
				previousPage = Common.getPrePage(listPaging, currentPage);
				request.setAttribute("previousPage", previousPage);
				// set currentPage cho request
				request.setAttribute("currentPage", currentPage);

				// System.out.println("test" + offset + " " + limit + " " + group_id + " " +
				// name + " " + sortType + " "
				// + sortByFullName + " " + sortByCodeLevel + " " + sortByEndDate);
				listUser = tblUserLogicImpl.getListUsers(offset, limit, group_id, name, sortType, sortByFullName,
						sortByCodeLevel, sortByEndDate);
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
