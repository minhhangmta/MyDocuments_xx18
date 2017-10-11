/**
* Copyright(C) 2017 Luvina
* Constants.java, Oct 6, 2017 minhhang
*/
package constants;

/**
 * Lớp lưu các hằng số của chương trình
 * 
 * @author minhhang
 */
public class Constants {
	// Tên hiển thị trên thanh tiêu đề của chương trình
	public static final String TITLE = "Game cờ caro";
	// Số hàng của bàn cờ
	public static final int ROW = 20;
	// Số cột của bàn cờ
	public static final int COL = 20;
	// Số cột của ma trận thế cờ
	public static final int MATRIX_COL = 5;
	// Số hàng của ma trận thế cờ
	public static final int MATRIX_ROW = 5;
	// Độ rộng ô cờ
	public static final int SIZE_O_CO = 33;
	// Chiều rộng của Frame
	public static final int WIDTH_FRAME = 700;
	// Chiều dài của Frame
	public static final int HEIGHT_FRAME = 700;
	// Đường dẫn tới file thế cờ
	public static final String PATH_THE_CO = "resources/theco.txt";
	// Kí hiệu cho các quân cờ trong danh sách thế cờ
	// Vị trí máy cần đánh
	public static final String POS_CAN_DANH = "T";
	// Vị trí máy đã đánh
	public static final String POS_MAY_DANH = "O";
	// Vị trí người đã đánh
	public static final String POS_NGUOI_DANH = "X";
	// Vị trí trống chưa đánh
	public static final String POS_TRONG = "D";
	// Title cho form thông báo
	public static final String TITLE_MASSAGE = "Thông báo";
	// Thông báo máy thắng
	public static final String NOTICE_MAY_WIN = "Máy thắng!";
	// Thông báo người thắng
	public static final String NOTICE_NGUOI_WIN = "Người thắng!";
	// Thông báo chơi hòa
	public static final String NOTICE_HOA = "Hòa!";

}
