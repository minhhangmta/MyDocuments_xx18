/**
 * Copyright(C) 2017 Luvina
 * caroController.java Oct 4, 2017 minhhang
 */
package controller;

import javax.swing.JButton;

import model.CaroModel;
import view.CaroView;

/**
 * Lớp điều khiển
 * 
 * @author minhhang
 */
public class CaroController {
	//Khai báo đối tượng model Caro
	private CaroModel model;
	private CaroView view;
	
	/**
	 * @param model
	 * @param view
	 */
	public CaroController(CaroModel model, CaroView view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * Gọi hàm check win từ model
	 * @param xRow
	 * @param yCol
	 * @param namePlayer
	 * @param lstOCo
	 * @return boolean value 
	 */
	public boolean checkWin(int xRow, int yCol, String namePlayer, JButton[][] lstOCo) {
		return model.checkWin(xRow, yCol, namePlayer, lstOCo);
	}
	
	public void showView() {
		view.showView();
	}
}
