/**
 * Copyright(C) 2017 Luvina
 * AddUserInputController.java Oct 23, 2017 minhhang
 */
package manageuser.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroup;
import manageuser.entities.MstJapan;
import manageuser.entities.UserInfor;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * 
 * Controller xử lý các logic của màn hình ADM003
 * 
 * @author minhhang
 */
@WebServlet({ "/addUserInput.do", "/addUserValidate.do" })
public class AddUserInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserInputController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RequestDispatcher requestDispatcher;
			String tab = request.getParameter("tab");
			if ("edit".equals(tab)) {
				TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
				int userId = Common.tryParseInt(request.getParameter("id"));
				// neu user khong ton tai
				if (!tblUserLogicImpl.existUserById(userId)) {
					String errorSystem = MessageErrorProperties.getData("ER013");
					request.setAttribute("error", errorSystem);
					requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
					requestDispatcher.forward(request, response);
				}
			}
			// set dữ liệu
			setDataLogicADM003(request, response);
			// set default
			UserInfor userInfor = setDefault(request, response);
			// set request
			request.setAttribute("userInfor", userInfor);
			// Forward đến ADM003
			requestDispatcher = request.getRequestDispatcher(Constant.ADM003);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RequestDispatcher requestDispatcher;
			ValidateUser validateUser = new ValidateUser();
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			String tab = request.getParameter("tab");
			if ("confirmEdit".equals(tab)) {
				// lay tu input hidden
				int userId = Common.tryParseInt(request.getParameter("id"));
				if (!tblUserLogicImpl.existUserById(userId)) {
					String errorSystem = MessageErrorProperties.getData("ER013");
					request.setAttribute("error", errorSystem);
					requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
					requestDispatcher.forward(request, response);
				}
			}
			UserInfor userInfor = setDefault(request, response);
			List<String> lstError = validateUser.validateUserInfor(userInfor);
			if (lstError.isEmpty()) {
				String keySession = Common.createKeySession(userInfor.getEmail());
				// Lưu userInfor vào session
				HttpSession session = request.getSession();
				session.setAttribute(keySession, userInfor);
				// điều hướng đến ADM004
				response.sendRedirect(
						request.getContextPath() + Constant.ADD_USER_CONFIRM + "?keySession=" + keySession);
			} else {
				request.setAttribute("lstError", lstError);
				request.setAttribute("userInfor", userInfor);
				// set dữ liệu
				setDataLogicADM003(request, response);
				// Forward đến ADM003
				requestDispatcher = request.getRequestDispatcher(Constant.ADM003);
				requestDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}

	/**
	 * Thực hiện set giá trị cho các hạng mục selectbox ở màn hình ADM003
	 * 
	 * @param request
	 *            yêu cầu
	 * @param response
	 *            phản hồi
	 */
	private void setDataLogicADM003(HttpServletRequest request, HttpServletResponse response) {
		MstGroupLogicImpl groupLogicImpl = new MstGroupLogicImpl();
		MstJapanLogicImpl japanLogicImpl = new MstJapanLogicImpl();
		try {
			List<MstGroup> listGroup = groupLogicImpl.getAllGroups();
			List<MstJapan> listJapan = japanLogicImpl.getAllMstJapan();
			request.setAttribute("listGroup", listGroup);
			request.setAttribute("listJapan", listJapan);
			request.setAttribute("listYear", Common.getListYear(Constant.START_YEAR, Common.getCurrentYear() + 1));
			request.setAttribute("listDay", Common.getListDay());
			request.setAttribute("listMonth", Common.getListMonth());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Set giá trị default cho màn hình ADM003
	 * 
	 * @param request
	 *            yêu cầu
	 * @param response
	 *            phản hồi
	 * @return UserInfor đối tượng UserInfor
	 */
	private UserInfor setDefault(HttpServletRequest request, HttpServletResponse response) {
		UserInfor userInfor = new UserInfor();
		String tab = request.getParameter("tab");
		// Khai bao bien
		int currentYear, currentMonth, currentDay;
		String username, fullName, fullNameKana, email, tel, password, confirmPassword, codeLevel, total;
		int groupId;
		int yearBirthday, monthBirthday, dayBirthday, yearStartDate, monthStartDate, dayStartDate, yearEndDate,
				monthEndDate, dayEndDate;
		String groupName, nameLevel;
		// get date current
		currentYear = Common.getCurrentYear();
		currentMonth = Common.getCurrentMonth();
		currentDay = Common.getCurrentDay();
		// Khoi tao gia tri mac dinh
		username = fullName = fullNameKana = email = tel = password = confirmPassword = Constant.EMPTY_STRING;
		yearBirthday = yearStartDate = currentYear;
		yearEndDate = currentYear + 1;
		monthBirthday = monthStartDate = monthEndDate = currentMonth;
		dayBirthday = dayStartDate = dayEndDate = currentDay;
		groupId = Constant.DEFAULT_INT;
		codeLevel = Constant.EMPTY_STRING;
		total = Constant.EMPTY_STRING;
		groupName = nameLevel = Constant.EMPTY_STRING;
		// truong hop edit
		if ("edit".equals(tab)) {
			int userId = Common.tryParseInt(request.getParameter("id").toString());
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			userInfor = tblUserLogicImpl.getUserById(userId);
			userInfor.setUserId(userId);
			// list date
			List<Integer> list;
			// birthday
			list = Common.getEachElementFromDate(userInfor.getBirthday());
			// Birthday
			yearBirthday = Common.tryParseInt(list.get(0).toString());
			monthBirthday = Common.tryParseInt(list.get(1).toString());
			dayBirthday = Common.tryParseInt(list.get(2).toString());
			userInfor.setYearBirthday(yearBirthday);
			userInfor.setMonthBirthday(monthBirthday);
			userInfor.setDayBirthday(dayBirthday);
			if (userInfor.getCodeLevel() != null) {
				// Start date
				list = Common.getEachElementFromDate(userInfor.getStartDate());
				yearStartDate = Common.tryParseInt(list.get(0).toString());
				monthStartDate = Common.tryParseInt(list.get(1).toString());
				dayStartDate = Common.tryParseInt(list.get(2).toString());

				// End date
				list = Common.getEachElementFromDate(userInfor.getEndDate());
				yearEndDate = Common.tryParseInt(list.get(0).toString());
				monthEndDate = Common.tryParseInt(list.get(1).toString());
				dayEndDate = Common.tryParseInt(list.get(2).toString());
			}
		} else if ("back".equals(tab)) {// trường hợp back từ ADM004
			HttpSession session = request.getSession();
			String keySession = request.getParameter("keySession");
			userInfor = (UserInfor) session.getAttribute(keySession);
		} else {
			// trường hợp xác nhận tại ADM003
			if ("confirmAdd".equals(tab) || "confirmEdit".equals(tab)) {
				// get from request or default
				username = request.getParameter("username");
				groupId = Common.tryParseInt(request.getParameter("groupId"));
				groupName = new MstGroupLogicImpl().getGroupName(groupId);
				fullName = request.getParameter("fullName");
				fullNameKana = request.getParameter("fullNameKana");
				email = request.getParameter("email");
				password = request.getParameter("password");
				confirmPassword = request.getParameter("confirmPassword");

				tel = request.getParameter("tel");
				codeLevel = request.getParameter("codeLevel");
				nameLevel = new MstJapanLogicImpl().getNameLevel(codeLevel);
				// Birthday
				yearBirthday = Common.tryParseInt(request.getParameter("yearBirthday"));
				monthBirthday = Common.tryParseInt(request.getParameter("monthBirthday"));
				dayBirthday = Common.tryParseInt(request.getParameter("dayBirthday"));

				// Start date
				yearStartDate = Common.tryParseInt(request.getParameter("yearStartDate"));
				monthStartDate = Common.tryParseInt(request.getParameter("monthStartDate"));
				dayStartDate = Common.tryParseInt(request.getParameter("dayStartDate"));

				// End date
				yearEndDate = Common.tryParseInt(request.getParameter("yearEndDate"));
				monthEndDate = Common.tryParseInt(request.getParameter("monthEndDate"));
				dayEndDate = Common.tryParseInt(request.getParameter("dayEndDate"));

				if (!codeLevel.isEmpty()) {
					// total
					total = request.getParameter("total");
				}
				if ("confirmEdit".equals(tab)) {
					int userId = Common.tryParseInt(request.getParameter("id"));
					userInfor.setUserId(userId);
				}
			}

			userInfor.setLoginName(username);
			userInfor.setPasswords(password);
			userInfor.setConfirmPassword(confirmPassword);
			userInfor.setGroupId(groupId);
			userInfor.setGroupName(groupName);
			userInfor.setFullName(fullName);
			userInfor.setFullNameKana(fullNameKana);
			userInfor.setEmail(email);
			userInfor.setTel(tel);

			// birthday
			userInfor.setYearBirthday(yearBirthday);
			userInfor.setMonthBirthday(monthBirthday);
			userInfor.setDayBirthday(dayBirthday);
			userInfor.setBirthday(Common.toDate(yearBirthday, monthBirthday, dayBirthday));

			if (!codeLevel.isEmpty()) {
				userInfor.setCodeLevel(codeLevel);
				userInfor.setNameLevel(nameLevel);
				userInfor.setStartDate(Common.toDate(yearStartDate, monthStartDate, dayStartDate));
				userInfor.setEndDate(Common.toDate(yearEndDate, monthEndDate, dayEndDate));
				userInfor.setTotal(total);
			}
		}

		// vi cac truong hop deu can den year, month, day hien tai, start, end nen set
		// cuoi cung
		// start date
		userInfor.setYearStartDate(yearStartDate);
		userInfor.setMonthStartDate(monthStartDate);
		userInfor.setDayStartDate(dayStartDate);
		// end date
		userInfor.setYearEndDate(yearEndDate);
		userInfor.setMonthEndDate(monthEndDate);
		userInfor.setDayEndDate(dayEndDate);
		// set default date current
		userInfor.setYear(currentYear);
		userInfor.setMonth(currentMonth);
		userInfor.setDay(currentDay);

		return userInfor;
	}

}
