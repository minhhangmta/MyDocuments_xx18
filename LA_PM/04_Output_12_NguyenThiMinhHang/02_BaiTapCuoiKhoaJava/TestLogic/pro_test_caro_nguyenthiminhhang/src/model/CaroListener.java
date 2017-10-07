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
		quanCo = new QuanCo(x,y);
		// if (btn.getText().equals(oCo.getText()) && oCo.getText().isEmpty()) {
		if (btn.getText().equals(lstOCo[x][y].getText()) && lstOCo[x][y].getText().isEmpty()) {
			lstOCo[x][y].setText("X");
			// Chỉnh font cho text trong ô cờ
			lstOCo[x][y].setFont(new Font("Arial", Font.BOLD, 30));
			// Chỉnh margin cho text trong ô cờ
			lstOCo[x][y].setMargin(new Insets(1, 1, 1, 1));
			if (model.checkWin(lstOCo[x][y].getText(), quanCo)) {
				// Hiển thị message thông báo thắng
				JOptionPane.showMessageDialog(null, lstOCo[x][y].getText() + " thắng!", "Finish",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void computerPlay() {

	}

}
