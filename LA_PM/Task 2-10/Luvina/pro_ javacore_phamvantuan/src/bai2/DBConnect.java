/**
 * Copyright(C) 2017 Luvina Software Company.
 * DBConnect.java, Sep 26, 2017, Pham Van Tuan
 */
package bai2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Connect Database
 *
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class DBConnect {
    private volatile static DBConnect INSTANCE;

    private Connection connection;

    private DBConnect() {

    }

    /**
     * Close connect DB
     */
    public synchronized static void close() {
        if (INSTANCE != null && INSTANCE.connection != null) {
            try {
                if (!INSTANCE.connection.isClosed()) {
                    INSTANCE.connection.close();
                }
                INSTANCE.connection = null;
            } catch (SQLException e) {
                //e.printStackTrace();
                Log.log(e.getMessage());
                ErrorMessage.showArlert(ErrorMessage.ERROR_CLOSE_CONNECT_DB);
            }
        }

    }

    /**
     * Get object DBConnect
     *
     * @return DBConnect
     * @throws SQLException
     */
    public synchronized static DBConnect getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DBConnect();
        }
        return INSTANCE;
    }

    /**
     * Create a preparedStatement
     *
     * @param sql
     * @return
     */
    public PreparedStatement createPreparedStatement(String sql) throws SQLException {
        try {
            if (connection == null) {
                connection = getConnection();
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement;
        } catch (SQLException e) {
            throw new SQLException(ErrorMessage.ERROR_CONNECT_DB); // lỗi kết nối db
        }
    }

    /**
     * Get Connection of DBConnect
     *
     * @return
     */
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DBConfig.getURL(), DBConfig.getUserName(),
                        DBConfig.getPASSWORD());
            } catch (SQLException e) {
                throw new SQLException(ErrorMessage.ERROR_CONNECT_DB); // lỗi kết nối db
            }
        }
        return connection;
    }


}
