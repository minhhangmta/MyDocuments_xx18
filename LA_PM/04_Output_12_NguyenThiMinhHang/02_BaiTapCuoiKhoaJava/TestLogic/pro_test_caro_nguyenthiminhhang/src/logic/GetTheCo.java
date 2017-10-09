/**
* Copyright(C) 2017 Luvina
* dataPlayCaro.java, Oct 6, 2017 minhhang
*/
package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;

import model.Constants;
import model.TheCo;

/**
 * Lớp lấy dữ liệu các thế cờ
 * 
 * @author minhhang
 */
public class GetTheCo {

	/**
	 * Hàm đọc file
	 * 
	 * @return mảng thế cờ 2 chiều
	 */
	public ArrayList<TheCo> getTheCoFile() {
		// Khởi tạo danh sách thế cờ
		ArrayList<TheCo> lstTheCo = new ArrayList<>();
		// Khởi tạo ma trận 5x5
		String[][] matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
		// Khởi tạo biến BufferedReader
		BufferedReader bufferedReader;
		// Khởi tạo biến FileInputStream
		FileInputStream fileInputStream;
		// Khởi tạo 1 thế cờ
		TheCo theCo = new TheCo();
		// Khởi tạo biến lấy từng dòng được đọc trong file
		String line = "";
		try {
			// khởi tạo file cần đọc
			fileInputStream = new FileInputStream(new File(Constants.PATH_THE_CO));
			// Đọc file
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			// Đọc dòng đầu tiên trong file
			line = bufferedReader.readLine();
			// Khởi tạo biến đếm hàng cho ma trận con
			int row = 0;
			// Duyệt từng dòng trong file đến hết
			while (line != null) {
				// Nếu dòng được đọc không bị rỗng
				if (!line.isEmpty()) {
					// Duyệt từng phần tử trong line vừa đọc
					for (int index = 0; index < line.length(); index++) {
						// Gán từng phần tử trong line vào ma trận
						matrix[row][index] = line.charAt(index) + "";
					}
					// Tăng hàng đọc
					row++;
				} else {
					//lưu ma trận con vào thế cờ
					theCo.setMatrix(matrix);
					//Lưu thế cờ đó vào danh sách thế cờ
					lstTheCo.add(theCo);
					//Reset thế cờ mới
					theCo = new TheCo();
					//Reset ma trận mới
					matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
					//Reset biến đếm hàng 
					row = 0;
				}
				line = bufferedReader.readLine();
			}
			fileInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstTheCo;
	}

	/**
	 * Hàm lấy danh sách thế cờ từ giao diện
	 * 
	 * @param lstOCo
	 * @return lstTheCoView danh sách thế cờ từ giao diện
	 */
	public ArrayList<TheCo> getTheCoView(JButton[][] lstOCo) {
		ArrayList<TheCo> lstTheCoView = new ArrayList<>();
		TheCo theCoView = new TheCo();
		String[][] matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
		// Duyệt từng mảng 5x5 trong bàn cờ
		//
		for (int i = 0; i < Constants.ROW - Constants.MATRIX_ROW + 1; i++) {
			for (int j = 0; j < Constants.COL - Constants.MATRIX_COL + 1; j++) {
				for (int l = i; l < i + Constants.MATRIX_ROW; l++) {
					for (int m = j; m < j + Constants.MATRIX_COL; m++) {
						if ("O".equals(lstOCo[l][m].getText())) {
							matrix[l - i][m - j] = "O";
						} else if ("X".equals(lstOCo[l][m].getText())) {
							matrix[l - i][m - j] = "X";
						} else {
							matrix[l - i][m - j] = "T";
						}
						theCoView.setMatrix(matrix);
					}
				}
				lstTheCoView.add(theCoView);
				theCoView = new TheCo();
				matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
			}
		}
		return lstTheCoView;
	}
}
