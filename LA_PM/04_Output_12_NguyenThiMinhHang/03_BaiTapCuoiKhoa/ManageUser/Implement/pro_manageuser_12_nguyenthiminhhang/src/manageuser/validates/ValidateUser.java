/**
 * Copyright(C) 2017 Luvina
 * ValidateUser.java Oct 20, 2017 minhhang
 */
package manageuser.validates;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.ConfigProperties;
import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Constant;

/**
 * Xử lý validate thông tin nhập vào từ màn hình
 * 
 * @author minhhang
 */
public class ValidateUser {
	/**
	 * Lớp validate tài khoản vừa đăng nhập
	 * 
	 * @param username
	 *            tên đăng nhập
	 * @param password
	 *            mật khẩu
	 * @return ArrayList<String> chuỗi thông báo lỗi
	 */
	public ArrayList<String> validateLogin(String username, String password) {
		TblUserLogicImpl logicImpl = new TblUserLogicImpl();
		ArrayList<String> errMassages = new ArrayList<>();
		if ("".equals(username) && "".equals(password)) {
			errMassages.add(MessageErrorProperties.getData("ER001_USERNAME"));
			errMassages.add(MessageErrorProperties.getData("ER001_PASS"));
		} else if ("".equals(username)) {
			errMassages.add(MessageErrorProperties.getData("ER001_USERNAME"));
		} else if ("".equals(password)) {
			errMassages.add(MessageErrorProperties.getData("ER001_PASS"));
		} else {
			if (!logicImpl.existLogin(username, password)) {
				errMassages.add(MessageErrorProperties.getData("ER016"));
			}
		}
		return errMassages;
	}

	/**
	 * Thực hiện validate thông tin user nhập từ màn hình ADM003
	 * 
	 * @param userInfor
	 *            Đối tượng user cần check
	 * @return List<String> lstError Danh sách lỗi
	 */
	public List<String> validateUserInfor(UserInfor userInfor) {
		List<String> lstError = new ArrayList<>();
		String errorUsername = validateUsername(userInfor.getLoginName());
		lstError.add(errorUsername);

		return lstError;
	}

	private String validateUsername(String username) {
		String errUsername = "";
		if (username.length() < 1) {
			errUsername = MessageErrorProperties.getData("ER001_USERNAME");
		} else {
			Pattern pattern = Pattern.compile(ConfigProperties.getData("regex"));
			boolean validFormat = pattern.matcher(username).matches();
			boolean validUser = new TblUserLogicImpl().existUsername(username);
			if (username.length() < 4 || username.length() > 15) {
				errUsername = MessageErrorProperties.getData("ER007_USERNAME");
			} else if (!validFormat) {
				errUsername = MessageErrorProperties.getData("ER019");
			} else if (!validUser) {
				errUsername = MessageErrorProperties.getData("ER003_USERNAME");
			}
		}
		return errUsername;
	}

	public static void main(String[] args) {
		// System.out.println(new ValidateUser().validateUsername("admin", "admin"));
	}
}
