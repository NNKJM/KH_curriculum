package Class;

import java.util.Scanner;

/*
 * 멤버변수 : 이름, 작업시간, 시간당 급여
 * 생성자 : 멤버 변수 초기화 역할
 * 메서드 : 급여 계산 관련 메서드
 *  총급여액 = 일한시간 * 시간당 급여
 *  공제액 = 총급여액 * 0.03
 *  실지급액 = 총급여액 - 공제액
 * main에서 키보드로 이름, 작업시간, 시간당 급여를 입력 받아 생성자를 이용해 멤버 변수 초기화 -> 급여 계산 관련 메서드를 호출해 급여 계산 후 결과 출력
 */

public class Exam_04 {
	String name;
	int time;
	int pay;

	public static void main(String[] args) {
		Exam_04 ex = new Exam_04();
		Scanner sc = new Scanner(System.in);
		System.out.println("이름, 작업시간, 시간당 급여를 입력하세요 : ");
		ex.name = sc.next();
		ex.time = sc.nextInt();
		ex.pay = sc.nextInt();
		int total = ex.time * ex.pay;
		double gongje = total * 0.03;
		double realMoney = total - gongje;
		System.out.printf("이름 : %s\n총급여 : %d원\n공제액 : %.0f원\n실지급액 : %.0f원\n", ex.name, total, gongje, realMoney);
		sc.close();
	}
}
