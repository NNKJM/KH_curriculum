package khie;

import java.util.Scanner;

/* Coffee 메뉴를 키보드로 받고 주문수량과 입금액을 입력하면 화면 출력하게 하세요
 *  총금액에는 부가세가 포함되어야 함.
*/

public class Exam_06 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("** coffee 메뉴 **");
		System.out.println("1. 아메리카노 - 3,000원");
		System.out.println("2. 카페라떼 - 4,000원");
		System.out.println("3. 마키아또 - 4,500원");
		System.out.println("4. 바닐라라떼 - 4,500원");
		
		System.out.print("위 메뉴 중 하나를 선택하세요. : ");
		int menuNum = sc.nextInt();
		System.out.print("주문수량 >> ");
		int muchNum = sc.nextInt();
		System.out.print("입금액 >> ");
		int inputMoney = sc.nextInt();
		
		double total = 0;
		int danga = 0;
		
		System.out.println();
		switch(menuNum) {
		case 1 : 
			System.out.println("주문한 메뉴 : 아메리카노");
			danga = 3000;
			total = muchNum*danga*1.1;
			break;
		case 2 : 
			System.out.println("주문한 메뉴 : 카페라떼");
			danga = 4000;
			total = muchNum*danga*1.1;
			break;
		case 3 : 
			System.out.println("주문한 메뉴 : 마끼아또");
			danga = 4500;
			total = muchNum*danga*1.1;
			break;
		case 4 : 
			System.out.println("주문한 메뉴 : 바닐라라떼");
			danga = 4500;
			total = muchNum*danga*1.1;
			break;
		default :
			System.out.println("그런 메뉴는 없습니다.");
		}
		if(danga != 0) {
			System.out.println("커피단가 : " + danga + "원");
			System.out.println("주문수량 : " + muchNum);
			System.out.println("입금액 : " + inputMoney +"원");
			System.out.printf("총금액 : %.0f원\n", total);
			double ex = inputMoney - total;
			System.out.printf("거스름 돈 : %.0f원\n", ex);

		}
		
		sc.close();
		
	}
}
