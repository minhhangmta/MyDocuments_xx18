/**
 * Copyright(C) 2017 Luvina Software Company.
 * Main.java, Sep 26, 2017, Pham Van Tuan
 */
package bai2;

import java.sql.SQLException;

/**
 * Run chương trình
 *
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class Main {
    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            CDView cdView = new CDView();
            cdView.showListCD();
            TestCDDatabase testCDDatabase = new TestCDDatabase();
            testCDDatabase.test();
        } catch (SQLException e) {
            ErrorMessage.showArlert(ErrorMessage.ERROR_CONNECT_DB);
        }

    }
}
