/**
* Copyright(C) 2017 Luvina
* TblDetailUserJapanDaoImpl.java, Nov 7, 2017 minhhang
*/
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) {
		StringBuilder query = new StringBuilder();
		boolean check = false;
		query.append("INSERT INTO tbl_detail_user_japan ").append("(")
				.append("user_id, code_level, start_date, end_date, total").append(")")
				.append(" VALUES(?, ?, ?, ?, ? )");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, tblDetailUserJapan.getUserId());
			preparedStatement.setString(index++, tblDetailUserJapan.getCodeLevel());
			preparedStatement.setString(index++, Common.convertDateToString(tblDetailUserJapan.getStartDate()));
			preparedStatement.setString(index++, Common.convertDateToString(tblDetailUserJapan.getEndDate()));
			preparedStatement.setInt(index++, Common.tryParseInt(tblDetailUserJapan.getTotal()));
			preparedStatement.executeUpdate();
			check = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return check;
	}
}
