package khie;
/*
 * 1���� ����ڰ� �Է��� �������� Ȧ�� �� ¦�� �� ���ϱ�
 */

import java.util.Scanner;

public class Exam_02 {

	public static void main(String[] args) {
		int oddSum = 0, evenSum = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("�� �Է� : ");
		int inputNum = sc.nextInt();
		for(int i=0; i<=inputNum; i++) {
			if(i%2==0) {
				evenSum = evenSum+i;
			} else
			{
				oddSum = oddSum+i;
			}
			
		}
		System.out.println("Ȧ���հ� : " + oddSum);
		System.out.println("¦���հ� : " + evenSum);
		sc.close();
	}

}
