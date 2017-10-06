/**
* Copyright(C) 2017 Luvina
* dataPlayCaro.java, Oct 6, 2017 minhhang
*/
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import view.Constants;

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
	public ArrayList<String> readFile() {
		ArrayList<String> lstTheCo = new ArrayList<>();
		FileReader fileReader;
		BufferedReader bufferedReader;

		try {
			fileReader = new FileReader(Constants.PATH_THE_CO);
			bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstTheCo;
	}

}
