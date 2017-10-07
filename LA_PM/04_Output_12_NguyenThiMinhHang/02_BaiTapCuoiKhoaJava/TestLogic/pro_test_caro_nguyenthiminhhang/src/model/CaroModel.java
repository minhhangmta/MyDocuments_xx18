/**
 * Copyright(C) 2017 Luvina
 * CaroModel.java Oct 5, 2017 minhhang
 */
package model;

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

	public boolean checkCheoPhai(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		int count = 0;
		for (int i = -4, j = 4; i <= 4 && j >= -4; i++, j--) {
			if ((xRow + i) >= 0 && (xRow + i) < Constants.ROW && (yCol + i) >= 0 && (yCol + i) < Constants.COL) {
				if (lstOCo[xRow + i][yCol + i].getText() == namePlayer) {
					count++;
					if (count < 5) {
						count = 0;
					}
				}
			}
		}
		if (count >= 5) {
			return true;
		}
		return false;

	}

	public boolean checkCheoTrai(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if ((xRow + i) >= 0 && (xRow + i) < Constants.ROW && (yCol + i) >= 0 && (yCol + i) < Constants.COL) {
				if (lstOCo[xRow + i][yCol + i].getText() == namePlayer) {
					count++;
					if (count < 5) {
						count = 0;
					}
				}
			}
		}
		if (count >= 5) {
			return true;
		}
		return false;
	}

	public boolean checkHangNgang(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if ((yCol + i) >= 0 && (yCol + i) < Constants.COL) {
				if (lstOCo[xRow][yCol + i].getText() == namePlayer) {
					count++;
					if (count < 5) {
						count = 0;
					}
				}
			}
		}
		if (count >= 5) {
			return true;
		}
		return false;
	}

	public boolean checkHangDoc(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if ((xRow + i) >= 0 && (xRow + i) < Constants.ROW) {
				if (lstOCo[xRow + i][yCol].getText() == namePlayer) {
					count++;
					if (count < 5) {
						count = 0;
					}
				}
			}
		}
		if (count >= 5) {
			return true;
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
	public boolean checkWin(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		// Kiểm tra điều kiện chơi thắng
		if (checkHangDoc(xRow, yCol, namePlayer, lstOCo) || checkHangNgang(xRow, yCol, namePlayer, lstOCo)
				|| checkCheoTrai(xRow, yCol, namePlayer, lstOCo) || checkCheoPhai(xRow, yCol, namePlayer, lstOCo)) {
			return true;
		}
		return false;
	}

	public int posPCPlay() {

		return 0;
	}

}
