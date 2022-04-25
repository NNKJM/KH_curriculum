package khie;

import java.util.Scanner;

/* 최대값 / 최소값 알고리즘
 * 키보드로 배열의 크기를 입력받고 생성된 배열요소 만큼 임의의 정수를 키보드로 입력받고
 * 그 중 최대값과 최소값을 구하세요.
*/

public class Exam_01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("배열 크기. : ");
		int size = sc.nextInt();
		System.out.println();
		int[] num = new int[size];
		for(int i=0;i<num.length;i++) {
			System.out.print((i+1)+"번째 값 입력 : ");
			num[i] = sc.nextInt();
		}
		int max = num[0], min = num[0];
		for (int i=0;i<num.length;i++) {
			if(max<num[i]) {
				max = num[i];
				}
			if(min>num[i]) {
				min = num[i];
			}
		}
		System.out.printf("max : %d, min : %d", max, min );
		sc.close();
	}
			
}
