/**
 * Copyright(C) 2017 Luvina
 * TblUserLogic.java Oct 20, 2017 minhhang
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;

/**
 * Interface xử lý logic lấy từ TblUserDao
 * 
 * @author minhhang
 */
public interface TblUserLogic {
	/**
	 * Hàm kiểm tra user login vào có tồn tại trong DB không
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
	 * @return
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
	 * @return true thành công, false nếu không thành công
	 */
	public int insertUser(TblUser tblUser);

	/**
	 * Inser thông tin chi tiết của user vào bảng tbl_detail_user_japan
	 * 
	 * @param tblDetailUserJapan
	 *            Đối tượng chứa thông tin của TblDetailUserJapan
	 * @return true nếu insert thành công, false nếu không
	 * @throws SQLException
	 */
	public boolean insertDetailUserJapan(TblDetailUserJapan tblDetailUserJapan) throws SQLException;

	/**
	 * Insert data user vào bảng tbl_user và tbl_detail_user_japan
	 * 
	 * @param userInfor
	 *            đối tượng UserInfor
	 * @return true nếu insert thành công, false nếu không insert thành công
	 * @throws SQLException
	 */
	public boolean createUser(UserInfor userInfor) throws SQLException;

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
	 * Hàm update data user vào bảng tbl_user và tbl_detail_user_japan
	 * 
	 * @param userInfor
	 *            đối tượng userInfor
	 * @return true nếu update thành công, false nếu không
	 * @throws SQLException
	 */
	public boolean updateUserInfor(UserInfor userInfor) throws SQLException;

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
	 */
	public boolean updatePass(String passwords, String salt, int userId);

	/**
	 * Hàm xóa user
	 * 
	 * @param userId
	 *            mã user
	 * @return true nếu xóa thành công, false nếu không thành công
	 * @throws SQLException 
	 */
	public boolean deleteUser(int userId) throws SQLException;
}
