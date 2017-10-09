/**
 * Copyright(C) 2017 Luvina
 * CaroModel.java Oct 5, 2017 minhhang
 */
package logic;

import java.util.ArrayList;

import javax.swing.JButton;

import controller.CaroController;
import model.Constants;
import model.QuanCo;
import model.TheCo;

/**
 * Lớp xử lý logic
 * 
 * @author minhhang
 */
public class CaroLogic {
	//Khởi tạo CaroController
	private CaroController controller = new CaroController();

	/**
	 * Hàm khởi tạo hàm CaroLogic
	 */
	public CaroLogic() {

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
		//Khởi tạo ô cờ dạng button
		JButton oCo = new JButton();
		//Khởi tạo biến kiểm tra máy đánh hay chưa. true-đã đánh, false-chưa
		boolean checked = false;
		//Tạo danh sách lấy thế cờ từ View
		ArrayList<TheCo> lstTheCoView = controller.getTheCoView(lstOCo);
		//Tạo danh sách lấy thế cờ từ File
		ArrayList<TheCo> lstTheCoFile = controller.getTheCoFile();
		//Duyệt từng thế cờ trong list thế cờ từ file
		for (TheCo theCoFile : lstTheCoFile) {
			//Duyệt từng thế cờ trong list thế cờ từ view 
			for (int i = 0; i < lstTheCoView.size(); i++) {
				//Xét điều kiện so sánh thế cờ từ file và từ view khớp nhau
				if (compareTheCo(theCoFile, lstTheCoView.get(i))) {
					
					/* Lấy vị trí T khớp nhau*/
					
					//Duyệt từng hàng của ma trận 5x5
					for (int l = 0; l < Constants.MATRIX_ROW; l++) {
						//Duyệt từng cột của ma trận 5x5
						for (int m = 0; m < Constants.MATRIX_COL; m++) {
							//Xét điều kiện thế cờ file và thế cờ view có vị trí T trùng nhau
							if ("T".equals(theCoFile.getMatrix()[l][m])
									&& "T".equals(lstTheCoView.get(i).getMatrix()[l][m])) {
								//Lấy vị trí đó 
								oCo.setLocation(l, m);
							}
						}
					}/* Lấy vị trí T khớp nhau */
					
					//Lấy vị trí hàng cần đánh trên bàn cờ từ view
					//Vì bàn có có 20x20 ô. duyệt thế cờ 5x5 hàng ngang là 20-5+1=16 nên muốn lấy x,y phải / lấy hàng, % lấy cột
					int x = i / (Constants.ROW - Constants.MATRIX_ROW + 1) + oCo.getX();
					//Lấy vị trí cột cần đánh trên  bàn cờ từ view
					int y = i % (Constants.COL - Constants.MATRIX_COL + 1) + oCo.getY();
					//Lưu vị trí đánh cho ô cờ
					oCo.setLocation(x, y);
					//Máy đã đánh
					checked = true;
					break;
				}
			}
			//Nếu máy đã đánh thì dừng lấy vị trí
			if (checked) {
				break;
			}
		}
		//Trả về ô cờ cần đánh
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
		//Duyệt theo hàng thế cờ 5x5
		for (int i = 0; i < Constants.MATRIX_ROW; i++) {
			//Duyệt theo cột thế cờ 5x5
			for (int j = 0; j < Constants.MATRIX_COL; j++) {
				//Nếu vị trí từ file O,X,T,D không khớp vị trí O,X,T,T từ view  
				if ("O".equals(theCoFile.getMatrix()[i][j]) && !"O".equals(theCoView.getMatrix()[i][j])
						|| "X".equals(theCoFile.getMatrix()[i][j]) && !"X".equals(theCoView.getMatrix()[i][j])
						|| "T".equals(theCoFile.getMatrix()[i][j]) && !"T".equals(theCoView.getMatrix()[i][j])
						|| "D".equals(theCoFile.getMatrix()[i][j]) && !"T".equals(theCoView.getMatrix()[i][j])) {
					//thì trả về false
					return false;
				}
			}
		}
		//Khớp thì trả về true
		return true;
	}

}
