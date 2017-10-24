/**
 * Copyright(C) 2017 Luvina
 * TblUserLogicImpl.java Oct 20, 2017 minhhang
 */
package manageuser.logics.impl;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;

/**
 * Implement TblUserLogic để xử lý logic lấy từ TblUserDao
 * 
 * @author minhhang
 */
public class TblUserLogicImpl implements TblUserLogic {
	
	/* (non-Javadoc)
	 * @see manageuser.logics.TblUserLogic#existLogin(java.lang.String, java.lang.String)
	 */
	public boolean existLogin(String username, String password) {
		//Khởi tạo lớp TblUserDaoImpl 
		TblUserDaoImpl userDaoImpl = new TblUserDaoImpl();
		//Khởi tạo lớp Common
		Common common = new Common();
		//Lấy salt từ DB
		String salt = userDaoImpl.getSalt(username);
		//Mã hóa password = SHA-1
		password = common.encodeSHA1(password, salt);
		//Nếu user tồn tại trong DB
		if (userDaoImpl.existLogin(username, password)) {
			return true;
		}//ngược lại
		return false;
	}
}
