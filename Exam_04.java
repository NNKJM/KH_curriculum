package khie;
// 다중 for문을 적용해 알파벳 역삼각형 만들기
public class Exam_04 {
	public static void main(String[] args) {
	int length = 'Z' - 'A' + 1;
	for(int i=0;i<=length;i++) {
		char alpha = 'A';
		for(int j=0;j<=length-i-1;j++) {
			System.out.print(alpha);
			alpha++;
			}
		System.out.println();
		}
	}
}
