/** 
 * Copyright (C) 2017 Luvina Academy
 * theCo.java , Oct 5, 2017 , Nguyễn Vũ Tuấn
 */
package model;

import view.Gaming;

/**
 * @author Nguyễn Vũ Tuấn
 *
 */
public class TheCo {
	private char[][] theCo;// mảng 2 chiều lưu thế cờ
	protected int xT, yT; // lưu tọa độ của điểm 'T' trong thế cờ

	/**
	 * khởi tạo class
	 * 
	 * @param theCo
	 *            nhập mảng lưu thế cờ vào
	 */
	public TheCo(char[][] theCo, int xT, int yT) {
		this.theCo = theCo; // gán mảng
		this.xT = xT; // gán giá trị cho tọa độ điểm T
		this.yT = yT;// gán giá trị cho tọa độ điểm T
	}// kết thúc khởi tạo

	/**
	 * trả về thế cờ
	 * 
	 * @return thế cờ
	 */
	public char[][] getTheCo() {
		return theCo; // trả về thế cờ đã khởi tạo
	}// kết thúc methor

	/**
	 * hàm kiểm tra xem đối tượng thế cờ truyền vào có giá trị phù hợp với thế cờ
	 * đang xét không
	 * 
	 * @param obj
	 *            đối tượng thế cờ cần truyền vào.
	 * @return true nếu bằng nhau, false nếu khác nhau.
	 */
	public boolean kiemTraPhuHop(char[][] banCo5x5) {
		if (banCo5x5[xT][yT] == Gaming.D) {// nếu vị trí tương ứng của T trong mảng bàn cờ con 5x5 là trống
			for (int i = 0; i < theCo.length; i++) { // đọc đến các hàng trong theco
				for (int j = 0; j < theCo.length; j++) { // đọc đến các cột trong theco
					// nếu vị trí đang xét trong thế cờ không là ký tự G và T
					if (theCo[i][j] != Gaming.G && theCo[i][j] != Gaming.T) {
						// nếu vị trí trong thế cờ và vị trí tương ứng ở bàn cờ không giống nhau
						if (banCo5x5[i][j] != theCo[i][j]) {
							return false; // trạng thái thế cờ chuyển về không hợp lý
						} // kết thúc câu lệnh điều kiện
					} // kết thúc kiểm tra vị trí đang xét trong thế cờ.
				} // kết thúc vòng lặp cột
			} // kết thúc vòng lặp hàng
		} else {// nếu không
			return false;// trả về sai
		} // kết thúc kiểm tra
		return true;// trả về kết quả mặc định là true
	}// kết thúc methor
}// kết thúc class.
