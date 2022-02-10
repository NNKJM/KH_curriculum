package Class;

import java.util.Scanner;

/*
 * [문제] NameCard 클래스
 * 		멤버 변수 : 이름, 전화번호, 주소, 직급
 *      생성자 : 멤버변수 초기화
 *      멤버 메서드 : 멤버 변수값을 콘솔에 출력하는 메서드
 *      Main 클래스(main 메서드)
 *      키보드로 명함 정보를 입력 받아서 NameCard 클래스 멤버 변수에 초기화
 *      NameCard 클래스의 멤버 메서드를 호출하여 콘솔에 출력
 */

public class Exam_03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Exam_NameCard namecard = new Exam_NameCard();
		System.out.println("이름, 전화번호, 주소, 직급을 입력하세요.");
		namecard.name = sc.nextLine();
		namecard.phone = sc.nextLine();
		namecard.addr = sc.nextLine();
		namecard.position = sc.nextLine();
		namecard.display();
		sc.close();
	}

}
