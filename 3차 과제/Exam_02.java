package khie;
/*
 * ������ ���� 5���� Ű����� �Է¹޾Ƽ� �迭�� ������ �� ������������ �����Ͽ� ����ϼ���.
 */

import java.util.Scanner;

public class Exam_02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] num = new int[5];
		System.out.println("5���� ���ڸ� �Է��ϼ���. : ");
		for(int i=0; i<5; i++) {
			num[i]=sc.nextInt();
		}
		System.out.println("===������������ ����===");
		int temp = 0;
		for(int i=0; i<5;i++) {
			for(int j=i+1; j<5;j++) {
				if(num[j] > num[i]) {
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
		}
		for(int i=0; i<num.length;i++) {
			System.out.print(num[i]+"          ");
		}
	}

}
