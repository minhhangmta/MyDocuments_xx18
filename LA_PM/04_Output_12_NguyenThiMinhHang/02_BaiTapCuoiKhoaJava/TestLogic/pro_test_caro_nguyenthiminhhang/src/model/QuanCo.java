/**
* Copyright(C) 2017 Luvina
* QuanCo.java, Oct 7, 2017 minhhang
*/
package model;

/**
 * Description here
 * 
 * @author minhhang
 */
public class QuanCo {
	private int xRow;
	private int yCol;

	/**
	 * @return the xRow
	 */
	public int getxRow() {
		return xRow;
	}

	/**
	 * @param xRow
	 *            the xRow to set
	 */
	public void setxRow(int xRow) {
		this.xRow = xRow;
	}

	/**
	 * @return the yCol
	 */
	public int getyCol() {
		return yCol;
	}

	/**
	 * @param yCol
	 *            the yCol to set
	 */
	public void setyCol(int yCol) {
		this.yCol = yCol;
	}

	/**
	 * 
	 */
	public QuanCo() {
	}

	/**
	 * @param xRow
	 * @param yCol
	 */
	public QuanCo(int xRow, int yCol) {
		this.xRow = xRow;
		this.yCol = yCol;
	}

}
