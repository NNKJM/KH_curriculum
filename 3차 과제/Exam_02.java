package khie;
/*
 * 임의의 숫자 5개를 키보드로 입력받아서 배열에 저장한 후 내림차순으로 정렬하여 출력하세요.
 */

import java.util.Scanner;

public class Exam_02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] num = new int[5];
		System.out.println("5개의 숫자를 입력하세요. : ");
		for(int i=0; i<5; i++) {
			num[i]=sc.nextInt();
		}
		System.out.println("===내림차순으로 정렬===");
		int temp = 0;
		for(int i=0; i<5;i++) {
			for(int j=i+1; j<5;j++) {
				if(num[j] > num[i]) {
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
		}
		for(int i=0; i<num.length;i++) {
			System.out.print(num[i]+"          ");
		}
	}

}
