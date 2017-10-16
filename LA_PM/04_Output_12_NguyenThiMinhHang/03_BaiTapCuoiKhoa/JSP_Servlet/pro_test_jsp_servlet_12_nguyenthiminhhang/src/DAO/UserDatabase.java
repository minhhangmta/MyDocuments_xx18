/**
 * Copyright(C) 2017 Luvina
 * UserDatabase.java Oct 16, 2017 minhhang
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

/**
 * Description class here
 * 
 * @author minhhang
 */
public class UserDatabase {
	private DBConnection dbConnection;

	/**
	 * Hàm lấy danh sách user
	 * 
	 * @return listUser
	 */
	public ArrayList<User> getListUser() {
		ArrayList<User> listUser = new ArrayList<>();
		String query = "SELECT * FROM USER;";
		try {
			dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setBirthday(resultSet.getDate("birthday").toString());
				user.setBirthplace(resultSet.getString("birthplace"));
				listUser.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}
		return listUser;
	}
}
