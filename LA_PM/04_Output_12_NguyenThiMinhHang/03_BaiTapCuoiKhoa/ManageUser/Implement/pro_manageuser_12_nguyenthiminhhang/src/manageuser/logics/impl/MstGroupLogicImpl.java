/**
* Copyright(C) 2017 Luvina
* MstGroupLogicImpl.java, Oct 26, 2017 minhhang
*/
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.List;

import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroup;
import manageuser.logics.MstGroupLogic;

/**
 * Implement MstGroupLogic để xử lý logic cho list group
 * 
 * @author minhhang
 */
public class MstGroupLogicImpl implements MstGroupLogic {

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.MstGroupLogic#getAllGroups()
	 */
	@Override
	public List<MstGroup> getAllGroups() throws SQLException {
		return new MstGroupDaoImpl().getAllGroups();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.MstGroupLogic#getGroupName(int)
	 */
	@Override
	public String getGroupName(int groupId) {
		return new MstGroupDaoImpl().getGroupName(groupId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.MstGroupLogic#existGroup(int)
	 */
	@Override
	public boolean existGroup(int groupId) {
		return new MstGroupDaoImpl().existGroup(groupId);
	}

}
