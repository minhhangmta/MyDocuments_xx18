package manageuser.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Servlet implementation class AddUserInputController
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// set dữ liệu
			setDataLogicADM003(request, response);
			// set default
			UserInfor userInfor = setDefault(request, response);
			request.setAttribute("userInfor", userInfor);
			// Forward đến ADM003
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM003);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			String errorSystem = MessageErrorProperties.getData("ERROR_SYSTEM");
			request.setAttribute("error", errorSystem);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
			try {
				requestDispatcher.forward(request, response);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserInfor userInfor = setDefault(request, response);
			ValidateUser validateUser = new ValidateUser();
			// validate thông tin
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
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM003);
				requestDispatcher.forward(request, response);
			}

		} catch (Exception e) {
			String errorSystem = MessageErrorProperties.getData("ERROR_SYSTEM");
			request.setAttribute("error", errorSystem);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
			try {
				requestDispatcher.forward(request, response);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			request.setAttribute("listYear", Common.getListYear(Constant.START_YEAR, Common.getCurrentYear()));
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
		int currentYear, currentMonth, currentDay;
		String username, fullName, fullNameKana, email, tel, password, confirmPassword, codeLevel, total;
		username = fullName = fullNameKana = email = tel = password = confirmPassword = Constant.EMPTY_STRING;
		int groupId;

		// get date
		currentYear = Common.getCurrentYear();
		currentMonth = Common.getCurrentMonth();
		currentDay = Common.getCurrentDay();

		int yearBirthday, monthBirthday, dayBirthday, yearStartDate, monthStartDate, dayStartDate, yearEndDate,
				monthEndDate, dayEndDate;
		yearBirthday = yearStartDate = yearEndDate = dayBirthday = currentYear;
		monthBirthday = monthStartDate = monthEndDate = currentMonth;
		dayBirthday = dayStartDate = dayEndDate = currentMonth;
		Date birthday, startDate, endDate;
		String groupName, nameLevel;
		// Khoi tao gia tri mac dinh
		groupId = Constant.DEFAULT_INT;
		codeLevel = Constant.EMPTY_STRING;
		total = Constant.EMPTY_STRING;
		birthday = startDate = endDate = null;
		groupName = nameLevel = Constant.EMPTY_STRING;

		// trường hợp từ ADM002 sang -> thêm mới
		// trường hợp xác nhận tại ADM003
		if ("add".equals(tab) || "confirm".equals(tab)) {
			userInfor.setYear(currentYear);
			userInfor.setMonth(currentMonth);
			userInfor.setDay(currentDay);
			// get request
			username = Common.getRequestValue(request, "username", Constant.EMPTY_STRING);
			groupId = Common
					.tryParseInt(Common.getRequestValue(request, "groupId", String.valueOf(Constant.DEFAULT_INT)));
			groupName = new MstGroupLogicImpl().getGroupName(groupId);
			fullName = Common.getRequestValue(request, "fullName", Constant.EMPTY_STRING);
			fullNameKana = Common.getRequestValue(request, "fullNameKana", Constant.EMPTY_STRING);
			email = Common.getRequestValue(request, "email", Constant.EMPTY_STRING);
			password = Common.getRequestValue(request, "password", Constant.EMPTY_STRING);
			confirmPassword = Common.getRequestValue(request, "confirmPassword", Constant.EMPTY_STRING);

			tel = Common.getRequestValue(request, "tel", Constant.EMPTY_STRING);

			codeLevel = Common.getRequestValue(request, "codeLevel", String.valueOf(Constant.EMPTY_STRING));
			// Birthday
			yearBirthday = Common
					.tryParseInt(Common.getRequestValue(request, "yearBirthday", String.valueOf(currentYear)));
			monthBirthday = Common
					.tryParseInt(Common.getRequestValue(request, "monthBirthday", String.valueOf(currentMonth)));
			dayBirthday = Common
					.tryParseInt(Common.getRequestValue(request, "dayBirthday", String.valueOf(currentDay)));
			birthday = Common.toDate(yearBirthday, monthBirthday, dayBirthday);
			// Start date
			yearStartDate = Common
					.tryParseInt(Common.getRequestValue(request, "yearStartDate", String.valueOf(currentYear)));
			monthStartDate = Common
					.tryParseInt(Common.getRequestValue(request, "monthStartDate", String.valueOf(currentMonth)));
			dayStartDate = Common
					.tryParseInt(Common.getRequestValue(request, "dayStartDate", String.valueOf(currentDay)));

			// End date
			yearEndDate = Common
					.tryParseInt(Common.getRequestValue(request, "yearEndDate", String.valueOf(currentYear)));
			monthEndDate = Common
					.tryParseInt(Common.getRequestValue(request, "monthEndDate", String.valueOf(currentMonth)));
			dayEndDate = Common.tryParseInt(Common.getRequestValue(request, "dayEndDate", String.valueOf(currentDay)));

			nameLevel = new MstJapanLogicImpl().getNameLevel(codeLevel);

			if (!codeLevel.isEmpty()) {
				startDate = Common.toDate(yearStartDate, monthStartDate, dayStartDate);
				endDate = Common.toDate(yearEndDate, monthEndDate, dayEndDate);
				// total
				total = Common.getRequestValue(request, "total", Constant.EMPTY_STRING);
			}

			// set vào userInfor
			userInfor.setLoginName(username);
			userInfor.setGroupId(groupId);
			userInfor.setGroupName(groupName);
			userInfor.setFullName(fullName);
			userInfor.setFullNameKana(fullNameKana);
			userInfor.setPasswords(password);
			userInfor.setConfirmPassword(confirmPassword);
			userInfor.setEmail(email);
			userInfor.setTel(tel);
			userInfor.setCodeLevel(codeLevel);
			userInfor.setNameLevel(nameLevel);
			userInfor.setTotal(total);
			// birthday
			userInfor.setYearBirthday(yearBirthday);
			userInfor.setMonthBirthday(monthBirthday);
			userInfor.setDayBirthday(dayBirthday);
			userInfor.setBirthday(birthday);
			// start date
			userInfor.setYearStartDate(yearStartDate);
			userInfor.setMonthStartDate(monthStartDate);
			userInfor.setDayStartDate(dayStartDate);
			userInfor.setStartDate(startDate);
			// end date
			userInfor.setYearEndDate(yearEndDate);
			userInfor.setMonthEndDate(monthEndDate);
			userInfor.setDayEndDate(dayEndDate);
			userInfor.setEndDate(endDate);

		} else if ("back".equals(tab)) {// trường hợp back từ ADM004
			HttpSession session = request.getSession();
			String keySession = request.getParameter("keySession");
			userInfor = (UserInfor) session.getAttribute(keySession);
		}

		// trường hợp sửa từ ADM005
		// database

		return userInfor;
	}

}
