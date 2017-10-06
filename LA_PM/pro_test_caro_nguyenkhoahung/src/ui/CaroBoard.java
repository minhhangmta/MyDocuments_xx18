/**
 * Copyright(C) 2017 Luvina
 * CaroBoard.java Oct 3, 2017 HungNK
 */
package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import constants.Constants;
import controller.CheckWin;
import ui.Button;

/**
 * @author HungNK
 *
 */
public class CaroBoard extends JFrame implements ActionListener {
	//khai báo đối tượng JFrame
	private JFrame frame;
	//khai báo đối tượng JPanel
	private JPanel panel;
	//khởi tạo đối tượng checkWin
	private CheckWin checkWin = new CheckWin();
	
	/**
	 * Khởi tạo đối tượng khung của bàn cờ
	 */
	public void drawBoard() {
		//khởi tạo đối tượng frame
		frame = new JFrame();
		//khởi tạo đối tượng panel
		panel = new JPanel();
		//khởi tạo mảng JButton
		Constants.arrJButton = new JButton[Constants.ROW][Constants.COL];
		// Thêm đối tượng jpanel Caro vào trong frame
		frame.add(panel);
		// Đặt dạng japnelCaro là dạng lưới để hiển thị bàn cờ
		panel.setLayout(new GridLayout(Constants.ROW, Constants.COL));
		// Đặt kích thước cho khung giao diện
		frame.setSize(Constants.WIDTH, Constants.HEIGHT);
		// Đặt tiêu đề cho khung giao diện
		frame.setTitle("Game caro");
		// Hiển thị frame giữa màn hình
		frame.setLocationRelativeTo(null);
		// Không cho phép chỉnh sửa kích thước của frame
		frame.setResizable(false);
		// Đóng frame thì kết thúc chương trình
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Hàm khởi tạo các Button trên bàn cờ
	 */
	public void addJButton() {
		// Vòng lặp hết các hàng
		for (int i = 0; i < Constants.ROW; i++) {
			// Vòng lặp hết các cột
			for (int j = 0; j < Constants.COL; j++) {
				// Khởi tạo mảng button
				Constants.arrJButton[i][j] = new Button();
				// Thêm sự kiện cho các button
				Constants.arrJButton[i][j].addActionListener(this);
				// Thêm button vào trong panel
				panel.add(Constants.arrJButton[i][j]);
			}
		}
	}
	
	public void initCaroBoard() {
		// Thêm button
		addJButton();
		// Hiển thị frame
		frame.setVisible(true);
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		// Vòng lặp qua các hàng
		for (int i = 0; i < 20; i++) {
			// Vòng lặp qua các cột
			for (int j = 0; j < 20; j++) {
				// Kiểm tại vị trị click button đó đã được đánh hay chưa
				if (ae.getSource() == Constants.arrJButton[i][j]
						&& "".equals(Constants.arrJButton[i][j].getText())) {
					// Kiểm tra đến lượt người đánh
					if (Constants.isHuman) {
						// Gọi phương thức play để đánh
						checkWin.play(i, j, "X", "Bạn", false, Color.BLUE);
						// Gọi phương thức máy đánh
						//may.mayDanh();
					}
				}
			}
		}
	}

}
