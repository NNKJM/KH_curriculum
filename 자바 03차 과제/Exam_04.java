package khie;

// �񿭾˰���

public class Exam_04 {
	public static void main(String[] args) {
		int[][] arr = new int[5][5];
		int num=1;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				arr[i][j]=num;
				System.out.printf("%2d         ", arr[i][j]);
				num++;
			}
			System.out.println();
		}
	}
}
