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

import javax.swing.JButton;

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
		ArrayList<TheCo> lstTheCo = new ArrayList<>();
		String[][] matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
		BufferedReader bufferedReader;
		FileInputStream fileInputStream;
		TheCo theCo = new TheCo();
		String line = "";
		try {
			fileInputStream = new FileInputStream(new File(Constants.PATH_THE_CO));
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			line = bufferedReader.readLine();
			int row = 0;
			while (line != null) {
				if (!line.isEmpty()) {
					for (int i = 0; i < Constants.MATRIX_ROW; i++) {
						int j = 0;
						for (int index = 0; index < line.length(); index++) {
							matrix[i][j] = line.charAt(index) + "";
							j++;
						}
						line = bufferedReader.readLine();
					}
				} else if (matrix != null) {
					theCo.setMatrix(matrix);
					lstTheCo.add(theCo);
					// theCo = new TheCo();
					// matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
				}
				row++;
			}
			fileInputStream.close();
		} catch (
		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstTheCo;
	}

	// public ArrayList<TheCo> getTheCoView(JButton[][] lstOCo) {
	// ArrayList<TheCo> lstTheCo = new ArrayList<>();
	// TheCo theCo = new TheCo();
	// String[][] matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
	// for (int i = 0; i < Constants.ROW - Constants.MATRIX_ROW + 1; i++) {
	// for (int j = 0; j < Constants.COL - Constants.MATRIX_COL + 1; j++) {
	// for (int l = i; l < i + Constants.MATRIX_ROW; l++) {
	// for (int m = j; m < j + Constants.MATRIX_COL; m++) {
	// if ("O".equals(lstOCo[l][m].getText())) {
	// matrix[l - i][m - j] = "O";
	// } else if ("X".equals(lstOCo[j + l][j + m].getText())) {
	// matrix[l - i][m - j] = "X";
	// } else {
	// matrix[l - i][m - j] = "T";
	// }
	// theCo.setMatrix(matrix);
	// }
	// }
	// lstTheCo.add(theCo);
	// }
	// }
	// return lstTheCo;
	// }
}
