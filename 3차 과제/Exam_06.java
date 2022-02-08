package khie;

public class Exam_06 {

	public static void main(String[] args) {
		int[][] arr = new int[5][];
		int num=1;
		for(int i=0 ; i<5 ; i++) {
			arr[i] = new int[i+1];
			for(int j=0 ; j<i+1 ; j++) {
				arr[i][j] = num;
				num++;
				System.out.printf("%-2d ", arr[i][j]);
			}
			System.out.println();
		}

	}

}
