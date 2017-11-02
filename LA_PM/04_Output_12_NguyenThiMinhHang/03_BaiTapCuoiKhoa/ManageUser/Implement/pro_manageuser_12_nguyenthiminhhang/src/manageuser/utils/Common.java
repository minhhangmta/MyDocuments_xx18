/**
 * Copyright(C) 2017 Luvina
 * Common.java Oct 20, 2017 minhhang
 */
package manageuser.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
>>>>>>> dfe03b2070027426b54a325038dcfaaada8b5c2e
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

<<<<<<< HEAD
=======
	/**
	 * Lấy danh sách các năm từ năm 1980 -> năm hiện tại + 1
	 * 
	 * @param fromYear
	 *            Lấy từ năm nào
	 * @param toYear
	 *            Lấy đến năm nào
	 * @return List<Integer> list các năm
	 */
	public static List<Integer> getListYear(int fromYear, int toYear) {
		List<Integer> listYear = new ArrayList<>();
		for (int year = fromYear; year <= toYear; year++) {
			listYear.add(year);
		}
		return listYear;
	}

	/**
	 * Hàm lấy năm hiện tại
	 * 
	 * @return int năm hiện tại
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * Lấy danh sách các tháng từ 1->12
	 * 
	 * @return List<Integer> list các tháng
	 */
	public static List<Integer> getListMonth() {
		List<Integer> listMonth = new ArrayList<>();
		for (int month = 0; month < 12; month++) {
			listMonth.add(month + 1);
		}
		return listMonth;
	}

	/**
	 * Lấy danh sách các ngày từ 1->31
	 * 
	 * @return List<Integer> list các ngày
	 */
	public static List<Integer> getListDay() {
		List<Integer> listDay = new ArrayList<>();
		for (int day = 0; day < 12; day++) {
			listDay.add(day + 1);
		}
		return listDay;
	}

	/**
	 * Hàm lấy ra ngày tháng năm hiện tại
	 * 
	 * @return String ngày tháng năm hiện tại
	 */
	public static String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		return dtf.format(localDate);
	}

	/**
	 * Hàm lấy ngày hiện tại
	 * 
	 * @return int ngày hiện tại
	 */
	public static int getCurrentDay() {
		return Calendar.getInstance().get(Calendar.DATE);
	}

	/**
	 * Hàm lấy tháng hiện tại
	 * 
	 * @return int tháng hiện tại
	 */
	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * Convert các số năm tháng ngày thành 1 chuỗi ngày tháng có format yyyy/mm/dd
	 * 
	 * @param year
	 *            năm
	 * @param month
	 *            tháng
	 * @param day
	 *            ngày
	 * @return String chuỗi ngày tháng
	 */
	public static String convertToString(int year, int month, int day) {
		return null;
	}

	/**
	 * Convert các số năm tháng ngày thành 1 ngày tháng có format yyyy/mm/dd
	 * 
	 * @param year
	 *            năm
	 * @param month
	 *            tháng
	 * @param day
	 *            ngày
	 * @return Date năm tháng ngày
	 */
	public static Date toDate(int year, int month, int day) {
		return null;
	}

>>>>>>> dfe03b2070027426b54a325038dcfaaada8b5c2e
}
