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
		this.lstOCo = lstOCo;
		this.oCo = oCo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int i = oCo.getX();
		int j = oCo.getY();
		// TODO Auto-generated method stub
		if (e.getSource() == lstOCo[i][j] && lstOCo[i][j].getText() != "X" && lstOCo[i][j].getText() != "O") {
			// Đánh X cho ô cờ đó
			lstOCo[i][j].setText("X");
			// Chỉnh font cho text trong ô cờ
			lstOCo[i][j].setFont(new Font("Arial", Font.BOLD, 30));
			// Chỉnh margin cho text trong ô cờ
			lstOCo[i][j].setMargin(new Insets(1, 1, 1, 1));
			if (model.checkWin(i, j, lstOCo[i][j].getText(), lstOCo) == true) {
				// Hiển thị message thông báo thắng
				JOptionPane.showMessageDialog(null, lstOCo[i][j].getText() + " thắng!", "Finish",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
