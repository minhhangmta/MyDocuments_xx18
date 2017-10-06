/**
* Copyright(C) 2017 Luvina
* QuanCo.java, Oct 6, 2017 minhhang
*/
package model;

/**
 * Đối tượng quân cờ
 * @author minhhang
 */
public class QuanCo {
	private int x, y;

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 
	 */
	public QuanCo() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 */
	public QuanCo(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
