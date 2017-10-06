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
	private String[][] matrix;

	/**
	 * @return the matrix
	 */
	public String[][] getMatrix() {
		return matrix;
	}

	/**
	 * @param matrix
	 *            the matrix to set
	 */
	public void setMatrix(String[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * 
	 */
	public TheCo() {
		matrix = new String[5][5];
	}

	/**
	 * @param matrix
	 */
	public TheCo(String[][] matrix) {
		this.matrix = matrix;
	}

}
