/**
 * Copyright(C) 2017 Luvina Software Company.
 * ErrorMessage.java, Sep 29, 2017, Pham Van Tuan
 */
package bai2;

/**
 * Chứa các thông điệp về lỗi
 *
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class ErrorMessage {
    public static final String ERROR_CLOSE_CONNECT_DB = "Lỗi kết nối database";
    public static final String ERROR_CONNECT_DB = "Hệ thống đang bị lỗi";
    public static final String ERROR_INPUT_DUPLICATE_PRIMARY_KEY = "Đã tồn tại CD này";
    public static final String ERROR_INPUT_EMPTY = "Không được để trống";
    public static final String ERROR_INPUT_OPTION = "Hãy nhập hàm cần test là từ 1 đến 4";
    public static final String ERROR_INPUT_TOO_LONG = "Không được nhập quá 255 ký tự";

    public static void showArlert(String msg) {
        System.out.println(msg);
    }
}
