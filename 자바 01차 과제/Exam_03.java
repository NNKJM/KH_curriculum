package khie;

/*
 * [����] Ű����κ���  �Աݾ�, ��ǰ�� �ܰ�, ������ �Է¹޾� �ΰ�����(��ǰ�ݾ��� 10%), ��ǰ�Ѿ�(��ǰ�ݾ�+�ΰ�����)�� �Ž�����(�Աݾ�-��ǰ�Ѿ�)�� ���Ͽ� ȭ�鿡 ����� ����.
 */

public class Exam_03 {
	public static void main(String[] args) {
		int moneyInput = Integer.parseInt(args[0]);
		int howMuch = Integer.parseInt(args[1]);
		int productMany = Integer.parseInt(args[2]);
		int productMuch = howMuch*productMany; // ��ǰ �ݾ�
		double tax = productMuch*0.1;
		double total = productMuch + tax;
		double extra = moneyInput - total;
		System.out.printf("�Աݾ� : %d��\n��ǰ �ܰ� : %d��\n���� : %d��\n�ΰ����� : %.0f��\n��ǰ�Ѿ� : %.0f��\n�ܾ� : %.0f��\n", moneyInput, howMuch, productMany, tax, total, extra);
	}
}
