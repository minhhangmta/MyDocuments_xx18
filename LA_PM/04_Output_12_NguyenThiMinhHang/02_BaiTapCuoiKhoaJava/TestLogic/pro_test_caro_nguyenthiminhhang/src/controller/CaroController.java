/**
 * Copyright(C) 2017 Luvina
 * caroController.java Oct 4, 2017 minhhang
 */
package controller;

import javax.swing.JButton;

import model.CaroModel;

/**
 * Lớp điều khiển
 * 
 * @author minhhang
 */
public class CaroController {
	//Khai báo đối tượng model Caro
	private CaroModel model;

	/**
	 * Gọi hàm check win từ model
	 * @param xRow
	 * @param yCol
	 * @param namePlayer
	 * @param lstBtn
	 * @return boolean value 
	 */
	public boolean checkWin(int xRow, int yCol, String namePlayer, JButton[][] lstBtn) {
		return model.checkWin(xRow, yCol, namePlayer, lstBtn);
	}
}
