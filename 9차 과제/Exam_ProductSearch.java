package khie;

import javax.swing.JOptionPane;

public class Exam_ProductSearch {
	void productSearch() {
		Exam_ProductSearchData ex = new Exam_ProductSearchData();
		String name = JOptionPane.showInputDialog("검색할 상품명을 입력하세요. : ");
		String info = ex.search(name);
		try {
			info.length();
			JOptionPane.showMessageDialog(null, ex.search(name));
		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "해당 상품이 없습니다.");
		}
	}
}
