/**
 * Copyright(C) 2017 Luvina
 * Common.java Oct 20, 2017 minhhang
 */
package manageuser.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
}
