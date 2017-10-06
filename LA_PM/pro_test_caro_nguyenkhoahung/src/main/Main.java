/**
 * Copyright(C) 2017 Luvina
 * Main.java Oct 3, 2017 HungNK
 */
package main;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import controller.ReadFile;
import ui.CaroBoard;

/**
 * Class chạy hàm main
 * @author HungNK
 *
 */
public class Main {

	/** Phương thức main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//khởi tạo class CaroBoard
		CaroBoard caroBoard = new CaroBoard();
		//khởi tạo các thuộc tính cho bàn cờ
		caroBoard.drawBoard();
		//khởi tạo các thành phần cho bàn cờ
		caroBoard.initCaroBoard();
		ReadFile readFile = new ReadFile();
		List<char[][]> listMatrix = new ArrayList<char[][]>();
		listMatrix = readFile.getMatrixFile(Constants.FILE_THECO);
		char[][] arrChar = listMatrix.get(0);
		for (int i = 0; i < arrChar.length; i++) {
			for (int j = 0; j < arrChar[0].length; j++) {
				System.out.print(arrChar[i][j]);
			}
			System.out.println();
		}
		/*
		for (char[][] cs : listMatrix) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.print(cs[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		}
		*/
	}

}
