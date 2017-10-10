package main;
/**
 * Copyright(C) 2017 Luvina
 * Main.java Oct 4, 2017 minhhang
 */

import controller.CaroController;

/**
 * Lớp thực hiện thi chương trình
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
		// Khởi tạo CaroController
		CaroController controller = new CaroController();
		// Gọi hàm showView trong controller
		controller.startApplication();
	}
}
