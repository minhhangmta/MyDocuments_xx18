/**
* Copyright(C) 2017 Luvina
* TheCo.java, Oct 6, 2017 minhhang
*/
package model;

/**
 * Đối tượng thế cờ
 * 
 * @author minhhang
 */
public class TheCo {
	//Khai báo biến mảng 2D ma trận con
	private String[][] matrix;

	/**
	 * @return the matrix
	 */
	public String[][] getMatrix() {
		return matrix;
	}

	/**
	 * @param matrix
	 */
	public TheCo(String[][] matrix) {
		this.matrix = matrix;
	}

}
