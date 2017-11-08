package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Constant;

/**
 * Controller xử lý các logic của màn hình ADM004
 */
@WebServlet({ "/addUserConfirm.do", "/addUserOK.do" })
public class AddUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserConfirmController() {
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
			HttpSession session = request.getSession();
			String keySesion = request.getParameter("keySession");
			UserInfor userInfor = (UserInfor) session.getAttribute(keySesion);
			session.setAttribute("userInfor", userInfor);
			request.setAttribute("keySession", keySesion);
			// Forward đến ADM004
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM004);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			String errorSystem = MessageErrorProperties.getData("ERROR_SYSTEM");
			request.setAttribute("error", errorSystem);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
			requestDispatcher.forward(request, response);
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
		try {
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			HttpSession session = req.getSession();
			String keySession = req.getParameter("keySession");
			UserInfor userInfor = (UserInfor) session.getAttribute(keySession);
			if (tblUserLogicImpl.createUser(userInfor)) {
				// req.setAttribute(keySession, keySession);
				resp.sendRedirect(req.getContextPath() + Constant.SUCCESS_SERVLET + "?type=" + Constant.INSERT_SUCCESS);
			} else {
				resp.sendRedirect(req.getContextPath() + Constant.SUCCESS_SERVLET + "?type=" + Constant.INSERT_FAIL);
			}
		} catch (Exception e) {
			String errorSystem = MessageErrorProperties.getData("ERROR_SYSTEM");
			req.setAttribute("error", errorSystem);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.SYSTEM_ERROR);
			requestDispatcher.forward(req, resp);
		}
	}
}
