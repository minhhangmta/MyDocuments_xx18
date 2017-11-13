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
	 */
	public String getSalt(String username);

	/**
	 * Hàm kiểm tra user tồn tại trong DB không
	 * 
	 * @param username
	 *            tên đăng nhập
	 * @param password
	 *            mật khẩu
	 * @return true nếu tồn tại, false nếu không tồn tại
	 */
	public boolean existLogin(String username, String password);

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
	 */
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate);

	/**
	 * Hàm lấy tổng số user
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @param fullName
	 *            tên user
	 * @return total tổng số records
	 */
	public int getTotalUsers(int groupId, String fullName);

	/**
	 * hàm kiểm tra username có tồn tại trong db chưa
	 * 
	 * @param username
	 *            tên đăng nhập cần check
	 * @return true nếu tồn tại, false nếu chưa tồn tại
	 */
	public boolean existUsername(String username);

	/**
	 * Hàm kiểm tra email có tồn tại trong DB không
	 * 
	 * @param email
	 *            email
	 * @param userId
	 *            mã user
	 * @return true nếu tồn tại, false nếu không tồn tại
	 */
	public boolean existEmail(String email, int userId);

	/**
	 * Hàm kiểm tra codelevel có tồn tại trong DB không
	 * 
	 * @param codeLevel
	 *            mã level
	 * @return true nếu tồn tại, false nếu không
	 */
	public boolean existCodeLevel(String codeLevel);

	/**
	 * Thực hiện thêm mới 1 user vào DB
	 * 
	 * @param tblUser
	 *            tblUser Đối tượng chứa thông tin của user
	 * @return int mã user vừa thêm
	 */
	public int insertUser(TblUser tblUser);

	/**
	 * Hàm get list userInfor theo id
	 * 
	 * @param userId
	 *            mã user
	 * @return UserInfor đối tượng UserInfor
	 */
	public UserInfor getUserById(int userId);

	/**
	 * Hàm check userid có tồn tại hay không
	 * 
	 * @param userId
	 *            mã user
	 * @return true nếu tồn tại, false nếu không
	 */
	public boolean existUserById(int userId);

	/**
	 * Hàm update data vào bảng tbl_user
	 * 
	 * @param tblUser
	 *            đối tượng tblUser
	 * @return true nếu update thành công, false nếu không thành công
	 * @throws SQLException
	 */
	public boolean updateUser(TblUser tblUser) throws SQLException;
}
