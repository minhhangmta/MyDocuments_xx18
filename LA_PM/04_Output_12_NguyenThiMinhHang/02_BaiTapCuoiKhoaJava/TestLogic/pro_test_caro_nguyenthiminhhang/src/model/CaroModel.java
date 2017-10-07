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
	private JButton[][] lstOCo;

	/**
	 * 
	 */
	public CaroModel() {
		this.lstOCo = CaroView.lstOCo;
	}

	public boolean checkCheoPhai(String namePlayer, QuanCo quanCo) {
		int x = quanCo.getPosRow();
		int y = quanCo.getPosCol();
		int count = 0;
		for (int i = -4, j = 4; i <= 4 && j >= -4; i++, j--) {
			if ((x + i) >= 0 && (x + i) < Constants.ROW && (y + j) >= 0 && (y + j) < Constants.COL) {
				if (lstOCo[x + i][y + j].getText() == namePlayer) {
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

	public boolean checkCheoTrai(String namePlayer, QuanCo quanCo) {
		int x = quanCo.getPosRow();
		int y = quanCo.getPosCol();
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if ((x + i) >= 0 && (x + i) < Constants.ROW && (y + i) >= 0 && (y + i) < Constants.COL) {
				if (lstOCo[x + i][y + i].getText() == namePlayer) {
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

	public boolean checkHangNgang(String namePlayer, QuanCo quanCo) {
		int x = quanCo.getPosRow();
		int y = quanCo.getPosCol();
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if ((y + i) >= 0 && (y + i) < Constants.COL) {
				if (lstOCo[x][y + i].getText() == namePlayer) {
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

	public boolean checkHangDoc(String namePlayer, QuanCo quanCo) {
		int x = quanCo.getPosRow();
		int y = quanCo.getPosCol();
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if ((x + i) >= 0 && (x + i) < Constants.ROW) {
				if (lstOCo[x + i][y].getText().equals(namePlayer)) {
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
	public boolean checkWin(String namePlayer, QuanCo quanCo) {

		// Kiểm tra điều kiện chơi thắng
		if (checkHangDoc(namePlayer, quanCo) || checkHangNgang(namePlayer, quanCo) || checkCheoTrai(namePlayer, quanCo)
				|| checkCheoPhai(namePlayer, quanCo)) {
			return true;
		}
		return false;
	}

	public int posPCPlay() {

		return 0;
	}

}
