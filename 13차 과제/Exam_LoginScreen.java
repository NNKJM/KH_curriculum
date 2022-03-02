package khie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exam_LoginScreen extends JFrame {
	Exam_LoginScreen(){
		setTitle("제품관리 시스템");
		JPanel t = new JPanel();
		JPanel container = new JPanel(new BorderLayout());
		container.setLayout(new GridLayout(3,2));
		JPanel idPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pwPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel pwPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JPanel btnPanel = new JPanel();
		
		// 1 컴포넌트
		// 1-1
		JLabel title = new JLabel("로그인 화면", JLabel.CENTER);
		title.setFont(new Font("궁서체", Font.BOLD, 25));
		title.setForeground(Color.BLUE);
		
		// 1-2
		JLabel jl1 = new JLabel("아이디 : ");
		JTextField jtf1 = new JTextField(10);
		JLabel jl2 = new JLabel("비밀번호 : ");
		JTextField jtf2 = new JTextField(10);
		
		// 1-3
		JButton login = new JButton("로그인");
		JButton join = new JButton("회원가입");
		
		// 2 컴포넌트를 컨테이너에
		// 2-1
		t.add(title);
		
		// 2-2
		idPanel1.add(jl1); idPanel2.add(jtf1);
		pwPanel1.add(jl2); pwPanel2.add(jtf2);
		container.add(idPanel1); container.add(idPanel2);
		container.add(pwPanel1); container.add(pwPanel2);
		
		// 2-3
		btnPanel.add(login); btnPanel.add(join);

		// 3
		add(t, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);
		
		setBounds(400, 400, 300, 250);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		

		// 4 이벤트
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "아이디 : " + jtf1.getText() + ", 비번 : " + jtf2.getText());
				jtf1.setText(null); jtf2.setText(null);
			}
		});
		join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Exam_JoinScreen();
				dispose();
			}
		});
		
	}
}
