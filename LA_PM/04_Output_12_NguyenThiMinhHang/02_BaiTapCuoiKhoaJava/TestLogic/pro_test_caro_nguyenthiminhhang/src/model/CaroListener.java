/**
 * Copyright(C) 2017 Luvina
 * CaroListener.java Oct 6, 2017 minhhang
 */
package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Description class here
 * 
 * @author minhhang
 */
public class CaroListener implements ActionListener {
	private CaroModel model = new CaroModel();
	private JButton[][] lstOCo;
	private QuanCo quanCo;

	/**
	 * @param lstOCo
	 * @param quanCo
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
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		int x = btn.getY() / Constants.SIZE_O_CO;
		int y = btn.getX() / Constants.SIZE_O_CO;
		quanCo = new QuanCo(x, y);
		if (btn.getText().equals(lstOCo[x][y].getText()) && "".equals(lstOCo[x][y].getText())) {
			lstOCo[x][y].setText("X");
			// Chỉnh font cho text trong ô cờ
			lstOCo[x][y].setFont(new Font("Arial", Font.BOLD, 30));
			lstOCo[x][y].setForeground(Color.BLUE);
			// Chỉnh margin cho text trong ô cờ
			lstOCo[x][y].setMargin(new Insets(1, 1, 1, 1));
			if (model.checkWin(quanCo, lstOCo)) {
				// Hiển thị message thông báo thắng
				JOptionPane.showMessageDialog(null, "Người thắng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				resetGame();
			} else {
				computerPlay();
			}
		}

	}

	public void computerPlay() {
		// Lay vi tri may danh
		JButton oCo = model.posComputerPlay(lstOCo);
		QuanCo co = new QuanCo();
		int row = oCo.getX();
		int col = oCo.getY();
		co.setPosRow(row);
		co.setPosCol(col);
		lstOCo[row][col].setText("O");
		// Chỉnh font cho text trong ô cờ
		lstOCo[row][col].setFont(new Font("Arial", Font.BOLD, 30));
		//
		lstOCo[row][col].setForeground(Color.BLACK);
		// Chỉnh margin cho text trong ô cờ
		lstOCo[row][col].setMargin(new Insets(1, 1, 1, 1));
		if (model.checkWin(co, lstOCo)) {
			// Hiển thị message thông báo thắng
			JOptionPane.showMessageDialog(null, "Máy thắng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			resetGame();
		}
	}

	public void resetGame() {
		for (int i = 0; i < Constants.ROW; i++) {
			for (int j = 0; j < Constants.COL; j++) {
				lstOCo[i][j].setText("");
			}
		}
	}
}
