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

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getSalt(java.lang.String)
	 */
	public String getSalt(String username) {
		String salt = "";
		listAdmin = new ArrayList<>();
		String query = "SELECT salt FROM tbl_user WHERE login_name = ? and rule = 1 ;";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#existLogin(java.lang.String, java.lang.String)
	 */
	public boolean existLogin(String username, String password) {
		String query = "SELECT * FROM tbl_user WHERE login_name = ? and passwords = ? and rule = 1;";
		try {
			baseDaoImpl = new BaseDaoImpl();
			Connection connection = baseDaoImpl.getConnection();
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
			baseDaoImpl.closeConnection();
		}
		return true;
	}
}
