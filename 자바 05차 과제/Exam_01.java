package Class;

import java.util.Scanner;

public class Exam_01 {
	public static void main(String[] args) {
		Exam_Person person = new Exam_Person();
		Scanner sc = new Scanner(System.in);
		System.out.println("이름, 성별(남자/여자), 나이를 입력하세요.");
		person.name = sc.next();
		person.gender = sc.next();
		person.age = sc.nextInt();
		person.display();
	}

}
