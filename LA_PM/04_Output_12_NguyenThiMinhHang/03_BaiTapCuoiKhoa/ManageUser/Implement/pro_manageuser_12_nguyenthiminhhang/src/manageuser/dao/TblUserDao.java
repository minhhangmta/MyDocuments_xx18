/**
 * Copyright(C) 2017 Luvina
 * TblUserDao.java Oct 20, 2017 minhhang
 */
package manageuser.dao;

import java.util.ArrayList;

import manageuser.entities.TblUser;

/**
 * Interface Thao tác với DB của các chức năng của TblUser
 * 
 * @author minhhang
 */
public interface TblUserDao {
	public ArrayList<TblUser> getListUser();

	public String getSalt(String username);

	public boolean existLogin(String username, String password);

}
