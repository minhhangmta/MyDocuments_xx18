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

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() throws SQLException {
		List<MstJapan> listJapan = new ArrayList<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM mst_japan");
		query.append(";");
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			while (resultSet.next()) {
				MstJapan mstJapan = new MstJapan();
				mstJapan.setCodeLevel(resultSet.getString("code_level"));
				mstJapan.setNameLevel(resultSet.getString("name_level"));
				listJapan.add(mstJapan);
			}
		} finally {
			closeConnection();
		}
		return listJapan;
	}

}
