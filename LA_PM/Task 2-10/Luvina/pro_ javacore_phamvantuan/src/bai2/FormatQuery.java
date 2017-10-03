/**
 * Copyright(C) 2017 Luvina Software Company.
 * FormatQuery.java, Sep 26, 2017, Pham Van Tuan
 */
package bai2;

/**
 * Format SQL query
 *
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class FormatQuery {
    /**
     * Format parameter in LIKE query
     *
     * @param parame
     * @return
     */
    public static String formatParameter(String parame) {
        parame = parame.replace("\\", "\\\\");
        parame = parame.replace("%", "\\%");
        parame = parame.replace("_", "\\_");
        return parame;
    }
}
