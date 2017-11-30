/**
 * Copyright(C) 2017 Luvina
 * MstJapanLogicImpl.java Nov 2, 2017 minhhang
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.List;

import manageuser.dao.impl.MstJapanDaoImpl;
import manageuser.entities.MstJapan;
import manageuser.logics.MstJapanLogic;

/**
 * Implement MstJapanLogic để xử lý logic cho list trình độ tiếng nhật
 * 
 * @author minhhang
 */
public class MstJapanLogicImpl implements MstJapanLogic {
	private MstJapanDaoImpl mstJapanDaoImpl = new MstJapanDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.MstJapanLogic#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() throws ClassNotFoundException, SQLException {
		List<MstJapan> result = mstJapanDaoImpl.getAllMstJapan();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.MstJapanLogic#getNameLevel(java.lang.String)
	 */
	@Override
	public String getNameLevel(String codeLevel) throws ClassNotFoundException, SQLException {
		String result = mstJapanDaoImpl.getNameLevel(codeLevel);
		return result;
	}

}
