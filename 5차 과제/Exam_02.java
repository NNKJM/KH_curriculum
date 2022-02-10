package Class;

import java.util.Scanner;

public class Exam_02 {

	public static void main(String[] args) {
		Exam_Rectangle rectangle = new Exam_Rectangle();
		Scanner sc = new Scanner(System.in);
		System.out.println("사각형의 가로, 세로를 입력하세요.");
		rectangle.garo = sc.nextInt();
		rectangle.sero = sc.nextInt();
		rectangle.display();
	}

}
