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

/**
 * Implement TblUserDao để Thao tác với DB của các chức năng của TblUser
 * 
 * @author minhhang
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {
	private ArrayList<TblUser> listUser;
	private ArrayList<UserInfor> listUserInfor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getSalt(java.lang.String)
	 */
	@Override
	public String getSalt(String username) {
		String salt = "";
		listUser = new ArrayList<>();
		String query = "SELECT salt FROM tbl_user WHERE login_name = ? and role = 1 ;";
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
		String query = "SELECT * FROM tbl_user WHERE login_name = ? and passwords = ? and role = 1;";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
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
	 * @see manageuser.dao.TblUserDao#getListUser(int, int, int, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<UserInfor> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) {
		listUserInfor = new ArrayList<>();
		String query = "SELECT us.user_id, " + "us.full_name, " + "us.email, " + "us.tel, " + "us.birthday, "
				+ "gr.group_name, " + "jp.name_level , " + "dt.end_date," + "dt.total"
				+ " FROM (tbl_user us INNER JOIN mst_group gr ON us.group_id= gr.group_id )"
				+ " LEFT JOIN (tbl_detail_user_japan dt INNER JOIN mst_japan jp ON dt.code_level= jp.code_level)"
				+ " ON us.user_id = dt.user_id WHERE us.role=0; ";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
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
				System.out.println(userInfor.getEmail());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listUserInfor;
	}

}
