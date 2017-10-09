/**
 * Copyright(C) 2017 Luvina
 * caroController.java Oct 4, 2017 minhhang
 */
package controller;

import java.util.ArrayList;

import javax.swing.JButton;

import logic.CaroLogic;
import logic.GetTheCo;
import model.QuanCo;
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
	// Khai báo Caro Logic
	private CaroLogic logic;
	// Khởi tạo lớp GetTheCo
	private GetTheCo getTheCo = new GetTheCo();

	/**
	 * 
	 */
	public CaroController() {
	}

	/**
	 * @param view
	 */
	public CaroController(CaroLogic logic, CaroView view) {
		this.view = view;
		this.logic = logic;
	}

	/**
	 * Điều hướng đến view ShowView
	 */
	public void showView() {
		view.showView();
	}

	/**
	 * Điều hướng đến GetTheCo để lấy thế cờ từ file
	 * @return danh sách thế cờ từ file
	 */
	public ArrayList<TheCo> getTheCoFile() {
		return getTheCo.getTheCoFile();
	}

	/**
	 * Điều hướng đến GetTheCo để lấy thê cờ từ View
	 * @param lstOCo
	 * @return danh sách thế cờ từ View
	 */
	public ArrayList<TheCo> getTheCoView(JButton[][] lstOCo) {
		return getTheCo.getTheCoView(lstOCo);
	}

}
