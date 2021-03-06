/**
 * Copyright(C) 2017 Luvina
 * AddUserConfirmController.java Oct 23, 2017 minhhang
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller xử lý các logic của màn hình ADM004
 * 
 * @author minhhang
 */
@WebServlet(urlPatterns = { "/addUserConfirm.do", "/addUserOK.do" })
public class AddUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserConfirmController() {
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// click submit adm003
		try {
			HttpSession session = request.getSession();
			String keySesion = request.getParameter("keySession");
			UserInfor userInfor = (UserInfor) session.getAttribute(keySesion);
			request.setAttribute("keySession", keySesion);
			//set cho view
			request.setAttribute("userInfor", userInfor);
			// Forward đến ADM004
			if (userInfor != null) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM004);
				requestDispatcher.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// click ok từ adm004
		try {
			String url = "";
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			HttpSession session = req.getSession();
			String keySession = req.getParameter("keySession");
			UserInfor userInfor = (UserInfor) session.getAttribute(keySession);
			int userId = userInfor.getUserId();
			// Xoa session
			session.removeAttribute(keySession);
			// truong hop add
			if (userId <= 0) {
				List<String> listError = new ValidateUser().validateUserInfor(userInfor);
				if (listError.isEmpty() && tblUserLogicImpl.createUser(userInfor)) {
					url = Constant.SUCCESS_SERVLET + "?type=" + Constant.INSERT_SUCCESS;
				} else {
					url = Constant.SUCCESS_SERVLET + "?type=" + Constant.INSERT_FAIL;
				}
			} else {// truong hop edit
				// Neu user khong ton tai
				if (!tblUserLogicImpl.existUserById(userId)) {
					url = Constant.ERROR_SERVLET + "?error=" + Constant.NOT_FOUND;
				} else {// user co ton tai
					List<String> listError = new ValidateUser().validateUserInfor(userInfor);
					if (listError.isEmpty() && tblUserLogicImpl.updateUserInfor(userInfor)) {
						url = Constant.SUCCESS_SERVLET + "?type=" + Constant.UPDATE_SUCCESS;
					} else {
						url = Constant.SUCCESS_SERVLET + "?type=" + Constant.UPDATE_FAIL;
					}
				}
			}
			resp.sendRedirect(req.getContextPath() + url);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + Constant.ERROR_SERVLET);
		}
	}
}
