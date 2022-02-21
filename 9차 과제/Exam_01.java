package khie;

import javax.swing.JOptionPane;

/*
 * [문제] ProductSearchData 클래스 작성 - 구현 클래스
 *      생성자 : 상품명/상품정보로 2차원 배열 객체를 작성
 *            각 행의 1열은 '상품명' 2열에는 '상품정보'가 저장
 *      search() : 상품명을 매개변수(productName)로 넘겨받아서 proTable에서 상품명 검색한 후 해당 상품 정보를 리턴하는 메서드
 */

/*
 * [문제] ProductSearch 클래스 작성 - 사용 클래스
 *             ProductSearchData 클래스의 search() 메서드를 호출해 상품명을 인수 값으로 호출할 경우 해당 상품 정보가 리턴되어 메시지 창으로 보여지도록 작성
 *             상품 정보가 null인 경우 해당 상품이 없습니다 라는 메시지가 출력되도록 예외 처리
 * 
 */

// JOptionPane.showInputDialog("정수를 입력하세요 : ")


public class Exam_01 {

	public static void main(String[] args) {
		Exam_ProductSearch ex = new Exam_ProductSearch();
		ex.productSearch();
	}
}
