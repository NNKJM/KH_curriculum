package khie;
import java.awt.BorderLayout;
import javax.swing.*;

public class Exam_01 extends JFrame {

	public Exam_01() {
		setTitle("계산기 예제");
		JPanel container1 = new JPanel(); // 상단-1
		JPanel container2 = new JPanel(); // 상단-2
		JPanel container3 = new JPanel(); // 하단
		
		// 1. 컴포넌트
		// 1-1. 상단-1 컨테이너에 올려질 컴포넌트
		JLabel jl1 = new JLabel("수 1 : ");
		JTextField su1 = new JTextField(5);
		JLabel jl2 = new JLabel("수 2 : ");
		JTextField su2 = new JTextField(5);
		// 1-2 상단-2 컨테이너에 올려질 컴포넌트

		JLabel jl3 = new JLabel("연산자 : ");
		JRadioButton jrb1 = new JRadioButton("+");
		JRadioButton jrb2 = new JRadioButton("-");
		JRadioButton jrb3 = new JRadioButton("*");
		JRadioButton jrb4 = new JRadioButton("/");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb1); bg.add(jrb2); bg.add(jrb3); bg.add(jrb4);
		
		// 1-3. 중단 컨테이너에 들어갈 컴포넌트를 만들자
		JTextArea jta = new JTextArea(5,20);
		JScrollPane jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setLineWrap(true); // 자동 줄바꿈 기능 설정

		// 1-4. 하단 컨테이너에 들어갈 컴포넌트를 만들자
		JButton result = new JButton("계산");
		JButton exit = new JButton("종료");
		JButton cancel = new JButton("취소");

		// 2. 컴포넌트들을 컨테이너에 추가해 주어야 한다
		// 2-1. 상단-1 컨테이너에 들어갈 컴포넌트들을 추가해 주자.
		container1.add(jl1); container1.add(su1); container1.add(jl2); container1.add(su2);

		// 2-2 상단-2 컨테이너에 들어갈 컴포넌트를 추가해 주자.
		container2.add(jl3);
		container2.add(jrb1); container2.add(jrb2); container2.add(jrb3); container2.add(jrb4);

		// 2-3 하단 컨테이너
		container3.add(result);
		container3.add(exit);
		container3.add(cancel);
		
		// 새로운 컨테이너
		JPanel group1 = new JPanel(new BorderLayout());

		// 새로운 컨테이너에 기존 컨테이너
		group1.add(container1, BorderLayout.NORTH);
		group1.add(container2, BorderLayout.SOUTH);

		// 3. 컨테이너를 프레임
		add(group1, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(container3, BorderLayout.SOUTH);

		setBounds(200, 200, 300, 300);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {

		new Exam_01();
	}
}