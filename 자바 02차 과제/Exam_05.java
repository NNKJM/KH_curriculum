package khie;

/*
 * [����] 1 - 2 + 3 - 4 + 5 - 6 + 7 - 8 + 9 - 10 ... - 100�� ������ ������� ����� ������.
 */

public class Exam_05 {
	public static void main(String[] args) {
		int hap = 0;
		for(int i=1;i<=100;i++)
			if(i%2==0) {
				hap -= i;
			} else {
				hap += i;
			}
		System.out.println("hap ==> " + hap);
	}
}
