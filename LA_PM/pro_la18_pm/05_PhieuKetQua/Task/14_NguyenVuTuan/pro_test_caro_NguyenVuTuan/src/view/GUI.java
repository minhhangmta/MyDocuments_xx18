/**
 * Copyright(C) 2017  Luvina
 * GUI.java, Oct 3, 2017 [Nguyễn Vũ Tuấn]
 */
package view;

import javax.swing.JFrame;

/**
 * @author [Nguyễn Vũ Tuấn] 
 * Purpose: tạo một khung chứa các panel 
 */
@SuppressWarnings("serial") // @SupressWarnings : đây là annotation của java cho phép "shut down" java
							// compiler khi nó lèm bèm về một điều mà nó cảm thấy không đúng.
							// SuppressWarning giúp tắt warning ở một số đoạn code mà mình biết chắc chắn là
							// không vấn đề gì. Đây là warning khi bạn cố tình thực hiện thao tác với một
							// object cần serialize trong khi object đó lại không implement đầy đủ Serialize
							// interface. Và @SuppressWarnings("serial") giúp tắt cái warning nó đi.
public class GUI extends JFrame {
//	public static final int W = 960; // Chiều rộng của khung giao diện
	public static final int W = 670; // Chiều rộng của khung giao diện
	public static final int H = 690; // Chiều dài của khung giao diện
	private Gaming gaming; // Khai báo panel gaming

	/**
	 * Khởi tạo GUI
	 */
	public GUI() {
		khoiTao(); // gọi đến phương thức init
		themComp(); // gọi đến phương thức addComp
	}//Kết thúc methor

	/**
	 * Phương thức khởi tạo và cài đặt thông số cho Frame
	 */
	private void khoiTao() {
		setTitle("Pro_LA18PM_NguyễnVũTuấn"); // đặt title
		setSize(W, H); // đặt size cho khung
		setLocationRelativeTo(null); // cho khung luôn ở giữa
		setResizable(false); // không cho phép thay đổi kích thước Frame
		setDefaultCloseOperation(EXIT_ON_CLOSE);// cài đặt để khi nhấn thoát sẽ thoát chương trình luôn.
	}//Kết thúc methor

	/**
	 * Phương thức thêm Panel vào Frame
	 */
	private void themComp() {
		gaming = new Gaming(); // khởi tạo Panel nội dung
		add(gaming); // thêm panel nội dung vào frame
	}//Kết thúc methor
	/**
	 * hàm mmain để hiển thị
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GUI gui = new GUI(); // khởi tạo GUI
		gui.setVisible(true); // cài đặt thuộc tính để hiển thị GUI
	}// Kết thúc methor
}
