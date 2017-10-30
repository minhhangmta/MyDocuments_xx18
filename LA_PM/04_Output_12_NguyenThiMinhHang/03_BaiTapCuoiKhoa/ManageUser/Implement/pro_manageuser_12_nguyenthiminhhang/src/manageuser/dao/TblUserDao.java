/**
 * Copyright(C) 2017 Luvina
 * TblUserDao.java Oct 20, 2017 minhhang
 */
package manageuser.dao;

import java.util.List;

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
}
