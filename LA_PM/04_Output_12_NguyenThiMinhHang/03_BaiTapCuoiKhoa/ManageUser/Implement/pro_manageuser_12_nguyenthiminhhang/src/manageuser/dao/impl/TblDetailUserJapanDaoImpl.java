/**
* Copyright(C) 2017 Luvina
* TblDetailUserJapanDaoImpl.java, Nov 7, 2017 minhhang
*/
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.entities.TblDetailUserJapan;

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
				.append("VALUES(?, ?, ?, ?, ? )");
		try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, tblDetailUserJapan.getUserId());
			preparedStatement.setString(index++, tblDetailUserJapan.getCodeLevel());
			preparedStatement.setDate(index++, (Date) tblDetailUserJapan.getStartDate());
			preparedStatement.setDate(index++, (Date) tblDetailUserJapan.getEndDate());
			preparedStatement.setString(index++, tblDetailUserJapan.getTotal());
			preparedStatement.executeUpdate();
			check = true;
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			closeConnection();
		}
		return check;
	}

}
