/**
 * Copyright(C) 2017 Luvina
 * Constant.java Oct 20, 2017 minhhang
 */
package manageuser.utils;

/**
 * Lớp constant hóa các alias
 * 
 * @author minhhang
 */
public class Constant {
	// View
	public static final String ADM001 = "view/jsp/ADM001.jsp";
	public static final String ADM002 = "view/jsp/ADM002.jsp";
	public static final String ADM003 = "view/jsp/ADM003.jsp";
	public static final String ADM004 = "view/jsp/ADM004.jsp";
	public static final String ADM005 = "view/jsp/ADM005.jsp";
	public static final String ADM006 = "view/jsp/ADM006.jsp";
	public static final String ADM007 = "view/jsp/ADM007.jsp";
	public static final String SYSTEM_ERROR = "view/jsp/System_Error.jsp";
	// Controller
	public static final String LISTUSER_SERVLET = "/listUser.do";
	public static final String LOGIN_SERVLET = "/login.do";
	public static final String LOGOUT_SERVLET = "/logout.do";
	public static final String ADD_USER_INPUT = "/addUserInput.do";
	public static final String ADD_USER_VALIDATE = "/addUserValidate.do";
	public static final String ADD_USER_CONFIRM = "/addUserConfirm.do";
	public static final String ADD_USER_OK = "/addUserOK.do";
	public static final String SUCCESS_SERVLET = "/success.do";
	public static final String ERROR_SERVLET = "/error.do";
	public static final String DETAIL_SERVLET = "/detailUser.do";
	public static final String EDIT_PASS_SERVLET = "/editPass.do";
	public static final String DELETE_USER_SERVLET = "/deleteUser.do";
	//
	public static final String FULL_NAME = "full_name";
	public static final String CODE_LEVEL = "code_level";
	public static final String END_DATE = "end_date";
	// Default value
	public static final String ASCENDING = "ASC";
	public static final String DECREASE = "DESC";
	public static final String DEFAULT_FULL_NAME_SORT = "ASC";
	public static final String DEFAULT_CODE_LEVEL_SORT = "ASC";
	public static final String DEFAULT_END_DATE_SORT = "DESC";
	public static final String EMPTY_STRING = "";
	public static final int DEFAULT_INT = 0;
	public static final int DEFAULT_TOTAL = -1;
	public static final int DEFAULT_CODE_LEVEL = -1;
	public static final int DEFAULT_CURRENT_PAGE = 1;
	// Format default
	public static final String CHARSET = "UTF-8";
	public static final int START_YEAR = 1980;
	public static final String FORMAT_DATE = "yyyy/MM/dd";
	public static final int ROLE_USER = 0;
	public static final String SALT_CHARS = "abcdefghijklmnopqrstuvwxyz";
	public static final String INSERT_SUCCESS = "insertSuccess";
	public static final String INSERT_FAIL = "insertFail";
	public static final String UPDATE_SUCCESS = "updateSuccess";
	public static final String UPDATE_FAIL = "updateFail";
	public static final String UPDATE_PASS_SUCCESS = "updatePassSuccess";
	public static final String UPDATE_PASS_FAIL = "updatePassFail";
	public static final String DELETE_SUCCESS = "deleteSuccess";
	public static final String DELETE_FAIL = "deleteFail";

}
