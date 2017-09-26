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
public class CD {
	private String artist, title;

	/**
	 * 
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * 
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Hàm khởi tạo không tham số
	 */
	public CD() {
	}

	/**
	 * Hàm khởi tạo có tham số
	 * 
	 * @param artist
	 * @param title
	 */
	public CD(String artist, String title) {
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
