/**
 * Copyright(C) 2017 Luvina
 * ListUserController.java Oct 23, 2017 minhhang
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
import manageuser.properties.MessageProperties;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller xử lý cho màn hình danh sách user ADM002
 * 
 * @author minhhang
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Send redirect từ login
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Sau khi login va Sau khi submit form search
		try {
			HttpSession session = request.getSession();
			// Khởi tạo
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			MstGroupLogicImpl groupLogicImpl = new MstGroupLogicImpl();
			List<UserInfor> listUser = new ArrayList<>();
			List<Integer> listPaging = new ArrayList<>();
			String fullName, sortType, sortByName, sortByCodeLevel, sortByEndDate;
			int groupId, offset, totalPage, nextPage, previousPage, totalRecord, currentPage, limit;
			// get type from jsp
			String type = request.getParameter("type");
			// set session cho type
			session.setAttribute("type", type);
			type = Common.getSessionValue(session, "type", Constant.EMPTY_STRING);
			// set data group cho man hinh
			List<MstGroup> listGroup = groupLogicImpl.getAllGroups();
			// Lan dau vao man hinh va khi click link top
			if ("default".equals(type)) {
				fullName = sortType = Constant.EMPTY_STRING;
				groupId = Constant.DEFAULT_INT;
				currentPage = Constant.DEFAULT_CURRENT_PAGE;
				sortType = Constant.EMPTY_STRING;
				sortByName = Constant.DEFAULT_FULL_NAME_SORT;
				sortByCodeLevel = Constant.DEFAULT_CODE_LEVEL_SORT;
				sortByEndDate = Constant.DEFAULT_END_DATE_SORT;
			} else {
				// lấy dữ liệu từ session
				fullName = Common.getSessionValue(session, "fullName", Constant.EMPTY_STRING);
				groupId = Common.tryParseInt(
						Common.getSessionValue(session, "groupId", Integer.toString(Constant.DEFAULT_INT)));
				sortType = Common.getSessionValue(session, "sortType", Constant.EMPTY_STRING);
				currentPage = Common.tryParseInt(Common.getSessionValue(session, "currentPage",
						Integer.toString(Constant.DEFAULT_CURRENT_PAGE)));
				sortByName = Common.getSessionValue(session, "sortByName", Constant.DEFAULT_FULL_NAME_SORT);
				sortByCodeLevel = Common.getSessionValue(session, "sortByCodeLevel", Constant.DEFAULT_CODE_LEVEL_SORT);
				sortByEndDate = Common.getSessionValue(session, "sortByEndDate", Constant.DEFAULT_END_DATE_SORT);

				if ("search".equals(type)) {
					// search
					fullName = request.getParameter("fullName");
					groupId = Common.tryParseInt(request.getParameter("groupId"));
					currentPage = Constant.DEFAULT_CURRENT_PAGE;
					sortType = Constant.EMPTY_STRING;
					sortByName = Constant.DEFAULT_FULL_NAME_SORT;
					sortByCodeLevel = Constant.DEFAULT_CODE_LEVEL_SORT;
					sortByEndDate = Constant.DEFAULT_END_DATE_SORT;
				} else if ("sort".equals(type)) {
					// sort
					sortType = request.getParameter("typeSort");
					currentPage = Constant.DEFAULT_CURRENT_PAGE;
					switch (sortType) {
					case Constant.FULL_NAME:
						if (sortByName.equals(Constant.DECREASE)) {
							sortByName = Constant.ASCENDING;
						} else if (sortByName.equals(Constant.ASCENDING)) {
							sortByName = Constant.DECREASE;
						}
						break;
					case Constant.CODE_LEVEL:
						if (sortByCodeLevel.equals(Constant.DECREASE)) {
							sortByCodeLevel = Constant.ASCENDING;
						} else if (sortByCodeLevel.equals(Constant.ASCENDING)) {
							sortByCodeLevel = Constant.DECREASE;
						}
						break;
					case Constant.END_DATE:
						if (sortByEndDate.equals(Constant.DECREASE)) {
							sortByEndDate = Constant.ASCENDING;
						} else if (sortByEndDate.equals(Constant.ASCENDING)) {
							sortByEndDate = Constant.DECREASE;
						}
						break;
					default:
						break;
					}
				} else if ("paging".equals(type)) {
					// paging
					currentPage = Common.tryParseInt(request.getParameter("page"));
				}
			}
			// set session cho cac gia tri
			session.setAttribute("sortByName", sortByName);
			session.setAttribute("sortByCodeLevel", sortByCodeLevel);
			session.setAttribute("sortByEndDate", sortByEndDate);
			session.setAttribute("sortType", sortType);
			session.setAttribute("fullName", fullName);
			session.setAttribute("groupId", groupId);
			session.setAttribute("currentPage", currentPage);

			// get totalRecord
			totalRecord = tblUserLogicImpl.getTotalUsers(groupId, fullName);
			if (totalRecord != 0) {
				// Lay ban ghi
				limit = Common.getLimit();
				offset = Common.getOffset(currentPage, limit);

				// get totalPage
				totalPage = Common.getTotalPage(totalRecord, limit);
				request.setAttribute("totalPage", totalPage);

				// get listPaging
				listPaging = Common.getListPaging(totalRecord, limit, currentPage);
				request.setAttribute("listPaging", listPaging);

				// Tinh nextPage
				nextPage = Common.getNextPage(listPaging, currentPage);
				request.setAttribute("nextPage", nextPage);

				// Tinh previousPage
				previousPage = Common.getPrePage(listPaging, currentPage);
				request.setAttribute("previousPage", previousPage);
				// get list user
				listUser = tblUserLogicImpl.getListUsers(offset, limit, groupId, fullName, sortType, sortByName,
						sortByCodeLevel, sortByEndDate);
				request.setAttribute("listUser", listUser);
				request.setAttribute("listGroup", listGroup);
			} else {
				String msg005 = MessageProperties.getData("MSG005");
				request.setAttribute("totalRecord", totalRecord);
				request.setAttribute("msg005", msg005);
			}

			// Forward đến ADM002
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM002);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}
}
