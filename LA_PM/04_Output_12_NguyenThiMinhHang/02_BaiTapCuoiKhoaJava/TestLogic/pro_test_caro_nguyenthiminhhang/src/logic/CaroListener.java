/**
 * Copyright(C) 2017 Luvina
 * CaroListener.java Oct 6, 2017 minhhang
 */
package logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Constants;
import model.QuanCo;

/**
 * Lớp thực hiện action cho các components
 * 
 * @author minhhang
 */
public class CaroListener implements ActionListener {
	// Khai báo và khởi tạo lớp CaroLogic
	private CaroLogic logic = new CaroLogic();
	// Khai báo mảng 2D button - danh sách ô cờ trên bàn cờ
	private JButton[][] lstOCo;
	// Khai báo đối tượng quân cờ
	private QuanCo quanCo;

	/**
	 * @param lstOCo
	 */
	public CaroListener(JButton[][] lstOCo) {
		this.lstOCo = lstOCo;
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
		// lấy button vừa thao tác
		JButton btn = (JButton) e.getSource();
		// lấy vị trí hàng của button đó
		int x = btn.getY() / Constants.SIZE_O_CO;
		// lấy vị trí cột của button đó
		int y = btn.getX() / Constants.SIZE_O_CO;
		// Tạo 1 quân cờ mới với tọa độ hàng x, cột y
		quanCo = new QuanCo(x, y);
		// Kiểm tra điều kiện khi ô cờ cần đánh chưa có text
		if (btn.getText().equals(lstOCo[x][y].getText()) && "".equals(lstOCo[x][y].getText())) {
			// Cho X đánh
			lstOCo[x][y].setText("X");
			// Chỉnh font cho text trong ô cờ
			lstOCo[x][y].setFont(new Font("Arial", Font.BOLD, 30));
			// Chỉnh màu xanh cho text X
			lstOCo[x][y].setForeground(Color.BLUE);
			// Chỉnh margin cho text trong ô cờ
			lstOCo[x][y].setMargin(new Insets(1, 1, 1, 1));
			// Kiểm tra điều kiện người chơi thắng
			if (logic.checkWin(quanCo, lstOCo)) {
				// Hiển thị message thông báo thắng
				JOptionPane.showMessageDialog(null, "Người thắng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
		// Lay vi tri may danh
		JButton oCo = logic.posComputerPlay(lstOCo);
		// Khởi tạo quân cờ mới
		QuanCo co = new QuanCo();
		// lấy vị trí hàng từ ô cờ vừa lấy được
		int row = oCo.getX();
		// lấy vị trí cột từ ô cờ vừa lấy được
		int col = oCo.getY();
		// set vị trí hàng cho quân cờ
		co.setPosRow(row);
		// set vị trí cột cho quân cờ
		co.setPosCol(col);
		// set text cho ô cờ mà máy đánh là O
		lstOCo[row][col].setText("O");
		// Đặt font cho text trong ô cờ
		lstOCo[row][col].setFont(new Font("Arial", Font.BOLD, 30));
		// Đặt màu cho text trong ô cờ
		lstOCo[row][col].setForeground(Color.BLACK);
		// Chỉnh margin cho text trong ô cờ
		lstOCo[row][col].setMargin(new Insets(1, 1, 1, 1));
		// Kiểm tra điều kiện máy chơi thắng với quân cờ vừa lấy được và ds ô cờ trên
		// bàn cờ hiện tại
		if (logic.checkWin(co, lstOCo)) {
			// Hiển thị message thông báo máy thắng
			JOptionPane.showMessageDialog(null, "Máy thắng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			// reset lại chương trình
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
