package khie;

/*
 * [����] Ű����κ��� ���� ���� 4���� �Է¹޾� ������ ȭ�� �ż��� �� ���� �ʿ����� �ڵ��� ���� ���Ͽ� ȭ�鿡 ����� ������.
 *       5õ��, 1õ��, 100��, 50��, 10��, 1��
 */

public class Exam_04 {
	public static void main(String[] args) {
		int su = Integer.parseInt(args[0]);
		int oc, c, ob, b, os, s, o, i, res;
		res = su;
		oc = res / 5000;
		res = res % 5000;
		c = res / 1000;
		res = res % 1000;
		ob = res / 500;
		res = res % 500;
		b = res / 100;
		res = res % 100;
		os = res / 50;
		res = res % 50;
		s = res / 10;
		res = res % 10;
		o = res / 5;
		res = res % 5;
		i = res;
		System.out.printf("�Է¹��� ���� : %d��\n5000�� %d��, 1000�� %d��, 500�� %d��, 100�� %d��, 50�� %d��, 10�� %d��, 5�� %d��, 1�� %d��", su, oc, c, ob, b, os, s, o, i);
	}
}
