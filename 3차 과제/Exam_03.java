package khie;
/*
 * [����] �迭 �˰���
 * Ű����� �л� ���� �̸�, ��������, ��������, ���������� �Է��մϴ�.
 * ���� �迭�� ���� �� ����, ���, ����, ���� �迭��  ������ ó���� �� ȭ�鿡 ����ϼ���.
 */


//		int int_val = Integer.toString(str);


import java.util.Scanner;

public class Exam_03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("�л� ���� �Է��ϼ���. : ");
		int size = sc.nextInt();
		String[][] arrayString = new String[2][size];
		double[][] arrayInt = new double[3][size];
		
		for(int i=0; i<size ; i++) {
			System.out.print("�̸� �Է� : ");
			arrayString[0][i] = sc.next();
			System.out.print("�������� �Է� : ");
			int c1 = sc.nextInt();
			System.out.print("�������� �Է� : ");
			int c2 = sc.nextInt();
			System.out.print("�������� �Է� : ");
			int c3 = sc.nextInt();
			int total = c1 + c2 + c3;
			double avg = total/3.0;
			arrayInt[0][i] = total;
			arrayInt[1][i] = avg;
			if(avg>=90) {
					arrayString[1][i] = "A����";
				} else if(avg>=80) {
					arrayString[1][i] = "B����";
				} else if(avg>=70) {
					arrayString[1][i] = "C����";
				} else if(avg>=60) {
					arrayString[1][i] = "D����";
				} else {
					arrayString[1][i] = "F����";
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
				System.out.printf("�̸� : %s   ���� : %.0f��    ��� : %.2f��   ���� : %s   ���� : %.0f��\n", arrayString[0][i], arrayInt[0][i], arrayInt[1][i], arrayString[1][i], arrayInt[2][i]);
				}
			sc.close();
		}
}