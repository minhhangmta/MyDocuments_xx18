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
	private JButton oCo;

	/**
	 * @param lstOCo
	 * @param oCo
	 */
	public CaroListener(JButton oCo, JButton[][] lstOCo) {
		this.oCo = oCo;
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
		if (e.getSource().equals(oCo) && oCo.getText().isEmpty()) {
			// Lấy vị trí hàng cho ô cờ
			int xRow = oCo.getY() / 30;
			// Lấy vị trí cột cho ô cờ
			int yCol = oCo.getX() / 30;
			// Đánh X cho ô cờ đó
			oCo.setText("X");
			// Chỉnh font cho text trong ô cờ
			oCo.setFont(new Font("Arial", Font.BOLD, 30));
			// Chỉnh margin cho text trong ô cờ
			oCo.setMargin(new Insets(1, 1, 1, 1));
			if (model.checkWin(xRow, yCol, oCo.getText(), lstOCo) == true) {
				// Hiển thị message thông báo thắng
				JOptionPane.showMessageDialog(null, oCo.getText() + " thắng!", "Finish",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void computerPlay() {

	}

}
