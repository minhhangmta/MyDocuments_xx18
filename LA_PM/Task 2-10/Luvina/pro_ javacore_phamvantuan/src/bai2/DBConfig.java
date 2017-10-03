/**
 * Copyright(C) 2017 Luvina Software Company.
 * DBConfig.java, Sep 27, 2017, Pham Van Tuan
 */
package bai2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Load config database
 *
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class DBConfig {
    private static String URL;
    private static String USER_NAME;
    private static String PASSWORD;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new BufferedInputStream(new FileInputStream("sql.properties")));
            URL = properties.getProperty("url");
            USER_NAME = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            //e.printStackTrace();
            Log.log(e.getMessage());
        }
    }

    /**
     * @return
     */
    public static String getURL() {
        return URL;
    }

    /**
     * @return
     */
    public static String getUserName() {
        return USER_NAME;
    }

    /**
     * @return
     */
    public static String getPASSWORD() {
        return PASSWORD;
    }
}
