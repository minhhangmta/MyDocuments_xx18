/**
 * Copyright(C) 2017 Luvina
 * caroController.java Oct 4, 2017 minhhang
 */
package controller;

import logic.CaroLogic;
import view.CaroView;

/**
 * Lớp điều khiển
 * 
 * @author minhhang
 */
public class CaroController {
	// Khai báo Caro view
	private CaroView view;

	/**
	 * 
	 */
	public CaroController() {

	}

	/**
	 * Điều hướng đến View
	 */
	public void startApplication() {
		view = new CaroView();
	}

}
