/**
 * Copyright(C) 2017 Luvina
 * MstJapanLogicImpl.java Nov 2, 2017 minhhang
 */
package manageuser.logics.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import manageuser.dao.impl.BaseDaoImpl;
import manageuser.dao.impl.MstJapanDaoImpl;
import manageuser.entities.MstJapan;
import manageuser.logics.MstJapanLogic;

/**
 * Implement MstJapanLogic để xử lý logic cho list trình độ tiếng nhật
 * 
 * @author minhhang
 */
public class MstJapanLogicImpl implements MstJapanLogic {
	Connection connection = new BaseDaoImpl().getConnection();
	private MstJapanDaoImpl mstJapanDaoImpl = new MstJapanDaoImpl(connection);

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.MstJapanLogic#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() throws SQLException {
		return mstJapanDaoImpl.getAllMstJapan();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.MstJapanLogic#getNameLevel(java.lang.String)
	 */
	@Override
	public String getNameLevel(String codeLevel) {
		return mstJapanDaoImpl.getNameLevel(codeLevel);
	}

}
