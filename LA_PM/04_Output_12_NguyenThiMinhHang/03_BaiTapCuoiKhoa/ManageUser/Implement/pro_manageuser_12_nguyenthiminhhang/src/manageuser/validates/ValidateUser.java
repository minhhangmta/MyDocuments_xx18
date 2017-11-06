/**
 * Copyright(C) 2017 Luvina
 * ValidateUser.java Oct 20, 2017 minhhang
 */
package manageuser.validates;

import java.util.ArrayList;
import java.util.List;

import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Common;

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
		String errorMsg = "";
		// username
		errorMsg = Common.validateUsername(userInfor.getLoginName());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		// group
		errorMsg = Common.validateGroup(userInfor.getGroupId());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		// fullname
		errorMsg = Common.validateFullname(userInfor.getFullName());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		// fullnameKana
		errorMsg = Common.validateFullnameKana(userInfor.getFullNameKana());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		// birthday x
		errorMsg = Common.validateBirthday(userInfor.getYearBirthday(), userInfor.getMonthBirthday(),
				userInfor.getDayBirthday());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		//
		// email
		errorMsg = Common.validateEmail(userInfor.getEmail());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		// tel
		errorMsg = Common.validateTel(userInfor.getTel());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		// password
		errorMsg = Common.validatePass(userInfor.getPasswords());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		// confirmPass
		errorMsg = Common.validatePassConfirm(userInfor.getPasswords(), userInfor.getConfirmPassword());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		// codeLevel
		errorMsg = Common.validateCodeLevel(userInfor.getCodeLevel());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		//
		// // date of issue x
		errorMsg = Common.validateStartDate(userInfor.getYearStartDate(), userInfor.getMonthStartDate(),
				userInfor.getDayStartDate());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		//
		// // expiration date x
		errorMsg = Common.validateEndDate(userInfor.getYearEndDate(), userInfor.getMonthEndDate(),
				userInfor.getDayEndDate());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		//
		// total
		errorMsg = Common.validateTotal(userInfor.getTotal(), userInfor.getCodeLevel());
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
		}
		return lstError;
	}

}
