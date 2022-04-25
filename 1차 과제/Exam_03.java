package khie;

/*
 * [문제] 키보드로부터  입금액, 상품의 단가, 수량을 입력받아 부가세액(상품금액의 10%), 상품총액(상품금액+부가세액)과 거스름돈(입금액-상품총액)을 구하여 화면에 출력해 보자.
 */

public class Exam_03 {
	public static void main(String[] args) {
		int moneyInput = Integer.parseInt(args[0]);
		int howMuch = Integer.parseInt(args[1]);
		int productMany = Integer.parseInt(args[2]);
		int productMuch = howMuch*productMany; // 상품 금액
		double tax = productMuch*0.1;
		double total = productMuch + tax;
		double extra = moneyInput - total;
		System.out.printf("입금액 : %d원\n제품 단가 : %d원\n수량 : %d개\n부가세액 : %.0f원\n상품총액 : %.0f원\n잔액 : %.0f원\n", moneyInput, howMuch, productMany, tax, total, extra);
	}
}
