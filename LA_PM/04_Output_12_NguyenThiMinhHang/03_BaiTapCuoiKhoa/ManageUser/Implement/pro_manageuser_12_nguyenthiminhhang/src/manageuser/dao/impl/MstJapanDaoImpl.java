/**
 * Copyright(C) 2017 Luvina
 * MstJapanImpl.java Nov 2, 2017 minhhang
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstJapanDao;
import manageuser.entities.MstJapan;

/**
 * Implement Thao tác với bảng MstJapan trong DB
 * 
 * @author minhhang
 */
public class MstJapanDaoImpl extends BaseDaoImpl implements MstJapanDao {

	/**
	 * 
	 */
	public MstJapanDaoImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException {
		List<MstJapan> listJapan = new ArrayList<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM mst_japan");
		query.append(";");
		Connection connection = null;
		try {
			connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				while (resultSet.next()) {
					MstJapan mstJapan = new MstJapan();
					mstJapan.setCodeLevel(resultSet.getString("code_level"));
					mstJapan.setNameLevel(resultSet.getString("name_level"));
					listJapan.add(mstJapan);
				}
			}
		} finally {
			closeConnection(connection);
		}
		return listJapan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#getNameLevel(java.lang.String)
	 */
	@Override
	public String getNameLevel(String codeLevel) throws ClassNotFoundException, SQLException {
		String query = "SELECT name_level FROM mst_japan WHERE code_level = ?";
		String nameLevel = "";
		Connection connection = null;
		try {
			connection = getConnection();
			if (connection != null) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, codeLevel);
				ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
				while (resultSet.next()) {
					nameLevel = resultSet.getString("name_level");
				}
			}
		} finally {
			closeConnection(connection);
		}
		return nameLevel;
	}

}
