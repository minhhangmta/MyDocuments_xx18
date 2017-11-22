package manageuser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller xử lý cho chức năng export file
 */
@WebServlet("/export.do")
public class ExportFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportFileController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String fileName = Constant.CSV_FILE_NAME;
			String fullName = Common.getSessionValue(session, "fullName", Constant.EMPTY_STRING);
			int groupId = Common
					.tryParseInt(Common.getSessionValue(session, "groupId", Integer.toString(Constant.DEFAULT_INT)));

			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			// get list user
			TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
			List<UserInfor> listUser = tblUserLogicImpl.getListUsers(groupId, fullName);
			// generate data
			String data = Common.generateData(listUser);
			// get header
			String header = Common.getHeader("headerCSV");
			// export
			Common.exportCSVFile(response, data, header);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + Constant.ERROR_SERVLET);
		}
	}

}
