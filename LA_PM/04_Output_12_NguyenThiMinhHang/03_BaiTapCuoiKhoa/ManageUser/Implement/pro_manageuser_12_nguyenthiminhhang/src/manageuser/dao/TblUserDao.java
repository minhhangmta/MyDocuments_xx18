/**
 * Copyright(C) 2017 Luvina
 * TblUserDao.java Oct 20, 2017 minhhang
 */
package manageuser.dao;

/**
 * Interface Thao tác với DB của các chức năng của TblUser
 * 
 * @author minhhang
 */
public interface TblUserDao {

	/**
	 * Hàm lấy salt của admin từ DB
	 * 
	 * @param username
	 * @return chuỗi salt
	 */
	public String getSalt(String username);

	/**
	 * Hàm kiểm tra user tồn tại trong DB không
	 * 
	 * @param username
	 * @param password
	 * @return true nếu tồn tại, false nếu không tồn tại
	 */
	public boolean existLogin(String username, String password);

}
