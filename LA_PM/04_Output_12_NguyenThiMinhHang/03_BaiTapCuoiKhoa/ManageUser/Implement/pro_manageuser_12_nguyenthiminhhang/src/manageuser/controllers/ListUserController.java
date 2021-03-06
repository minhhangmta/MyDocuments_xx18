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
@WebServlet(urlPatterns = "/listUser.do")
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUserController() {
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
		// sau khi sort, paging, back, top
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
			String fullName;
			String sortType;
			String sortByName;
			String sortByCodeLevel;
			String sortByEndDate;
			int groupId;
			int offset;
			int totalPage;
			int nextPage;
			int previousPage;
			int totalRecord;
			int currentPage;
			int limit;
			// get type from jsp
			String type = request.getParameter("type");
			// set data group cho man hinh
			List<MstGroup> listGroup = groupLogicImpl.getAllGroups();
			// khi click link top
			if ("default" == type) {
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
					// get sort tu url
					String sort = request.getParameter("sort");
					switch (sortType) {
					case Constant.FULL_NAME:
						sortByName = Common.getCorrectSort(sortByName, sort);
						break;
					case Constant.CODE_LEVEL:
						sortByCodeLevel = Common.getCorrectSort(sortByCodeLevel, sort);
						break;
					case Constant.END_DATE:
						sortByEndDate = Common.getCorrectSort(sortByEndDate, sort);
						break;
					default:
						break;
					}
				} else if ("paging".equals(type)) {
					// paging
					currentPage = Common.tryParseInt(request.getParameter("page"));
					if (currentPage <= 0) {
						currentPage = Constant.DEFAULT_CURRENT_PAGE;
					}
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

			fullName = Common.replaceWildCard(fullName);
			// get totalRecord
			totalRecord = tblUserLogicImpl.getTotalUsers(groupId, fullName);
			// Nếu có bản ghi
			if (totalRecord > 0) {
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
			} else {// Nếu không có bản ghi nào
				String msg005 = MessageProperties.getData("MSG005");
				request.setAttribute("totalRecord", totalRecord);
				request.setAttribute("msg005", msg005);
			}

			// Forward đến ADM002
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM002);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}
}
