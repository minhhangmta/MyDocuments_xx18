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
import manageuser.entities.UserInfor;
import manageuser.logics.impl.TblUserLogicImpl;

/**
 * Implement MstGroupDao để Thao tác với DB bảng mst_group
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
	public List<MstGroup> getAllGroups() {
		List<MstGroup> listGroup = new ArrayList<>();
		String query = "SELECT * FROM mst_group";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
			while (resultSet.next()) {
				MstGroup mstGroup = new MstGroup();
				mstGroup.setGroupId(resultSet.getInt("group_id"));
				mstGroup.setGroupName(resultSet.getString("group_name"));
				listGroup.add(mstGroup);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listGroup;
	}
}
