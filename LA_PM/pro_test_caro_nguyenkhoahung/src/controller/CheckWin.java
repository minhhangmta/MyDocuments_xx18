/**
 * Copyright(C) 2017 Luvina
 * CheckWin.java Oct 3, 2017 HungNK
 */
package controller;

import java.awt.Color;

import javax.swing.JOptionPane;

import constants.Constants;

/**
 * @author HungNK
 *
 */
public class CheckWin {
	
	/**
	 * Kiểm tra điều kiện thắng
	 * 
	 * @param x
	 *            tọa độ cột
	 * @param y
	 *            tọa độ hàng
	 * @param txtBtn
	 *            giá trị của button
	 * @return true nếu có ai thắng, false nếu chưa có ai thắng
	 */
	public boolean check(int x, int y, String txtBtn) {
		// Nếu thỏa mãn 1 trong 4 điều kiện thì trả về true
		//if (checkCot(x, y, txtBtn) || checkHang(x, y, txtBtn) || checkCheoPhu(x, y, txtBtn)
				//|| checkCheoChinh(x, y, txtBtn)) {
			// Trả về true nếu có ai thắng
			//return true;
		//}
		// Trả về false chưa có người thắng
		return false;
	}
	
	/**
	 * Đánh và kiểm tra đã có người thắng hay chưa
	 * 
	 * @param x
	 *            tọa độ cột
	 * @param y
	 *            tọa độ hàng
	 * @param textBtn
	 *            giá trị text của button
	 * @param name
	 *            tên người chơi hay máy chơi
	 * @param isHuman
	 *            giá trị đến lượt người hay máy chơi
	 * @param colorText
	 *            màu chữ cho button
	 */
	public void play(int x, int y, String textBtn, String name, boolean isHuman, Color colorText) {
		// Đặt chữ cho button
		Constants.arrJButton[x][y].setText(textBtn);
		// Đặt màu chữ cho button
		Constants.arrJButton[x][y].setForeground(colorText);
		// Gán giá trị cho người đánh hay máy đánh
		Constants.isHuman = isHuman;
		// Kiểm tra thắng hay chưa
		if (check(x, y, Constants.arrJButton[x][y].getText())) {
			// Nếu thắng in ra thông báo
			JOptionPane.showMessageDialog(null, name + " đã thắng");
			// Cho chơi lại
			reset();
		}
	}

	/**
	 * Chơi lại ván mới, đặt các thuộc tính về giá trị ban đầu
	 */
	public void reset() {
		// Đặt lại giá trị isUse thành true;
		Constants.isHuman = true;
		// Vòng lặp qua tất cả các hàng
		for (int i = 0; i < Constants.ROW; i++) {
			// Vòng lặp qua tất cả các cột
			for (int j = 0; j < Constants.COL; j++) {
				// Đặt lại màu nền cho button
				Constants.arrJButton[i][j].setBackground(Color.WHITE);
				// Đặt lại text cho button
				Constants.arrJButton[i][j].setText("");
			}
		}
	}
}
