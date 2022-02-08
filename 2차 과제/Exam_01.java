package khie;
/*
 * 지방, 탄수화물, 단백질 칼로리의 합계를 계산하는 프로그램
 * 총 칼로리 : 지방 9칼로리/그램, 탄수화물 & 단백질 4칼로리/그램
 */

import java.util.Scanner;

public class Exam_01 {
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.print("지방의 그램을 입력하세요 : ");
	int fat = sc.nextInt();
	System.out.print("탄수화물의 그램을 입력하세요 : ");
	int carbohydrate = sc.nextInt();
	System.out.print("단백질의 그램을 입력하세요 : ");
	int protein = sc.nextInt();
	System.out.printf("총칼로리 : %d cal", (fat*9)+(carbohydrate*4)+(protein*4));
	
	sc.close();
	
	}
}
