package khie;

import java.awt.BorderLayout;

import javax.swing.*;

public class Exam_03 extends JFrame {
	
	public Exam_03() {
		setTitle("커피 자판기");
		JPanel container1 = new JPanel(); // 상단-1
		JPanel container2 = new JPanel(); // 상단-2
		JPanel container3 = new JPanel(); // 상단-3
		JPanel container4 = new JPanel(); // 하단
		
		// 1. 컴포넌트
		// 1-1. 상단-1 컨테이너에 올려질 컴포넌트
		JLabel title = new JLabel("원하는 커피 선택");
		
		// 1-2 상단-2 컨테이너에 올려질 컴포넌트
		JRadioButton jrb1 = new JRadioButton("아메리카노(2500)");
		JRadioButton jrb2 = new JRadioButton("카페모카(3500)");
		JRadioButton jrb3 = new JRadioButton("에스프레소(2500)");
		JRadioButton jrb4 = new JRadioButton("카페라떼(4000)");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb1); bg.add(jrb2); bg.add(jrb3); bg.add(jrb4);
		
		// 1-3. 상단-3 컨테이너에 들어갈 컴포넌트
		JLabel jl1 = new JLabel("수  량 : ");
		JTextField jf1 = new JTextField(5);
		JLabel jl2 = new JLabel("입금액 : ");
		JTextField jf2 = new JTextField(5);
		
		// 1-4. 중단 컨테이너에 들어갈 컴포넌트
		JTextArea jta = new JTextArea(10,20);
		JScrollPane jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setLineWrap(true); // 자동 줄바꿈 기능 설정
		
		// 1-5. 하단 컨테이너에 들어갈 컴포넌트
		JButton result = new JButton("계산");
		JButton exit = new JButton("종료");
		JButton cancel = new JButton("취소");

		// 2. 컴포넌트들을 컨테이너에 추가
		// 2-1. 상단-1 컨테이너에 들어갈 컴포넌트 추가
		container1.add(title);

		// 2-2. 상단-2 컨테이너에 들어갈 컴포넌트 추가
		container2.add(jrb1); container2.add(jrb2); container2.add(jrb3); container2.add(jrb4);
		
		// 2-3. 상단-3 컨테이너에 들어갈 컴포넌트 추가
		container3.add(jl1); container3.add(jf1); container3.add(jl2); container3.add(jf2);
		
		// 2-4. 하단 컨테이너에 들어갈 컴포넌트 추가
		container4.add(result);
		container4.add(exit);
		container4.add(cancel);
		
		// 새로운 컨테이너
		JPanel group1 = new JPanel(new BorderLayout());
		
		// 새로운 컨테이너에 기존 컨테이너
		group1.add(container1, BorderLayout.NORTH);
		group1.add(container2, BorderLayout.CENTER);
		group1.add(container3, BorderLayout.SOUTH);
		
		// 3. 컨테이너를 프레임
		add(group1, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(container4, BorderLayout.SOUTH);

		setBounds(200, 200, 300, 300);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Exam_03();
	}
}