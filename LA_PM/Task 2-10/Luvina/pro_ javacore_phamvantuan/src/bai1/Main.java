/**
 * Copyright(C) 2017 Luvina Software Company. Main.java, Sep 25, 2017, Pham Van Tuan
 */
package bai1;

import java.util.Scanner;

/**
 * Run chương trình
 *
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class Main {
    /**
     * Hàm main
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int soA = 0;
        int soB = 0;
        soA = nhapSo(scanner, "A");
        soB = nhapSo(scanner, "B");
        Division bai1 = new Division(soA, soB);
        System.out.printf("Thương A/B bằng: %d", bai1.tinhThuong());

    }

    /**
     * Nhập số từ bàn phím: number >=0 && number <10000
     *
     * @param scanner Scanner
     * @param name    tên của số cần nhập
     * @return number >=0 && number <10000
     */
    private static int nhapSo(Scanner scanner, String name) {
        int number = 0;
        do {
            System.out.printf("Giá trị [%s]:", name);
            String line = scanner.nextLine();
            String alert = checkInput(line, name);
            if (alert == null) {
                number = Integer.parseInt(line);
            } else {
                System.out.println(alert);
            }
        } while (number <= 0);
        return number;
    }

    /**
     * Kiểm tra giá trị
     *
     * @param line - giá trị cần kiểm tra
     * @param name - tên của biến
     * @return if valid return <tt>null</tt>. Nếu không return alert String
     */
    private static String checkInput(String line, String name) {
        String alert = null;
        if ("".equals(line)) {
            alert = String.format("Hãy nhập giá trị cho [%s].", name);
        } else if (line.matches("0+") || !line.matches("[0-9]{1,5}")) {
            alert = String.format("Giá trị [%s] phải là giá trị số và > 0. Hãy nhập lại.", name);
        } else if (line.length() > 5) {
            alert = String.format("Giá trị [%s] không được lớn hơn 5 số. Hãy nhập lại.", name);
        }
        return alert;
    }
}
