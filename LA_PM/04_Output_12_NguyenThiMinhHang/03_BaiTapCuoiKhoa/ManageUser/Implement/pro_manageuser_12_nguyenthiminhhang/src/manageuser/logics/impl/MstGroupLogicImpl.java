/**
* Copyright(C) 2017 Luvina
* MstGroupLogicImpl.java, Oct 26, 2017 minhhang
*/
package manageuser.logics.impl;

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
	public List<MstGroup> getAllGroups() {
		return new MstGroupDaoImpl().getAllGroups();
	}
}
