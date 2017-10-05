/**
 * Copyright(C) 2017 Luvina
 * caroGUI.java Oct 4, 2017 minhhang
 */
package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.CaroController;
import model.CaroModel;

/**
 * Giao diện chương trình
 * 
 * @author minhhang
 */
public class CaroView extends JPanel {
	private int ROW = 20;
	private int COL = 20;
	private int SIZE = 40;
	private int WIDTH_FRAME = 700;
	private int HEIGHT_FRAME = 700;
	private JFrame mainFrame;
	private JButton[][] lstOCo = new JButton[ROW][COL];
	private CaroModel model = new CaroModel();

	/**
	 * @throws HeadlessException
	 */
	public CaroView() throws HeadlessException {
	}

	/**
	 * 
	 */
	public void showView() {
		mainFrame = new JFrame("Game cờ caro");
		createCaroBoard();
		mainFrame.setSize(WIDTH_FRAME, HEIGHT_FRAME);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
	}

	public void createCaroBoard() {
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.WHITE);
		mainFrame.add(jPanel);
		jPanel.setLayout(new GridLayout(ROW, COL));

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				lstOCo[i][j] = new JButton();
				lstOCo[i][j].setBackground(Color.white);
				lstOCo[i][j].addActionListener(new Action() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						for (int i = 0; i < ROW; i++) {
							for (int j = 0; j < ROW; j++) {
								if (e.getSource() == lstOCo[i][j] && lstOCo[i][j].getText() != "X"
										&& lstOCo[i][j].getText() != "O") {
									lstOCo[i][j].setText("X");
									lstOCo[i][j].setFont(new Font("Arial", Font.BOLD, 30));
									lstOCo[i][j].setMargin(new Insets(1, 1, 1, 1));
									if (model.checkWin(i, j, lstOCo[i][j].getText(), lstOCo) == true) {
										JOptionPane.showMessageDialog(null, lstOCo[i][j].getText() + " thắng!",
												"Finish", JOptionPane.INFORMATION_MESSAGE);
										for (Component com : jPanel.getComponents()) {
											com.setEnabled(false);
										}
									}
								}
							}
						}
					}

					@Override
					public void setEnabled(boolean b) {
						// TODO Auto-generated method stub

					}

					@Override
					public void removePropertyChangeListener(PropertyChangeListener listener) {
						// TODO Auto-generated method stub

					}

					@Override
					public void putValue(String key, Object value) {
						// TODO Auto-generated method stub

					}

					@Override
					public boolean isEnabled() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public Object getValue(String key) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void addPropertyChangeListener(PropertyChangeListener listener) {
						// TODO Auto-generated method stub

					}
				});

				jPanel.add(lstOCo[i][j]);
			}
		}
		mainFrame.setLayout(new GridLayout());
	}

}
