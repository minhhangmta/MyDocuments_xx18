/**
 * Copyright(C) 2017 Luvina
 * UserInfor.java Oct 25, 2017 minhhang
 */
package manageuser.entities;

import java.util.Date;

import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Lớp đối tượng UserInfor
 * 
 * @author minhhang
 */
public class UserInfor {
	private int groupId;
	private String loginName;
	private String passwords;
	private String confirmPassword;
	private String fullNameKana;
	private int userId;
	private String fullName;
	private String email;
	private String tel;
	private String groupName;
	private String nameLevel;
	private String codeLevel;
	private Date birthday;
	private Date startDate;
	private Date endDate;
	private String total;
	private int year;
	private int month;
	private int day;
	private int yearBirthday;
	private int monthBirthday;
	private int dayBirthday;
	private int yearStartDate;
	private int monthStartDate;
	private int dayStartDate;
	private int yearEndDate;
	private int monthEndDate;
	private int dayEndDate;

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the passwords
	 */
	public String getPasswords() {
		return passwords;
	}

	/**
	 * @param passwords
	 *            the passwords to set
	 */
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the fullNameKana
	 */
	public String getFullNameKana() {
		return fullNameKana;
	}

	/**
	 * @param fullNameKana
	 *            the fullNameKana to set
	 */
	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}

	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}

	/**
	 * @param codeLevel
	 *            the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return the yearBirthday
	 */
	public int getYearBirthday() {
		return yearBirthday;
	}

	/**
	 * @param yearBirthday
	 *            the yearBirthday to set
	 */
	public void setYearBirthday(int yearBirthday) {
		this.yearBirthday = yearBirthday;
	}

	/**
	 * @return the monthBirthday
	 */
	public int getMonthBirthday() {
		return monthBirthday;
	}

	/**
	 * @param monthBirthday
	 *            the monthBirthday to set
	 */
	public void setMonthBirthday(int monthBirthday) {
		this.monthBirthday = monthBirthday;
	}

	/**
	 * @return the dayBirthday
	 */
	public int getDayBirthday() {
		return dayBirthday;
	}

	/**
	 * @param dayBirthday
	 *            the dayBirthday to set
	 */
	public void setDayBirthday(int dayBirthday) {
		this.dayBirthday = dayBirthday;
	}

	/**
	 * @return the yearStartDate
	 */
	public int getYearStartDate() {
		return yearStartDate;
	}

	/**
	 * @param yearStartDate
	 *            the yearStartDate to set
	 */
	public void setYearStartDate(int yearStartDate) {
		this.yearStartDate = yearStartDate;
	}

	/**
	 * @return the monthStartDate
	 */
	public int getMonthStartDate() {
		return monthStartDate;
	}

	/**
	 * @param monthStartDate
	 *            the monthStartDate to set
	 */
	public void setMonthStartDate(int monthStartDate) {
		this.monthStartDate = monthStartDate;
	}

	/**
	 * @return the dayStartDate
	 */
	public int getDayStartDate() {
		return dayStartDate;
	}

	/**
	 * @param dayStartDate
	 *            the dayStartDate to set
	 */
	public void setDayStartDate(int dayStartDate) {
		this.dayStartDate = dayStartDate;
	}

	/**
	 * @return the yearEndDate
	 */
	public int getYearEndDate() {
		return yearEndDate;
	}

	/**
	 * @param yearEndDate
	 *            the yearEndDate to set
	 */
	public void setYearEndDate(int yearEndDate) {
		this.yearEndDate = yearEndDate;
	}

	/**
	 * @return the monthEndDate
	 */
	public int getMonthEndDate() {
		return monthEndDate;
	}

	/**
	 * @param monthEndDate
	 *            the monthEndDate to set
	 */
	public void setMonthEndDate(int monthEndDate) {
		this.monthEndDate = monthEndDate;
	}

	/**
	 * @return the dayEndDate
	 */
	public int getDayEndDate() {
		return dayEndDate;
	}

	/**
	 * @param dayEndDate
	 *            the dayEndDate to set
	 */
	public void setDayEndDate(int dayEndDate) {
		this.dayEndDate = dayEndDate;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}

	/**
	 * @param nameLevel
	 *            the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * 
	 */
	public UserInfor() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// UserID,LoginName,FullName,Birthday,GroupName,Email,Tel,NameLevel,EndDate,Total
		StringBuilder result = new StringBuilder("");
		result.append("\n");
		result.append(userId);
		result.append(Constant.COMMA_CHAR);
		result.append(loginName);
		result.append(Constant.COMMA_CHAR);
		result.append(fullName);
		result.append(Constant.COMMA_CHAR);
		result.append(birthday);
		result.append(Constant.COMMA_CHAR);
		result.append(groupName);
		result.append(Constant.COMMA_CHAR);
		result.append(email);
		result.append(Constant.COMMA_CHAR);
		result.append(tel);
		if (codeLevel != null) {
			result.append(Constant.COMMA_CHAR);
			result.append(nameLevel);
			result.append(Constant.COMMA_CHAR);
			result.append(Common.convertDateToString(endDate));
			result.append(Constant.COMMA_CHAR);
			result.append(total);
		}
		return result.toString();
	}

}
