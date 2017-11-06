/**
 * Copyright(C) 2017 Luvina
 * Common.java Oct 20, 2017 minhhang
 */
package manageuser.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.ConfigProperties;
import manageuser.properties.MessageErrorProperties;

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

	public static String getRequestValue(HttpServletRequest request, String key, String defaultValue) {
		Object value = request.getParameter(key);
		if (value == null) {
			return defaultValue;
		} else {
			return value.toString();
		}
	}

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
		for (int month = 1; month <= 12; month++) {
			listMonth.add(month);
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
		for (int day = 1; day <= 31; day++) {
			listDay.add(day);
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
		StringBuilder date = new StringBuilder("");
		date.append(String.valueOf(year)).append("/").append(String.valueOf(month)).append("/")
				.append(String.valueOf(day));
		return date.toString();
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
		DateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
		String date = convertToString(year, month, day);
		Date dt = new Date();
		try {
			dt = (Date) df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;
	}

	/**
	 * Hàm convert kiểu Date sang String
	 * 
	 * @param date
	 *            ngày cần chuyển đổi
	 * @return String chuỗi yyyy/MM/dd
	 */
	public static String convertDateToString(Date date) {
		DateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
		return df.format(date);
	}

	/**
	 * Hàm validate username
	 * 
	 * @param username
	 *            tên đăng nhập
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateUsername(String username) {
		String errUsername = "";
		if (username.length() < 1) {
			errUsername = MessageErrorProperties.getData("ER001_USERNAME");
		} else {
			Pattern pattern = Pattern.compile(getRegex());
			int minLength = getMinLength();
			int maxLength = getMaxLength();
			boolean validFormat = pattern.matcher(username).matches();
			boolean validUser = new TblUserLogicImpl().existUsername(username);
			if (username.length() < minLength || username.length() > maxLength) {
				errUsername = MessageErrorProperties.getData("ER007_USERNAME");
			} else if (!validFormat) {
				errUsername = MessageErrorProperties.getData("ER019");
			} else if (validUser) {
				errUsername = MessageErrorProperties.getData("ER003_USERNAME");
			}
		}
		return errUsername;
	}

	/**
	 * Hàm lấy giá trị minlength (username, tel) từ config.properties
	 * 
	 * @return int giá trị minlength
	 */
	public static int getMinLength() {
		return tryParseInt(ConfigProperties.getData("minLength"));
	}

	/**
	 * Hàm lấy giá trị maxlength(username, tel) từ config.properties
	 * 
	 * @return int giá trị maxlength
	 */
	public static int getMaxLength() {
		return tryParseInt(ConfigProperties.getData("maxLength"));
	}

	/**
	 * Hàm lấy regex từ file config.properties
	 * 
	 * @return String chuỗi regex
	 */
	public static String getRegex() {
		return ConfigProperties.getData("regex");
	}

	/**
	 * Hàm validate group
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateGroup(int groupId) {
		String errGroup = "";
		boolean valid = new MstGroupLogicImpl().existGroup(groupId);
		// Chua chon nhom
		if (groupId == 0) {
			errGroup = MessageErrorProperties.getData("ER002");
		} else if (!valid) {// Nhom khong ton tai
			errGroup = MessageErrorProperties.getData("ER004_GROUP");
		}
		return errGroup;
	}

	/**
	 * Hàm lấy giá trị max của chuỗi(tên, email) từ file config.properties
	 * 
	 * @return int giá trị maxLength
	 */
	public static int getMaxLengthString() {
		return tryParseInt(ConfigProperties.getData("maxLengthString"));
	}

	/**
	 * Hàm validate fullname
	 * 
	 * @param fullName
	 *            họ và tên
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateFullname(String fullName) {
		String errName = "";
		// Nếu không nhập
		if (fullName.length() < 1) {
			errName = MessageErrorProperties.getData("ER001_FULLNAME");
		} else if (fullName.length() > getMaxLengthString()) {// Nếu quá 255 kí tự
			errName = MessageErrorProperties.getData("ER006_FULLNAME");
		}
		return errName;
	}

	/**
	 * Hàm validate fullname kana
	 * 
	 * @param fullnameKana
	 *            họ tên kana
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateFullnameKana(String fullnameKana) {
		String errKana = "";
		// Nếu vượt quá 255 kí tự
		if (fullnameKana.length() > getMaxLengthString()) {
			errKana = MessageErrorProperties.getData("ER006_FULLNAME_KANA");
		} else if (!fullnameKana.isEmpty() && !checkKana(fullnameKana)) {// Nếu không có kí tự kana
			errKana = MessageErrorProperties.getData("ER009");
		}
		return errKana;
	}

	/**
	 * Hàm kiểm tra chuỗi có phải chuỗi kana không
	 * 
	 * @param str
	 *            chuỗi cần check
	 * @return true nếu đúng, false nếu sai
	 */
	public static boolean checkKana(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.UnicodeBlock.of(c) != Character.UnicodeBlock.KATAKANA) {
				return false;
			}
		}
		return true;
	}

	// Check ngày sinh

	/**
	 * Hàm validate Email
	 * 
	 * @param email
	 *            email cần check
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateEmail(String email) {
		String errEmail = "";
		if (email.length() < 1) {
			errEmail = MessageErrorProperties.getData("ER001_EMAIL");
		} else {
			Pattern pattern = Pattern.compile(ConfigProperties.getData("regexEmail"));
			boolean validFormat = pattern.matcher(email).matches();
			boolean validEmail = new TblUserLogicImpl().existEmail(email);
			if (email.length() > getMaxLengthString()) {// Nếu quá 255 kí tự
				errEmail = MessageErrorProperties.getData("ER006_EMAIL");
			} else if (!validFormat) {// Nếu sai định dạng
				errEmail = MessageErrorProperties.getData("ER005_EMAIL");
			} else if (validEmail) {// Nếu đã tồn tại email
				errEmail = MessageErrorProperties.getData("ER003_EMAIL");
			}
		}
		return errEmail;
	}

	/**
	 * Hàm validate telephone
	 * 
	 * @param tel
	 *            số điện thoại
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateTel(String tel) {
		String errTel = "";
		if (tel.length() < 1) {
			errTel = MessageErrorProperties.getData("ER001_TEL");
		} else {
			Pattern pattern = Pattern.compile(ConfigProperties.getData("regexTel"));
			boolean validFormat = pattern.matcher(tel).matches();
			if (tel.length() > getMaxLength()) {
				errTel = MessageErrorProperties.getData("ER006_TEL");
			} else if (!validFormat) {// định dạng xxxx-xxxx-xxxx
				errTel = MessageErrorProperties.getData("ER005_TEL");
			}
		}
		return errTel;
	}

	/**
	 * Hàm validate password
	 * 
	 * @param password
	 *            mật khẩu
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validatePass(String password) {
		String errPass = "";
		if (password.length() < 1) {// check khong nhap
			errPass = MessageErrorProperties.getData("ER001_PASS");
		} else {
			// check length
			if (password.length() < getMinLength() || password.length() > getMaxLength()) {
				errPass = MessageErrorProperties.getData("ER007_PASS");
			} else if (!checkOneByteChar(password)) {// check co ki tu khac 1 byte
				errPass = MessageErrorProperties.getData("ER008");
			}
		}
		return errPass;
	}

	/**
	 * Hàm check kí tự 1 byte trong chuỗi
	 * 
	 * @param str
	 *            chuỗi cần check
	 * @return true nếu cả chuỗi chứa kí tự 1 byte, false nếu khác
	 */
	public static boolean checkOneByteChar(String str) {
		for (int i = 0; i < str.length(); i++) {
			// Lấy từng phần tử của str dạng string
			String s = new StringBuilder().append("").append(str.charAt(i)).toString();
			try {
				byte[] utf8Bytes = s.getBytes("UTF-8");
				if (utf8Bytes.length != 1) {
					return false;
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * Hàm validate xác nhận mật khẩu
	 * 
	 * @param password
	 *            mật khẩu
	 * @param passConfirm
	 *            mật khẩu xác nhận
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validatePassConfirm(String password, String passConfirm) {
		if (!password.isEmpty() && !password.equals(passConfirm)) {
			return MessageErrorProperties.getData("ER017");
		}
		return "";
	}

	/**
	 * Hàm validate trình độ tiếng nhật
	 * 
	 * @param codeLevel
	 *            mã trình độ
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateCodeLevel(String codeLevel) {
		boolean valid = new TblUserLogicImpl().existCodeLevel(codeLevel);
		// Nếu không tồn tại
		if (codeLevel != Constant.EMPTY_STRING && !valid) {
			return MessageErrorProperties.getData("ER004_LEVEL");
		}
		return "";
	}

	public static String validateTotal(String total, String code_level) {
		String errTotal = "";
		String totalStr = String.valueOf(total);
		// trình độ japan được chọn
		if (code_level != Constant.EMPTY_STRING) {
			// nếu ko nhập
			if (total == Constant.EMPTY_STRING) {
				errTotal = MessageErrorProperties.getData("ER001_TOTAL");
			} else if (!checkHalfSizeTotal(totalStr)) {// nếu có kí tự khác halfsize
				errTotal = MessageErrorProperties.getData("ER018");
			}
		}
		return errTotal;
	}

	/**
	 * Hàm check giá trị halfsize
	 * 
	 * @param number
	 *            số cần check
	 * @return true nếu đều là halfsize, false nếu có kí tự khác halfsize
	 */
	public static boolean checkHalfSizeTotal(String number) {
		Pattern pattern = Pattern.compile(ConfigProperties.getData("regexTotal"));
		boolean validFormat = pattern.matcher(number).matches();
		return validFormat;
	}

	/**
	 * Hàm check date hợp lệ
	 * 
	 * @param date
	 *            ngày cần check
	 * @return true nếu hợp lệ, false nếu không
	 */
	public static boolean isDateValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Hàm validate birthday
	 * 
	 * @param year
	 *            năm
	 * @param month
	 *            tháng
	 * @param day
	 *            ngày
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateBirthday(int year, int month, int day) {
		String date = convertToString(year, month, day);
		if (!isDateValid(date)) {
			return MessageErrorProperties.getData("ER011_BIRTHDAY");
		}
		return "";
	}

	/**
	 * Hàm validate ngày bắt đầu
	 * 
	 * @param year
	 *            năm
	 * @param month
	 *            tháng
	 * @param day
	 *            ngày
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateStartDate(int year, int month, int day) {
		String date = convertToString(year, month, day);
		if (!isDateValid(date)) {
			return MessageErrorProperties.getData("ER011_START_DATE");
		}
		return "";
	}

	/**
	 * Hàm validate ngày kết thúc
	 * 
	 * @param year
	 *            năm
	 * @param month
	 *            tháng
	 * @param day
	 *            ngày
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateEndDate(int year, int month, int day) {
		String date = convertToString(year, month, day);
		if (!isDateValid(date)) {
			return MessageErrorProperties.getData("ER011_END_DATE");
		}
		return "";
	}

	/**
	 * Hàm tạo key cho session
	 * 
	 * @param email
	 *            chuỗi email
	 * @return String key của session
	 */
	public static String createKeySession(String email) {
		return encodeSHA1("", email).substring(0, 10);
	}

}
