/**
 * Copyright(C) 2017  Luvina
 * Gaming.java, Oct 3, 2017 [Nguyễn Vũ Tuấn]
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.*;
import control.XulySuKienCLick;
import model.XuLyLogic;

/**
 * @author [Nguyễn Vũ Tuấn] Purpose: tạo ra 1 panel để chứa các giao diện đồ
 *         họa.
 */
@SuppressWarnings("serial") // @SupressWarnings : đây là annotation của java cho phép "shut down" java
							// compiler khi nó lèm bèm về một điều mà nó cảm thấy không đúng.
							// SuppressWarning giúp tắt warning ở một số đoạn code mà mình biết chắc chắn là
							// không vấn đề gì. Đây là warning khi bạn cố tình thực hiện thao tác với một
							// object cần serialize trong khi object đó lại không implement đầy đủ Serialize
							// interface. Và @SuppressWarnings("serial") giúp tắt cái warning nó đi.
public class Gaming extends JPanel {
	public static JButton[][] dsQuanCo; // Danh sách các button quân cờ
	public static final int DO_RONG = 30, KICH_THUOC = 20; // kích thước của bàn cờ; // độ rộng của 1 button ô cờ
	public static final char X = 'X', O = 'O', G='G', D='D', T='T'; // khai báo 2 biến cố định diễn tả quân cờ X và O, G, T, D trong mảng bàn cờ và thế cờ.
	public XulySuKienCLick suKienClick = new XulySuKienCLick(); //khai báo 1 class  xử lí sự kiện
	/**
	 * khở tạo class
	 * 
	 * @param label
	 */
	public Gaming() {
		khoiTao(); // gọi đến methor init()
		themComp(); // gọi đến methor addComp()
	}// kết thúc methor

	/**
	 * dùng để cài đặt các thuộc tính của class và khởi tạo các thuộc tính của class
	 */
	private void khoiTao() {
		setLayout(null); // cài đặt layout
		XuLyLogic.layTheCo(); //lấy thế cờ từ file ra
		setBackground(Color.LIGHT_GRAY); // cài đặt background
		dsQuanCo = new JButton[20][20]; // khởi tạo danh sách button quân cờ
	}

	/**
	 * dùng để thêm các Component của panel
	 */
	private void themComp() {
		for (int i = 0; i < KICH_THUOC; i++) { // vòng lặp duyệt từng hàng
			for (int j = 0; j < KICH_THUOC; j++) { // vòng lặp duyệt từng cột
				JButton caro = taoButtonCaro(i, j);// dùng methor caro() để tạo ra 1 button, gán cho biến caro
				add(caro); // thêm button vào panel
				dsQuanCo[i][j] = caro; // thêm button vào danh sách button quân cờ
			} // kết thúc vòng duyệt từng cột
		} // kết thúc vòng duyệt từng hàng
		add(label("Design by Nguyễn Vũ Tuấn"));// thêm dòng label cho đẹp
	}// kết thúc methor

	/**
	 * 
	 * @param x
	 *            tọa độ của quân cờ
	 * @param y
	 *            tọa độ của quân cờ
	 * @return button hiển thị quân cờ
	 */
	private JButton taoButtonCaro(int x, int y) {
		JButton bt = new JButton();// khởi tạo 1 biến địa phương button
		// cài dặt tọa độ, kích thước cho button trên.
		bt.setBounds(x * DO_RONG + DO_RONG, y * DO_RONG + DO_RONG, DO_RONG, DO_RONG);
		bt.setBackground(Color.WHITE);// Cài màu nền cho button
		bt.setName("D");
		bt.addActionListener(suKienClick);// cài lắng nghe hoạt động cho button
		return bt;// trả về button vừa tạo
	}// kết thúc methor

	/**
	 * tạo ra label hiển thị trên giao diện
	 * 
	 * @param str
	 *            nội dung hiển thị
	 * @return một label
	 */
	private JLabel label(String str) {
		JLabel lb = new JLabel(str); // Khởi tạo label thông tin
		Font font = new Font("Time New Romans", Font.BOLD, 12); // Khởi tạo font
		lb.setFont(font);
		FontMetrics fontMetrics = getFontMetrics(font); // Lấy đối tượng FontMetrics
		int lbWidth = fontMetrics.stringWidth(lb.getText()); // Lấy chiều dài đoạn text trong label
		int lbHeight = fontMetrics.getHeight(); // Lấy chiều cao đoạn text trong label
		lb.setBounds(GUI.W / 2 - lbWidth / 2, GUI.H - lbHeight - DO_RONG - 5, lbWidth, lbHeight); // Căn giữa label
		return lb;// trả lại label vừa cài đặt
	}// kết thúc methor

	/**
	 * dùng để hiển thị thông báo.
	 * 
	 * @param str
	 *            câu thông báo cần hiển thị
	 */
	public static void thongBaoThang(String str) {
		// khởi tạo câu thông báo, trả dữ liệu lựa chọn cho biến jop.
		int jop = thongBao(str);
		if (jop == JOptionPane.YES_OPTION) { // nếu lựa chọn là Yes
			taoLai();// gọi methor reset()
			XuLyLogic.taoLai();
		} else {// nếu không lựa chọn Yes
			if (jop == JOptionPane.NO_OPTION) {// Nếu lựa chọn No
				System.exit(0);// dừng chương trình, thoát.
			} else {// nếu không chọn Yes hoặc No
				dungLai();// gọi đến phương thức stop();
			} // kết thúc câu lệnh kiểm tra lựa chọn ngoài nhánh Yes
		} // kết thúc lệnh kiểm tra JOP
	}// kết thúc methor

	/**
	 * để đưa ra 1 thông báo theo hình thức hộp thoại
	 * 
	 * @return lựa chọn của người dùng
	 */
	public static int thongBao(String str) {
		// trả về lựa chọn sau khi thông báo
		return JOptionPane.showConfirmDialog(null, str, "Thông báo", JOptionPane.YES_NO_OPTION);
	}// kết thúc methor

	/**
	 * dùng để dừng hẳn chương trình.
	 */
	public static void dungLai() {
		for (int i = 0; i < KICH_THUOC; i++) {// vòng lặp để duyệt các quân cờ trong danh sách
			for (int j = 0; j < KICH_THUOC; j++) {
				dsQuanCo[i][j].setEnabled(false);// cài đặt để button quân cờ không hoạt động nữa
			}
		} // kết thúc vòng lặp
	}// kết thúc methor

	/**
	 * dùng để cài đặt lại các thông số của bàn cờ
	 */
	public static void taoLai() {
		for (int i = 0; i < KICH_THUOC; i++) {// vòng lặp để duyệt các quân cờ trong danh sách
			for (int j = 0; j < KICH_THUOC; j++) {
				dsQuanCo[i][j].setIcon(null);;// cài đặt để button quân cờ không hoạt động nữa
			}
		} // kết thúc vòng lặp
	}// kết thúc methor
}// kết thúc chương trình
