package khie;

public class Exam_02 {
	/*
	 * [문제] 임의의 숫자 하나를 키보드로부터 입력 받아서 그 숫자의 제곱과 세제곱을 구해보자.
	 */

	public static void main(String[] args) {
		int su = Integer.parseInt(args[0]);
		System.out.println("입력받은 정수 : " + su);
		System.out.println(su+ "의 제곱 : " + (su*su));
		System.out.println(su+ "의 세제곱 : " + (su*su*su));
	}
}
