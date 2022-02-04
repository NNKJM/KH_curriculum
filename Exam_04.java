package khie;

/*
 * [문제] 키보드로부터 임의 숫자 4개를 입력받아 각각의 화폐 매수가 몇 장이 필요한지 코딩을 통해 구하여 화면에 출력해 보세요.
 *       5천원, 1천원, 100원, 50원, 10원, 1원
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
		System.out.printf("입력받은 숫자 : %d원\n5000원 %d장, 1000원 %d장, 500원 %d개, 100원 %d개, 50원 %d개, 10원 %d개, 5원 %d개, 1원 %d개", su, oc, c, ob, b, os, s, o, i);
	}
}
