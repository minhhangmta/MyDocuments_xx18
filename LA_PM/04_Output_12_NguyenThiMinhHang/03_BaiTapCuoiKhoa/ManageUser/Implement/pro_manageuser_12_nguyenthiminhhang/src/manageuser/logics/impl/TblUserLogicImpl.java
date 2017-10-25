/**
 * Copyright(C) 2017 Luvina
 * TblUserLogicImpl.java Oct 20, 2017 minhhang
 */
package manageuser.logics.impl;

import java.util.ArrayList;
import java.util.List;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.UserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;

/**
 * Implement TblUserLogic để xử lý logic lấy từ TblUserDao
 * 
 * @author minhhang
 */
public class TblUserLogicImpl implements TblUserLogic {
	private TblUserDaoImpl userDaoImpl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existLogin(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean existLogin(String username, String password) {
		// Khởi tạo lớp TblUserDaoImpl
		userDaoImpl = new TblUserDaoImpl();
		// Khởi tạo lớp Common
		Common common = new Common();
		// Lấy salt từ DB
		String salt = userDaoImpl.getSalt(username);
		// Mã hóa password = SHA-1
		password = common.encodeSHA1(password, salt);
		// Nếu user tồn tại trong DB
		if (userDaoImpl.existLogin(username, password)) {
			return true;
		} // ngược lại
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getListUser(int, int, int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ArrayList<UserInfor> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) {
		ArrayList<UserInfor> listUserInfor = new ArrayList<>();
		userDaoImpl = new TblUserDaoImpl();
		listUserInfor = userDaoImpl.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName,
				sortByCodeLevel, sortByEndDate);
		for (UserInfor user : listUserInfor) {
			System.out.println(user.getEmail());
		}
		return listUserInfor;
	}
}
