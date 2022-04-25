package khie;
/*
 * [문제] 배열 알고리즘
 * 키보드로 학생 수와 이름, 국어점수, 영어점수, 수학점수를 입력합니다.
 * 이후 배열에 저장 후 총점, 평균, 학점, 석차 배열에  성적을 처리한 후 화면에 출력하세요.
 */


//		int int_val = Integer.toString(str);


import java.util.Scanner;

public class Exam_03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("학생 수를 입력하세요. : ");
		int size = sc.nextInt();
		String[][] arrayString = new String[2][size];
		double[][] arrayInt = new double[3][size];
		
		for(int i=0; i<size ; i++) {
			System.out.print("이름 입력 : ");
			arrayString[0][i] = sc.next();
			System.out.print("국어점수 입력 : ");
			int c1 = sc.nextInt();
			System.out.print("영어점수 입력 : ");
			int c2 = sc.nextInt();
			System.out.print("수학점수 입력 : ");
			int c3 = sc.nextInt();
			int total = c1 + c2 + c3;
			double avg = total/3.0;
			arrayInt[0][i] = total;
			arrayInt[1][i] = avg;
			if(avg>=90) {
					arrayString[1][i] = "A학점";
				} else if(avg>=80) {
					arrayString[1][i] = "B학점";
				} else if(avg>=70) {
					arrayString[1][i] = "C학점";
				} else if(avg>=60) {
					arrayString[1][i] = "D학점";
				} else {
					arrayString[1][i] = "F학점";
				}
			arrayInt[2][i]=1;
			}
			for(int i=0;i<size;i++) {
				for(int j=0;j<size;j++) {
					if(arrayInt[0][j]>arrayInt[0][i]) {
					arrayInt[2][i]++;
					}
				}
			}
			
			for(int i=0; i<size; i++) {
				System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::");
				System.out.printf("이름 : %s   총점 : %.0f점    평균 : %.2f점   학점 : %s   순위 : %.0f등\n", arrayString[0][i], arrayInt[0][i], arrayInt[1][i], arrayString[1][i], arrayInt[2][i]);
				}
			sc.close();
		}
}