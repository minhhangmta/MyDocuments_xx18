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
		// Khởi tạo caro Model để lấy và xử lý dữ liệu
		CaroLogic logic = new CaroLogic();
		// Khởi tạo caro View để show giao diện
		CaroView view = new CaroView();
		//Khởi tạo controller
		CaroController controller = new CaroController(logic, view);
		//Gọi hàm showView từ controller
		controller.showView();
		
	}
}
