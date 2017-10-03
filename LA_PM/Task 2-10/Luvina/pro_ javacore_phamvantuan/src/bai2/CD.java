/**
 * Copyright(C) 2017 Luvina Software Company
 * CD.java, Sep 26, 2017, Pham Van Tuan
 */
package bai2;

/**
 * Đối tượng CD ánh xạ với bảng CD trong database
 *
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class CD {

    private String artist;
    private String title;

    /**
     * Constructor khởi tạo CD với 2 tham số
     *
     * @param artist
     * @param title
     */
    public CD(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    /**
     * Get Artist của CD hiện tại
     *
     * @return
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Get Title của CD hiện tại
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "CD{" +
                "artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
