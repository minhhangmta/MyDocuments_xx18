/**
 * Copyright(C) 2017 Luvina Software Company.
 * TestCDDatabase.java, Sep 26, 2017, Pham Van Tuan
 */
package bai2;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class TestCDDatabase {

    /**
     * Test thử các chức năng insert , remove, find trong CDDatabase
     */
    public void test() {
        try {
            CDDatabase cdDatabase = new CDDatabase();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println();
                System.out.println("1- Thêm bài hát \t 2- Xóa bài hát \t 3- Tìm bài hát thêm tiêu" +
                        " đề \t 4- Tìm bài hát theo tác giả \t 0- Exit");
                System.out.printf("Hãy nhập tùy chọn: ");
                String line = scanner.nextLine();
                int value = -1;
                if (line.matches("[0-4]")) {
                    value = Integer.parseInt(line.trim());
                }
                switch (value) {
                    case 1:
                        // insert
                        String artist = "";
                        String title = "";
                        do {
                            System.out.print("Nhập vào Artist:");
                            artist = scanner.nextLine();
                        } while (!validateInput(artist, false));
                        do {
                            System.out.print("Nhập vào Title:");
                            title = scanner.nextLine();
                        } while (!validateInput(title, false));
                        boolean rs = cdDatabase.insertCD(new CD(artist, title));
                        if (rs) {
                            System.out.println("Đã thêm CD thanh công");
                        } else {
                            System.out.println("Thêm CD thất bại");
                        }
                        break;

                    case 2:
                        //remove
                        do {
                            System.out.print("Nhập vào Artist:");
                            artist = scanner.nextLine();
                        } while (!validateInput(artist, false));
                        do {
                            System.out.print("Nhập vào Title:");
                            title = scanner.nextLine();
                        } while (!validateInput(title, false));
                        rs = cdDatabase.removeCD(new CD(artist, title));
                        if (rs) {
                            System.out.println("Đã xóa thành công");
                        } else {
                            System.out.println("Xóa không thành công");
                        }
                        break;

                    case 3:
                        //findByTitle
                        do {
                            System.out.print("Nhập vào Title:");
                            title = scanner.nextLine();
                        } while (!validateInput(title, true));
                        CD[] cds = cdDatabase.findByTitle(title);
                        CDView.showListCD(Arrays.asList(cds));
                        break;
                    case 4:
                        //findByArtist
                        do {
                            System.out.print("Nhập vào Artist:");
                            artist = scanner.nextLine();
                        } while (!validateInput(artist, true));
                        cds = cdDatabase.findByArtist(artist);
                        System.out.println(Arrays.toString(cds));
                        CDView.showListCD(Arrays.asList(cds));
                        break;
                    case 0:
                        //exit
                        exit();
                        return;
                    default:
                        System.out.println("Hãy nhập hàm cần test là từ 1 đến 4");
                        break;

                }
            }
        } catch (SQLException e) {
            ErrorMessage.showArlert(ErrorMessage.ERROR_CONNECT_DB);
            exit();
        }

    }

    private void exit() {
        DBConnect.close();
        System.out.println("Kết thúc");
    }

    public boolean validateInput(String input, boolean empty) {
        if (!empty && input.isEmpty()) {
            ErrorMessage.showArlert(ErrorMessage.ERROR_INPUT_EMPTY);
            return false;
        }
        if (input.length() > 255) {
            ErrorMessage.showArlert(ErrorMessage.ERROR_INPUT_TOO_LONG);
            return false;
        }
        return true;
    }

}
