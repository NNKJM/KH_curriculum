package khie;
// [����] ���� for���� �����Ͽ� ���� �ﰢ�� �����

public class Exam_03 {
	public static void main(String[] args) {
		for (int i=0;i<=12;i++) {
			if(i<=5) {
				for(int j=0;j<=i;j++) {
					System.out.print("*");
				}
			} else {
				for(int j=0;j<=12-i;j++) {
					System.out.print("*");
				}
			}
		System.out.println("");
		}
	}
}
