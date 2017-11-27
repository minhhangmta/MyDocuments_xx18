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
		// Không nhập username và pass
		if (!Common.checkPressTxt(username) && !Common.checkPressTxt(password)) {
			errMassages.add(MessageErrorProperties.getData("ER001_USERNAME"));
			errMassages.add(MessageErrorProperties.getData("ER001_PASS"));
		} else if (!Common.checkPressTxt(username)) {// không nhập username
			errMassages.add(MessageErrorProperties.getData("ER001_USERNAME"));
		} else if (!Common.checkPressTxt(password)) {// không nhập pass
			errMassages.add(MessageErrorProperties.getData("ER001_PASS"));
		} else {
			// không tồn tại trong DB
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
		int userId = userInfor.getUserId();
		if (userId <= 0) {
			// username
			errorMsg = Common.validateUsername(userInfor.getLoginName());
			// add lỗi nếu có
			Common.checkErrorString(lstError, errorMsg);
		}
		// group
		errorMsg = Common.validateGroup(userInfor.getGroupId());
		Common.checkErrorString(lstError, errorMsg);
		// fullname
		errorMsg = Common.validateFullname(userInfor.getFullName());
		Common.checkErrorString(lstError, errorMsg);
		// fullnameKana
		String fullNameKana = userInfor.getFullNameKana();
		if (fullNameKana != null) {
			errorMsg = Common.validateFullnameKana(fullNameKana);
			Common.checkErrorString(lstError, errorMsg);
		}
		// birthday
		errorMsg = Common.validateBirthday(userInfor.getYearBirthday(), userInfor.getMonthBirthday(),
				userInfor.getDayBirthday());
		Common.checkErrorString(lstError, errorMsg);
		// email
		errorMsg = Common.validateEmail(userInfor.getEmail(), userId);
		Common.checkErrorString(lstError, errorMsg);
		// tel
		errorMsg = Common.validateTel(userInfor.getTel());
		Common.checkErrorString(lstError, errorMsg);
		// truong hop khac edit
		if (userId <= 0) {
			// password
			errorMsg = Common.validatePass(userInfor.getPasswords());
			// trường hợp password đúng thì mới check đến confirm
			if (!Common.checkErrorString(lstError, errorMsg)) {
				// confirmPass
				errorMsg = Common.validatePassConfirm(userInfor.getPasswords(), userInfor.getConfirmPassword());
				Common.checkErrorString(lstError, errorMsg);
			}
		}
		String codeLevel = userInfor.getCodeLevel();
		// codeLevel
		if (codeLevel != null) {
			errorMsg = Common.validateCodeLevel(codeLevel);
			Common.checkErrorString(lstError, errorMsg);
			if (!codeLevel.isEmpty()) {
				// date of issue
				errorMsg = Common.validateStartDate(userInfor.getYearStartDate(), userInfor.getMonthStartDate(),
						userInfor.getDayStartDate());
				Common.checkErrorString(lstError, errorMsg);
				// expiration date
				errorMsg = Common.validateEndDate(userInfor.getYearStartDate(), userInfor.getMonthStartDate(),
						userInfor.getDayStartDate(), userInfor.getYearEndDate(), userInfor.getMonthEndDate(),
						userInfor.getDayEndDate());
				Common.checkErrorString(lstError, errorMsg);
				// total
				errorMsg = Common.validateTotal(userInfor.getTotal(), userInfor.getCodeLevel());
				Common.checkErrorString(lstError, errorMsg);
			}
		}
		return lstError;
	}

	/**
	 * Hàm validate password
	 * 
	 * @param password
	 *            mật khẩu
	 * @param confirmPassword
	 *            xác nhận mật khẩu
	 * @return List<String> list thông báo lỗi
	 */
	public List<String> validatePasswords(String password, String confirmPassword) {
		List<String> lstError = new ArrayList<>();
		String errorMsg = "";
		// password
		errorMsg = Common.validatePass(password);
		// trường hợp password đúng thì mới check confirm
		if (!Common.checkErrorString(lstError, errorMsg)) {
			// confirmPass
			errorMsg = Common.validatePassConfirm(password, confirmPassword);
			Common.checkErrorString(lstError, errorMsg);
		}
		return lstError;
	}
}
