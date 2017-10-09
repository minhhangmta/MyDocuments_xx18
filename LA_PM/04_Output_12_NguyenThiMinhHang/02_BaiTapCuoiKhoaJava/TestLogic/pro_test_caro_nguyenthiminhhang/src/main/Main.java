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
//		CaroView caroView = new CaroView();
//		caroView.showView();
		CaroLogic logic = new CaroLogic();
		CaroView view = new CaroView();
		CaroController controller= new CaroController(logic, view);
		controller.showView();
	}
}
