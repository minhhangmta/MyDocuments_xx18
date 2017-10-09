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
	//Khai báo Caro logic
	private CaroLogic logic;
	//Khai báo Caro view
	private CaroView view;
	
	/**
	 * @param logic 
	 * @param view
	 */
	public CaroController(CaroLogic logic, CaroView view) {
		this.logic = logic;
		this.view = view;
	}

	/**
	 * Điều hướng đến view ShowView		
	 */
	public void showView() {
		view.showView();
	}
	
//	public void 
	
}
