/**
 * Copyright(C) 2017 Luvina
 * CaroModel.java Oct 5, 2017 minhhang
 */
package logic;

import java.util.ArrayList;

import javax.swing.JButton;

import model.Constants;
import model.QuanCo;
import model.TheCo;

/**
 * Lớp xử lý logic
 * 
 * @author minhhang
 */
public class CaroLogic {
	// Khai báo danh sách thế cờ từ file
	private ArrayList<TheCo> lstTheCoFile;
	// Khởi tạo lớp GetTheCo
	private GetTheCo getTheCo = new GetTheCo();

	/**
	 * Hàm khởi tạo hàm CaroLogic
	 */
	public CaroLogic() {
		// Gọi hàm lấy thế cờ từ file trong class GetTheCo
		lstTheCoFile = getTheCo.getTheCoFile();
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
					if (count >= 5) {
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
					if (count >= 5) {
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
					if (count >= 5) {
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
					if (count >= 5) {
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
		JButton oCo = new JButton();
		boolean checked = false;
		ArrayList<TheCo> lstTheCoView = getTheCo.getTheCoView(lstOCo);
		for (TheCo theCoFile : lstTheCoFile) {
			for (int i = 0; i < lstTheCoView.size(); i++) {
				if (compareTheCo(theCoFile, lstTheCoView.get(i))) {
					oCo = getPos(theCoFile, lstTheCoView.get(i));
					int x = i / (Constants.ROW - 4) + oCo.getX();
					int y = i % (Constants.COL - 4) + oCo.getY();
					oCo.setLocation(x, y);
					checked = true;
					break;
				}
			}
			if (checked) {
				break;
			}
		}
		return oCo;
	}

	/**
	 * Hàm so sánh thế cờ
	 * 
	 * @param theCoFile
	 * @param theCoView
	 * @return true nếu so sánh khớp, false nếu so sánh chưa khớp
	 */
	public boolean compareTheCo(TheCo theCoFile, TheCo theCoView) {
		for (int i = 0; i < Constants.MATRIX_ROW; i++) {
			for (int j = 0; j < Constants.MATRIX_COL; j++) {
				if ("O".equals(theCoFile.getMatrix()[i][j]) && !"O".equals(theCoView.getMatrix()[i][j])
						|| "X".equals(theCoFile.getMatrix()[i][j]) && !"X".equals(theCoView.getMatrix()[i][j])
						|| "T".equals(theCoFile.getMatrix()[i][j]) && !"T".equals(theCoView.getMatrix()[i][j])
						|| "D".equals(theCoFile.getMatrix()[i][j]) && !"T".equals(theCoView.getMatrix()[i][j])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Hàm lấy vị trí máy đánh
	 * 
	 * @param theCoFile
	 * @param theCoView
	 * @return ô cờ cần đánh dạng button
	 */
	public JButton getPos(TheCo theCoFile, TheCo theCoView) {
		JButton oCo = new JButton();
		for (int i = 0; i < Constants.MATRIX_ROW; i++) {
			for (int j = 0; j < Constants.MATRIX_COL; j++) {
				if ("T".equals(theCoFile.getMatrix()[i][j]) && "T".equals(theCoView.getMatrix()[i][j])) {
					oCo.setLocation(i, j);
				}
			}
		}
		return oCo;
	}
}
