package abstracts;

import java.util.Scanner;

import overriding.Exam_Permanent;

public class Exam_01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("고용형태 - 정규직<P>, 임시직<T>를 입력하세요 : ");
		String choice = sc.next();
		if(choice.equalsIgnoreCase("P") || choice.equalsIgnoreCase("정규직")) {
			System.out.println("이름, 기본급, 보너스를 입력하세요.");
			Exam_Permanent1 p = new Exam_Permanent1(sc.next(), sc.nextInt(), sc.nextInt());
			System.out.println("=============================");
			System.out.println("고용형태 : 정규직");
			System.out.println("이름 : " + p.getName());
			System.out.println("급여 : " + p.getPays() + "원");
		} else if (choice.equalsIgnoreCase("T") || choice.equalsIgnoreCase("임시직"))
		{
			System.out.println("이름, 작업시간, 시간당 급여를 입력하세요.");
			Exam_Temporary1 t = new Exam_Temporary1(sc.next(), sc.nextInt(), sc.nextInt());
			System.out.println("=============================");
			System.out.println("고용형태 : 임시직");
			System.out.println("이름 : " + t.getName());
			System.out.println("급여 : " + t.getPays() + "원");
		} else {
			System.out.println("정규직<P>, 임시직<T> 중에서만 입력해주세요.");
		}
		
		sc.close();
	}
}
