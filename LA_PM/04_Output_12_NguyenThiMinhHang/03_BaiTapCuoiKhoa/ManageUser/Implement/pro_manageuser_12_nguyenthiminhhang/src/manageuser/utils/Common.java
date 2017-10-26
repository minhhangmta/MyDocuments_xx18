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
	 * @return mật khẩu đã mã hóa
	 */
	public String encodeSHA1(String password, String salt) {
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
	public boolean checkLogin(HttpSession session) {
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
	 * @return List<Integer> danh sách số trang hiển thị lên
	 */
	public List<Integer> getListPaging(int totalRecord, int limit, int currentPage) {
		List<Integer> listPage = new ArrayList<>();
		int totalPage = 0;
		if (totalRecord % limit == 0) {
			totalPage = totalRecord / limit;
		} else {
			totalPage = totalRecord / limit + 1;
		}
		int t = currentPage / 3;
		int k = currentPage % 3;
		if (k == 0)
			t--;
		for (int i = 0; i < 3; i++) {
			listPage.add(3 * t + 1 + i);
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
}
