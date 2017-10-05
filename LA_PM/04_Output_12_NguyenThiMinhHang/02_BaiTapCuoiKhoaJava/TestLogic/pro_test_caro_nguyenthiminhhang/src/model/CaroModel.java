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
	 * Hàm kiểm tra thắng thua cho người/máy chơi
	 * 
	 * @param xRow
	 *            vị trí hàng
	 * @param yCol
	 *            vị trí cột
	 * @param namePlayer
	 *            tên nhân vật chơi: người/máy
	 * @param lstBtn
	 *            danh sách các ô cờ
	 * @return true nếu thắng, false nếu ko thắng
	 */
	public boolean checkWin(int xRow, int yCol, String namePlayer, JButton[][] lstBtn) {
		// biến đếm ô cờ để kiểm tra
		int count = 0;

		for (int i = -4; i <= 4; i++) {
			// Check hàng dọc
			if (lstBtn[xRow + i][yCol].getText() == namePlayer) {
				count++;
				// Check hàng ngang
			} else if (lstBtn[xRow][yCol + i].getText() == namePlayer) {
				count++;
				// Check đường chéo
			} else if (lstBtn[xRow + i][yCol + i].getText() == namePlayer) {
				count++;
				//Kiểm tra điều kiện chơi chưa thắng
			} else if (count < 5) {
				count = 0;
			}
		}
		//Kiểm tra điều kiện chơi thắng
		if (count >= 5) {
			return true;
		} else {
			count = 0;
		}
		return false;
	}
}
