/**
* Copyright(C) 2017 Luvina
* DoTest.java, Sep 26, 2017 minhhang
*/
package bai2;

import java.sql.Connection;
import java.util.Scanner;

/**
 * Lớp test các method trong CDDatabase
 * 
 * @author minhhang
 */
public class DoTest {
	/**
	 * Hàm main
	 * @param args
	 */
	public static void main(String[] args) {
		CDDatabase cdDatabase = new CDDatabase();
		CD cd1 = new CD("ABC", "xxxssa 100%");
		CD cd2 = new CD("Taylor", "Style");
		Scanner sc = new Scanner(System.in);
		int choice;
		while (true) {
			System.out.println("Hay nhap vao so (1-4) de chon: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println(cdDatabase.insertCD(cd1));
				return;
			case 2:
				System.out.println(cdDatabase.removeCD(cd2));
				return;
			case 3:
				System.out.println(cdDatabase.findByTitle("0%").toString());
				return;
			case 4:
				System.out.println(cdDatabase.findByArtist("ha").toString());
				return;
			default:
				System.out.println("Hãy nhập hàm cần test là từ 1 đến 4");
				break;
			}
		}

	}
}
