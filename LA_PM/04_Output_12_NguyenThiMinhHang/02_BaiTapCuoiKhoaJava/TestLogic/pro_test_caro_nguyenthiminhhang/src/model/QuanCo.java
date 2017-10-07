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
	private int posRow;
	private int posCol;

	/**
	 * @return the posRow
	 */
	public int getPosRow() {
		return posRow;
	}

	/**
	 * @param posRow
	 *            the posRow to set
	 */
	public void setPosRow(int posRow) {
		this.posRow = posRow;
	}

	/**
	 * @return the posCol
	 */
	public int getPosCol() {
		return posCol;
	}

	/**
	 * @param posCol
	 *            the posCol to set
	 */
	public void setPosCol(int posCol) {
		this.posCol = posCol;
	}

	/**
	 * 
	 */
	public QuanCo() {
		super();
	}

	/**
	 * @param posRow
	 * @param posCol
	 */
	public QuanCo(int posRow, int posCol) {
		super();
		this.posRow = posRow;
		this.posCol = posCol;
	}

}
