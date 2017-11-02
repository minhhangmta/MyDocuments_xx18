/**
* Copyright(C) 2017 Luvina
* MstGroupDaoImpl.java, Oct 26, 2017 minhhang
*/
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroup;
import manageuser.utils.Constant;

/**
 * Implement Thao tác với bảng MstGroup trong DB
 * 
 * @author minhhang
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstGroupDao#getAllGroups()
	 */
	@Override
	public List<MstGroup> getAllGroups() throws SQLException {
		List<MstGroup> listGroup = new ArrayList<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM mst_group");
		query.append(" ORDER BY").append(" group_name ").append(Constant.ASCENDING);
		query.append(";");
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			while (resultSet.next()) {
				MstGroup mstGroup = new MstGroup();
				mstGroup.setGroupId(resultSet.getInt("group_id"));
				mstGroup.setGroupName(resultSet.getString("group_name"));
				listGroup.add(mstGroup);
			}
		} finally {
			closeConnection();
		}
		return listGroup;
	}

}
