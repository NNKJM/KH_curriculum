package khie;

import java.util.Scanner;

/* �ִ밪 / �ּҰ� �˰���
 * Ű����� �迭�� ũ�⸦ �Է¹ް� ������ �迭��� ��ŭ ������ ������ Ű����� �Է¹ް�
 * �� �� �ִ밪�� �ּҰ��� ���ϼ���.
*/

public class Exam_01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("�迭 ũ��. : ");
		int size = sc.nextInt();
		System.out.println();
		int[] num = new int[size];
		for(int i=0;i<num.length;i++) {
			System.out.print((i+1)+"��° �� �Է� : ");
			num[i] = sc.nextInt();
		}
		int max = num[0], min = num[0];
		for (int i=0;i<num.length;i++) {
			if(max<num[i]) {
				max = num[i];
				}
			if(min>num[i]) {
				min = num[i];
			}
		}
		System.out.printf("max : %d, min : %d", max, min );
		sc.close();
	}
			
}
