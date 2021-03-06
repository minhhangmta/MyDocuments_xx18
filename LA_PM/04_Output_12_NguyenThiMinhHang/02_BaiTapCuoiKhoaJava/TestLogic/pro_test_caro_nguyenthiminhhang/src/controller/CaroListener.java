/**
 * Copyright(C) 2017 Luvina
 * CaroListener.java Oct 6, 2017 minhhang
 */
package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import constants.Constants;
import logic.CaroLogic;
import model.QuanCo;
import model.TheCo;

/**
 * Lớp thực hiện action cho các components
 * 
 * @author minhhang
 */
public class CaroListener implements ActionListener {
	// Khai báo mảng 2D button - danh sách ô cờ trên bàn cờ
	private JButton[][] lstOCo;
	// Khai báo đối tượng quân cờ
	private QuanCo quanCo;
	// Khởi tạo CaroLogic
	private CaroLogic logic;

	/**
	 * Hàm khởi tạo có tham số
	 * 
	 * @param lstOCo
	 * @param lstTheCoFile
	 */
	public CaroListener(JButton[][] lstOCo, ArrayList<TheCo> lstTheCoFile) {
		// Gán giá trị cho danh sách ô cờ
		this.lstOCo = lstOCo;
		// Khởi tạo lớp CaroLogic
		logic = new CaroLogic(lstTheCoFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Cho người chơi
		// lấy button vừa click
		JButton btn = (JButton) e.getSource();
		// lấy vị trí hàng của button đó
		int x = btn.getY() / Constants.SIZE_O_CO;
		// lấy vị trí cột của button đó
		int y = btn.getX() / Constants.SIZE_O_CO;
		// Tạo 1 quân cờ mới với tọa độ hàng x, cột y
		quanCo = new QuanCo(x, y);
		// Kiểm tra điều kiện khi ô cờ cần đánh chưa có text
		if ("".equals(lstOCo[x][y].getText())) {
			// Cho X đánh
			lstOCo[x][y].setText(Constants.POS_NGUOI_DANH);
			// Chỉnh font cho text trong ô cờ
			lstOCo[x][y].setFont(new Font("Arial", Font.BOLD, 30));
			// Chỉnh màu cho text X
			lstOCo[x][y].setForeground(Color.BLUE);
			// Chỉnh margin cho text trong ô cờ
			lstOCo[x][y].setMargin(new Insets(1, 1, 1, 1));
			// Kiểm tra điều kiện người chơi thắng
			if (logic.checkWin(quanCo, lstOCo)) {
				// Hiển thị message thông báo thắng
				JOptionPane.showMessageDialog(null, Constants.NOTICE_NGUOI_WIN, Constants.TITLE_MASSAGE,
						JOptionPane.INFORMATION_MESSAGE);
				// reset chương trình
				resetGame();
			} else {
				// cho máy chơi
				computerPlay();
			}
		}
	}

	/**
	 * Hàm máy chơi game
	 */
	public void computerPlay() {
		// Lấy vị trí quân cờ cần đánh
		QuanCo quanCo = logic.posComputerPlay(lstOCo);
		// Tạo biến đếm quân cờ máy chơi
		int countCo = 0;
		// lấy vị trí hàng từ ô cờ vừa lấy được
		int row = quanCo.getPosRow();
		// lấy vị trí cột từ ô cờ vừa lấy được
		int col = quanCo.getPosCol();
		// set text cho ô cờ mà máy đánh là O
		lstOCo[row][col].setText(Constants.POS_MAY_DANH);
		// Tăng biến đếm quân cờ máy chơi
		countCo++;
		// Đặt font cho text trong ô cờ
		lstOCo[row][col].setFont(new Font("Arial", Font.BOLD, 30));
		// Đặt màu cho text trong ô cờ
		lstOCo[row][col].setForeground(Color.BLACK);
		// Chỉnh margin cho text trong ô cờ
		lstOCo[row][col].setMargin(new Insets(1, 1, 1, 1));
		// Kiểm tra điều kiện máy chơi thắng với quân cờ vừa lấy được và ds ô cờ trên
		// bàn cờ hiện tại
		if (logic.checkWin(quanCo, lstOCo)) {
			// Hiển thị message thông báo máy thắng
			JOptionPane.showMessageDialog(null, Constants.NOTICE_MAY_WIN, Constants.TITLE_MASSAGE,
					JOptionPane.INFORMATION_MESSAGE);
			// reset lại chương trình
			resetGame();
		} else if (logic.checkEqualPlay(countCo)) {
			// Hiển thị message thông báo hòa
			JOptionPane.showMessageDialog(null, Constants.NOTICE_HOA, Constants.TITLE_MASSAGE,
					JOptionPane.INFORMATION_MESSAGE);
			// reset chương trình
			resetGame();
		}
	}

	/**
	 * Hàm thiết lập lại chương trình
	 */
	public void resetGame() {
		// Duyệt hàng trong bàn cờ
		for (int i = 0; i < Constants.ROW; i++) {
			// Duyệt cột trong bàn cờ
			for (int j = 0; j < Constants.COL; j++) {
				// set toàn bộ text cho các ô cờ là rỗng
				lstOCo[i][j].setText("");
			}
		}
	}
}
