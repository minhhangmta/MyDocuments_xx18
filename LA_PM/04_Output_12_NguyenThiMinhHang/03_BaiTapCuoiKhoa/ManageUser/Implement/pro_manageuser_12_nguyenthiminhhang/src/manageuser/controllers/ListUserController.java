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
@WebServlet("/listUser.do")
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
			// Khởi tạo
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			MstGroupLogicImpl groupLogicImpl = new MstGroupLogicImpl();
			List<UserInfor> listUser = new ArrayList<>();
			List<Integer> listPaging = new ArrayList<>();
			String fullName, sortType, sortByFullName, sortByCodeLevel, sortByEndDate;
			int group_id = 0;
			int currentPage = 1;
			int limit = Common.getLimit();
			int offset = Common.getOffset(currentPage, limit);
			int totalPage = 0;
			int nextPage = 0;
			int previousPage = 0;
			int totalRecord = 0;

			// get type from jsp
			String type = request.getParameter("type");
			// get listGroup
			List<MstGroup> listGroup = groupLogicImpl.getAllGroups();
			// set default cho cac gia tri sort
			sortType = Common.getSessionValue(session, "sortType", Constant.EMPTY_STRING);

			if (sortType.isEmpty()) {
				sortByFullName = Constant.DEFAULT_FULL_NAME_SORT;
				sortByCodeLevel = Constant.DEFAULT_CODE_LEVEL_SORT;
				sortByEndDate = Constant.DEFAULT_END_DATE_SORT;

				request.setAttribute("sortByFullname", Constant.DECREASE);
				request.setAttribute("sortByCodeLevel", Constant.DECREASE);
				request.setAttribute("sortByEndDate", Constant.ASCENDING);

				session.setAttribute("sortByFullname", sortByFullName);
				session.setAttribute("sortByCodeLevel", sortByCodeLevel);
				session.setAttribute("sortByEndDate", sortByEndDate);
			}
			// truong hop tim kiem
			if ("search".equals(type)) {
				fullName = request.getParameter("fullName");
				session.setAttribute("fullName", fullName);

				group_id = Common.tryParseInt(request.getParameter("group_id"));
				session.setAttribute("group_id", group_id);
			}
			if ("sort".equals(type)) {
				sortType = request.getParameter("typeSort");
				session.setAttribute("sortType", sortType);

				fullName = Common.getSessionValue(session, "fullName", Constant.EMPTY_STRING);
				group_id = Common.tryParseInt(Common.getSessionValue(session, "group_id", Constant.DEFAULT_INT));

				switch (sortType) {
				case Constant.FULL_NAME:
					sortByFullName = Common.getSessionValue(session, "sortByFullName", Constant.DEFAULT_FULL_NAME_SORT);
					request.setAttribute("sortByFullname", sortByFullName);
					if (sortByFullName.equals(Constant.DECREASE)) {
						sortByFullName = Constant.ASCENDING;
					} else if (sortByFullName.equals(Constant.ASCENDING)) {
						sortByFullName = Constant.DECREASE;
					}
					session.setAttribute("sortByFullName", sortByFullName);
					break;
				case Constant.CODE_LEVEL:
					sortByCodeLevel = Common.getSessionValue(session, "sortByCodeLevel",
							Constant.DEFAULT_CODE_LEVEL_SORT);
					request.setAttribute("sortByCodeLevel", sortByCodeLevel);
					if (sortByCodeLevel.equals(Constant.DECREASE)) {
						sortByCodeLevel = Constant.ASCENDING;
					} else if (sortByCodeLevel.equals(Constant.ASCENDING)) {
						sortByCodeLevel = Constant.DECREASE;
					}
					session.setAttribute("sortByCodeLevel", sortByCodeLevel);
					break;
				case Constant.END_DATE:
					sortByEndDate = Common.getSessionValue(session, "sortByEndDate", Constant.DEFAULT_END_DATE_SORT);
					request.setAttribute("sortByEndDate", sortByEndDate);
					if (sortByEndDate.equals(Constant.DECREASE)) {
						sortByEndDate = Constant.ASCENDING;
					} else if (sortByEndDate.equals(Constant.ASCENDING)) {
						sortByEndDate = Constant.DECREASE;
					}
					session.setAttribute("sortByEndDate", sortByEndDate);
					break;
				default:
					break;
				}
			}

			if ("paging".equals(type)) {
				currentPage = Common.tryParseInt(request.getParameter("page"));
				offset = Common.getOffset(currentPage, limit);
			}
			session.setAttribute("currentPage", currentPage);

			fullName = Common.getSessionValue(session, "fullName", Constant.EMPTY_STRING);
			group_id = Common.tryParseInt(Common.getSessionValue(session, "group_id", Constant.DEFAULT_INT));

			sortType = Common.getSessionValue(session, "sortType", Constant.EMPTY_STRING);

			sortByFullName = Common.getSessionValue(session, "sortByFullName", Constant.DEFAULT_FULL_NAME_SORT);
			sortByCodeLevel = Common.getSessionValue(session, "sortByCodeLevel", Constant.DEFAULT_CODE_LEVEL_SORT);
			sortByEndDate = Common.getSessionValue(session, "sortByEndDate", Constant.DEFAULT_CODE_LEVEL_SORT);
			currentPage = Common
					.tryParseInt(Common.getSessionValue(session, "currentPage", Constant.DEFAULT_CURRENT_PAGE));
			// get totalRecord
			totalRecord = tblUserLogicImpl.getTotalUsers(group_id, fullName);
			// get totalPage
			totalPage = Common.getTotalPage(totalRecord, limit);
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

			listUser = tblUserLogicImpl.getListUsers(offset, limit, group_id, fullName, sortType, sortByFullName,
					sortByCodeLevel, sortByEndDate);
			request.setAttribute("listUser", listUser);
			request.setAttribute("listGroup", listGroup);
			// Forward đến ADM002
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM002);
			requestDispatcher.forward(request, response);
			// }
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
