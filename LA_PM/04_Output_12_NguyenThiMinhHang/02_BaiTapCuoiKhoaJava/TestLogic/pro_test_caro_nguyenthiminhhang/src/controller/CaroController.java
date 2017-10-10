/**
 * Copyright(C) 2017 Luvina
 * caroController.java Oct 4, 2017 minhhang
 */
package controller;

import java.util.ArrayList;

import logic.CaroLogic;
import logic.DataTheCoFile;
import model.TheCo;
import view.CaroView;

/**
 * Lớp điều khiển
 * 
 * @author minhhang
 */
public class CaroController {
	// Khai báo Caro view
	private CaroView view;
	// Khai báo lớp DataTheCoFile
	private DataTheCoFile data;

	/**
	 * 
	 */
	public CaroController() {
		data = new DataTheCoFile();
	}

	/**
	 * Hàm lấy thế cờ từ file. Điều hướng đến DataTheCoFile
	 * 
	 * @return
	 */
	public ArrayList<TheCo> getTheCoFile() {
		return data.getTheCoFile();
	}

	/**
	 * Điều hướng đến View
	 */
	public void startApplication() {
		view = new CaroView(getTheCoFile());
	}

}
