/**
 * Copyright(C) 2017 Luvina
 * TblUserLogicImpl.java Oct 20, 2017 minhhang
 */
package manageuser.logics.impl;

import java.sql.Connection;
import java.util.List;

import manageuser.dao.impl.BaseDaoImpl;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Xử lý logic liên quan đến thông tin của user
 * 
 * @author minhhang
 */
public class TblUserLogicImpl implements TblUserLogic {
	Connection connection = new BaseDaoImpl().getConnection();
	private TblUserDaoImpl userDaoImpl = new TblUserDaoImpl(connection);
	private TblDetailUserJapanDaoImpl detailJapanDaoImpl = new TblDetailUserJapanDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existLogin(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean existLogin(String username, String password) {
		// Lấy salt từ DB
		String salt = userDaoImpl.getSalt(username);
		// Mã hóa password = SHA-1
		password = Common.encodeSHA1(password, salt);
		// Nếu user tồn tại trong DB
		if (userDaoImpl.existLogin(username, password)) {
			return true;
		} // ngược lại
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getListUsers(int, int, int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) {
		return (userDaoImpl.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel,
				sortByEndDate));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) {
		return userDaoImpl.getTotalUsers(groupId, fullName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existUsername(java.lang.String)
	 */
	@Override
	public boolean existUsername(String username) {
		if (userDaoImpl.existUsername(username)) {
			return true;
		} // ngược lại
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existEmail(java.lang.String)
	 */
	@Override
	public boolean existEmail(String email) {
		return userDaoImpl.existEmail(email);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existCodeLevel(java.lang.String)
	 */
	@Override
	public boolean existCodeLevel(String codeLevel) {
		return userDaoImpl.existCodeLevel(codeLevel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#insertUser(manageuser.entities.TblUser)
	 */
	@Override
	public int insertUser(TblUser tblUser) {
		return userDaoImpl.insertUser(tblUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.logics.TblUserLogic#insertDetailUserJapan(manageuser.entities.
	 * TblDetailUserJapan)
	 */
	@Override
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) {
		return detailJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapan);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#createUser(manageuser.entities.UserInfor)
	 */
	@Override
	public boolean createUser(UserInfor userInfor) {
		boolean check = false;
		String fullNameKana = userInfor.getFullNameKana();
		// insert vao tbl_user
		TblUser tblUser = new TblUser();
		tblUser.setLoginName(userInfor.getLoginName());
		tblUser.setEmail(userInfor.getEmail());
		tblUser.setBirthday(userInfor.getBirthday());
		tblUser.setFullName(userInfor.getFullName());
		if (!fullNameKana.isEmpty()) {
			tblUser.setFullNameKana(fullNameKana);
		}
		tblUser.setGroupId(userInfor.getGroupId());
		// get salt from createSaltString()
		tblUser.setSalt(Common.createSaltString());
		// ma hoa password
		tblUser.setPasswords(Common.encodeSHA1(userInfor.getPasswords(), tblUser.getSalt()));
		tblUser.setTel(userInfor.getTel());
		tblUser.setRole(Constant.ROLE_USER);
		// get userId from TblUser
		int userId = insertUser(tblUser);
		if (userId == Constant.DEFAULT_INT) {
			return false;
		}
		// insert vao detail_japan (neu co)
		String codeLevel = userInfor.getCodeLevel();
		if (!codeLevel.isEmpty()) {
			TblDetailUserJapan detailUserJapan = new TblDetailUserJapan();
			detailUserJapan.setCodeLevel(codeLevel);
			detailUserJapan.setUserId(userId);
			detailUserJapan.setStartDate(userInfor.getStartDate());
			detailUserJapan.setEndDate(userInfor.getEndDate());
			detailUserJapan.setTotal(userInfor.getTotal());
			check = insertDetailUserJapan(detailUserJapan);
		}
		if (!check) {
			return false;
		}
		return true;
	}

}
