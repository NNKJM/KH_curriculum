package Class;

import java.util.Scanner;

/* 부가세 계산
 * 품명, 단가, 수량은 키보드로 입력 받아서 배열에 저장한다.
 * 부가가치세율 : 1.1로 클래스 멤버로 선언한다.
 * 품명별 금액, 총금액, 공급가액, 부가세, 청구금액은 관련 수식 참조
 *  [관련 수식]
 *  	금액 = 단가*수량
 *  	총금액 = 금액의 합계
 *  	공급가액 = 총금액 / 부가세율
 *  	부가세 = 총금액 - 공급가액
 *  	청구금액 = 공급가액 + 부가세
 */

public class Exam_01 {
	static double tax = 1.1; // 클래스 멤버

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("몇 종류의 음식을 샀는지 적어주세요 : ");
		Exam_Receipt[] receipt = new Exam_Receipt[sc.nextInt()];
		int[] gold = new int[receipt.length];
		for (int i=0; i<receipt.length; i++) {
			System.out.print((i+1) + ": 먹은 음식의 품명, 단가, 수량을 입력하세요");
			receipt[i] = new Exam_Receipt(sc.next(), sc.nextInt(), sc.nextInt());
			gold[i] = receipt[i].price * receipt[i].amount;
		}
		
		System.out.println("품명\t단가\t수량\t금액");
		System.out.println("--------------------------------------------");
		for(int i=0; i<receipt.length; i++) {
			System.out.printf("%s\t%d\t%d\t%d원\n", receipt[i].name, receipt[i].price, receipt[i].amount, gold[i]);
		}
		System.out.println("--------------------------------------------");
		int totalGold = 0;
		for(int i=0; i<gold.length; i++) {
			totalGold += gold[i];
		}
		double supPrice = totalGold / Exam_05.tax;
		double extraPrice = totalGold - supPrice;
		double giveMeMoney = supPrice + extraPrice;
		System.out.printf("공급가액\t\t\t%.0f원\n", supPrice);
		System.out.printf("부가세\t\t\t %.0f원\n", extraPrice);
		System.out.println("--------------------------------------------");
		System.out.printf("청구금액\t\t\t%.0f원\n", giveMeMoney);
		

	}

}
