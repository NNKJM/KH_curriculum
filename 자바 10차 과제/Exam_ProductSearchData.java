package collection;

import java.util.HashMap;
import java.util.Map;

public class Exam_ProductSearchData {
	Map<String, String> map = new HashMap<String, String>();

	public Exam_ProductSearchData() {
		map.put("세탁기", "드럼 세탁기 최신형");
		map.put("냉장고", "지펠 냉장고 최신형");
		map.put("TV", "HDTV 150인치 최신 모델");
	}
	
	String search(String name) {
		String answer = "해당 상품이 없습니다";
		if(map.containsKey(name)) {
			answer = map.get(name);
		}
		return answer;
	}

}
