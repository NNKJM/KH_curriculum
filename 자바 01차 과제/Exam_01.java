package khie;
/*
 *  [문제] 이름과 국어점수, 영어점수, 수학점수, 자바점수를 키보드로 입력을 받아서 성적을 처리해 보자.
 *            총점, 평균까지 출력해 보자.
 *  조건1. 평균은 소숫점 두 자리까지 입력할 것.
 */

public class Exam_01 {
	public static void main(String[] args) {
		String name = args[0];
		int korean = Integer.parseInt(args[1]);
		int english = Integer.parseInt(args[2]);
		int math = Integer.parseInt(args[3]);
		int java = Integer.parseInt(args[4]);
		int total = korean + english + math + java;
		double avg = total / 4.0;
		System.out.println(name + "의 성적표");
		System.out.println("국어 점수 : " + korean + "점");
		System.out.println("영어 점수 : " + english + "점");
		System.out.println("수학 점수 : " + math + "점");
		System.out.println("자바 점수 : " + java + "점");
		System.out.println("총    점 : " + total + "점");
		System.out.printf("평    균 : %.2f\n", avg + "점");
	}
}
