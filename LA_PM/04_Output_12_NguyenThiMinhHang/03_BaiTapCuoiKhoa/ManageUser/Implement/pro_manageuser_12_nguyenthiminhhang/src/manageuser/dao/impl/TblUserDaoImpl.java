/**
 * Copyright(C) 2017 Luvina
 * TblUserDaoImpl.java Oct 20, 2017 minhhang
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Implement Thao tác với bảng TblUser trong DB
 * 
 * @author minhhang
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {

	/**
	 * @param connection
	 */
	public TblUserDaoImpl(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 
	 */
	public TblUserDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getSalt(java.lang.String)
	 */
	@Override
	public String getSalt(String username) {
		String salt = "";
		String query = "SELECT salt FROM tbl_user WHERE login_name = ?";
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, username);
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				while (resultSet.next()) {
					salt = resultSet.getString("salt");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return salt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#existLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existLogin(String username, String password) {
		String query = "SELECT * FROM tbl_user WHERE login_name = ? AND passwords = ? AND role = 1;";
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				int index = 1;
				preparedStatement.setString(index++, username);
				preparedStatement.setString(index++, password);
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				if (!resultSet.first()) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getListUsers(int, int, int, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) {
		List<UserInfor> listUserInfor = new ArrayList<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT us.user_id, us.full_name, us.email, us.tel, us.birthday,")
				.append(" gr.group_name, jp.name_level, dt.end_date, dt.total")
				.append(" FROM (tbl_user us INNER JOIN mst_group gr ON us.group_id = gr.group_id )")
				.append(" LEFT JOIN (tbl_detail_user_japan dt INNER JOIN mst_japan jp")
				.append(" ON dt.code_level = jp.code_level)").append(" ON us.user_id = dt.user_id WHERE us.role = 0");
		// Nếu groupId có giá trị
		if (groupId > 0) {
			query.append(" AND gr.group_id = ?");
		}
		query.append(" AND us.full_name LIKE ?");

		if (Constant.CODE_LEVEL.equals(sortType)) {
			query.append(" ORDER BY ");
			query.append("dt.").append(Constant.CODE_LEVEL).append(" ").append(sortByCodeLevel);
			query.append(", us.").append(Constant.FULL_NAME).append(" ").append(sortByFullName);
			query.append(", dt.").append(Constant.END_DATE).append(" ").append(sortByEndDate);
		} else if (Constant.END_DATE.equals(sortType)) {
			query.append(" ORDER BY ");
			query.append("dt.").append(Constant.END_DATE).append(" ").append(sortByEndDate);
			query.append(", us.").append(Constant.FULL_NAME).append(" ").append(sortByFullName);
			query.append(", dt.").append(Constant.CODE_LEVEL).append(" ").append(sortByCodeLevel);
		} else {
			query.append(" ORDER BY ");
			query.append("us.").append(Constant.FULL_NAME).append(" ").append(sortByFullName);
			query.append(", dt.").append(Constant.CODE_LEVEL).append(" ").append(sortByCodeLevel);
			query.append(", dt.").append(Constant.END_DATE).append(" ").append(sortByEndDate);
		}
		query.append(" LIMIT ?,?");
		query.append(";");
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				int index = 1;
				if (groupId > 0) {
					preparedStatement.setInt(index++, groupId);
				}
				fullName = Common.standardString(fullName);
				preparedStatement.setString(index++, "%" + fullName + "%");

				preparedStatement.setInt(index++, offset);
				preparedStatement.setInt(index++, limit);

				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				while (resultSet.next()) {
					UserInfor userInfor = new UserInfor();
					userInfor.setUserId(resultSet.getInt("user_id"));
					userInfor.setFullName(resultSet.getString("full_name"));
					userInfor.setEmail(resultSet.getString("email"));
					userInfor.setTel(resultSet.getString("tel"));
					userInfor.setBirthday(resultSet.getDate("birthday"));
					userInfor.setGroupName(resultSet.getString("group_name"));
					userInfor.setNameLevel(resultSet.getString("name_level"));
					userInfor.setEndDate(resultSet.getDate("end_date"));
					userInfor.setTotal(resultSet.getString("total"));
					listUserInfor.add(userInfor);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listUserInfor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) {
		int total = 0;
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(user_id) AS total FROM tbl_user us")
				.append(" INNER JOIN mst_group gr ON us.group_id = gr.group_id").append(" WHERE role = 0");
		if (groupId > 0) {
			query.append(" AND us.group_id = ?");
		}
		if (!fullName.isEmpty()) {
			query.append(" AND us.full_name LIKE ?");
		}
		query.append(";");
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				int index = 1;
				if (groupId > 0) {
					preparedStatement.setInt(index++, groupId);
				}
				if (!fullName.isEmpty()) {
					fullName = Common.standardString(fullName);
					preparedStatement.setString(index++, "%" + fullName + "%");
				}
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				if (resultSet.first()) {
					total = resultSet.getInt("total");
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#existUsername(java.lang.String)
	 */
	@Override
	public boolean existUsername(String username) {
		String query = "SELECT login_name FROM tbl_user WHERE login_name = ?";
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, username);
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				if (!resultSet.first()) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#existEmail(java.lang.String, int)
	 */
	public boolean existEmail(String email, int userId) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT email FROM tbl_user WHERE email = ?");
		if (userId > 0) {
			query.append(" AND user_id != ?");
		}
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				int index = 1;
				preparedStatement.setString(index++, email);
				if (userId > 0) {
					preparedStatement.setInt(index++, userId);
				}
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				if (!resultSet.first()) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#existCodeLevel(java.lang.String)
	 */
	@Override
	public boolean existCodeLevel(String codeLevel) {
		String query = "SELECT code_level FROM mst_japan WHERE code_level = ?";
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, codeLevel);
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				if (!resultSet.first()) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#insertUser(manageuser.entities.TblUser)
	 */
	@Override
	public int insertUser(TblUser tblUser) throws SQLException {
		int userId = Constant.DEFAULT_INT;
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO tbl_user ").append("(group_id, login_name, passwords, full_name, full_name_kana, ")
				.append("email, tel, birthday, salt, role) ").append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ? )")
				.append(";");
		if (connection != null) {
			PreparedStatement preparedStatement = connection.prepareStatement(query.toString(),
					PreparedStatement.RETURN_GENERATED_KEYS);
			int index = 1;
			preparedStatement.setInt(index++, tblUser.getGroupId());
			preparedStatement.setString(index++, tblUser.getLoginName());
			preparedStatement.setString(index++, tblUser.getPasswords());
			preparedStatement.setString(index++, tblUser.getFullName());
			preparedStatement.setString(index++, tblUser.getFullNameKana());
			preparedStatement.setString(index++, tblUser.getEmail());
			preparedStatement.setString(index++, tblUser.getTel());
			preparedStatement.setString(index++, Common.convertDateToString(tblUser.getBirthday()));
			preparedStatement.setString(index++, tblUser.getSalt());
			preparedStatement.setInt(index++, tblUser.getRole());
			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.last()) {
				userId = resultSet.getInt(1);
			}
		}
		return userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getUserById(int)
	 */
	@Override
	public UserInfor getUserById(int userId) {
		UserInfor userInfor = new UserInfor();
		StringBuilder query = new StringBuilder();
		query.append("SELECT us.login_name, us.full_name, us.email, us.tel, us.birthday, us.full_name_kana,").append(
				" gr.group_name, gr.group_id, jp.name_level, jp.code_level, dt.start_date, dt.end_date, dt.total")
				.append(" FROM (tbl_user us INNER JOIN mst_group gr ON us.group_id = gr.group_id )")
				.append(" LEFT JOIN (tbl_detail_user_japan dt INNER JOIN mst_japan jp")
				.append(" ON dt.code_level = jp.code_level)").append(" ON us.user_id = dt.user_id WHERE us.role = 0")
				.append(" AND us.user_id = ?").append(";");

		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				preparedStatement.setInt(1, userId);
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();

				while (resultSet.next()) {
					userInfor.setLoginName(resultSet.getString("login_name"));
					userInfor.setFullName(resultSet.getString("full_name"));
					userInfor.setFullNameKana(resultSet.getString("full_name_kana"));
					userInfor.setEmail(resultSet.getString("email"));
					userInfor.setTel(resultSet.getString("tel"));
					userInfor.setBirthday(resultSet.getDate("birthday"));
					userInfor.setGroupName(resultSet.getString("group_name"));
					userInfor.setGroupId(resultSet.getInt("group_id"));
					userInfor.setCodeLevel(resultSet.getString("code_level"));
					userInfor.setNameLevel(resultSet.getString("name_level"));
					userInfor.setStartDate(resultSet.getDate("start_date"));
					userInfor.setEndDate(resultSet.getDate("end_date"));
					userInfor.setTotal(resultSet.getString("total"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return userInfor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#existUserById(int)
	 */
	public boolean existUserById(int userId) {
		String query = "SELECT login_name FROM tbl_user WHERE user_id = ? AND role = ?";
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				int index = 1;
				preparedStatement.setInt(index++, userId);
				preparedStatement.setInt(index++, Constant.ROLE_USER);
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				if (!resultSet.first()) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#updateUser(manageuser.entities.TblUser)
	 */
	@Override
	public boolean updateUser(TblUser tblUser) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append(
				"UPDATE tbl_user SET group_id = ?, full_name = ?, full_name_kana = ?, email = ?, tel = ?, birthday =?")
				.append(" WHERE user_id = ?").append(";");
		if (connection != null) {
			PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, tblUser.getGroupId());
			preparedStatement.setString(index++, tblUser.getFullName());
			preparedStatement.setString(index++, tblUser.getFullNameKana());
			preparedStatement.setString(index++, tblUser.getEmail());
			preparedStatement.setString(index++, tblUser.getTel());
			preparedStatement.setString(index++, Common.convertDateToString(tblUser.getBirthday()));
			preparedStatement.setInt(index++, tblUser.getUserId());
			int row = preparedStatement.executeUpdate();
			if (row == 0) {
				return false;
			}
		}
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#updatePass(java.lang.String, java.lang.String,
	 * int)
	 */
	@Override
	public boolean updatePass(String passwords, String salt, int userId) {
		String query = "UPDATE tbl_user SET passwords = ?, salt = ? WHERE user_id = ?";
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				int index = 1;
				preparedStatement.setString(index++, passwords);
				preparedStatement.setString(index++, salt);
				preparedStatement.setInt(index++, userId);
				int row = preparedStatement.executeUpdate();
				if (row == 0) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#deleteUser(int)
	 */
	@Override
	public boolean deleteUser(int userId) throws SQLException {
		String query = "DELETE FROM tbl_user WHERE user_id = ? ";
		if (connection != null) {
			PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, userId);
			int row = preparedStatement.executeUpdate();
			if (row == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getListUsers(int, java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUsers(int groupId, String fullName) {
		List<UserInfor> list = new ArrayList<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT us.user_id, us.login_name, us.full_name, us.email, us.tel, us.birthday,")
				.append(" gr.group_name, jp.code_level, jp.name_level, dt.end_date, dt.total")
				.append(" FROM (tbl_user us INNER JOIN mst_group gr ON us.group_id = gr.group_id )")
				.append(" LEFT JOIN (tbl_detail_user_japan dt INNER JOIN mst_japan jp")
				.append(" ON dt.code_level = jp.code_level)").append(" ON us.user_id = dt.user_id WHERE us.role = 0");
		if (groupId > 0) {
			query.append(" AND us.group_id = ?");
		}
		if (!fullName.isEmpty()) {
			query.append(" AND us.full_name LIKE ?");
		}
		query.append(";");
		try {
			Connection connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				int index = 1;
				if (groupId > 0) {
					preparedStatement.setInt(index++, groupId);
				}
				if (!fullName.isEmpty()) {
					fullName = Common.standardString(fullName);
					preparedStatement.setString(index++, "%" + fullName + "%");
				}
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				while (resultSet.next()) {
					UserInfor userInfor = new UserInfor();
					userInfor.setUserId(resultSet.getInt("user_id"));
					userInfor.setLoginName(resultSet.getString("login_name"));
					userInfor.setFullName(resultSet.getString("full_name"));
					userInfor.setEmail(resultSet.getString("email"));
					userInfor.setTel(resultSet.getString("tel"));
					userInfor.setBirthday(resultSet.getDate("birthday"));
					userInfor.setGroupName(resultSet.getString("group_name"));
					userInfor.setCodeLevel(resultSet.getString("code_level"));
					userInfor.setNameLevel(resultSet.getString("name_level"));
					userInfor.setEndDate(resultSet.getDate("end_date"));
					userInfor.setTotal(resultSet.getString("total"));
					list.add(userInfor);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return list;
	}
}
