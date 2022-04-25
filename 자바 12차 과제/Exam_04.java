package khie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.Math;

import javax.swing.*;

public class Exam_04 extends JFrame {
	public Exam_04() {
	setTitle("성적 처리");
	JPanel container1 = new JPanel(); // 상단-1
	JPanel container2 = new JPanel(); // 상단-2
	JPanel container3 = new JPanel(); // 하단
	
	// 1. 컴포넌트
	// 1-1. 상단-1 컨테이너에 올려질 컴포넌트
	JLabel jl1 = new JLabel("이  름 : ");
	JTextField name = new JTextField(10);
	
	// 1-2. 상단-2 컨테이너에 올려질 컴포넌트
	JLabel jlb1 = new JLabel("국  어 : ");
	JTextField p1 = new JTextField(3);
	JLabel jlb2 = new JLabel("영  어 : ");
	JTextField p2 = new JTextField(3);
	JLabel jlb3 = new JLabel("수  학 : ");
	JTextField p3 = new JTextField(3);
	
	// 1-3. 중단 컨테이너에 들어갈 컴포넌트
	JTextArea jta = new JTextArea(5,20);
	JScrollPane jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	jta.setLineWrap(true); // 자동 줄바꿈 기능 설정
	
	// 1-4. 하단 컨테이너에 들어갈 컴포넌트
	JButton result = new JButton("계산");
	JButton exit = new JButton("종료");
	JButton cancel = new JButton("취소");
	
	// 2. 컴포넌트를 컨테이너에 추가
	// 2-1. 상단-1 컨테이너에 들어갈 컴포넌트들을 추가
	container1.add(jl1); container1.add(name);
	
	// 2-2 상단-2 컨테이너에 들어갈 컴포넌트를 추가
	container2.add(jlb1); container2.add(p1);
	container2.add(jlb2); container2.add(p2);
	container2.add(jlb3); container2.add(p3);
	
	// 2-3 하단 컨테이너에 들어갈 컴포넌트를 추가
	container3.add(result); container3.add(exit); container3.add(cancel);
	
	// 새로운 컨테이너 생성
	JPanel group1 = new JPanel(new BorderLayout());
			
	// 새로운 컨테이너에 기존 컨테이너를 추가
	group1.add(container1, BorderLayout.NORTH);
	group1.add(container2, BorderLayout.SOUTH);
	
	// 3. 컨테이너를 프레임에 추가
	add(group1, BorderLayout.NORTH);
	add(jsp, BorderLayout.CENTER);
	add(container3, BorderLayout.SOUTH);

	setBounds(200, 200, 300, 300);
	pack();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);

	// 4. 이벤트 생성
	// 4-1. 계산 버튼
	result.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int n1 = Integer.parseInt(p1.getText());
			int n2 = Integer.parseInt(p2.getText());
			int n3 = Integer.parseInt(p3.getText());
			String getName = name.getText();
			int total = n1 + n2 + n3;
			double avg = total / 3.0  ;
			String point;
			if (avg >= 90) {
				point = "A학점";
			} else if (avg >= 80) {
				point = "B학점";
			} else if (avg >= 70) {
				point = "C학점";
			} else if (avg >= 60) {
				point = "D학점";
			} else {
				point = "F학점";
			}
			
			jta.append("*** "+ getName +"님 성적결과***\n");
			jta.append("이    름 : "+ getName + "\n");
			jta.append("국어점수 : "+ n1 + "점\n");
			jta.append("영어점수 : "+ n2 + "점\n");
			jta.append("수학점수 : "+ n3 + "점\n");
			jta.append("총    점  : "+ total + "점\n");
			jta.append("평    균 : "+ String.format("%.2f점\n", avg));
			jta.append("학    점 : "+ point + "\n");

		}
	});

	// 4-2 종료 버튼
	exit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	});

	// 4-3 취소 버튼을 클릭했을 때 이벤트 처리
	cancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			name.setText(null);
			p1.setText(null);
			p2.setText(null);
			p3.setText(null);
			jta.setText(null);
		}
	});
	
	}
	
	public static void main(String[] args) {
		new Exam_04();
	}
}
