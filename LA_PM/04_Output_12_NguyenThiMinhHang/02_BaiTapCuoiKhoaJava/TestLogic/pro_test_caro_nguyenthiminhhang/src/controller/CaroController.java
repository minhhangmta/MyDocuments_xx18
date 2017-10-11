/**
 * Copyright(C) 2017 Luvina
 * caroController.java Oct 4, 2017 minhhang
 */
package controller;

import java.util.ArrayList;

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
	 * Hàm khởi tạo không tham số
	 */
	public CaroController() {
		//Khởi tạo lớp DataTheCoFile
		data = new DataTheCoFile();
	}

	/**
	 * Hàm lấy thế cờ từ file. Điều hướng đến DataTheCoFile
	 * 
	 * @return danh sách thế cờ từ file 
	 */
	public ArrayList<TheCo> getTheCoFile() {
		//Trả về danh sách thế cờ từ file
		return data.getTheCoFile();
	}

	/**
	 * Điều hướng đến View
	 */
	public void startApplication() {
		//Khởi tạo CaroView
		view = new CaroView(getTheCoFile());
	}

}
