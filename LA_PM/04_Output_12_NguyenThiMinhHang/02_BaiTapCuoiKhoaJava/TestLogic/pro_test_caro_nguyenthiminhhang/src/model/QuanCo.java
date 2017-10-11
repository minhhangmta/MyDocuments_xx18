/**
* Copyright(C) 2017 Luvina
* QuanCo.java, Oct 7, 2017 minhhang
*/
package model;

/**
 * Đối tượng quân cờ
 * 
 * @author minhhang
 */
public class QuanCo {
	//Khai báo vị trí hàng cho quân cờ
	private int posRow;
	//Khai báo vị trí cột cho quân cờ
	private int posCol;

	/**
	 * @return the posRow
	 */
	public int getPosRow() {
		return posRow;
	}

	/**
	 * @return the posCol
	 */
	public int getPosCol() {
		return posCol;
	}

	/**
	 * @param posRow
	 * @param posCol
	 */
	public QuanCo(int posRow, int posCol) {
		this.posRow = posRow;
		this.posCol = posCol;
	}

}
