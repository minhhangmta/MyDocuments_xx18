/**
 * Copyright(C) 2017 Luvina
 * CaroListener.java Oct 6, 2017 minhhang
 */
package model;

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
		if ("human".equals(Constants.NAME_PLAYER)) {
			humanPlay(x, y, btn);
		} else {
			computerPlay(x,y,btn);
		}
	}

	public void humanPlay(int x, int y, JButton btn) {
		if (btn.getText().equals(lstOCo[x][y].getText()) && lstOCo[x][y].getText().isEmpty()) {
			lstOCo[x][y].setText("X");
			// Chỉnh font cho text trong ô cờ
			lstOCo[x][y].setFont(new Font("Arial", Font.BOLD, 30));
			// Chỉnh margin cho text trong ô cờ
			lstOCo[x][y].setMargin(new Insets(1, 1, 1, 1));
			if (model.checkWin(quanCo, lstOCo)) {
				// Hiển thị message thông báo thắng
				JOptionPane.showMessageDialog(null, "Người thắng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			} else {
				computerPlay(x,y,btn);
			}
		}
	}

	public void computerPlay(int x, int y, JButton btn) {
		// Lay vi tri may danh
		quanCo = model.posComputerPlay(lstOCo);
		int row = quanCo.getPosRow();
		int col = quanCo.getPosCol();
		// check win
		if (btn.getText().equals(lstOCo[row][col].getText()) && lstOCo[row][col].getText().isEmpty()) {
			lstOCo[row][col].setText("O");
			// Chỉnh font cho text trong ô cờ
			lstOCo[row][col].setFont(new Font("Arial", Font.BOLD, 30));
			// Chỉnh margin cho text trong ô cờ
			lstOCo[row][col].setMargin(new Insets(1, 1, 1, 1));
			if (model.checkWin(quanCo, lstOCo)) {
				// Hiển thị message thông báo thắng
				JOptionPane.showMessageDialog(null, "Máy thắng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			} else {
				humanPlay(x, y, btn);
			}
		}
	}
}
