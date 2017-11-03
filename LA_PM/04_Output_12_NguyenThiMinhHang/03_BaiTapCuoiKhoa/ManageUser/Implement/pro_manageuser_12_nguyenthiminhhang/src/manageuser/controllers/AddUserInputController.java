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
@WebServlet("/addUserInput.do")
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
				//
			} else {
				request.setAttribute("lstError", lstError);
				// Forward đến ADM003
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM003);
				requestDispatcher.forward(request, response);
			}
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
		String username = Constant.EMPTY_STRING;

		// trường hợp từ ADM002 sang -> thêm mới
		// trường hợp xác nhận tại ADM003
		if ("add".equals(tab) || "confirm".equals(tab)) {
			// rỗng
			currentYear = Common.getCurrentYear();
			currentMonth = Common.getCurrentMonth();
			currentDay = Common.getCurrentDay();

			userInfor.setYear(currentYear);
			userInfor.setMonth(currentMonth);
			userInfor.setDay(currentDay);
			// request
			username = Common.getRequestValue(request, "username", Constant.EMPTY_STRING);
			userInfor.setLoginName(username);
		}

		// trường hợp back từ ADM004
		// session

		// trường hợp sửa từ ADM005
		// database

		return userInfor;
	}

}
