/**
 * Copyright(C) 2017 Luvina
 * Properties.java Oct 3, 2017 HungNK
 */
package constants;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Class lưu các hằng số cơ bản của cờ caro
 * @author HungNK
 *
 */
public class Constants {
	//số hàng của bàn cờ
	public static final int ROW = 20;
	//số cột của bàn cờ
	public static final int COL = 20;
	//chiều dài của bàn cờ
	public static final int WIDTH = 800;
	//chiều rộng của bàn cờ
	public static final int HEIGHT = 800;
	//màu border của jbutton
	public static final Color COLOR_BUTTON = Color.BLACK;
	//màu button khi có người chiến thắng
	public static final Color COLOR_WIN = Color.GRAY;
	//mảng 2 chiều của jbutton
	public static JButton[][] arrJButton;
	//biến xác định xem là người dùng không
	public static boolean isHuman = true;
	//tên file thế cờ
	public static final String FILE_THECO = "theco.txt";
}
