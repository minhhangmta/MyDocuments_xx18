/**
 * Copyright(C) 2017 Luvina
 * caroGUI.java Oct 4, 2017 minhhang
 */
package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Giao diện chương trình
 * 
 * @author minhhang
 */
public class CaroGUI extends Frame implements ActionListener {
	private int X0 = 20;
	private int Y0 = 20;
	private int SIZE = 40;
	private int WIDTH_FRAME = 1000;
	private int HEIGHT_FRAME = 600;
	private int WIDTH_LEFT_PANEL = 1000;
	private int HEIGHT_LEFT_PANEL = 600;
	private JFrame mainFrame;
	private JRadioButton rdHuman;
	private JRadioButton rdPC;

	/**
	 * @throws HeadlessException
	 */
	public CaroGUI() throws HeadlessException {
		showGUI();
	}

	public void showGUI() {
		mainFrame = new JFrame("Game cờ caro");
		createLayout();
		// createButton();

		mainFrame.setSize(WIDTH_FRAME, HEIGHT_FRAME);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
	}

	public void createLayout() {
		JPanel jPanelLeft = new JPanel();
		JPanel jPanelRight = new JPanel();

		jPanelLeft.setBackground(Color.WHITE);
		mainFrame.add(jPanelLeft);
		mainFrame.setLayout(new FlowLayout());
		jPanelLeft.setPreferredSize(new Dimension(WIDTH_LEFT_PANEL, HEIGHT_LEFT_PANEL));

		for (int i = 0; i < X0; i++) {
			for (int j = 0; j < Y0; j++) {
				JButton button = new JButton();
				button.setBackground(Color.white);
				button.addActionListener(new Action() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

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
				jPanelLeft.add(button);
			}
		}
		jPanelLeft.setLayout(new GridLayout(X0, Y0));

		jPanelRight.setBackground(Color.LIGHT_GRAY);
		JButton btnStart = new JButton("Bắt đầu");
		btnStart.setActionCommand("btnStart");
		btnStart.addMouseListener(new MouseAdapter() {
			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		
		Label txtTitle = new Label("Chọn nhân vật đánh trước:");
		rdHuman = new JRadioButton("Người");
		rdPC = new JRadioButton("PC");
		
		rdPC.setActionCommand("rdPC");
		rdPC.addActionListener(this);
		rdHuman.setActionCommand("rdHuman");
		rdHuman.addActionListener(this);
		
		jPanelRight.add(txtTitle);
		jPanelRight.add(rdHuman);
		jPanelRight.add(rdPC);
		jPanelRight.add(btnStart);

		mainFrame.add(jPanelRight);
		mainFrame.setLayout(new GridLayout(1, 2, 2, 2));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String st = event.getActionCommand();
		switch (st) {
		case "rdPC":
			rdHuman.setSelected(false);
			break;
		case "rdHuman":
			rdPC.setSelected(false);
			break;
		default:
			break;
		}
	}

}
