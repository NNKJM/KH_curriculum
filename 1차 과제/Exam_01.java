package khie;
/*
 *  [����] �̸��� ��������, ��������, ��������, �ڹ������� Ű����� �Է��� �޾Ƽ� ������ ó���� ����.
 *            ����, ��ձ��� ����� ����.
 *  ����1. ����� �Ҽ��� �� �ڸ����� �Է��� ��.
 */

public class Exam_01 {
	public static void main(String[] args) {
		String name = args[0];
		int korean = Integer.parseInt(args[1]);
		int english = Integer.parseInt(args[2]);
		int math = Integer.parseInt(args[3]);
		int java = Integer.parseInt(args[4]);
		int total = korean + english + math + java;
		double avg = total / 4.0;
		System.out.println(name + "�� ����ǥ");
		System.out.println("���� ���� : " + korean + "��");
		System.out.println("���� ���� : " + english + "��");
		System.out.println("���� ���� : " + math + "��");
		System.out.println("�ڹ� ���� : " + java + "��");
		System.out.println("��    �� : " + total + "��");
		System.out.printf("��    �� : %.2f\n", avg + "��");
	}
}
