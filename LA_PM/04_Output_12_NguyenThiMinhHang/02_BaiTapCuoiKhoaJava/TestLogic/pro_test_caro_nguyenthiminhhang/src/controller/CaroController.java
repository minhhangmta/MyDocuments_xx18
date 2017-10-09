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
	public CaroController(CaroView view) {
		this.view = view;
	}

	/**
	 * Điều hướng đến view ShowView
	 */
	public void showView() {
		view.showView();
	}

	public ArrayList<TheCo> getTheCoFile() {
		return getTheCo.getTheCoFile();
	}

	public ArrayList<TheCo> getTheCoView(JButton[][] lstOCo) {
		return getTheCo.getTheCoView(lstOCo);
	}

}
