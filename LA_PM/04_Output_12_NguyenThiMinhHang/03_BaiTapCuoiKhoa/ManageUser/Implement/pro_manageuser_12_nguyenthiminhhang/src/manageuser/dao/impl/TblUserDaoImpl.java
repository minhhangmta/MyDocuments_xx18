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

/**
 * Implement TblUserDao để Thao tác với DB của các chức năng của TblUser
 * 
 * @author minhhang
 */
public class TblUserDaoImpl implements TblUserDao {
	private BaseDaoImpl baseDaoImpl;
	private ArrayList<TblUser> listAdmin;

	/**
	 * Hàm lấy danh sách user
	 * 
	 * @return ArrayList<TblUser>
	 */
	public ArrayList<TblUser> getListUser() {
		listAdmin = new ArrayList<>();
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
				// ... continue
				listAdmin.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			baseDaoImpl.closeConnection();
		}
		return listAdmin;
	}
	/**
	 * Hàm lấy list user là admin
	 * @return ArrayList<TblUser>
	 */
	public ArrayList<TblUser> getListAdmin() {
		listAdmin = new ArrayList<>();
		String query = "SELECT * FROM tbl_user WHERE rule = 0;";
		try {
			baseDaoImpl = new BaseDaoImpl();
			Connection connection = baseDaoImpl.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			while (resultSet.next()) {
				TblUser user = new TblUser();
				user.setLoginName(resultSet.getString("login_name"));
				user.setPasswords(resultSet.getString("passwords"));
				listAdmin.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			baseDaoImpl.closeConnection();
		}
		return listAdmin;
	}

	/**
	 * Hàm lấy salt từ database
	 * 
	 * @param username
	 * @return
	 */
	public String getSalt(String username) {
		String salt = "";
		listAdmin = new ArrayList<>();
		String query = "SELECT * FROM tbl_user WHERE login_name = ? and rule = 0 ;";
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
	 * Hàm kiểm tra user login vào có tồn tại trong DB không
	 * 
	 * @param username
	 * @param password
	 * @return true nếu tồn tại, false nếu không tồn tại
	 */
	public boolean existLogin(String username, String password) {
		listAdmin = getListAdmin();
		for (TblUser admin : listAdmin) {
			if (username.equals(admin.getLoginName()) && password.equals(admin.getPasswords())) {
				return true;
			}
		}
		return false;
	}
}
