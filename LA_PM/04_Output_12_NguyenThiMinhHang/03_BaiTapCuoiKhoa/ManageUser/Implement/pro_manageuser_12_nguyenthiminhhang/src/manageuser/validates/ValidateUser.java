/**
 * Copyright(C) 2017 Luvina
 * ValidateUser.java Oct 20, 2017 minhhang
 */
package manageuser.validates;

import java.util.ArrayList;

import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageErrorProperties;

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
			errMassages.add(MessageErrorProperties.getData("ER001_Username"));
			errMassages.add(MessageErrorProperties.getData("ER001_Pass"));
		} else if ("".equals(username)) {
			errMassages.add(MessageErrorProperties.getData("ER001_Username"));
		} else if ("".equals(password)) {
			errMassages.add(MessageErrorProperties.getData("ER001_Pass"));
		} else {
			if (!logicImpl.existLogin(username, password)) {
				errMassages.add(MessageErrorProperties.getData("ER016"));
			}
		}
		return errMassages;
	}
}
