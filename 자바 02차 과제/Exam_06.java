package khie;

import java.util.Scanner;

/* Coffee �޴��� Ű����� �ް� �ֹ������� �Աݾ��� �Է��ϸ� ȭ�� ����ϰ� �ϼ���
 *  �ѱݾ׿��� �ΰ����� ���ԵǾ�� ��.
*/

public class Exam_06 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("** coffee �޴� **");
		System.out.println("1. �Ƹ޸�ī�� - 3,000��");
		System.out.println("2. ī��� - 4,000��");
		System.out.println("3. ��Ű�ƶ� - 4,500��");
		System.out.println("4. �ٴҶ�� - 4,500��");
		
		System.out.print("�� �޴� �� �ϳ��� �����ϼ���. : ");
		int menuNum = sc.nextInt();
		System.out.print("�ֹ����� >> ");
		int muchNum = sc.nextInt();
		System.out.print("�Աݾ� >> ");
		int inputMoney = sc.nextInt();
		
		double total = 0;
		int danga = 0;
		
		System.out.println();
		switch(menuNum) {
		case 1 : 
			System.out.println("�ֹ��� �޴� : �Ƹ޸�ī��");
			danga = 3000;
			total = muchNum*danga*1.1;
			break;
		case 2 : 
			System.out.println("�ֹ��� �޴� : ī���");
			danga = 4000;
			total = muchNum*danga*1.1;
			break;
		case 3 : 
			System.out.println("�ֹ��� �޴� : �����ƶ�");
			danga = 4500;
			total = muchNum*danga*1.1;
			break;
		case 4 : 
			System.out.println("�ֹ��� �޴� : �ٴҶ��");
			danga = 4500;
			total = muchNum*danga*1.1;
			break;
		default :
			System.out.println("�׷� �޴��� �����ϴ�.");
		}
		if(danga != 0) {
			System.out.println("Ŀ�Ǵܰ� : " + danga + "��");
			System.out.println("�ֹ����� : " + muchNum);
			System.out.println("�Աݾ� : " + inputMoney +"��");
			System.out.printf("�ѱݾ� : %.0f��\n", total);
			double ex = inputMoney - total;
			System.out.printf("�Ž��� �� : %.0f��\n", ex);

		}
		
		sc.close();
		
	}
}
