package meal_kit;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Password extends JFrame {

	Connection con = null; // DB와 연결하는 객체
	PreparedStatement pstmt = null; // SQL문을 DB에 전송하는 객체
	ResultSet rs = null; // SQL문 실행 결과를 가지고 오는 객체
	String sql = null; // SQL문을 저장하는 문자열 변수

	static String id, name, phone, password;

	private JPanel contentPane;
	private JTextField jtf1;
	private JTextField jtf2;
	private JTextField jtf3;

	/**
	 * Create the frame.
	 */
	public Password() {
		connect();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jl1 = new JLabel("비밀번호 찾기");
		jl1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jl1.setBounds(145, 27, 175, 24);
		contentPane.add(jl1);

		JLabel jl2 = new JLabel("아이디");
		jl2.setBounds(114, 79, 50, 18);
		jl2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentPane.add(jl2);

		jtf1 = new JTextField();
		jtf1.setBounds(178, 76, 150, 24);
		contentPane.add(jtf1);
		jtf1.setColumns(10);

		JLabel jl3 = new JLabel("이름");
		jl3.setBounds(114, 109, 50, 18);
		jl3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentPane.add(jl3);

		jtf2 = new JTextField();
		jtf2.setBounds(178, 106, 150, 24);
		contentPane.add(jtf2);
		jtf2.setColumns(10);

		JLabel jl4 = new JLabel("연락처");
		jl4.setBounds(114, 139, 50, 18);
		jl4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentPane.add(jl4);

		jtf3 = new JTextField();
		jtf3.setBounds(178, 136, 150, 24);
		contentPane.add(jtf3);
		jtf3.setColumns(10);

		JButton jb1 = new JButton("비밀번호 찾기");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				findPsw();

			}
		});
		jb1.setBounds(160, 184, 121, 27);
		contentPane.add(jb1);

		setVisible(true);
	}

	void connect() {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";

		try {
			// 1. 접속할 오라클 데베 드라이버를 메모리에 올리기 - 동적 작업
			Class.forName(driver);

			// 2. 오라클 데베와 연결 시도
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// connect()메소드 end

	void findPsw() {

		try {
			sql = "select id, name, phone, password from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jtf1.getText());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				id = rs.getString("id");
				name = rs.getString("name");
				phone = rs.getString("phone");
				password = rs.getString("password");
			}

			if (jtf1.getText().equals(id) && jtf2.getText().equals(name) && jtf3.getText().equals(phone)) {
				JOptionPane.showMessageDialog(null, name + " 회원님의 비밀번호는 " + password + " 입니다.");
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "입력하신 정보와 일치하는 계정이 존재하지 않습니다.");
			}

			// rs.close(); pstmt.close(); con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}