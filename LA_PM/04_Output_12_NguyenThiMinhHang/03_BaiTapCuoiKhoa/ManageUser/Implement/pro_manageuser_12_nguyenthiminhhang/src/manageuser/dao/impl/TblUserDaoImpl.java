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

/**
 * Implement Thao tác với bảng TblUser trong DB
 * 
 * @author minhhang
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {
	private List<TblUser> listUser;
	private List<UserInfor> listUserInfor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getSalt(java.lang.String)
	 */
	@Override
	public String getSalt(String username) {
		String salt = "";
		listUser = new ArrayList<>();
		String query = "SELECT salt FROM tbl_user WHERE login_name = ? AND role = 1 ;";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			while (resultSet.next()) {
				salt = resultSet.getString("salt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			int index = 0;
			preparedStatement.setString(index++, username);
			preparedStatement.setString(index++, password);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			if (!resultSet.first()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		StringBuilder fullnameQuery;
		StringBuilder groupQuery;

		if (groupId > 0) {
			groupQuery = new StringBuilder(" AND gr.group_id = ").append("?");
		} else {
			groupQuery = new StringBuilder("");
		}
		if (!"".equals(fullName)) {
			fullnameQuery = new StringBuilder(" AND us.full_name LIKE ").append("?");
		} else {
			fullnameQuery = new StringBuilder("");
		}

		listUserInfor = new ArrayList<>();
		StringBuilder query = new StringBuilder("");
		query.append(
				"SELECT us.user_id, us.full_name, us.email, us.tel, us.birthday, gr.group_name, jp.name_level, dt.end_date, dt.total")
				.append(" FROM (tbl_user us INNER JOIN mst_group gr ON us.group_id = gr.group_id )")
				.append(" LEFT JOIN (tbl_detail_user_japan dt INNER JOIN mst_japan jp ON dt.code_level = jp.code_level)")
				.append(" ON us.user_id = dt.user_id WHERE us.role = 0").append(groupQuery).append(fullnameQuery)
				.append(";");
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
			int index = 0;
			if (groupId > 0) {
				index++;
				preparedStatement.setInt(index, groupId);
			}
			if (!"".equals(fullName)) {
				index++;
				fullName = Common.standardString(fullName);
				preparedStatement.setString(index, "%" + fullName + "%");
			}
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
				userInfor.setTotal(resultSet.getInt("total"));
				listUserInfor.add(userInfor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public int getTotalUsers(int groupId, String fullname) {
		int total = 0;
		String query = "SELECT COUNT(user_id) AS total FROM tbl_user WHERE role = 0;";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			if (resultSet.first()) {
				total = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return total;
	}
}
