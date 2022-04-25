package khie;
/*
 * 1부터 사용자가 입력한 수까지의 홀수 및 짝수 합 구하기
 */

import java.util.Scanner;

public class Exam_02 {

	public static void main(String[] args) {
		int oddSum = 0, evenSum = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("수 입력 : ");
		int inputNum = sc.nextInt();
		for(int i=0; i<=inputNum; i++) {
			if(i%2==0) {
				evenSum = evenSum+i;
			} else
			{
				oddSum = oddSum+i;
			}
			
		}
		System.out.println("홀수합계 : " + oddSum);
		System.out.println("짝수합계 : " + evenSum);
		sc.close();
	}

}
