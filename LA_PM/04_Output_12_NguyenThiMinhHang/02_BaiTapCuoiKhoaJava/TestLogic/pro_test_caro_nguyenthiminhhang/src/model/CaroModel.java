/**
 * Copyright(C) 2017 Luvina
 * CaroModel.java Oct 5, 2017 minhhang
 */
package model;

import javax.swing.JButton;

/**
 * Lớp xử lý logic
 * 
 * @author minhhang
 */
public class CaroModel {
	/**
	 * Hàm xử lý đánh cờ
	 * 
	 * @param lstOCo
	 * @param e
	 * @param jPanel
	 */
//	public void playCaro(JButton oCo, JButton[][] lstOCo) {
//		humanPlay(oCo, lstOCo);
//	}
//
//	public void humanPlay(JButton oCo, JButton[][] lstOCo) {
//		int i = oCo.getX();
//		int j = oCo.getY();
//		// Kiểm tra điều kiện ô cờ vừa click chưa được đánh
//		if (oCo == lstOCo[i][j] && lstOCo[i][j].getText() != "X" && lstOCo[i][j].getText() != "O") {
//			// Đánh X cho ô cờ đó
//			lstOCo[i][j].setText("X");
//			// Chỉnh font cho text trong ô cờ
//			lstOCo[i][j].setFont(new Font("Arial", Font.BOLD, 30));
//			// Chỉnh margin cho text trong ô cờ
//			lstOCo[i][j].setMargin(new Insets(1, 1, 1, 1));
//			if (checkWin(i, j, lstOCo[i][j].getText(), lstOCo) == true) {
//				// Hiển thị message thông báo thắng
//				JOptionPane.showMessageDialog(null, lstOCo[i][j].getText() + " thắng!", "Finish",
//						JOptionPane.INFORMATION_MESSAGE);
//				// Duyệt từng button trong panel
//				for (Component com : mainFrame.getComponents()) {
//					// Vô hiệu hóa cho các button đó
//					com.setEnabled(false);
//				}
//			}
//		}
//	}
//
//	public void computerPlay(JButton[][] lstOCo, ActionEvent e, JPanel jPanel) {
//
//		int i = 0;
//		int j = 0;
//
//		if (checkWin(i, j, "O", lstOCo) == true) {
//			// Hiển thị message thông báo thắng
//			JOptionPane.showMessageDialog(null, lstOCo[i][j].getText() + " thắng!", "Finish",
//					JOptionPane.INFORMATION_MESSAGE);
//			// Duyệt từng button trong panel
//			for (Component com : jPanel.getComponents()) {
//				// Vô hiệu hóa cho các button đó
//				com.setEnabled(false);
//			}
//		} else {
//			// humanPlay(lstOCo, e, jPanel);
//		}
//	}

	public boolean checkCheoPhai(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		int count = 0;
		for (int i = -4, j = -4; i <= 4 && j <= 4; i++, j++) {
			if (xRow + i >= 0 && xRow + i < Constants.ROW && yCol - i >= 0 && yCol - i < Constants.COL) {
				if (lstOCo[xRow + i][yCol - i].getText() == namePlayer) {
					count++;
					if (count < 5) {
						count = 0;
					}
				}
			}
		}
		if (count >= 5) {
			return true;
		} else {
			count = 0;
		}
		return false;

	}

	public boolean checkCheoTrai(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if (xRow + i >= 0 && xRow + i < Constants.ROW && yCol + i >= 0 && yCol + i < Constants.COL) {
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
		} else {
			count = 0;
		}
		return false;
	}

	public boolean checkHangNgang(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if (yCol + i >= 0 && yCol + i < Constants.COL) {
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
		} else {
			count = 0;
		}
		return false;
	}

	public boolean checkHangDoc(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		int count = 0;
		for (int i = -4; i <= 4; i++) {
			if (xRow + i >= 0 && xRow + i < Constants.ROW) {
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
		} else {
			count = 0;
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

	// public void

}
