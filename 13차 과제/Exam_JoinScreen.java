package khie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Exam_JoinScreen extends JFrame implements ActionListener{


	JRadioButton jrb1, jrb2, jrb3;
	String button;
	
	Exam_JoinScreen(){
		setTitle("제품관리 시스템");
		JPanel t = new JPanel();
		JPanel radioPanel = new JPanel();
		JPanel tCon = new JPanel(new BorderLayout());

		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel pwPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel nmPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel adPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel mCon = new JPanel(new BorderLayout());
		mCon.setLayout(new GridLayout(4, 1));

		JPanel bCon = new JPanel();

		// 1  컴포넌트
		// 1-1 
		JLabel title = new JLabel("회원가입", JLabel.CENTER);
		title.setFont(new Font("궁서체", Font.BOLD, 25));
		title.setForeground(Color.BLUE);

		// 1-2
		jrb1 = new JRadioButton("고객");
		jrb2 = new JRadioButton("관리자");
		jrb3 = new JRadioButton("기타");
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb1); bg.add(jrb2); bg.add(jrb3);

		// 1-3
		JLabel jl1 = new JLabel("아이디 : ");
		JTextField jtf1 = new JTextField(10);
		JLabel jl2 = new JLabel("비밀번호 : ");
		JTextField jtf2 = new JTextField(10);
		JLabel jl3 = new JLabel("이름 : ");
		JTextField jtf3 = new JTextField(10);
		JLabel jl4 = new JLabel("전화번호 : ");
		JTextField jtf4 = new JTextField(10);
		
		// 1-4
		JButton join = new JButton("회원가입");
		JButton cancel = new JButton("취소");
		
		// 2 컴포넌트를 컨테이너에
		// 2-1
		t.add(title);
		radioPanel.add(jrb1); radioPanel.add(jrb2); radioPanel.add(jrb3);
		tCon.add(t, BorderLayout.NORTH);
		tCon.add(radioPanel, BorderLayout.SOUTH);
		
		// 2-2
		idPanel.add(jl1); idPanel.add(jtf1);
		pwPanel.add(jl2); pwPanel.add(jtf2);
		nmPanel.add(jl3); nmPanel.add(jtf3);
		adPanel.add(jl4); adPanel.add(jtf4);
		mCon.add(idPanel);
		mCon.add(pwPanel);
		mCon.add(nmPanel);
		mCon.add(adPanel);
		
		// 2-3
		bCon.add(join); bCon.add(cancel);
		
		// 3
		add(tCon, BorderLayout.NORTH);
		add(mCon, BorderLayout.CENTER);
		add(bCon, BorderLayout.SOUTH);
		

		setBounds(400, 400, 500, 450);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// 4 이벤트
		join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jrb1.isSelected()) {
					button = jrb1.getText();
				}else if(jrb2.isSelected()) {
					button= jrb2.getText();
				}else if(jrb3.isSelected()) {
					button= jrb3.getText();
				} else {
					button="";
				}
				JOptionPane.showMessageDialog(null, jtf1.getText() + jtf2.getText() + jtf3.getText() + jtf4.getText() + button);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Exam_LoginScreen();
				dispose();
			}
		});
		
		jrb1.addActionListener(this);
		jrb2.addActionListener(this);
		jrb3.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(jrb1.isSelected()) {
			button = jrb1.getText();
		} else if(jrb2.isSelected()) {
			button= jrb2.getText();
		} else if(jrb3.isSelected()){
			button= jrb3.getText();
		}
		JOptionPane.showMessageDialog(null, button + "를(을) 선택했군요");
	}
	
}
