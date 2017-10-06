/**
* Copyright(C) 2017 Luvina
* dataPlayCaro.java, Oct 6, 2017 minhhang
*/
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Lớp lấy dữ liệu thế cờ từ file
 * 
 * @author minhhang
 */
public class DataPlayCaro {
	/**
	 * Hàm đọc file
	 * 
	 * @return mảng thế cờ 2 chiều
	 */
	public ArrayList<TheCo> readFile() {
		ArrayList<TheCo> lstTheCo = new ArrayList<>();
		String[][] matrix = null;
		BufferedReader bufferedReader;
		FileInputStream fileInputStream;
		String line = "";
		try {
			fileInputStream = new FileInputStream(new File(Constants.PATH_THE_CO));
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			line = bufferedReader.readLine();
			int row = 0;
			while (line != null) {
				if (line.isEmpty() && matrix != null) {
					TheCo theCo = new TheCo();
					theCo.setMatrix(matrix);
					lstTheCo.add(theCo);
				} else {
					for (int i = 0; i < line.length(); i++) {
						matrix[i][row] = line.charAt(i) + "";
					}
				}
				row++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstTheCo;
	}
	
	


}
