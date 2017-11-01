/**
 * Copyright(C) 2017 Luvina
 * Common.java Oct 20, 2017 minhhang
 */
package manageuser.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.properties.ConfigProperties;

/**
 * Lớp chứa các hàm common của dự án
 * 
 * @author minhhang
 */
public class Common {
	/**
	 * Hàm mã hóa password
	 * 
	 * @param password
	 *            mật khẩu user nhập vào
	 * @param salt
	 *            mã salt từ DB
	 * @return String mật khẩu đã mã hóa
	 */
	public static String encodeSHA1(String password, String salt) {
		String input = password + salt;
		String sha1 = null;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA1");
			digest.update(input.getBytes());
			BigInteger bigInteger = new BigInteger(1, digest.digest());
			sha1 = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	/**
	 * Hàm kiểm tra user đã đăng nhập chưa
	 * 
	 * @param session
	 *            đối tượng HttpSession
	 * @return true nếu đã đăng nhập, false nếu chưa đăng nhập
	 */
	public static boolean checkLogin(HttpSession session) {
		if (session.getAttribute("username") != null) {
			return true;
		}
		return false;
	}

	/**
	 * Tạo chuỗi paging
	 * 
	 * @param totalRecord
	 *            tổng sô user
	 * @param limit
	 *            số lượng cần hiển thị trên 1 trang
	 * @param currentPage
	 *            trang hiện tại
	 * @return List<Integer> Danh sách các trang cần hiển thị ở chuỗi paging theo
	 *         trang hiện tại
	 */
	public static List<Integer> getListPaging(int totalRecord, int limit, int currentPage) {
		List<Integer> listPage = new ArrayList<>();
		totalRecord = new TblUserDaoImpl().getTotalUsers(0, "");
		int totalPage = getTotalPage(totalRecord, limit);
		if (currentPage <= totalPage) {
			int thuong = currentPage / Common.getLimitPage();
			int du = currentPage % Common.getLimitPage();
			if (du == 0)
				thuong--;
			for (int i = 0; i < Common.getLimitPage(); i++) {
				listPage.add(Common.getLimitPage() * thuong + 1 + i);
			}
		}
		return listPage;
	}

	/**
	 * Hàm chuẩn hóa chuỗi có kí tự đặc biệt "%" "_" để đưa vào query
	 * 
	 * @param key
	 *            tên cần chuẩn hóa
	 * @return String chuỗi đã được chuẩn hóa
	 */
	public static String standardString(String key) {
		key = key.replace("%", "\\%");
		key = key.replace("_", "\\_");
		return key.trim();
	}

	/**
	 * Lấy vị trí data cần lấy
	 * 
	 * @param currentPage
	 *            Trang hiện tại
	 * @param limit
	 *            Số lượng cần hiển thị trên 1 trang
	 * @return int vị trí cần lấy
	 */
	public static int getOffset(int currentPage, int limit) {
		return (currentPage - 1) * limit;
	}

	/**
	 * Lấy số lượng hiển thị bản ghi trên 1 trang
	 * 
	 * @return int số lượng records cần lấy
	 */
	public static int getLimit() {
		// Lấy limit từ file config.properties
		return tryParseInt(ConfigProperties.getData("limit"));
	}

	/**
	 * Lấy số trang hiển thị trên một màn hình
	 * 
	 * @return int số trang
	 */
	public static int getLimitPage() {
		// Lấy limit từ file config.properties
		return tryParseInt(ConfigProperties.getData("limitPage"));
	}

	/**
	 * Tính tổng số trang
	 * 
	 * @param totalUser
	 *            tổng số User
	 * @param limit
	 *            số lượng cần hiển thị trên 1 trang
	 * @return int tổng số trang
	 */
	public static int getTotalPage(int totalUser, int limit) {
		int totalPage = 0;
		if (totalUser % limit == 0) {
			totalPage = totalUser / limit;
		} else {
			totalPage = totalUser / limit + 1;
		}
		return totalPage;
	}

	/**
	 * Hàm ép kiểu kiểu String to int
	 * 
	 * @param text
	 *            chuỗi cần convert
	 * @return Integer số được convert
	 */
	public static Integer tryParseInt(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Lấy vị trí trang hiện tại ở phân đoạn tiếp theo
	 * 
	 * @param list
	 * @param currentPage
	 * @return int vị trí trang hiện tại tiếp theo
	 */
	public static int getNextPage(List<Integer> list, int currentPage) {
		return (currentPage - list.indexOf(currentPage) + Common.getLimitPage());
	}

	/**
	 * Lấy vị trí trang hiện tại ở phân đoạn trước
	 * 
	 * @param list
	 * @param currentPage
	 * @return int vị trí trang hiện tại trước đó
	 */
	public static int getPrePage(List<Integer> list, int currentPage) {
		return (currentPage - list.indexOf(currentPage) - Common.getLimitPage());
	}

	/**
	 * Hàm lấy giá trị từ session
	 * 
	 * @param session
	 *            đối tượng HttpSession
	 * @param key
	 *            từ khóa cần lấy giá trị từ session
	 * @param defaultValue
	 *            giá trị mặc định của key
	 * @return String giá trị của key
	 */
	public static String getSessionValue(HttpSession session, String key, String defaultValue) {
		Object value = session.getAttribute(key);
		if (value == null) {
			return defaultValue;
		} else {
			return value.toString();
		}
	}

}
