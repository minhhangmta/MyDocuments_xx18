/**
 * Copyright(C) 2017 Luvina
 * ReadFile.java Oct 3, 2017 HungNK
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Class thực hiện việc đọc file
 * @author HungNK
 *
 */
public class ReadFile {
	/**
	 * Hàm lấy ma trận từ file
	 * 
	 * @param String
	 *            fileName đường dẫn đến file thế cờ
	 * 
	 * @return List<char[][]> 1 danh sách cách mảng char 2 chiều
	 * 
	 */

	public List<char[][]> getMatrixFile(String fileName) {
		try {
			// Khai báo đối tượng FileReader
			FileReader f = new FileReader(fileName);
			// Khởi tạo đối được BufferedReader
			BufferedReader br = new BufferedReader(f);
			// Tạo ma trận char 2 chiều
			char[][] matrix = new char[5][5];
			// Lưu các mảng ma trận vào list
			List<char[][]> list = new ArrayList<>();
			// Khởi tạo biến đếm count
			int count = 0;
			// Khai báo 1 biến String
			String str;
			// Nếu str khác null
			while ((str = br.readLine()) != null  ) {
				if((str == null) || ("".equals(str))) {
					continue;
				}
				// Kiểm tra biến đếm count chia hết cho 5 và khác 0
				if (count % 5 == 0 && count > 0) {
					// Nếu thỏa mãn điều kiện thì thêm ma trận lấy được từ file vào list
					list.add(matrix);
					// Khởi tạo lại ma trận
					matrix = new char[5][5];
					// Set lại giá trị cho biến đếm
					count = 0;
					// Tiếp tục
					continue;
				}
				// Duyệt để lưu ma trận 5x5
				for (int i = 0; i < 5; i++) {
					char character = str.charAt(i);
					// Set giá trị cho ma trận lấy từ file
					matrix[count][i] = character;
				}
				// Tăng biến đếm lên 1
				count++;

			}
			// Trả về list
			return list;
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy file !");
			//System.out.println("Không tìm thấy file !");
			//Không thấy file thì trả về giá trị null
			return null;
		} catch (IOException ex) {
			//Hiện thông báo lỗi khi xảy ra IOException
			JOptionPane.showMessageDialog(null, "Lỗi IOException !");
			//System.out.println("Lỗi IOException !");
			return null; //Xảy ra lỗi thì trả về null
		}

	}

}
