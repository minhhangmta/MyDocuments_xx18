package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.properties.MessageErrorProperties;
import manageuser.properties.MessageProperties;
import manageuser.utils.Constant;

/**
 * Servlet implementation class SuccessController
 */
@WebServlet("/success.do")
public class SuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuccessController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String type = request.getParameter("type");
			if (type == Constant.INSERT_SUCCESS) {
				request.setAttribute("content", MessageProperties.getData("MSG001"));
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM006);
				requestDispatcher.forward(request, response);
			} else if (type == Constant.INSERT_FAIL) {
				request.setAttribute("error", MessageProperties.getData("MSG005"));
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR);
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
}
