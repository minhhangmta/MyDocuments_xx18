/**
 * Copyright(C) 2017 Luvina
 * Button.java Oct 3, 2017 HungNK
 */
package ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import constants.Constants;

/**
 * Class đặc tả các thuộc tính của button trên bàn cờ
 * @author HungNK
 *
 */
public class Button extends JButton {
	/**
	 * Constructor hàm button
	 */
	public Button() {
		// Đặt backgroud cho button
		this.setBackground(Color.WHITE);
		// Đặt font cho button
		this.setFont(new Font("Tahoma", Font.BOLD, 30));
		// Đặt border cho button
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Constants.COLOR_BUTTON));
	}
}
