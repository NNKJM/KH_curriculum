package khie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exam_05 extends JFrame {
	public Exam_05() {
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
		JButton renewal = new JButton("새창");
	
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
		container4.add(renewal);
		
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
		
		// 4. 이벤트
		// 4-1. 계산 버튼
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int su = Integer.parseInt(jf1.getText());
				int input = Integer.parseInt(jf2.getText());
				String coffeeN;
				int coffeeM;

				if(jrb1.isSelected()) { // 아메리카노 2500원
					coffeeN = "아메리카노";
					coffeeM = 2500;
				} else if(jrb2.isSelected()) { // 카페모카 3500원
					coffeeN = "카페모카";
					coffeeM = 3500;
				} else if(jrb3.isSelected()) { // 에스프레소 2500원
					coffeeN = "에스프레소";
					coffeeM = 2500;
				} else { // 카페라떼 4000원
					coffeeN = "카페라떼";
					coffeeM = 4000;
				}
				int charge = su*coffeeM;
				double tax = charge*0.1;
				double total = charge + tax;
				double extra = input - total;

				if(extra >= 0) {
					jta.append("커피종류 : "+ coffeeN + "\n");
					jta.append("커피단가 : "+ coffeeM + "원\n");
					jta.append("수    량 : "+ su + "\n");				
					jta.append("공급가액 : "+ charge + "원\n");				
					jta.append("부가세액 : "+ Math.round(tax) + "원\n");				
					jta.append("총 금 액 : "+ Math.round(total) + "원\n");				
					jta.append("입 금 액 : " + input + "원\n");
					jta.append("거스름돈 : " + Math.round(extra) + "원\n\n");
				} else {
					jta.append("커피를 구매할 수 없습니다!\n\n");
				}
			}
		});

		// 4-2 종료 버튼
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// 4-3 취소 버튼
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jta.setText(null);
				jf1.setText(null);
				jf2.setText(null);
				bg.clearSelection();
				jl1.requestFocus(); // 커서를 옮김
				
			}
		});
		
		// 4-4 새창 버튼
		renewal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "새창을 띄웠습니다");
				new Exam_05();
			}
		});
	
}
	public static void main(String[] args) {
		new Exam_05();
		
	}

}
