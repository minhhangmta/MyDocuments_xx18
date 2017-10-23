/**
 * Copyright(C) 2017 Luvina
 * TblUserLogicImpl.java Oct 20, 2017 minhhang
 */
package manageuser.logics.impl;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;

/**
 * Description class here
 * 
 * @author minhhang
 */
public class TblUserLogicImpl implements TblUserLogic {
	public boolean existLogin(String username, String password) {
		TblUserDaoImpl userDaoImpl = new TblUserDaoImpl();
		Common common = new Common();
		String salt = userDaoImpl.getSalt(username);
		password = common.encode(password, salt);
		if (userDaoImpl.existLogin(username, password)) {
			return true;
		}
		return false;
	}
}
