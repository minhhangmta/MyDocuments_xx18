/**
* Copyright(C) 2017 Luvina
* CDDatabase.java, Sep 26, 2017 minhhang
*/
package bai2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Lớp quản lý dữ liệu CD, tác động tới DB
 * 
 * @author minhhang
 */
public class CDDatabase {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private String URL;
	private String USER;
	private String PASS;
	Properties properties = new Properties();

	/**
	 * Hàm mở connection
	 */
	public void getConnection() {
		try {
			try {
				properties.load(new FileReader(new File("info.properties")));
				// lấy danh sách property từ file vào
				URL = properties.getProperty("url"); // lấy giá trị url trong file
				USER = properties.getProperty("user"); // lấy giá trị user trong file
				PASS = properties.getProperty("password");
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USER, PASS);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Hàm thêm mới một CD
	 * 
	 * @param cd
	 *            đối tượng CD
	 * @return chuỗi thông báo
	 */
	public String insertCD(CD cd) {
		String query = "INSERT INTO cds values(?,?);";
		String notice = "";
		try {
			getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cd.getArtist());
			preparedStatement.setString(2, cd.getTitle());
			int excute = preparedStatement.executeUpdate();
			if (excute > 0) {
				notice = "Đã thêm!";
			} else {
				notice = "Không thể thêm!";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return notice;
	}

	/**
	 * Hàm xóa một CD
	 * 
	 * @param cd
	 *            đối tượng CD
	 * @return chuỗi thông báo
	 */
	public String removeCD(CD cd) {
		String notice = "";
		String query = "DELETE FROM cds WHERE artist = ? and title = ? ;";
		try {
			getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cd.getArtist());
			preparedStatement.setString(2, cd.getTitle());
			int excute = preparedStatement.executeUpdate();
			if (excute > 0) {
				notice = "Đã xóa!";
			} else {
				notice = "Không thể xóa!";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return notice;
	}

	/**
	 * Hàm tìm kiếm theo Title
	 * 
	 * @param key
	 *            từ khóa tìm kiếm
	 * @return danh sách CD tìm được
	 */
	public ArrayList<CD> findByTitle(String key) {
		ArrayList<CD> listCD = new ArrayList<CD>();
		String query = "SELECT * FROM cds WHERE title LIKE ? ;";
		if ("".equals(key)) {
			return listCD;
		} else {
			try {
				getConnection();
				key = key.replace("%", "\\%");
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, "%" + key + "%");
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				while (resultSet.next()) {
					String ar = resultSet.getString("artist");
					String ti = resultSet.getString("title");
					listCD.add(new CD(ar, ti));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return listCD;
	}

	/**
	 * Hàm tìm kiếm theo Artist
	 * 
	 * @param key
	 *            từ khóa tìm kiếm
	 * @return danh sách CD tìm được
	 */
	public ArrayList<CD> findByArtist(String key) {
		ArrayList<CD> listCD = new ArrayList<CD>();
		String query = "SELECT * FROM cds WHERE artist LIKE ? ;";
		if ("".equals(key)) {
			return listCD;
		} else {
			try {
				getConnection();
				key = key.replace("%", "\\%");
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, "%" + key + "%");
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				while (resultSet.next()) {
					String ar = resultSet.getString("artist");
					String ti = resultSet.getString("title");
					listCD.add(new CD(ar, ti));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
		return listCD;
	}

	/**
	 * Hàm đóng connection
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
