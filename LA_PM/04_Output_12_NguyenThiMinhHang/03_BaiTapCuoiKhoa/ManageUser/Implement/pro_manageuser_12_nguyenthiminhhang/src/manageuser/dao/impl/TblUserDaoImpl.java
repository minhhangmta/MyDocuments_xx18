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
	public Connection connection = getConnection();

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
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			while (resultSet.next()) {
				salt = resultSet.getString("salt");
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
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			int index = 1;
			preparedStatement.setString(index++, username);
			preparedStatement.setString(index++, password);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			if (!resultSet.first()) {
				return false;
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
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			if (!resultSet.first()) {
				return false;
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
	 * @see manageuser.dao.TblUserDao#existEmail(java.lang.String)
	 */
	public boolean existEmail(String email) {
		String query = "SELECT email FROM tbl_user WHERE email = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			if (!resultSet.first()) {
				return false;
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
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, codeLevel);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			if (!resultSet.first()) {
				return false;
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
	public int insertUser(TblUser tblUser) {
		int userId = Constant.DEFAULT_INT;
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO tbl_user ").append("(group_id, login_name, passwords, full_name, full_name_kana, ")
				.append("email, tel, birthday, salt, role) ").append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ? )")
				.append(";");
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return userId;
	}

}
