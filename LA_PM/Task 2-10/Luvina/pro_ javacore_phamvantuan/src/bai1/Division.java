/**
 * Copyright(C) 2017 Luvina Software Company. Division.java, Sep 25, 2017, Pham Van Tuan
 */
package bai1;

/**
 * Viết chương trình chia2 số A/B
 *
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class Division {
    private int soA;
    private int soB;

    /**
     * Hàm khởi tạo
     * @param soA
     * @param soB
     */
    public Division(int soA, int soB) {
        this.soA = soA;
        this.soB = soB;
    }

    /*
        Hàm khởi tạo không tham số
     */
    public Division() {
    }

    /**
     * Tính thương soA/soB
     * @return soA/soB
     * @throws ArithmeticException
     * if soB =0
     */
    public double tinhThuong() throws ArithmeticException {
        return (double) soA / soB;
    }

    /**
     * Get value của soA
     * @return value của soA
     */
    public int getSoA() {
        return soA;
    }

    /**
     * Set value của soA
     * @param soA
     */
    public void setSoA(int soA) {
        this.soA = soA;
    }

    /**
     * Get value của soB
     * @return value của soB
     */
    public int getSoB() {
        return soB;
    }

    /**
     * Get value của soB
     * @param soB
     */
    public void setSoB(int soB) {
        this.soB = soB;
    }
}
