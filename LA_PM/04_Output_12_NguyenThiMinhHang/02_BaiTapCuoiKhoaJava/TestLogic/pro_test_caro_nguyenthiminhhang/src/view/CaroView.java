/**
 * Copyright(C) 2017 Luvina
 * caroGUI.java Oct 4, 2017 minhhang
 */
package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.CaroListener;
import model.CaroModel;
import model.Constants;

/**
 * Lớp làm việc với giao diện chương trình
 * 
 * @author minhhang
 */
public class CaroView {
	// Khai báo JFrame
	private JFrame mainFrame;
	// Khai báo mảng ô cờ dạng JButton, mảng có tối đa 20 Row, 20 Column
	private JButton[][] lstOCo = new JButton[Constants.ROW][Constants.COL];
	// Khai báo và khởi tạo lớp model
	private CaroModel model = new CaroModel();

	/**
	 * @throws HeadlessException
	 */
	public CaroView() {
	}

	/**
	 * Hàm show Giao diện
	 */
	public void showView() {
		// Khởi tạo Frame
		mainFrame = new JFrame("Game cờ caro");
		// Gọi hàm tạo bàn cờ
		createCaroBoard();
		// Set kích thước cho frame
		mainFrame.setSize(Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME);
		// Cho phép hiển thị frame
		mainFrame.setVisible(true);
		// Cho phép frame hiển thị chính giữa màn hình
		mainFrame.setLocationRelativeTo(null);
		// Mặc định thoát chương trình khi chọn close
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Không cho phép thay đổi kích cỡ frame
		mainFrame.setResizable(false);
	}

	/**
	 * Hàm tạo bàn cờ caro
	 */
	public void createCaroBoard() {
		// Chia layout cho frame thành 20 hàng 20 cột
		mainFrame.setLayout(new GridLayout(Constants.ROW, Constants.COL));
		// Duyệt hàng của bàn cờ
		for (int i = 0; i < Constants.ROW; i++) {
			// Duyệt cột của bàn cờ
			for (int j = 0; j < Constants.COL; j++) {
				JButton oCo = new JButton();
				// Khởi tạo mảng ô cờ dạng button
				lstOCo[i][j] = oCo;
				// Set nền trắng cho các ô cờ
				lstOCo[i][j].setBackground(Color.white);
				// Tạo sự kiện cho từng ô
				lstOCo[i][j].addActionListener(new CaroListener(oCo, lstOCo));
				// Thêm button vào frame
				mainFrame.add(lstOCo[i][j]);
			}
		}
	}
}
