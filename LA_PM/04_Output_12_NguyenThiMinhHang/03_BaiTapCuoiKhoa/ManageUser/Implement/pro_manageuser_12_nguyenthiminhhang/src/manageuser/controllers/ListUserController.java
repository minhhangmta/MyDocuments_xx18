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
import manageuser.properties.MessageErrorProperties;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			// Khởi tạo
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			MstGroupLogicImpl groupLogicImpl = new MstGroupLogicImpl();
			List<UserInfor> listUser = new ArrayList<>();
			List<Integer> listPaging = new ArrayList<>();
			String fullName, sortType, sortByName, sortByCodeLevel, sortByEndDate;
			int group_id, offset, totalPage, nextPage, previousPage, totalRecord;
			int currentPage = Constant.DEFAULT_CURRENT_PAGE; // Common.tryParseInt(Constant.DEFAULT_CURRENT_PAGE);
			int limit = Common.getLimit();
			offset = Common.getOffset(currentPage, limit);

			// get type from jsp
			String type = request.getParameter("type");
			// get listGroup
			List<MstGroup> listGroup = groupLogicImpl.getAllGroups();

			// set default cho cac gia tri sort
			if (session.getAttribute("sortByName") == null || "default".equals(type)) {
				fullName = Constant.EMPTY_STRING;
				group_id = Constant.DEFAULT_INT;
				sortType = Constant.EMPTY_STRING;
				sortByName = Constant.DEFAULT_FULL_NAME_SORT;
				sortByCodeLevel = Constant.DEFAULT_CODE_LEVEL_SORT;
				sortByEndDate = Constant.DEFAULT_END_DATE_SORT;
				session.setAttribute("sortByName", sortByName);
				session.setAttribute("sortByCodeLevel", sortByCodeLevel);
				session.setAttribute("sortByEndDate", sortByEndDate);
				session.setAttribute("sortType", sortType);
				session.setAttribute("fullName", fullName);
				session.setAttribute("group_id", group_id);
			} else {
				sortByName = session.getAttribute("sortByName").toString();
				sortByCodeLevel = session.getAttribute("sortByCodeLevel").toString();
				sortByEndDate = session.getAttribute("sortByEndDate").toString();
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
				group_id = Common.tryParseInt(
						Common.getSessionValue(session, "group_id", Integer.toString(Constant.DEFAULT_INT)));

				switch (sortType) {
				case Constant.FULL_NAME:
					if (sortByName.equals(Constant.DECREASE)) {
						sortByName = Constant.ASCENDING;
					} else if (sortByName.equals(Constant.ASCENDING)) {
						sortByName = Constant.DECREASE;
					}
					session.setAttribute("sortByName", sortByName);
					break;
				case Constant.CODE_LEVEL:
					if (sortByCodeLevel.equals(Constant.DECREASE)) {
						sortByCodeLevel = Constant.ASCENDING;
					} else if (sortByCodeLevel.equals(Constant.ASCENDING)) {
						sortByCodeLevel = Constant.DECREASE;
					}
					session.setAttribute("sortByCodeLevel", sortByCodeLevel);
					break;
				case Constant.END_DATE:
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
			group_id = Common
					.tryParseInt(Common.getSessionValue(session, "group_id", Integer.toString(Constant.DEFAULT_INT)));
			sortType = Common.getSessionValue(session, "sortType", Constant.EMPTY_STRING);
			currentPage = Common.tryParseInt(
					Common.getSessionValue(session, "currentPage", Integer.toString(Constant.DEFAULT_CURRENT_PAGE)));

			request.setAttribute("sortByName", sortByName);
			request.setAttribute("sortByCodeLevel", sortByCodeLevel);
			request.setAttribute("sortByEndDate", sortByEndDate);
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

			listUser = tblUserLogicImpl.getListUsers(offset, limit, group_id, fullName, sortType, sortByName,
					sortByCodeLevel, sortByEndDate);
			request.setAttribute("listUser", listUser);
			request.setAttribute("listGroup", listGroup);
			// Forward đến ADM002
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM002);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			String errorSystem = MessageErrorProperties.getData("ERROR_SYSTEM");
			request.setAttribute("errorSystem", errorSystem);
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
