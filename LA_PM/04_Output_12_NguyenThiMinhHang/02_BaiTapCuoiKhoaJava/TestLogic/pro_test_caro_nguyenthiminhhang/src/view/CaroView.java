/**
 * Copyright(C) 2017 Luvina
 * caroGUI.java Oct 4, 2017 minhhang
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.CaroModel;

/**
 * Lớp làm việc với giao diện chương trình
 * 
 * @author minhhang
 */
public class CaroView {
	// Khai báo JFrame
	private JFrame mainFrame;
	// Khai báo mảng ô cờ dạng JButton, mảng có tối đa 20 Row, 20 Column
	private JButton[][] lstOCo = new JButton[Constants.ROW][Constants.COL];
	// Khai báo và khởi tạo lớp model
	private CaroModel model = new CaroModel();

	/**
	 * @throws HeadlessException
	 */
	public CaroView() throws HeadlessException {
	}

	/**
	 * Hàm show Giao diện
	 */
	public void showView() {
		// Khởi tạo Frame
		mainFrame = new JFrame("Game cờ caro");
		// Gọi hàm tạo bàn cờ
		createCaroBoard();
		// Set kích thước cho frame
		mainFrame.setSize(Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME);
		// Cho phép hiển thị frame
		mainFrame.setVisible(true);
		// Cho phép frame hiển thị chính giữa màn hình
		mainFrame.setLocationRelativeTo(null);
		// Mặc định thoát chương trình khi chọn close
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Không cho phép thay đổi kích cỡ frame
		mainFrame.setResizable(false);
	}

	/**
	 * Hàm tạo bàn cờ caro
	 */
	public void createCaroBoard() {
		// Khởi tạo 1 JPanel
		JPanel jPanel = new JPanel();
		// Thêm panel vào frame
		mainFrame.add(jPanel);
		// Chia layout cho Panel thành 20 hàng 20 cột
		jPanel.setLayout(new GridLayout(Constants.ROW, Constants.COL));
		// Duyệt hàng của bàn cờ
		for (int i = 0; i < Constants.ROW; i++) {
			// Duyệt cột của bàn cờ
			for (int j = 0; j < Constants.COL; j++) {
				// Khởi tạo mảng ô cờ dạng button
				lstOCo[i][j] = new JButton();
				// Set nền trắng cho các ô cờ
				lstOCo[i][j].setBackground(Color.white);
				// Tạo sự kiện cho từng ô
				lstOCo[i][j].addActionListener(new Action() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						// Duyệt hàng của ô cờ
						for (int i = 0; i < Constants.ROW; i++) {
							// Duyệt cột của ô cờ
							for (int j = 0; j < Constants.ROW; j++) {
								// Kiểm tra điều kiện ô cờ vừa click chưa được đánh
								if (e.getSource() == lstOCo[i][j] && lstOCo[i][j].getText() != "X"
										&& lstOCo[i][j].getText() != "O") {
									// Đánh X cho ô cờ đó
									lstOCo[i][j].setText("X");
									// Chỉnh font cho text trong ô cờ
									lstOCo[i][j].setFont(new Font("Arial", Font.BOLD, 30));
									// Chỉnh margin cho text trong ô cờ
									lstOCo[i][j].setMargin(new Insets(1, 1, 1, 1));
									// Kiểm tra nếu ô cờ vừa đánh thì thắng
									if (model.checkWin(i, j, lstOCo[i][j].getText(), lstOCo) == true) {
										// Hiển thị message thông báo thắng
										JOptionPane.showMessageDialog(null, lstOCo[i][j].getText() + " thắng!",
												"Finish", JOptionPane.INFORMATION_MESSAGE);
										// Duyệt từng button trong panel
										for (Component com : jPanel.getComponents()) {
											// Vô hiệu hóa cho các button đó
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
				// Thêm button vào panel
				jPanel.add(lstOCo[i][j]);
			}
		}
	}
}
