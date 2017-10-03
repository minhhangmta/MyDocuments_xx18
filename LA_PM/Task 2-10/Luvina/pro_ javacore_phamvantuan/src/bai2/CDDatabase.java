/**
 * Copyright(C) 2017 Luvina Software Company.
 * CDDatabase.java, Sep 26, 2017, Pham Van Tuan
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
public class CDDatabase {

    private DBConnect dbConnect;

    public CDDatabase() {
        dbConnect = DBConnect.getInstance();
    }

    /**
     * @param cd object CD của lớp CD
     * @return true nếu insert thành công vào database false nếu không thành công
     */
    public boolean insertCD(CD cd) throws SQLException {
        String sql = "INSERT CDs(artist,title)VALUE (?,?)";
        PreparedStatement preparedStatement = dbConnect.createPreparedStatement(sql);
        if (preparedStatement != null) {
            try {
                preparedStatement.setString(1, cd.getArtist());
                preparedStatement.setString(2, cd.getTitle());
                if (preparedStatement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException e) {
                if (e.getMessage().contains("Duplicate")) {
                    ErrorMessage.showArlert(ErrorMessage.ERROR_INPUT_DUPLICATE_PRIMARY_KEY);
                } else {
                    //e.printStackTrace();
                    Log.log(e.getMessage());
                }

                //  Dublicate primary Key
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    /**
     * remove CD trong database với artist, title trùng
     *
     * @param cd object CD của lớp CD
     * @return true nếu trong database không còn bản ghi nào như vậy nữa . Ngoài ra return false
     */
    public boolean removeCD(CD cd) throws SQLException {
        String sql = "DELETE cds FROM cds WHERE artist = ? AND title =?;";
        PreparedStatement preparedStatement = dbConnect.createPreparedStatement(sql);
        if (preparedStatement != null) {
            try {
                preparedStatement.setString(1, cd.getArtist());
                preparedStatement.setString(2, cd.getTitle());
                if (preparedStatement.executeUpdate() >= 0) {
                    return true;
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                Log.log(e.getMessage());
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    /**
     * tìm kiếm các CD có mà artist của nó có chứa chuỗi giá trị artist
     *
     * @param artist
     * @return - Array CD
     */
    public CD[] findByArtist(String artist) throws SQLException {
        List<CD> listCD = new ArrayList<>();
        String sql = "SELECT * FROM cds WHERE cds.artist LIKE ?";
        PreparedStatement preparedStatement = dbConnect.createPreparedStatement(sql);
        if (preparedStatement != null) {
            try {
                artist = "%" + FormatQuery.formatParameter(artist) + "%";
                preparedStatement.setString(1, artist);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    CD cd = new CD(rs.getString("artist"), rs.getString("title"));
                    listCD.add(cd);
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                Log.log(e.toString());
            } finally {
                DBConnect.close();
            }
        }
        return listCD.toArray(new CD[0]);
    }

    /**
     * tìm kiếm các CD có mà title của nó có chứa chuỗi giá trị title truyền vào
     *
     * @param title
     * @return
     */
    public CD[] findByTitle(String title) throws SQLException {
        List<CD> listCD = new ArrayList<>();
        String sql = "SELECT * FROM cds WHERE cds.title LIKE ?";
        PreparedStatement preparedStatement = dbConnect.createPreparedStatement(sql);
        if (preparedStatement != null) {
            try {
                title = "%" + FormatQuery.formatParameter(title) + "%";
                preparedStatement.setString(1, title);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    CD cd = new CD(rs.getString("artist"), rs.getString("title"));
                    listCD.add(cd);
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                Log.log(e.getMessage());
            } finally {
                DBConnect.close();
            }
        }
        return listCD.toArray(new CD[0]);
    }


}
