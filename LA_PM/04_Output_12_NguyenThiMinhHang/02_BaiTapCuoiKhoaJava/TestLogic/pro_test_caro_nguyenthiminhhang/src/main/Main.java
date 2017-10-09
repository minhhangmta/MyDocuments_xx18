package main;
/**
 * Copyright(C) 2017 Luvina
 * Main.java Oct 4, 2017 minhhang
 */

import controller.CaroController;
import logic.CaroLogic;
import view.CaroView;

/**
 * Description class here
 * 
 * @author minhhang
 */
public class Main {
	/**
	 * Hàm main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Khởi tạo CaroView
		CaroView view = new CaroView();
		//Khởi tạo CaroLogic
		CaroLogic logic = new CaroLogic();
		//Khởi tạo CaroController
		CaroController controller = new CaroController(logic, view);
		//Gọi hàm showView trong controller
		controller.showView();
	}
}
