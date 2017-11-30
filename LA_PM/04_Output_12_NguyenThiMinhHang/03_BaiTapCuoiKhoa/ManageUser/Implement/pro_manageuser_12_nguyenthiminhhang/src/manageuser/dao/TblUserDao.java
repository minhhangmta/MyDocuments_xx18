/**
 * Copyright(C) 2017 Luvina
 * TblUserDao.java Oct 20, 2017 minhhang
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;

/**
 * Thao tác với bảng TblUser trong DB
 * 
 * @author minhhang
 */
public interface TblUserDao {

	/**
	 * Hàm lấy salt của admin từ DB
	 * 
	 * @param username
	 *            tên đăng nhập
	 * @return salt chuỗi salt
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public String getSalt(String username) throws  ClassNotFoundException, SQLException;

	/**
	 * Hàm kiểm tra user tồn tại trong DB không
	 * 
	 * @param username
	 *            tên đăng nhập
	 * @param password
	 *            mật khẩu
	 * @return true nếu tồn tại, false nếu không tồn tại
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public boolean existLogin(String username, String password) throws  ClassNotFoundException, SQLException;

	/**
	 * Hàm lấy danh sách user
	 * 
	 * @param offset
	 *            vị trí data cần lấy
	 * @param limit
	 *            số lượng lấy
	 * @param groupId
	 *            mã nhóm
	 * @param fullName
	 *            tên tìm kiếm
	 * @param sortType
	 *            nhận biết xem cột nào được ưu tiên
	 * @param sortByFullName
	 *            giá trị sắp xếp của cột Tên (ASC or DESC)
	 * @param sortByCodeLevel
	 *            giá trị sắp xếp của cột Trình độ tiếng nhật(ASC or DESC)
	 * @param sortByEndDate
	 *            giá trị sắp xếp của cột Ngày kết hạn(ASC or DESC)
	 * @return List<UserInfor> danh sách user
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws  ClassNotFoundException, SQLException;

	/**
	 * Hàm lấy tổng số user
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @param fullName
	 *            tên user
	 * @return total tổng số records
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public int getTotalUsers(int groupId, String fullName) throws  ClassNotFoundException, SQLException;

	/**
	 * Hàm get listUser theo groupId và fullName
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @param fullName
	 *            tên user
	 * @return List<UserInfor> danh sách userInfor
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public List<UserInfor> getListUsers(int groupId, String fullName) throws  ClassNotFoundException, SQLException;

	/**
	 * hàm kiểm tra username có tồn tại trong db chưa
	 * 
	 * @param username
	 *            tên đăng nhập cần check
	 * @return true nếu tồn tại, false nếu chưa tồn tại
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public boolean existUsername(String username) throws  ClassNotFoundException, SQLException;

	/**
	 * Hàm kiểm tra email có tồn tại trong DB không
	 * 
	 * @param email
	 *            email
	 * @param userId
	 *            mã user
	 * @return true nếu tồn tại, false nếu không tồn tại
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public boolean existEmail(String email, int userId) throws  ClassNotFoundException, SQLException;

	/**
	 * Hàm kiểm tra codelevel có tồn tại trong DB không
	 * 
	 * @param codeLevel
	 *            mã level
	 * @return true nếu tồn tại, false nếu không
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public boolean existCodeLevel(String codeLevel) throws  ClassNotFoundException, SQLException;

	/**
	 * Thực hiện thêm mới 1 user vào DB
	 * 
	 * @param tblUser
	 *            tblUser Đối tượng chứa thông tin của user
	 * @return int mã user vừa thêm
	 * @throws SQLException
	 */
	public int insertUser(TblUser tblUser) throws SQLException;

	/**
	 * Hàm get list userInfor theo id
	 * 
	 * @param userId
	 *            mã user
	 * @return UserInfor đối tượng UserInfor
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public UserInfor getUserById(int userId) throws  ClassNotFoundException, SQLException;

	/**
	 * Hàm check userid có tồn tại hay không
	 * 
	 * @param userId
	 *            mã user
	 * @return true nếu tồn tại, false nếu không
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public boolean existUserById(int userId) throws  ClassNotFoundException, SQLException;

	/**
	 * Hàm update data vào bảng tbl_user
	 * 
	 * @param tblUser
	 *            đối tượng tblUser
	 * @return true nếu update thành công, false nếu không thành công
	 * @throws SQLException
	 */
	public boolean updateUser(TblUser tblUser) throws SQLException;

	/**
	 * Hàm update password cho tblUser
	 * 
	 * @param passwords
	 *            mật khẩu
	 * @param salt
	 *            chuỗi salt
	 * @param userId
	 *            mã user
	 * @return true nếu update thành công, false nếu không thành công
	 * @throws  ClassNotFoundException, SQLException 
	 */
	public boolean updatePass(String passwords, String salt, int userId) throws  ClassNotFoundException, SQLException;

	/**
	 * Hàm delete user theo userId
	 * 
	 * @param userId
	 *            mã user
	 * @return true nếu delete thành công, false nếu không thành công
	 * @throws SQLException
	 */
	public boolean deleteUser(int userId) throws SQLException;
}
