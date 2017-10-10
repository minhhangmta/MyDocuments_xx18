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

import constants.Constants;
import model.TheCo;

/**
 * Lớp lấy dữ liệu các thế cờ
 * 
 * @author minhhang
 */
public class DataTheCoFile {

	/**
	 * Hàm lấy thế cờ từ file
	 * 
	 * @return mảng thế cờ 2 chiều
	 */
	public ArrayList<TheCo> getTheCoFile() {
		//Khởi tạo danh sách thế cờ
		ArrayList<TheCo> lstTheCo = new ArrayList<>();
		//Khởi tạo ma trận con 5x5
		String[][] matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
		//Khai báo biến BufferedReader
		BufferedReader bufferedReader;
		//Khai báo biến FileInputStream
		FileInputStream fileInputStream;
		//Khởi tạo giá trị cho thế cờ
		TheCo theCo = null;
		//Khởi tạo biến đọc từng dòng
		String line = "";
		try {
			//Tạo đối tượng luồng và liên kết nguồn dữ liệu
			fileInputStream = new FileInputStream(new File(Constants.PATH_THE_CO));
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			//Đọc dòng đầu tiên
			line = bufferedReader.readLine();
			//Khởi tạo biến đếm hàng được đọc
			int row = 0;
			//Duyệt từ đầu đến hết file
			while (line != null) {
				// Nếu dòng được đọc không rỗng
				if (!line.isEmpty()) { 
					//duyệt từ phần tử đầu tiên đến phần tử cuối cùng của line
					for (int index = 0; index < line.length(); index++) {
						//Gán từng phần tử vào ma trận con
						matrix[row][index] = line.charAt(index) + "";
					}
					//Tăng biến đếm hàng +1
					row++;
				} else {
					//Gán ma trận vừa đọc được vào thế cờ
					theCo = new TheCo(matrix);
					//Lưu thế cờ vào danh sách thế cờ
					lstTheCo.add(theCo);
					//reset lại ma trận
					matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
					//reset biến đếm hàng 
					row = 0;
				}
				//Đọc dòng tiếp theo
				line = bufferedReader.readLine();
			}
			//Đóng file
			fileInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstTheCo;
	}
}
