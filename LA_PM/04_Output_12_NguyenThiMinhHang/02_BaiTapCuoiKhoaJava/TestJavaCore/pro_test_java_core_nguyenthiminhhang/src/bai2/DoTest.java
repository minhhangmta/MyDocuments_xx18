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
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CDDatabase cdDatabase = new CDDatabase();
		CD cd = new CD();
		Scanner sc = new Scanner(System.in);
		String choice;
		while (true) {
			System.out.println("Hãy nhập tùy chọn: \r\n" + "Nhập 0: Thoát khỏi chương trình\r\n"
					+ "Nhập 1: Thêm bài hát\r\n" + "Nhập 2: Xóa bài hát\r\n" + "Nhập 3: Tìm bài hát thêm tiêu đề\r\n"
					+ "Nhập 4: Tìm bài hát theo tác giả ");
			choice = sc.nextLine();
			switch (choice) {
			case "0":
				System.exit(1);
				break;
			case "1":
				cdDatabase.inputCD(cd, "Artist", cd.getArtist());
				cdDatabase.inputCD(cd, "Title", cd.getTitle());
				System.out.println(cdDatabase.insertCD(cd));
				break;
			case "2":
				cdDatabase.inputCD(cd, "Artist", cd.getArtist());
				cdDatabase.inputCD(cd, "Title", cd.getTitle());
				System.out.println(cdDatabase.removeCD(cd));
				break;
			case "3":
				System.out.println(cdDatabase.findByTitle(cdDatabase.inputKey()));
				break;
			case "4":
				System.out.println(cdDatabase.findByArtist(cdDatabase.inputKey()));
				break;
			default:
				System.out.println("Hãy nhập hàm cần test là từ 1 đến 4");
				break;
			}
		}

	}
}
