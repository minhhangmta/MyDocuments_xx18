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
	// Khai báo biến danh sách thế cờ file
	private ArrayList<TheCo> lstTheCoFile;

	/**
	 * Hàm khởi tạo có tham số
	 * 
	 * @param lstTheCoFile
	 */
	public CaroLogic(ArrayList<TheCo> lstTheCoFile) {
		//Gán giá trị cho danh sách thế cờ từ file
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
						if (Constants.POS_MAY_DANH.equals(lstOCo[l][m].getText())) {
							// Gán giá trị O cho phần tử tương ứng của thế cờ
							matrix[l - i][m - j] = Constants.POS_MAY_DANH;
							// Xét điều kiện text của ô cờ trên bàn cờ là X
						} else if (Constants.POS_NGUOI_DANH.equals(lstOCo[l][m].getText())) {
							// Gán giá trị X cho phần tử tương ứng của thế cờ
							matrix[l - i][m - j] = Constants.POS_NGUOI_DANH;
						} else {
							// Ngược lại còn trường hợp ô cờ có text rỗng
							// Gán giá trị T cho phần tử tương ứng của thế cờ
							matrix[l - i][m - j] = Constants.POS_CAN_DANH;
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
					// Không thắng thì reset lại biến đếm
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
					// Không thắng thì reset lại biến đếm
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
					// Không thắng thì reset lại biến đếm
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
	 * Hàm lấy vị trí máy đánh trên bàn cờ chính
	 * 
	 * @param lstOCo
	 * @return QuanCo quân cờ cần đánh
	 */
	public QuanCo posComputerPlay(JButton[][] lstOCo) {
		// Khởi tạo quân cờ
		QuanCo quanCo = null;
		// Khởi tạo biến kiểm tra máy đánh hay chưa. true-đã đánh, false-chưa
		boolean checked = false;
		// Tạo danh sách lấy thế cờ từ View
		ArrayList<TheCo> lstTheCoView = getTheCoView(lstOCo);
		// Duyệt từng thế cờ trong list thế cờ từ file
		for (TheCo theCoFile : lstTheCoFile) {
			// Duyệt từng thế cờ trong list thế cờ từ view
			for (int i = 0; i < lstTheCoView.size(); i++) {
				// Lấy vị trí tìm được từ ma trận con
				quanCo = getPos(theCoFile, lstTheCoView.get(i));
				// Nếu vị trí tìm được có giá trị
				if (quanCo != null) {
					// Lấy vị trí hàng của ô cờ cần đánh
					int x = i / (Constants.ROW - Constants.MATRIX_ROW + 1) + quanCo.getPosRow();
					// Lấy vị trí cột của ô cờ cần đánh
					int y = i % (Constants.COL - Constants.MATRIX_COL + 1) + quanCo.getPosCol();
					// Khởi tạo giá trị x,y cho quanCo
					quanCo = new QuanCo(x, y);
					// Máy đã đánh
					checked = true;
					break;
				}
			}
			// Nếu máy đã đánh
			if (checked) {
				//Dừng duyệt tiếp
				break;
			}
		}
		//Trả về quân cờ tìm được
		return quanCo;
	}

	/**
	 * Hàm lấy vị trí máy cần đánh trong ma trận con từ 2 thế cờ file và view
	 * 
	 * @param theCoFile
	 * @param theCoView
	 * @return ô cờ cần đánh dạng button
	 */
	public QuanCo getPos(TheCo theCoFile, TheCo theCoView) {
		// Khởi tạo quân cờ
		QuanCo quanCo = null;
		// Tạo biến check máy đánh
		boolean checked = true;
		// Duyệt theo hàng thế cờ/ma trận con 5x5
		for (int i = 0; i < Constants.MATRIX_ROW; i++) {
			// Duyệt theo cột thế cờ/ma trận con 5x5
			for (int j = 0; j < Constants.MATRIX_COL; j++) {
				// Nếu vị trí từ file O,X,T,D không khớp vị trí O,X,T,T từ view
				if (Constants.POS_MAY_DANH.equals(theCoFile.getMatrix()[i][j]) && !Constants.POS_MAY_DANH.equals(theCoView.getMatrix()[i][j])
						|| Constants.POS_NGUOI_DANH.equals(theCoFile.getMatrix()[i][j]) && !Constants.POS_NGUOI_DANH.equals(theCoView.getMatrix()[i][j])
						|| Constants.POS_CAN_DANH.equals(theCoFile.getMatrix()[i][j]) && !Constants.POS_CAN_DANH.equals(theCoView.getMatrix()[i][j])
						|| Constants.POS_TRONG.equals(theCoFile.getMatrix()[i][j]) && !Constants.POS_TRONG.equals(theCoView.getMatrix()[i][j])) {
					// 2 matrix chưa khớp
					checked = false;
					// Trả về quân cờ null
					return null;
					// Xét điều kiện 2 thế cờ có vị trí T khớp nhau
				} else if (Constants.POS_CAN_DANH.equals(theCoFile.getMatrix()[i][j]) && Constants.POS_CAN_DANH.equals(theCoView.getMatrix()[i][j])) {
					// Lấy vị trí cho quân cờ đó
					quanCo = new QuanCo(i, j);
				}
			}
		}
		// Nếu so sánh khớp và lấy được vị trí
		if (checked) {
			// Trả về quân cờ được lấy
			return quanCo;
		}
		// Trả về ô cờ null
		return null;
	}

	/**
	 * Hàm kiểm tra đánh hòa
	 * 
	 * @param countCo
	 *            biến đếm số quân cờ máy đánh
	 * @return true nếu hòa, false nếu không
	 */
	public boolean checkEqualPlay(int countCo) {
		// Nếu số quân cờ máy đánh = nửa số quân cờ trên bàn cờ.
		if (countCo == (Constants.COL * Constants.ROW) / 2) {
			//Trả về đúng nếu hòa
			return true;
		}
		//Trả về false nếu không
		return false;
	}
}
