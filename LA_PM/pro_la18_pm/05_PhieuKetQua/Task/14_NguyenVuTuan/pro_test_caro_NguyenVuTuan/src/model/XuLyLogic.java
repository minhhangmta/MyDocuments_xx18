/** 
 * Copyright (C) 2017 Luvina Academy
 * CheckingMove.java , Oct 4, 2017 , Nguyễn Vũ Tuấn
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import view.Gaming;

/**
 * @author Nguyễn Vũ Tuấn
 *
 */
public class XuLyLogic {
	public static final String PATH_FILE = "src/model/theco.txt"; // đường dẫn file thế cờ
	public static final int SIZE_THE_CO = 5; // kích thước cạnh của 1 thế cờ
	public static ArrayList<TheCo> dsTheCo; // danh sách các thế cờ
	int dem; // biến để đếm số lần kiểm tra thắng cuộc, đồng thời biểu thị số quân cờ có mặt
				// trên bàn cờ.

	/**
	 * khởi tạo class
	 */
	public XuLyLogic() {
	}// Kết thúc methor

	/**
	 * Lấy ra các thế cờ trong file thế cờ
	 */
	public static void layTheCo() {
		dsTheCo = new ArrayList<>(); // khởi tạo danh sách thế cờ
		try {// tạo try-catch để bắt ngoại lệ
				// tạo thuộc tính scan để đọc file thế cờ
			Scanner scan = new Scanner(new InputStreamReader(new FileInputStream(new File(PATH_FILE))));
			int dem = 0; // biến cục bộ dem để đếm số dòng đọc được trong 1 thế cờ.
			char[][] arr = new char[SIZE_THE_CO][SIZE_THE_CO]; // mảng 2 chiều để lưu thế cờ đọc được
			int xT = -1, yT = -1; // biến để lưu tọa độ điểm T trong mảng.
			while (scan.hasNext()) { // trong khi còn chưa đọc hết tệp theco.txt
				String str = scan.nextLine(); // đọc dòng tiếp theo
				dem++; // tăng số dòng đọc trong 1 thế cờ được.
				if (dem == 6) { // nếu dòng đó biểu thị kết thúc 1 thế cờ
					arr = new char[SIZE_THE_CO][SIZE_THE_CO];// tạo mới mảng lưu thế cờ.
					dem = 0; // biểu thị đã đọc xong 1 thế cờ, giá trị dem về 0 để sẵn sàng đọc thế mới
				} else { // nếu số dòng đọc được nằm trong 1 - SIZE_THE_CO
					for (int i = 0; i < SIZE_THE_CO; i++) { // vòng lặp chạy từ 0 đến SIZE_THE_CO-1
						arr[dem - 1][i] = str.charAt(i); // gán giá trị từ ký tự trong chuối đọc được vào mảng
						if (str.charAt(i) == Gaming.T) { // Nếu là ký tự 'T'
							xT = dem - 1; // gán giá trị tọa độ của 'T'
							yT = i;// gán giá trị tọa độ của 'T'
						} // kếtt thúc kiểm tra là 'T' hay không.
					} // kết thúc vòng lặp
					if (dem == 5) {// nếu đọc được 5 dòng trong 1 thế cờ
						dsTheCo.add(new TheCo(arr, xT, yT)); // thế cờ đã hoàn thành => thêm vào danh sách thế cờ.
					} // kết thúc kiểm tra
				} // kết thúc trường hợp số dòng đọc được > 0
			} // kết thúc 1 vòng đọc dòng trong tệp theco.txt
			scan.close();// đóng đối tượng scan
		} catch (FileNotFoundException e) {// bắt lỗi file không tồn tại
			Gaming.thongBao("File theco.txt không tồn tại");// hiển thị lỗi ra giao diện
			System.exit(0); // kết thúc chương trình
		} // kết thúc bắt ngoại lệ
	}// kết thúc methor đọc thế cờ.

	/**
	 * tìm kiếm nước đi phù hợp với các thế đã cho sẵn trong file thế cờ.
	 * 
	 * @return trả về {-1,-1} nếu không tìm được nước đi phù hợp trong file thế cờ,
	 *         trả về tọa độ quân cờ máy sẽ đánh nếu tìm được thế cờ hợp lí
	 */
	public int[] layViTriMayDanh() {
		char[][][][] listBanCoCon = listBanCoCon(); //lấy dữ liệu từ methor
		for (TheCo theCo : dsTheCo) {// vòng lặp đưa ra các thế cờ
			for (int i = 0; i < Gaming.KICH_THUOC - 4; i++) {// vòng lặp để đi đến từng hàng trong Gaming.dsQuanCo
				for (int j = 0; j < Gaming.KICH_THUOC - 4; j++) { // vòng lặp để đi đến từng cột trong Gaming.dsQuanCo
					if (theCo.kiemTraPhuHop(listBanCoCon[i][j])) {//nếu kiểm tra phù hợp
						return (new int [] {i+theCo.xT, j+theCo.yT});//trả về vị trí cần đánh trên bàn cờ
					}//kết thúc kiểm tra
				}//kết thúc vòng lặp
			}//kết thúc vòng lặp
		} // kết thúc vòng lặp lấy ra các thế cờ.
			// trả về kết quả mặc định nếu không tìm được nước cờ hợp lý, dừng methor.
		return (new int[] { -1, -1 });
	} // hết methor.
/**
 * tạo ra list bàn cờ con 5x5 từ bàn cờ to 20x20
 * @return  mảng 2 chiều mà mỗi phần tử là 1 mảng 2 chiều lưu một ô cờ con 5x5
 */
	public char[][][][] listBanCoCon() {
		char[][][][] listBanCoCon = new char[20][20][5][5];//biến lưu dữ liệu trả về
		for (int i = 0; i < Gaming.KICH_THUOC - 4; i++) {// vòng lặp để đi đến từng hàng 
			for (int j = 0; j < Gaming.KICH_THUOC - 4; j++) { // vòng lặp để đi đến từng cột 
				for (int x = 0; x < SIZE_THE_CO; x++) {// vòng lặp để đi đến từng hàng
					for (int y = 0; y < SIZE_THE_CO; y++) {// vòng lặp để đi đến từng cột
						//gán dữ liệu
						listBanCoCon[i][j][x][y] = Gaming.dsQuanCo[i + x][j + y].getName().charAt(0);
					}//đóng vòng lặp
				} // kết thúc vòng lặp lấy ra từng hàng trong thế cờ
			} // kết thúc vòng lặp lấy ra từng cột trong bàn cờ
		} // kết thúc vòng lặp lấy ra từng cột trong bàn cờ
		return listBanCoCon;//trả về dữ liệu
	}//kết thúc methor

	/**
	 * Kiểm tra ô đang kiểm tra có trống hay không?
	 * 
	 * @param x
	 *            tọa độ ô cờ kiểm tra
	 * @param y
	 *            tọa độ ô cờ kiểm tra
	 * @return true nếu đúng, false nếu sai.
	 */
	public boolean OCoLaD(int x, int y) {
		boolean kt = false;// biến kết quả lưu kiểm tra
		if (Gaming.dsQuanCo[x][y].getName().equals("D")) { // nếu ô cờ trống
			kt = true; // gán là true
		} // đóng kiểm tra
		return kt;// trả lại kt
	}// kết thúc methor

	/**
	 * dùng để định dạng mảng 2 chiều Gaming.dsQuanCo về rỗng (tức là chứa toàn ký tự 'D')
	 */
	public static void taoLai() {
		for (int i = 0; i < Gaming.KICH_THUOC; i++) { // chạy các hàng
			for (int j = 0; j < Gaming.KICH_THUOC; j++) { // chạy các cột
				Gaming.dsQuanCo[i][j].setName("D"); // gán giá trị 'D' cho các phần tử của mảng
			} // đóng vòng lặp chạy các cột.
		} // đóng vòng lặp chạy các hàng.
	}// kết thúc methor taolai().

	/**
	 * dùng để kiểm tra xem đã thắng, chưa thắng, hay hòa.
	 * 
	 * @param x
	 *            vị trí quan cờ kiểm tra
	 * @param y
	 *            vị trí quân cờ kiểm tra
	 * @return 'Y' nếu thắng, 'N' nếu không thắng, 'T' nếu hòa cờ.
	 */

	public char kiemTraThang(int x, int y) {
		dem++;// tăng số lần check
		char kq = 'N';// biến lưu kết quả của methor, mặc định là 'N'
		int ngang = 0, doc = 0, trai = 0, phai = 0;// biến biểu thị số lượng quân cờ giống giá trị quân cờ vừa đánh
		for (int i = -4; i <= 4; i++) {// vòng for để đếm ô cùng giá trị liền nhau.
			boolean yPlus = false, xPlus = false, xDown = false;// biến thể hiện giá trị của x+i,y+i,x-i là hợp lệ
			if (0 <= y + i && y + i < Gaming.KICH_THUOC) {// nếu y+i hợp lệ
				yPlus = true;// thay đổi giá trị biến sang true
			} // kết thúc kiểm tra
			if (0 <= x + i && x + i < Gaming.KICH_THUOC) {// nếu y+i hợp lệ
				xPlus = true;// thay đổi giá trị biến sang true
			} // kết thúc kiểm tra
			if (0 <= x - i && x - i < Gaming.KICH_THUOC) {// nếu y+i hợp lệ
				xDown = true;// thay đổi giá trị biến sang true
			} // kết thúc kiểm tra
				// nếu có ô nằm ngang cùng giá trị với ô đang xét thì tăng biến đếm
			if (yPlus && (Gaming.dsQuanCo[x][y + i].getName().equals(Gaming.dsQuanCo[x][y].getName()))) {
				ngang++;// tăng biến đếm
			} else {// nếu không
				ngang = 0;// cho biến đếm về 0
			} // kết thúc kiểm tra
				// nếu có ô nằm dọc cùng giá trị với ô đang xét thì tăng biến đếm
			if (xPlus && (Gaming.dsQuanCo[x + i][y].getName().equals(Gaming.dsQuanCo[x][y].getName()))) {
				doc++;// tăng biến đếm
			} else {// nếu không
				doc = 0;// cho biến đếm về 0
			} // kết thúc kiểm tra
				// nếu có ô nằm chéo trái cùng giá trị với ô đang xét thì tăng biến đếm
			if (yPlus && xPlus && (Gaming.dsQuanCo[x + i][y + i].getName().equals(Gaming.dsQuanCo[x][y].getName()))) {
				trai++;// tăng biến đếm
			} else {// nếu không
				trai = 0;// cho biến đếm về 0
			} // kết thúc kiểm tra
				// nếu có ô nằm chéo phải cùng giá trị với ô đang xét thì tăng biến đếm
			if (yPlus && xDown && (Gaming.dsQuanCo[x - i][y + i].getName().equals(Gaming.dsQuanCo[x][y].getName()))) {
				phai++;// tăng biến đếm
			} else {// nếu không
				phai = 0;// cho biến đếm về 0
			} // kết thúc kiểm tra
				// kiểm tra có đủ 5 ô cờ cạnh nhau, cùng giá trị không
			if (ngang == 5 || doc == 5 || trai == 5 || phai == 5) {
				kq = 'Y';// đúng thì gán kq='Y'
				break;// thoát vòng lặp
			} else {// nếu không
				if (Gaming.KICH_THUOC * Gaming.KICH_THUOC == dem) {// nếu đánh đủ 400 quân cờ
					kq = 'T'; // giá trị hòa, viết tắt của Tie
				} // kết thúc kiểm tra hòa cờ
			} // kết thúc kiểm tra thắng cờ
		} // kết thúc vòng lặp
		return kq;// trả về kết quả
	}// hết methor.

}// kết thúc class.
