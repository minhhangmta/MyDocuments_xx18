/**
* Copyright(C) 2017 Luvina
* TblDetailUserJapanDaoImpl.java, Nov 7, 2017 minhhang
*/
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.entities.TblDetailUserJapan;
import manageuser.utils.Common;

/**
 * Implement Thao tác với bảng TblDetailUserJapan trong DB
 * 
 * @author minhhang
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {
	public Connection connection = getConnection();

	/**
	 * 
	 */
	public TblDetailUserJapanDaoImpl() {

	}

	/**
	 * @param connection
	 */
	public TblDetailUserJapanDaoImpl(Connection connection) {
		this.connection = connection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblDetailUserJapanDao#insertDetailUserJapan(manageuser.
	 * entities.TblDetailUserJapan)
	 */
	@Override
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO tbl_detail_user_japan ").append("(")
				.append("user_id, code_level, start_date, end_date, total").append(")")
				.append(" VALUES(?, ?, ?, ?, ? )");
		PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
		int index = 1;
		preparedStatement.setInt(index++, tblDetailUserJapan.getUserId());
		preparedStatement.setString(index++, tblDetailUserJapan.getCodeLevel());
		preparedStatement.setString(index++, Common.convertDateToString(tblDetailUserJapan.getStartDate()));
		preparedStatement.setString(index++, Common.convertDateToString(tblDetailUserJapan.getEndDate()));
		preparedStatement.setInt(index++, Common.tryParseInt(tblDetailUserJapan.getTotal()));
		int row = preparedStatement.executeUpdate();
		if (row == 0) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblDetailUserJapanDao#getCodeLevelById(int)
	 */
	@Override
	public String getCodeLevelById(int userId) throws SQLException {
		String codeLevel = "";
		String query = "SELECT code_level FROM tbl_detail_user_japan WHERE user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, userId);
		ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
		while (resultSet.next()) {
			codeLevel = resultSet.getString("code_level");
		}
		return codeLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.dao.TblDetailUserJapanDao#updateDetailJapan(manageuser.entities.
	 * TblDetailUserJapan)
	 */
	@Override
	public boolean updateDetailJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE tbl_detail_user_japan SET code_level = ?, start_date = ?, end_date = ?, total = ?")
				.append(" WHERE user_id = ?");
		PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
		int index = 1;
		preparedStatement.setString(index++, tblDetailUserJapan.getCodeLevel());
		preparedStatement.setString(index++, Common.convertDateToString(tblDetailUserJapan.getStartDate()));
		preparedStatement.setString(index++, Common.convertDateToString(tblDetailUserJapan.getEndDate()));
		preparedStatement.setInt(index++, Common.tryParseInt(tblDetailUserJapan.getTotal()));
		preparedStatement.setInt(index++, tblDetailUserJapan.getUserId());
		int row = preparedStatement.executeUpdate();
		if (row == 0) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblDetailUserJapanDao#deleteDetailJapan(java.lang.String)
	 */
	@Override
	public boolean deleteDetailJapan(int userId) throws SQLException {
		String query = "DELETE FROM tbl_detail_user_japan WHERE user_id = ? ";
		PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
		preparedStatement.setInt(1, userId);
		int row = preparedStatement.executeUpdate();
		if (row == 0) {
			return false;
		}
		return true;
	}
}
