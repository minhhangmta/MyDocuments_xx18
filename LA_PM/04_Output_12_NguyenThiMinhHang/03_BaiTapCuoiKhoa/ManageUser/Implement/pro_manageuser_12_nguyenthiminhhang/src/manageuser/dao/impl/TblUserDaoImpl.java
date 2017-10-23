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

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUser;
import manageuser.properties.DatabaseProperties;

/**
 * Interface Thao tác với DB của các chức năng của TblUser
 * 
 * @author minhhang
 */
public class TblUserDaoImpl implements TblUserDao {
	private BaseDaoImpl baseDaoImpl;
	private ArrayList<TblUser> listUser;

	/**
	 * Hàm lấy danh sách user
	 * 
	 * @return listUser
	 */
	public ArrayList<TblUser> getListUser() {
		listUser = new ArrayList<>();
		String query = "SELECT * FROM tbl_user;";
		try {
			baseDaoImpl = new BaseDaoImpl();
			Connection connection = baseDaoImpl.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			while (resultSet.next()) {
				TblUser user = new TblUser();
				user.setUserID(resultSet.getInt("user_id"));
				user.setGroupID(resultSet.getInt("group_id"));
				user.setLoginName(resultSet.getString("login_name"));
				user.setPasswords(resultSet.getString("passwords"));
				//... continue
				listUser.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			baseDaoImpl.closeConnection();
		}
		return listUser;
	}

	/**
	 * Hàm lấy salt từ database
	 * @param username
	 * @return
	 */
	public String getSalt(String username) {
		String salt = "";
		listUser = new ArrayList<>();
		String query = "SELECT * FROM tbl_user WHERE login_name = ? ;";
		try {
			baseDaoImpl = new BaseDaoImpl();
			Connection connection = baseDaoImpl.getConnection();
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
			baseDaoImpl.closeConnection();
		}
		return salt;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean existLogin(String username, String password) {
		listUser = getListUser();
		for (TblUser user : listUser) {
			if (username.equals(user.getLoginName()) && password.equals(user.getPasswords())) {
				return true;
			}
		}
		return false;
	}
}
