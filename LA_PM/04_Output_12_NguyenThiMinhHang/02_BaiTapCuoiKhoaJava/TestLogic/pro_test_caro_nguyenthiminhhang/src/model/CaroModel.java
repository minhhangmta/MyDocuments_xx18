/**
 * Copyright(C) 2017 Luvina
 * CaroModel.java Oct 5, 2017 minhhang
 */
package model;

import java.util.ArrayList;

import javax.swing.JButton;

import view.CaroView;

/**
 * Lớp xử lý logic
 * 
 * @author minhhang
 */
public class CaroModel {

	/**
	 * 
	 */
	public CaroModel() {
	}

	public boolean checkCheoPhai(QuanCo quanCo, JButton[][] lstOCo) {
		int x = quanCo.getPosRow();
		int y = quanCo.getPosCol();
		int count = 0;
		for (int i = -4, j = 4; i <= 4 && j >= -4; i++, j--) {
			if ((x + i) >= 0 && (x + i) < Constants.ROW && (y + j) >= 0 && (y + j) < Constants.COL) {
				if (lstOCo[x + i][y + j].getText() == lstOCo[x][y].getText()) {
					count++;
					if (count >= 5) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		return false;
	}

	public boolean checkCheoTrai(QuanCo quanCo, JButton[][] lstOCo) {
		int x = quanCo.getPosRow();
		int y = quanCo.getPosCol();
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if ((x + i) >= 0 && (x + i) < Constants.ROW && (y + i) >= 0 && (y + i) < Constants.COL) {
				if (lstOCo[x + i][y + i].getText() == lstOCo[x][y].getText()) {
					count++;
					if (count >= 5) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		return false;
	}

	public boolean checkHangNgang(QuanCo quanCo, JButton[][] lstOCo) {
		int x = quanCo.getPosRow();
		int y = quanCo.getPosCol();
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if ((y + i) >= 0 && (y + i) < Constants.COL) {
				if (lstOCo[x][y + i].getText() == lstOCo[x][y].getText()) {
					count++;
					if (count >= 5) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		return false;
	}

	public boolean checkHangDoc(QuanCo quanCo, JButton[][] lstOCo) {
		int x = quanCo.getPosRow();
		int y = quanCo.getPosCol();
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if ((x + i) >= 0 && (x + i) < Constants.ROW) {
				if (lstOCo[x + i][y].getText().equals(lstOCo[x][y].getText())) {
					count++;
					if (count >= 5) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		return false;
	}

	/**
	 * Hàm kiểm tra thắng thua cho người/máy chơi
	 * 
	 * @param xRow
	 *            vị trí hàng
	 * @param yCol
	 *            vị trí cột
	 * @param namePlayer
	 *            tên nhân vật chơi: người/máy
	 * @param lstOCo
	 *            danh sách các ô cờ
	 * @return true nếu thắng, false nếu ko thắng
	 */
	public boolean checkWin(QuanCo quanCo, JButton[][] lstOCo) {
		// Kiểm tra điều kiện chơi thắng
		if (checkHangDoc(quanCo, lstOCo) || checkHangNgang(quanCo, lstOCo) || checkCheoTrai(quanCo, lstOCo)
				|| checkCheoPhai(quanCo, lstOCo)) {
			return true;
		}
		return false;
	}

	public QuanCo posComputerPlay(JButton[][] lstOCo) {
		QuanCo quanCo = new QuanCo();
		TheCo theCoView = new TheCo();
		GetTheCo getTheCo = new GetTheCo();
		ArrayList<TheCo> lstTheCoFile = getTheCo.getTheCoFile();
		String[][] matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
		// Duyệt từng mảng 5x5 trong bàn cờ
		//
		for (int i = 0; i < Constants.ROW - Constants.MATRIX_ROW + 1; i++) {
			for (int j = 0; j < Constants.COL - Constants.MATRIX_COL + 1; j++) {
				for (int l = i; l < i + Constants.MATRIX_ROW; l++) {
					for (int m = j; m < j + Constants.MATRIX_COL; m++) {
						if ("O".equals(lstOCo[l][m].getText())) {
							matrix[l - i][m - j] = "O";
						} else if ("X".equals(lstOCo[j + l][j + m].getText())) {
							matrix[l - i][m - j] = "X";
						} else {
							matrix[l - i][m - j] = "T";
						}
						theCoView.setMatrix(matrix);
					}
				}
				for (TheCo theCoFile : lstTheCoFile) {
					if (compareTheCo(theCoFile, theCoView)) {
						quanCo.setPosRow(i);
						quanCo.setPosCol(j);
					}
				}
			}
		}
		return quanCo;
	}

	public boolean compareTheCo(TheCo theCoFile, TheCo theCoView) {
		for (int i = 0; i < Constants.MATRIX_ROW; i++) {
			for (int j = 0; j < Constants.MATRIX_COL; j++) {
				if ("O".equals(theCoFile.getMatrix()[i][j]) && !"O".equals(theCoView.getMatrix()[i][j])
						|| "X".equals(theCoFile.getMatrix()[i][j]) && !"X".equals(theCoView.getMatrix()[i][j])
						|| "T".equals(theCoFile.getMatrix()[i][j]) && !"T".equals(theCoView.getMatrix()[i][j])
						|| "D".equals(theCoFile.getMatrix()[i][j]) && !"T".equals(theCoView.getMatrix()[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
}
