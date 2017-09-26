/**
* Copyright(C) 2017 Luvina
* CDDatabase.java, Sep 26, 2017 minhhang
*/
package bai2;

/**
 * Lớp xây dựng đối tượng
 * 
 * @author minhhang
 */
public class CDs {
	private int id;
	private String artist, title;

	
	/**
	 * Hàm get id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Hàm set id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Hàm get Artist
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Hàm set Artist
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Hàm get Title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Hàm set Title
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Hàm khởi tạo không tham số
	 */
	public CDs() {
	}

	/**
	 * Hàm khởi tạo có tham số
	 * @param artist
	 * @param title
	 */
	public CDs(String artist, String title) {
		super();
		this.artist = artist;
		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CDDatabase [artist=" + artist + ", title=" + title + "]";
	}

}
