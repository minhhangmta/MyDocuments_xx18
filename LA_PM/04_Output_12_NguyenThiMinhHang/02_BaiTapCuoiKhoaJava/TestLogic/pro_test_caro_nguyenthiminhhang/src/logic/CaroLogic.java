/**
 * Copyright(C) 2017 Luvina
 * CaroModel.java Oct 5, 2017 minhhang
 */
package logic;

import java.util.ArrayList;

import javax.swing.JButton;

import constants.Constants;
import model.QuanCo;
import model.TheCo;

/**
 * Lớp xử lý logic
 * 
 * @author minhhang
 */
public class CaroLogic {
	private DataTheCoFile data;
	private ArrayList<TheCo> lstTheCoFile;

	/**
	 * Hàm khởi tạo có tham số 
	 * @param lstTheCoFile
	 */
	public CaroLogic(ArrayList<TheCo> lstTheCoFile) {
		this.lstTheCoFile = lstTheCoFile;
	}

	/**
	 * Hàm lấy danh sách thế cờ từ giao diện
	 * 
	 * @param lstOCo
	 * @return lstTheCoView danh sách thế cờ từ giao diện
	 */
	public ArrayList<TheCo> getTheCoView(JButton[][] lstOCo) {
		// Khởi tạo danh sách thế cờ lấy từ View
		ArrayList<TheCo> lstTheCoView = new ArrayList<>();
		// Khởi tạo thế cờ View
		TheCo theCoView = null;
		// Khởi tạo mảng 2D ma trận 5x5
		String[][] matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
		// Duyệt từng ma trận con 5x5 trong bàn cờ - là thế cờ trên giao diện
		// Duyệt từng ma trận con 5x5 theo hàng (có 16x16 thế cờ)
		for (int i = 0; i < Constants.ROW - Constants.MATRIX_ROW + 1; i++) {
			// duyệt từng ma trận con 5x5 theo cột
			for (int j = 0; j < Constants.COL - Constants.MATRIX_COL + 1; j++) {
				// Duyệt từng hàng của thế cờ/ma trận con trên View
				for (int l = i; l < i + Constants.MATRIX_ROW; l++) {
					// Duyệt từng cột của thế cờ/ma trận con trên view
					for (int m = j; m < j + Constants.MATRIX_COL; m++) {
						// Xét điều kiện text của ô cờ trên bàn cờ là O
						if ("O".equals(lstOCo[l][m].getText())) {
							// Gán giá trị O cho phần tử tương ứng của thế cờ
							matrix[l - i][m - j] = "O";
							// Xét điều kiện text của ô cờ trên bàn cờ là X
						} else if ("X".equals(lstOCo[l][m].getText())) {
							// Gán giá trị X cho phần tử tương ứng của thế cờ
							matrix[l - i][m - j] = "X";
						} else {
							// Ngược lại còn trường hợp ô cờ có text rỗng
							// Gán giá trị T cho phần tử tương ứng của thế cờ
							matrix[l - i][m - j] = "T";
						}
						// Gán ma trận vào thế cờ
						theCoView = new TheCo(matrix);
					}
				}
				// thêm thế cờ vừa đọc được vào danh sách
				lstTheCoView.add(theCoView);
				// Khởi tạo lại ma trận
				matrix = new String[Constants.MATRIX_ROW][Constants.MATRIX_COL];
			}
		}
		// Trả về danh sách thế cờ từ view
		return lstTheCoView;
	}

	/**
	 * Hàm check win hàng chéo phải
	 * 
	 * @param quanCo
	 *            quân cờ vừa đánh
	 * @param lstOCo
	 *            danh sách bàn cờ trên giao diện
	 * @return true nếu thắng, false nếu không thắng
	 */
	public boolean checkCheoPhai(QuanCo quanCo, JButton[][] lstOCo) {
		// Lấy vị trí hàng của quân cờ vừa đánh trong bàn cờ
		int x = quanCo.getPosRow();
		// Lấy vị trí cột của quân cờ vừa đánh trong bàn cờ
		int y = quanCo.getPosCol();
		// khởi tạo biến đếm số quân cờ để check thắng
		int count = 0;
		// Duyệt 4 vị trí xung quanh quân cờ vừa đánh. Vì chéo phải hàng và cột không
		// cùng tăng hoặc cùng giảm nên xét cả i,j
		for (int i = -4, j = 4; i <= 4 && j >= -4; i++, j--) {
			// Xét điều kiện bao gồm cả vị trí biên
			if ((x + i) >= 0 && (x + i) < Constants.ROW && (y + j) >= 0 && (y + j) < Constants.COL) {
				// Xét điều kiện quân cờ xung quanh ở hàng chéo phải giống ô cờ vừa đánh
				if (lstOCo[x + i][y + j].getText() == lstOCo[x][y].getText()) {
					// Tăng biến đếm lên 1
					count++;
					// Nếu có ít nhất 5 quân cờ liên tiếp nhau
					if (count == 5) {
						// Trả về giá trị true - thắng
						return true;
					}
				} else {
					// Không thì reset lại biến đếm
					count = 0;
				}
			}
		}
		// Chưa thắng trả về false
		return false;
	}

	/**
	 * Hàm check win hàng chéo trái
	 * 
	 * @param quanCo
	 *            quân cờ vừa đánh
	 * @param lstOCo
	 *            danh sách bàn cờ trên giao diện
	 * @return true nếu thắng, false nếu không thắng
	 */
	public boolean checkCheoTrai(QuanCo quanCo, JButton[][] lstOCo) {
		// Lấy vị trí hàng của quân cờ vừa đánh trong bàn cờ
		int x = quanCo.getPosRow();
		// Lấy vị trí cột của quân cờ vừa đánh trong bàn cờ
		int y = quanCo.getPosCol();
		// khởi tạo biến đếm số quân cờ để check thắng
		int count = 0;
		// Duyệt 4 vị trí xung quanh quân cờ vừa đánh
		for (int i = -4; i <= 4; i++) {
			// Xét điều kiện bao gồm cả vị trí biên
			if ((x + i) >= 0 && (x + i) < Constants.ROW && (y + i) >= 0 && (y + i) < Constants.COL) {
				// Xét điều kiện quân cờ xung quanh ở hàng chéo trái giống ô cờ vừa đánh
				if (lstOCo[x + i][y + i].getText() == lstOCo[x][y].getText()) {
					// Tăng biến đếm lên 1
					count++;
					// Xét điều kiện có ít nhất 5 quân cờ liên tiếp nhau
					if (count == 5) {
						// Trả về giá trị true - thắng
						return true;
					}
				} else {
					// Không thì reset lại biến đếm
					count = 0;
				}
			}
		}
		// Chưa thắng trả về false
		return false;
	}

	/**
	 * Hàm check win hàng ngang
	 * 
	 * @param quanCo
	 *            quân cờ vừa đánh
	 * @param lstOCo
	 *            danh sách bàn cờ trên giao diện
	 * @return true nếu thắng, false nếu không thắng
	 */
	public boolean checkHangNgang(QuanCo quanCo, JButton[][] lstOCo) {
		// Lấy vị trí hàng của quân cờ vừa đánh trong bàn cờ
		int x = quanCo.getPosRow();
		// Lấy vị trí cột của quân cờ vừa đánh trong bàn cờ
		int y = quanCo.getPosCol();
		// khởi tạo biến đếm số quân cờ để check thắng
		int count = 0;
		// Duyệt 4 vị trí xung quanh quân cờ vừa đánh
		for (int i = -4; i <= 4; i++) {
			// Xét điều kiện bao gồm cả vị trí biên
			if ((y + i) >= 0 && (y + i) < Constants.COL) {
				// Xét điều kiện quân cờ xung quanh ở hàng ngang giống ô cờ vừa đánh
				if (lstOCo[x][y + i].getText() == lstOCo[x][y].getText()) {
					// Tăng biến đếm lên 1
					count++;
					// Xét điều kiện có ít nhất 5 quân cờ liên tiếp nhau
					if (count == 5) {
						// Trả về giá trị true - thắng
						return true;
					}
				} else {
					// Không thì reset lại biến đếm
					count = 0;
				}
			}
		}
		// Chưa thắng trả về false
		return false;
	}

	/**
	 * Hàm check win hàng dọc
	 * 
	 * @param quanCo
	 *            quân cờ vừa đánh
	 * @param lstOCo
	 *            danh sách bàn cờ trên giao diện
	 * @return true nếu thắng, false nếu không thắng
	 */
	public boolean checkHangDoc(QuanCo quanCo, JButton[][] lstOCo) {
		// Lấy vị trí hàng của quân cờ vừa đánh trong bàn cờ
		int x = quanCo.getPosRow();
		// Lấy vị trí cột của quân cờ vừa đánh trong bàn cờ
		int y = quanCo.getPosCol();
		// khởi tạo biến đếm số quân cờ để check thắng
		int count = 0;
		// Duyệt 4 vị trí xung quanh quân cờ vừa đánh
		for (int i = -4; i <= 4; i++) {
			// Xét điều kiện bao gồm cả vị trí biên
			if ((x + i) >= 0 && (x + i) < Constants.ROW) {
				// Xét điều kiện quân cờ xung quanh ở hàng dọc giống ô cờ vừa đánh
				if (lstOCo[x + i][y].getText().equals(lstOCo[x][y].getText())) {
					// Tăng biến đếm lên 1
					count++;
					// Xét điều kiện có ít nhất 5 quân cờ liên tiếp nhau
					if (count == 5) {
						// Trả về giá trị true - thắng
						return true;
					}
				} else {
					// Không thì reset lại biến đếm
					count = 0;
				}
			}
		}
		// Chưa thắng trả về false
		return false;
	}

	/**
	 * Hàm kiểm tra thắng thua cho người/máy chơi
	 * 
	 * @param quanCo
	 *            quân cờ cần kiểm tra
	 * @param lstOCo
	 *            danh sách các ô cờ
	 * @return true nếu thắng, false nếu ko thắng
	 */
	public boolean checkWin(QuanCo quanCo, JButton[][] lstOCo) {
		// Kiểm tra điều kiện chơi thắng
		if (checkHangDoc(quanCo, lstOCo) || checkHangNgang(quanCo, lstOCo) || checkCheoTrai(quanCo, lstOCo)
				|| checkCheoPhai(quanCo, lstOCo)) {
			// Đúng trả về true
			return true;
		}
		// Sai trả về false
		return false;
	}

	/**
	 * Hàm lấy vị trí máy đánh
	 * 
	 * @param lstOCo
	 * @return jButton ô cờ cần đánh
	 */
	public JButton posComputerPlay(JButton[][] lstOCo) {
		// Khởi tạo ô cờ dạng button
		JButton oCo = new JButton();
		// Khởi tạo biến kiểm tra máy đánh hay chưa. true-đã đánh, false-chưa
		boolean checked = false;
		// Tạo danh sách lấy thế cờ từ View
		ArrayList<TheCo> lstTheCoView = getTheCoView(lstOCo);
		// Duyệt từng thế cờ trong list thế cờ từ file
		for (TheCo theCoFile : lstTheCoFile) {
			// Duyệt từng thế cờ trong list thế cờ từ view
			for (int i = 0; i < lstTheCoView.size(); i++) {
				// Xét điều kiện 2 thế cờ khớp nhau
				if (compareTheCo(theCoFile, lstTheCoView.get(i))) {
					// Lấy vị trí T
					oCo = getPos(theCoFile, lstTheCoView.get(i));
					// Lấy vị trí hàng của ô cờ cần đánh
					int x = i / (Constants.ROW - Constants.MATRIX_ROW + 1) + oCo.getX();
					// Lấy vị trí cột của ô cờ cần đánh
					int y = i % (Constants.COL - Constants.MATRIX_COL + 1) + oCo.getY();
					// Set location cho ô button
					oCo.setLocation(x, y);
					// Máy đã đánh
					checked = true;
					break;
				}
			}
			// Nếu máy đã đánh
			if (checked) {
				break;
			}
		}
		return oCo;
	}

	/**
	 * Hàm so sánh 2 thế cờ từ file và view
	 * 
	 * @param theCoFile
	 * @param theCoView
	 * @return true nếu so sánh khớp, false nếu so sánh chưa khớp
	 */
	public boolean compareTheCo(TheCo theCoFile, TheCo theCoView) {
		// Duyệt theo hàng thế cờ 5x5
		for (int i = 0; i < Constants.MATRIX_ROW; i++) {
			// Duyệt theo cột thế cờ 5x5
			for (int j = 0; j < Constants.MATRIX_COL; j++) {
				// Nếu vị trí từ file O,X,T,D không khớp vị trí O,X,T,T từ view
				if ("O".equals(theCoFile.getMatrix()[i][j]) && !"O".equals(theCoView.getMatrix()[i][j])
						|| "X".equals(theCoFile.getMatrix()[i][j]) && !"X".equals(theCoView.getMatrix()[i][j])
						|| "T".equals(theCoFile.getMatrix()[i][j]) && !"T".equals(theCoView.getMatrix()[i][j])
						|| "D".equals(theCoFile.getMatrix()[i][j]) && !"T".equals(theCoView.getMatrix()[i][j])) {
					// thì trả về false
					return false;
				}
			}
		}
		// Khớp thì trả về true
		return true;
	}

	/**
	 * Hàm lấy vị trí máy cần đánh từ 2 thế cờ file và view
	 * 
	 * @param theCoFile
	 * @param theCoView
	 * @return ô cờ cần đánh dạng button
	 */
	public JButton getPos(TheCo theCoFile, TheCo theCoView) {
		// Khởi tạo button
		JButton oCo = new JButton();
		// Duyệt theo hàng thế cờ/ma trận con 5x5
		for (int i = 0; i < Constants.MATRIX_ROW; i++) {
			// Duyệt theo cột thế cờ/ma trận con 5x5
			for (int j = 0; j < Constants.MATRIX_COL; j++) {
				// Xét điều kiện 2 thế cờ có vị trí T khớp nhau
				if ("T".equals(theCoFile.getMatrix()[i][j]) && "T".equals(theCoView.getMatrix()[i][j])) {
					// setLocation cho ô cờ button đó
					oCo.setLocation(i, j);
				}
			}
		}
		// Trả về ô cờ
		return oCo;
	}

	/**
	 * Hàm kiểm tra đánh hòa
	 */
	public boolean checkEqualPlay(int countCo) {
		if (countCo == (Constants.COL * Constants.ROW) / 2) {
			return true;
		}
		return false;
	}

}
