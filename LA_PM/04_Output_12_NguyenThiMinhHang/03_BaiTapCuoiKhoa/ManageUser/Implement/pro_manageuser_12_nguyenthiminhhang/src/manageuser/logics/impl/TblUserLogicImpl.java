/**
 * Copyright(C) 2017 Luvina
 * TblUserLogicImpl.java Oct 20, 2017 minhhang
 */
package manageuser.logics.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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
	BaseDaoImpl baseDaoImpl = new BaseDaoImpl();
	Connection connection = baseDaoImpl.getConnection();
	private TblUserDaoImpl userDaoImpl = new TblUserDaoImpl(connection);
	private TblDetailUserJapanDaoImpl detailJapanDaoImpl = new TblDetailUserJapanDaoImpl(connection);

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
	 * @see manageuser.logics.TblUserLogic#existEmail(java.lang.String, int)
	 */
	@Override
	public boolean existEmail(String email, int userId) {
		return userDaoImpl.existEmail(email, userId);
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
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException {
		return detailJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapan);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#createUser(manageuser.entities.UserInfor)
	 */
	@Override
	public boolean createUser(UserInfor userInfor) throws SQLException {
		String fullNameKana = userInfor.getFullNameKana();
		// toi uu lai
		Date birthday = Common.toDate(userInfor.getYearBirthday(), userInfor.getMonthBirthday(),
				userInfor.getDayBirthday());
		Date startDate = Common.toDate(userInfor.getYearStartDate(), userInfor.getMonthStartDate(),
				userInfor.getDayStartDate());
		Date endDate = Common.toDate(userInfor.getYearEndDate(), userInfor.getMonthEndDate(),
				userInfor.getDayEndDate());
		// insert vao tbl_user
		TblUser tblUser = new TblUser();
		tblUser.setLoginName(userInfor.getLoginName());
		tblUser.setEmail(userInfor.getEmail());
		tblUser.setBirthday(birthday);
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
		try {
			connection.setAutoCommit(false);
			// get userId from TblUser
			int userId = insertUser(tblUser);
			if (userId == Constant.DEFAULT_INT) {
				return false;
			}
			// insert vao detail_japan (neu co)
			String codeLevel = userInfor.getCodeLevel();
			if (codeLevel != null && userId > 0) {
				TblDetailUserJapan detailUserJapan = new TblDetailUserJapan();
				detailUserJapan.setCodeLevel(codeLevel);
				detailUserJapan.setUserId(userId);
				detailUserJapan.setStartDate(startDate);
				detailUserJapan.setEndDate(endDate);
				detailUserJapan.setTotal(userInfor.getTotal());
				insertDetailUserJapan(detailUserJapan);
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			baseDaoImpl.closeConnection();
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
		return userDaoImpl.getUserById(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#existUserById(int)
	 */
	@Override
	public boolean existUserById(int userId) {
		return userDaoImpl.existUserById(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.logics.TblUserLogic#updateUserInfor(manageuser.entities.UserInfor)
	 */
	public boolean updateUserInfor(UserInfor userInfor) throws SQLException {
		TblUser tblUser = new TblUser();
		int userId = userInfor.getUserId();
		String fullNameKana = userInfor.getFullNameKana();
		if (fullNameKana.isEmpty()) {
			fullNameKana = null;
		}
		tblUser.setUserId(userId);
		tblUser.setFullName(userInfor.getFullName());
		tblUser.setFullNameKana(fullNameKana);
		tblUser.setEmail(userInfor.getEmail());
		tblUser.setTel(userInfor.getTel());
		tblUser.setBirthday(userInfor.getBirthday());
		tblUser.setGroupId(userInfor.getGroupId());

		String codeLevelNew = userInfor.getCodeLevel();
		try {
			connection.setAutoCommit(false);
			userDaoImpl.updateUser(tblUser);
			String codeLevelOld = detailJapanDaoImpl.getCodeLevelById(userId);

			// Từ DB ra thì xét null, từ textbox thì xét empty
			// Trường hợp code level mới có giá trị
			if (codeLevelNew != null) {
				TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
				tblDetailUserJapan.setUserId(userId);
				tblDetailUserJapan.setCodeLevel(codeLevelNew);
				tblDetailUserJapan.setStartDate(userInfor.getStartDate());
				tblDetailUserJapan.setEndDate(userInfor.getEndDate());
				tblDetailUserJapan.setTotal(userInfor.getTotal());
				if (!codeLevelOld.isEmpty()) {// Có -> có
					detailJapanDaoImpl.updateDetailJapan(tblDetailUserJapan);
				} else {// không -> có
					detailJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapan);
				}
			} else if (!codeLevelOld.isEmpty()) {// Trường hợp code level mới không có( có -> không)
				detailJapanDaoImpl.deleteDetailJapan(userId);
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			baseDaoImpl.closeConnection();
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
		return userDaoImpl.updatePass(passwords, salt, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#deleteUser(int)
	 */
	@Override
	public boolean deleteUser(int userId) throws SQLException {
		try {
			connection.setAutoCommit(false);
			// xoa detail japan
			detailJapanDaoImpl.deleteDetailJapan(userId);
			// xoa user
			userDaoImpl.deleteUser(userId);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			baseDaoImpl.closeConnection();
		}
		return true;
	}

}
