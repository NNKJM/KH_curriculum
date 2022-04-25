package khie;

public class Exam_ProductSearchData {
	String[][] proTable = new String[3][2];
	public Exam_ProductSearchData() {
		proTable[0][0] = "세탁기";
		proTable[1][0] = "냉장고";
		proTable[2][0] = "TV";
		proTable[0][1] = "드럼 세탁기 최신형";
		proTable[1][1] = "지펠 냉장고 최신형";
		proTable[2][1] = "HDTV 150인치 최신 모델";
	}
	
	String search(String productName) {
		String answer = null;
		for(int i=0; i<proTable.length; i++) {
			if(productName.equals(proTable[i][0])) {
				answer = proTable[i][1];
			}
		}
		return answer;
	}
}
