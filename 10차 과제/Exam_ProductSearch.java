package collection;

import javax.swing.JOptionPane;

public class Exam_ProductSearch {
	Exam_ProductSearchData ex = new Exam_ProductSearchData();

	void search() {
		String name = JOptionPane.showInputDialog("검색할 상품명을 입력하세요. : ");
		String info = ex.search(name);
		JOptionPane.showMessageDialog(null, ex.search(name));

	}

}
