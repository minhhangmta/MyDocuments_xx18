/**
 * Copyright(C) 2017 Luvina
 * TblUserLogicImpl.java Oct 20, 2017 minhhang
 */
package manageuser.logics.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import manageuser.dao.impl.BaseDaoImpl;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;

/**
 * Xử lý logic liên quan đến thông tin của user
 * 
 * @author minhhang
 */
public class TblUserLogicImpl implements TblUserLogic {
	private TblUserDaoImpl userDaoImpl = new TblUserDaoImpl();
	private TblDetailUserJapanDaoImpl detailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();

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
		// Nếu user là admin tồn tại trong DB
		if (userDaoImpl.existLogin(username, password)) {
			return true;
		}
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
		List<UserInfor> list = userDaoImpl.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName,
				sortByCodeLevel, sortByEndDate);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) {
		int result = userDaoImpl.getTotalUsers(groupId, fullName);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existUsername(java.lang.String)
	 */
	@Override
	public boolean existUsername(String username) {
		boolean result = userDaoImpl.existUsername(username);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existEmail(java.lang.String, int)
	 */
	@Override
	public boolean existEmail(String email, int userId) {
		boolean result = userDaoImpl.existEmail(email, userId);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existCodeLevel(java.lang.String)
	 */
	@Override
	public boolean existCodeLevel(String codeLevel) {
		boolean result = userDaoImpl.existCodeLevel(codeLevel);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#insertUser(manageuser.entities.TblUser)
	 */
	@Override
	public int insertUser(TblUser tblUser) throws SQLException {
		int result = userDaoImpl.insertUser(tblUser);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.logics.TblUserLogic#insertDetailUserJapan(manageuser.entities.
	 * TblDetailUserJapan)
	 */
	@Override
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException {
		boolean result = detailUserJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapan);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#createUser(manageuser.entities.UserInfor)
	 */
	@Override
	public boolean createUser(UserInfor userInfor) throws SQLException {
		BaseDaoImpl baseDaoImpl = new BaseDaoImpl();
		Connection connection = baseDaoImpl.getConnection();
		TblDetailUserJapanDaoImpl detailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl(connection);
		TblUserDaoImpl userDaoImpl = new TblUserDaoImpl(connection);
		// get tblUser
		TblUser tblUser = Common.getTblUserFromUserInfor(userInfor);
		try {
			baseDaoImpl.setAutoCommitFalse(connection);
			// get userId from TblUser
			int userId = userDaoImpl.insertUser(tblUser);
			// get codelevel
			String codeLevel = userInfor.getCodeLevel();
			// user id tồn tại và có code level
			if (!Common.isEmptyOrNull(codeLevel) && userId > 0) {
				userInfor.setUserId(userId);
				TblDetailUserJapan detailUserJapan = Common.getDetailUserJapanFromUserInfor(userInfor);
				detailUserJapanDaoImpl.insertDetailUserJapan(detailUserJapan);
			}
			baseDaoImpl.commitConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			baseDaoImpl.rollBack(connection);
			return false;
		} finally {
			baseDaoImpl.closeConnection(connection);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getUserById(int)
	 */
	@Override
	public UserInfor getUserById(int userId) {
		UserInfor result = userDaoImpl.getUserById(userId);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existUserById(int)
	 */
	@Override
	public boolean existUserById(int userId) {
		boolean result = userDaoImpl.existUserById(userId);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.logics.TblUserLogic#updateUserInfor(manageuser.entities.UserInfor)
	 */
	public boolean updateUserInfor(UserInfor userInfor) throws SQLException {
		BaseDaoImpl baseDaoImpl = new BaseDaoImpl();
		Connection connection = baseDaoImpl.getConnection();
		TblUserDaoImpl userDaoImpl = new TblUserDaoImpl(connection);
		TblDetailUserJapanDaoImpl detailJapanDaoImpl = new TblDetailUserJapanDaoImpl(connection);

		int userId = userInfor.getUserId();
		TblUser tblUser = Common.getTblUserFromUserInfor(userInfor);

		String codeLevelNew = userInfor.getCodeLevel();
		String codeLevelOld = detailJapanDaoImpl.getCodeLevelById(userId);
		try {
			baseDaoImpl.setAutoCommitFalse(connection);
			userDaoImpl.updateUser(tblUser);
			// Trường hợp code level mới có giá trị
			if (!Common.isEmptyOrNull(codeLevelNew)) {
				TblDetailUserJapan tblDetailUserJapan = Common.getDetailUserJapanFromUserInfor(userInfor);
				if (!codeLevelOld.isEmpty()) {// Có -> có
					detailJapanDaoImpl.updateDetailJapan(tblDetailUserJapan);
				} else {// không -> có
					detailJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapan);
				}
			} else if (!codeLevelOld.isEmpty()) {// Trường hợp code level mới không có( có -> không)
				detailJapanDaoImpl.deleteDetailJapan(userId);
			}
			baseDaoImpl.commitConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			baseDaoImpl.rollBack(connection);
			return false;
		} finally {
			baseDaoImpl.closeConnection(connection);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#updatePass(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public boolean updatePass(String passwords, String salt, int userId) {
		passwords = Common.encodeSHA1(passwords, salt);
		boolean result = userDaoImpl.updatePass(passwords, salt, userId);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#deleteUser(int)
	 */
	@Override
	public boolean deleteUser(int userId) throws SQLException {
		BaseDaoImpl baseDaoImpl = new BaseDaoImpl();
		Connection connection = baseDaoImpl.getConnection();
		TblUserDaoImpl userDaoImpl = new TblUserDaoImpl(connection);
		TblDetailUserJapanDaoImpl detailJapanDaoImpl = new TblDetailUserJapanDaoImpl(connection);
		try {
			baseDaoImpl.setAutoCommitFalse(connection);
			// xoa detail japan
			detailJapanDaoImpl.deleteDetailJapan(userId);
			// xoa user
			userDaoImpl.deleteUser(userId);
			baseDaoImpl.commitConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			baseDaoImpl.rollBack(connection);
			return false;
		} finally {
			baseDaoImpl.closeConnection(connection);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getListUsers(int, java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUsers(int groupId, String fullName) {
		List<UserInfor> result = userDaoImpl.getListUsers(groupId, fullName);
		return result;
	}

}
