package khie;
/*
 * ����, ź��ȭ��, �ܹ��� Į�θ��� �հ踦 ����ϴ� ���α׷�
 * �� Į�θ� : ���� 9Į�θ�/�׷�, ź��ȭ�� & �ܹ��� 4Į�θ�/�׷�
 */

import java.util.Scanner;

public class Exam_01 {
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.print("������ �׷��� �Է��ϼ��� : ");
	int fat = sc.nextInt();
	System.out.print("ź��ȭ���� �׷��� �Է��ϼ��� : ");
	int carbohydrate = sc.nextInt();
	System.out.print("�ܹ����� �׷��� �Է��ϼ��� : ");
	int protein = sc.nextInt();
	System.out.printf("��Į�θ� : %d cal", (fat*9)+(carbohydrate*4)+(protein*4));
	
	sc.close();
	
	}
}
