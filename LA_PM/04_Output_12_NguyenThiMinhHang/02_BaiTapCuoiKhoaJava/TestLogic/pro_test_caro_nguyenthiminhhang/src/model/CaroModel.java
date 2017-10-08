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
	private ArrayList<TheCo> lstTheCoFile;
	private GetTheCo getTheCo = new GetTheCo();

	/**
	 * 
	 */
	public CaroModel() {
		lstTheCoFile = getTheCo.getTheCoFile();
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

	public JButton posComputerPlay(JButton[][] lstOCo) {
		JButton oCo = new JButton();
		boolean checked = false;
		ArrayList<TheCo> lstTheCoView = getTheCo.getTheCoView(lstOCo);
		for (TheCo theCoFile : lstTheCoFile) {
			for (int i = 0; i < lstTheCoView.size(); i++) {
				if (compareTheCo(theCoFile, lstTheCoView.get(i))) {
					oCo = getPos(theCoFile, lstTheCoView.get(i));
					int x = i / 16 + oCo.getX();
					int y = i % 16 + oCo.getY();
					oCo.setLocation(x, y);
					checked = true;
					break;
				}
			}
			if (checked) {
				break;
			}
		}
		return oCo;
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

	public JButton getPos(TheCo theCoFile, TheCo theCoView) {
		JButton oCo = new JButton();
		for (int i = 0; i < Constants.MATRIX_ROW; i++) {
			for (int j = 0; j < Constants.MATRIX_COL; j++) {
				if ("T".equals(theCoFile.getMatrix()[i][j]) && "T".equals(theCoView.getMatrix()[i][j])) {
					oCo.setLocation(i, j);
				}
			}
		}
		return oCo;
	}
}
