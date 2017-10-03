/**
 * Copyright(C) 2017 Luvina Software Company.
 * CDView.java, Sep 26, 2017, Pham Van Tuan
 */
package bai2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class CDView {
    private List<CD> listCD;

    /**
     * Constructor CDView
     */
    public CDView() throws SQLException {
        listCD = new ArrayList<>();
        try {
            DBConnect dbConnect = DBConnect.getInstance();
            PreparedStatement pst = dbConnect.createPreparedStatement("SELECT * FROM cds;");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CD cd = new CD(rs.getString("artist"), rs.getString("title"));
                listCD.add(cd);
            }
        } catch (SQLException e) {
            throw e; // lỗi kết nối DB
        }


    }

    /**
     * Hiển thị các CD trong DB
     */
    public void showListCD() {
        showListCD(listCD);
    }

    /**
     * Hiển thị các CD theo danh sach
     */
    public static void showListCD(List<CD> list) {
        if (list.size() > 0) {
            System.out.println("=================================================================");
            System.out.printf("%-60s \t %-60s\n", "Artist", "Title");
            for (CD cd : list) {
                String artist = cd.getArtist();
                String title = cd.getTitle();
                List<String> artists = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                int index = 50;
                while (!artist.isEmpty() || !title.isEmpty()) {
                    artists.add(artist.substring(0, Math.min(index, artist.length())));
                    artist = artist.substring(Math.min(index, artist.length()), artist.length());
                    titles.add(title.substring(0, Math.min(index, title.length())));
                    title = title.substring(Math.min(index, title.length()), title.length());
                }
                for (int i = 0; i < artists.size(); i++) {
                    System.out.printf("%-60s \t %-60s\n", artists.get(i), titles.get(i));
                }
                System.out.println();
            }
            System.out.println("=================================================================");
        } else {
            System.out.println("Không có CD nào");
        }
    }
}
