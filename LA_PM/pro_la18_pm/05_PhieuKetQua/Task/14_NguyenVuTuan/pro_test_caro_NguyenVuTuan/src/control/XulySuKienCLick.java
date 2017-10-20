/**
 * Copyright(C) 2017  Luvina
 * ActionListener.java, Oct 4, 2017 [Nguyễn Vũ Tuấn]
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.XuLyLogic;
import view.Gaming;

/**
 * @author [Nguyễn Vũ Tuấn] Purpose: dùng để bắt trạng thái thay đổi của các
 *         button trong giao diện.
 */
public class XulySuKienCLick implements ActionListener {
	/**
	 * Override phương thức bắt sự kiện của Button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		XuLyLogic logic = new XuLyLogic();//khởi tạo một đối tượng logic.
		JButton bt = (JButton) e.getSource();// lấy ra button mà sự kiện rơi vào nó.
		// int x = (int) bt.getClientProperty("x");//lấy tọa độ ô cờ xảy ra sự kiện
		// int y = (int) bt.getClientProperty("y");//lấy tọa độ ô cờ xảy ra sự kiện
		int x = (bt.getX() - Gaming.DO_RONG) / Gaming.DO_RONG;//lấy ra vị trí ô vừa đánh
		int y = (bt.getY() - Gaming.DO_RONG) / Gaming.DO_RONG;//lấy ra vị trí ô vừa đánh
		if (logic.OCoLaD(x, y)) { // nếu vị trí button đó còn trống, chưa có quân cờ nào
			// Người chơi đánh.
			bt.setIcon(new ImageIcon("src/img/chessX.png")); // thay đổi trạng thái hiển thị từ ô trống sang icon X
			// thay đổi giá trị của tọa độ quân cờ vừa đánh trong mảng bàn cờ từ D sang X
			Gaming.dsQuanCo[x][y].setName(Gaming.X + "");
			char kq = logic.kiemTraThang(x, y);// kiểm tra thắng, lưu kết quả kiểm tra được vào kq.
			if ('N' == kq) { // kiểm tra xem người chơi thắng chưa. nếu người chơi chưa thắng
				// Người chơi đánh xong.
				// Máy đánh.
				int[] viTriDanh = logic.layViTriMayDanh(); // lấy ra vị trí mà máy sẽ đánh
				// thay đổi trạng thái hiển thị từ ô trống vị trí máy đánh sang icon O
				Gaming.dsQuanCo[viTriDanh[0]][viTriDanh[1]].setIcon(new ImageIcon("src/img/chessO.png"));
				// thay đổi giá trị của tọa độ quân cờ vừa đánh trong mảng bàn cờ từ D sang O
				Gaming.dsQuanCo[viTriDanh[0]][viTriDanh[1]].setName(Gaming.O + "");
				// hiển thị tọa độ quân cờ máy vừa đánh lên màn hình console
				kq = logic.kiemTraThang(viTriDanh[0], viTriDanh[1]);// kiểm tra xem máy có thắng không.
				if ('Y' == kq) { // Nếu máy thắng
					// gọi đến thông báo của gaming
					Gaming.thongBaoThang("Hệ thống chiến thắng. Muốn chơi tiếp không?");
				} // kết thúc máy đánh
				if ('H' == kq) {// nếu hòa
					Gaming.thongBaoThang("Hòa cờ. Muốn chơi tiếp không?");// gọi đến thông báo của gaming
				} // kết thúc nếu hòa cờ
					// Máy đánh xong.
			} else {// nếu người chơi thắng hoặc hòa
				if ('Y' == kq) {// nếu người chơi thắng
					// gọi đến thông báo của gaming
					Gaming.thongBaoThang("Người chơi chiến thắng. Muốn chơi tiếp không?");
				} else {// nếu hòa cờ
					Gaming.thongBaoThang("Hòa cờ. Muốn chơi tiếp không?");// gọi đến thông báo của gaming
				} // kết thúc trường hợp người chơi hòa
			} // kết thúc trường hợp người chơi thắng hoặc hòa.
		} // kết thúc trường hợp cho phép người chơi đánh cờ vào vị trí button được chọn.
	}// kết thúc phương thức bắt sự kiện thay đổi button.
}// kết thúc class.
