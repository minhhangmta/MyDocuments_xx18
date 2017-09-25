/**
 * Copyright(C) 2017 Luvina
 * Mai.java Sep 25, 2017 minhhang
 */
package bai1;

/**
 * Description class here
 * 
 * @author minhhang
 */
public class Main {

	/**
	 * Hàm main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DivisionNumber div = new DivisionNumber();
		String A = div.getData("A");
		String errorA = div.validateData(A, "A");
		while (!"".equals(errorA)) {
			System.out.println(errorA);
			A = div.getData("A");
			errorA = div.validateData(A, "A");
		}

		String B = div.getData("B");
		String errorB = div.validateData(B, "B");
		while (!"".equals(errorB)) {
			System.out.println(errorB);
			B = div.getData("B");
			errorB = div.validateData(B, "B");
		}

		double result = div.divideNumber(Integer.parseInt(A), Integer.parseInt(B));
		System.out.println(div.printResult(result));
	}
}
