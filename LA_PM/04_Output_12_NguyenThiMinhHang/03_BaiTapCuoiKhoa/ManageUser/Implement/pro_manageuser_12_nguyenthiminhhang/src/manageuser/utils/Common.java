/**
 * Copyright(C) 2017 Luvina
 * Common.java Oct 20, 2017 minhhang
 */
package manageuser.utils;

import java.io.IOException;
import java.io.PrintWriter;
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
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.ConfigProperties;
import manageuser.properties.ExportFileProperties;
import manageuser.properties.MessageErrorProperties;
import manageuser.properties.MessageProperties;

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
			// lấy thuật toán mã hóa SHA1
			digest = MessageDigest.getInstance("SHA1");
			// update thuật toán với input là mảng byte
			digest.update(input.getBytes());
			// mã hóa data và đưa về đối tượng BigInteger
			BigInteger bigInteger = new BigInteger(1, digest.digest());
			// chuyển về kiểu string hexa
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
			// phan doan trang (0-1-2...)
			int phanDoan = currentPage / Common.getLimitPage();
			// tính số dư phân đoạn trang
			int du = currentPage % Common.getLimitPage();
			// neu chia het thi phan doan phai giam 1
			if (du == 0) {
				phanDoan--;
			}
			for (int i = 1; i <= Common.getLimitPage(); i++) {
				// tính trang thuộc phân đoạn hiện tại
				int page = phanDoan * Common.getLimitPage() + i;
				// add list
				listPage.add(page);
			}
		}
		return listPage;
	}

	/**
	 * Hàm chuẩn hóa chuỗi có kí tự wildcard để đưa vào query
	 * 
	 * @param key
	 *            tên cần chuẩn hóa
	 * @return String chuỗi đã được chuẩn hóa
	 */
	public static String replaceWildCard(String key) {
		if (!isEmptyOrNull(key)) {
			key = key.replace("\\", "\\\\");
			key = key.replace("%", "\\%");
			key = key.replace("_", "\\_");
		}
		return key.trim();
	}

	/**
	 * Hàm kiểm tra xem một chuỗi có là null hoặc rỗng không
	 * 
	 * @param input
	 *            chuỗi cần kiểm tra
	 * @return true nếu null hoặc rỗng, false nếu không phải
	 */
	public static boolean isEmptyOrNull(String input) {
		if (input == null || input.isEmpty()) {
			return true;
		}
		return false;
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
			return 0;
		}
	}

	/**
	 * Hàm thay đổi kiểu sort
	 * 
	 * @param sortType
	 *            giá trị kiểu sort ban đầu
	 * @return String giá trị kiểu sort sau khi đổi
	 */
	public static String changeSortType(String sortType) {
		if (sortType.equals(Constant.DECREASE)) {
			sortType = Constant.ASCENDING;
		} else if (sortType.equals(Constant.ASCENDING)) {
			sortType = Constant.DECREASE;
		}
		return sortType;
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

	/**
	 * Hàm lấy giá trị từ request
	 * 
	 * @param request
	 *            đối tượng HttpServletRequest
	 * @param key
	 *            từ khóa cần lấy giá trị từ request
	 * @param defaultValue
	 *            giá trị mặc định của key
	 * @return String giá trị của key
	 */
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
		String dt = "";
		if (date != null) {
			DateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
			dt = df.format(date);
		}
		return dt;
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
		if (!checkInputTxt(username)) {// neu khong nhap
			errUsername = MessageErrorProperties.getData("ER001_USERNAME");
		} else {
			Pattern pattern = Pattern.compile(getRegex());
			boolean validFormat = pattern.matcher(username).matches();
			boolean validUser = new TblUserLogicImpl().existUsername(username);
			// do dai trong khoang min - max
			if (!checkMinLength(getlengthString("minLengthUsername"), username)
					|| !checkMaxLength(getlengthString("maxLengthUsername"), username)) {
				errUsername = MessageErrorProperties.getData("ER007_USERNAME");
			} else if (!validFormat) {// sai dinh dang
				errUsername = MessageErrorProperties.getData("ER019");
			} else if (validUser) {// da ton tai
				errUsername = MessageErrorProperties.getData("ER003_USERNAME");
			}
		}
		return errUsername;
	}

	/**
	 * Hàm lấy giá trị length string từ file config.properties
	 * 
	 * @param keyProperties
	 *            key cần get từ file config.properties
	 * @return int độ dài chuỗi
	 */
	public static int getlengthString(String keyProperties) {
		if (!keyProperties.isEmpty()) {
			return tryParseInt(ConfigProperties.getData(keyProperties));
		}
		return 0;
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
		if (groupId < 0) {
			errGroup = MessageErrorProperties.getData("ER002");
		} else if (!valid) {// Nhom khong ton tai
			errGroup = MessageErrorProperties.getData("ER004_GROUP");
		}
		return errGroup;
	}

	/**
	 * Check không nhập
	 * 
	 * @param key
	 *            chuỗi cần check
	 * @return true nếu nhập, false nếu không nhập
	 */
	public static boolean checkInputTxt(String key) {
		if (key.length() < 1) {
			return false;
		}
		return true;
	}

	/**
	 * Check chuỗi có thỏa mãn độ dài maxLength không
	 * 
	 * @param key
	 *            chuỗi cần check
	 * @param maxLength
	 *            độ dài max chuỗi cần check
	 * @return true nếu nhỏ hơn hoặc bằng, false nếu vượt quá size chuỗi
	 */
	public static boolean checkMaxLength(int maxLength, String key) {
		if (key.length() > maxLength) {
			return false;
		}
		return true;
	}

	/**
	 * Check chuỗi có thỏa mãn độ dài minLength không
	 * 
	 * @param key
	 *            chuỗi cần check
	 * @param minLength
	 *            độ dài min chuỗi cần check
	 * @return true nếu lớn hơn hoặc bằng, false nếu nhỏ hơn
	 */
	public static boolean checkMinLength(int minLength, String key) {
		if (key.length() < minLength) {
			return false;
		}
		return true;
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
		if (!checkInputTxt(fullName)) {
			errName = MessageErrorProperties.getData("ER001_FULLNAME");
		} else if (!checkMaxLength(getlengthString("maxLengthName"), fullName)) {// Nếu quá 255 kí tự
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
		if (!checkMaxLength(getlengthString("maxLengthName"), fullnameKana)) {
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
	 * @param loginName
	 *            tên đăng nhập
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateEmail(String email, int userId) {
		String errEmail = "";
		if (!checkInputTxt(email)) {// Khong nhap
			errEmail = MessageErrorProperties.getData("ER001_EMAIL");
		} else {
			if (!checkMaxLength(getlengthString("maxLengthEmail"), email)) {// Nếu quá 255 kí tự
				errEmail = MessageErrorProperties.getData("ER006_EMAIL");
			} else {
				Pattern pattern = Pattern.compile(ConfigProperties.getData("regexEmail"));
				boolean validFormat = pattern.matcher(email).matches();
				if (!validFormat) {// Nếu sai định dạng
					errEmail = MessageErrorProperties.getData("ER005_EMAIL");
				} else {
					boolean validEmail = new TblUserLogicImpl().existEmail(email, userId);
					if (validEmail) {// Nếu đã tồn tại email
						errEmail = MessageErrorProperties.getData("ER003_EMAIL");
					}
				}
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
		if (!checkInputTxt(tel)) {// Khong nhap
			errTel = MessageErrorProperties.getData("ER001_TEL");
		} else {
			if (!checkMaxLength(getlengthString("maxLengthTel"), tel)) {// check max length
				errTel = MessageErrorProperties.getData("ER006_TEL");
			} else {
				Pattern pattern = Pattern.compile(ConfigProperties.getData("regexTel"));
				boolean validFormat = pattern.matcher(tel).matches();
				if (!validFormat) {// định dạng xxxx-xxxx-xxxx
					errTel = MessageErrorProperties.getData("ER005_TEL");
				}
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
		if (!checkInputTxt(password)) {// check khong nhap
			errPass = MessageErrorProperties.getData("ER001_PASS");
		} else {
			// check length
			if (!checkMinLength(getlengthString("minLengthPass"), password)
					|| !checkMaxLength(getlengthString("maxLengthPass"), password)) {
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
		String errMsg = "";
		if (!password.equals(passConfirm)) {
			errMsg = MessageErrorProperties.getData("ER017");
		}
		return errMsg;
	}

	/**
	 * Hàm validate trình độ tiếng nhật
	 * 
	 * @param codeLevel
	 *            mã trình độ
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateCodeLevel(String codeLevel) {
		String errMsg = "";
		boolean valid = new TblUserLogicImpl().existCodeLevel(codeLevel);
		// Nếu không tồn tại
		if (codeLevel != Constant.EMPTY_STRING && !valid) {
			errMsg = MessageErrorProperties.getData("ER004_LEVEL");
		}
		return errMsg;
	}

	/**
	 * Hàm validate điểm total
	 * 
	 * @param total
	 *            điểm
	 * @param codeLevel
	 *            mã trình độ
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateTotal(String total, String codeLevel) {
		String errTotal = "";
		String totalStr = String.valueOf(total);
		// trình độ japan được chọn
		if (codeLevel != Constant.EMPTY_STRING) {
			// nếu ko nhập
			if (!checkInputTxt(totalStr)) {
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
		String errorMsg = "";
		String date = convertToString(year, month, day);
		if (!isDateValid(date)) {
			errorMsg = MessageErrorProperties.getData("ER011_BIRTHDAY");
		}
		return errorMsg;
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
		String errMsg = "";
		String date = convertToString(year, month, day);
		if (!isDateValid(date)) {
			errMsg = MessageErrorProperties.getData("ER011_START_DATE");
		}
		return errMsg;
	}

	/**
	 * Hàm validate ngày kết thúc
	 * 
	 * @param yearStart
	 *            năm bắt đầu
	 * @param monthStart
	 *            tháng bắt đầu
	 * @param dayStart
	 *            ngày bắt đầu
	 * @param yearEnd
	 *            năm kết thúc
	 * @param monthEnd
	 *            tháng kết thúc
	 * @param dayEnd
	 *            ngày kết thúc
	 * @return String chuỗi thông báo lỗi
	 */
	public static String validateEndDate(int yearStart, int monthStart, int dayStart, int yearEnd, int monthEnd,
			int dayEnd) {
		String errMsg = "";
		String endDate = convertToString(yearEnd, monthEnd, dayEnd);
		if (!isDateValid(endDate)) {// sai định dạng
			errMsg = MessageErrorProperties.getData("ER011_END_DATE");
			// so sánh end
		} else if (!compareStartEndDate(yearStart, monthStart, dayStart, yearEnd, monthEnd, dayEnd)) {
			errMsg = MessageErrorProperties.getData("ER012");
		}
		return errMsg;
	}

	/**
	 * Hàm so sánh end date và start date
	 * 
	 * @param yearStart
	 *            năm bắt đầu
	 * @param monthStart
	 *            tháng bắt đầu
	 * @param dayStart
	 *            ngày bắt đầu
	 * @param yearEnd
	 *            năm kết thúc
	 * @param monthEnd
	 *            tháng kết thúc
	 * @param dayEnd
	 *            ngày kết thúc
	 * @return true nếu end date lớn hơn start date, false nếu ngược lại
	 */
	public static boolean compareStartEndDate(int yearStart, int monthStart, int dayStart, int yearEnd, int monthEnd,
			int dayEnd) {
		boolean check = true;
		// nếu năm = nhau
		if (yearStart == yearEnd) {
			// nếu tháng start lớn hơn
			if (monthStart > monthEnd) {
				check = false;
			} else if (monthStart == monthEnd) {// nếu tháng bằng nhau
				if (dayStart >= dayEnd) {// nếu ngày start lớn hơn =
					check = false;
				}
			}
		} else if (yearStart > yearEnd) {// năm start lớn hơn
			check = false;
		}
		return check;
	}

	/**
	 * Hàm tạo key cho session
	 * 
	 * @return String key của session
	 */
	public static String createKeySession() {
		String salt = createSaltString();
		return encodeSHA1("", salt).substring(0, 10);
	}

	/**
	 * Hàm sinh chuỗi salt ngẫu nhiên
	 * 
	 * @return String chuỗi salt
	 */
	public static String createSaltString() {
		char[] chars = Constant.SALT_CHARS.toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		// chuoi random 20 ki tu
		for (int i = 0; i < 20; i++) {
			char c = chars[random.nextInt(chars.length)];
			stringBuilder.append(c);
		}
		return stringBuilder.toString();
	}

	/**
	 * Lấy từng ngày, tháng, năm từ Date
	 * 
	 * @param date
	 *            ngày cần lấy
	 * @return ArrayList<Integer> mảng 3 phần tử ngày, tháng, năm theo thứ tự
	 */
	public static ArrayList<Integer> getEachElementFromDate(Date date) {
		ArrayList<Integer> list = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		list.add(cal.get(Calendar.YEAR));
		list.add(cal.get(Calendar.MONTH) + 1);
		list.add(cal.get(Calendar.DAY_OF_MONTH));
		return list;
	}

	/**
	 * Hàm lấy header từ file_export.properties theo key tương ứng
	 * 
	 * @param key
	 *            tên loại header cần lấy
	 * @return String giá trị key cần lấy
	 */
	public static String getHeader(String key) {
		return ExportFileProperties.getData(key);
	}

	/**
	 * Hàm generate dữ liệu sang kiểu String
	 * 
	 * @param list
	 *            đối tượng list cần generate
	 * @param download
	 *            đối tượng download
	 * @return String chuỗi dữ liệu đã được generate
	 * 
	 */
	public static String generateData(List<?> list, String download) {
		StringBuilder result = new StringBuilder("");
		if (list != null) {
			if ("userInfor".equals(download)) {
				for (Object userInfor : list) {
					result.append(userInfor.toString());
				}
			}
		}
		return result.toString();
	}

	/**
	 * Hàm export ra file CSV
	 * 
	 * @param response
	 *            đối tượng HttpServletResponse
	 * @param data
	 *            dữ liệu cần export
	 * @param header
	 *            chuỗi header của file
	 * @param fileName
	 *            tên file cần lưu
	 */
	public static void exportCSVFile(HttpServletResponse response, String data, String header, String fileName) {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		try {
			PrintWriter printWriter = response.getWriter();
			// Neu co du lieu
			if (!data.isEmpty()) {
				printWriter.append(header);
				printWriter.append(data);
			} else {// Neu khong co du lieu
				printWriter.append(MessageProperties.getData("MSG005"));
			}
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hàm add chuỗi thông báo lỗi vào list error
	 * 
	 * @param lstError
	 *            list thông báo lỗi
	 * @param errorMsg
	 *            thông báo lỗi
	 * @return true nếu có lỗi để add, false nếu không có lỗi để add
	 */
	public static boolean addErrorString(List<String> lstError, String errorMsg) {
		if (!errorMsg.isEmpty()) {
			lstError.add(errorMsg);
			return true;
		}
		return false;
	}

	/**
	 * Hàm lấy giá trị đúng của sort từ view
	 * 
	 * @param currentSort
	 *            giá trị sort hiện tại
	 * @param urlSort
	 *            giá trị sort lấy từ url
	 * @return String giá trị sort sau khi lọc
	 */
	public static String getCorrectSort(String currentSort, String urlSort) {
		if ("ASC".equals(urlSort) || "DESC".equals(urlSort)) {
			return urlSort;
		}
		return currentSort;
	}

	/**
	 * Hàm lấy giá trị TblUser từ UserInfor
	 * 
	 * @param userInfor
	 *            đối tượng UserInfor
	 * @return TblUser đối tượng TblUser
	 */
	public static TblUser getTblUserFromUserInfor(UserInfor userInfor) {
		TblUser tblUser = new TblUser();
		String fullNameKana = userInfor.getFullNameKana();
		// fullnameKana lay tu textbox la rong
		if (fullNameKana.isEmpty()) {
			fullNameKana = null;
		}
		int userId = userInfor.getUserId();
		// truong hop edit co userId
		if (userId > 0) {
			tblUser.setUserId(userId);
		} else {// truong hop insert chua co userId
			// get salt from createSaltString()
			tblUser.setSalt(Common.createSaltString());
			// ma hoa password
			tblUser.setPasswords(Common.encodeSHA1(userInfor.getPasswords(), tblUser.getSalt()));
			tblUser.setRole(Constant.ROLE_USER);
		}
		tblUser.setLoginName(userInfor.getLoginName());
		tblUser.setFullName(userInfor.getFullName());
		tblUser.setFullNameKana(fullNameKana);
		tblUser.setEmail(userInfor.getEmail());
		tblUser.setTel(userInfor.getTel());
		tblUser.setBirthday(userInfor.getBirthday());
		tblUser.setGroupId(userInfor.getGroupId());
		return tblUser;
	}

	/**
	 * Hàm lấy giá trị TblDetailUserJapan từ UserInfor
	 * 
	 * @param userInfor
	 *            đối tượng UserInfor
	 * @return TblDetailUserJapan đối tượng TblDetailUserJapan
	 */
	public static TblDetailUserJapan getDetailUserJapanFromUserInfor(UserInfor userInfor) {
		TblDetailUserJapan tblDetailUserJapan = new TblDetailUserJapan();
		tblDetailUserJapan.setUserId(userInfor.getUserId());
		tblDetailUserJapan.setCodeLevel(userInfor.getCodeLevel());
		tblDetailUserJapan.setStartDate(userInfor.getStartDate());
		tblDetailUserJapan.setEndDate(userInfor.getEndDate());
		tblDetailUserJapan.setTotal(userInfor.getTotal());
		return tblDetailUserJapan;
	}
}
