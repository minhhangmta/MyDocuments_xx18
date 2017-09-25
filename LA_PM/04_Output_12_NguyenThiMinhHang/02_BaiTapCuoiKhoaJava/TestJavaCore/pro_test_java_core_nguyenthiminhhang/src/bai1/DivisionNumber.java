/**
 * Copyright(C) 2017 Luvina
 * DivisionNumber.java Sep 25, 2017 minhhang
 */
package bai1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description class here
 * 
 * @author minhhang
 */
public class DivisionNumber {

	/**
	 * Hàm nhập giá trị một số
	 * 
	 * @param character
	 *            là tham số kiểu String. Kí tự (A,B,...)
	 * @return number là số kiểu String vừa nhập vào
	 */
	public static String getData(String character) {
		Scanner sc = new Scanner(System.in);
		String number;
		System.out.print("Giá trị [" + character + "]: ");
		number = sc.nextLine();
		return number;
	}

	/**
	 * Hàm validate giá trị
	 * 
	 * @param number
	 *            là tham số cần kiểm tra
	 * @return errorNotice là chuỗi thông báo lỗi
	 */
	public static String validateData(String number, String character) {
		String errorNotice = "";
		// Kiểm tra đã nhập hay chưa
		if (number.length() == 0) {
			errorNotice = "Hãy nhập giá trị cho [" + character + "] ";
		} else {
			// Kiểm tra giá trị nhập vào là số và >0
			Pattern pattern = Pattern.compile("\\d");
			Matcher matcher = pattern.matcher(number);
			boolean check = matcher.matches();
			if (!check) {
				errorNotice = "Giá trị [" + character + "] phải là giá trị số và > 0. Hãy nhập lại.";
			} else if (Integer.parseInt(number) <= 0) {
				errorNotice = "Giá trị [" + character + "] phải là giá trị số và > 0. Hãy nhập lại.";
			}
			// Kiểm tra giá trị nhập vào ko được lớn hơn 5 số
			else if (number.length() > 5) {
				errorNotice = "Giá trị [" + character + "] không được lớn hơn 5 số. Hãy nhập lại.";
			}

		}
		return errorNotice;
	}

	/**
	 * Hàm tính thương của 2 số
	 * 
	 * @param number1
	 *            là tử số
	 * @param number2
	 *            là mẫu số
	 * @return thương của 2 số kiểu số double
	 */
	public static double divideNumber(int number1, int number2) {
		return (double) number1 / number2;
	}

	/**
	 * Hàm in ra kết quả thương của 2 số
	 * 
	 * @param result
	 *            là kết quả cần in, kiểu double
	 * @return chuỗi kết quả thương 2 số
	 */
	public static String printResult(double result) {
		return "Thương của A/B bằng: " + result;
	}

}
