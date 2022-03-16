package meal_kit;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

	static String id, password;
	/**
	 * Launch the application.
	 */

	// db와 연결하는 객체
	Connection con = null;

	// db에 sql문을 전송하는 객체
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;

	// sql문의 실행 결과를 저장할 객체
	ResultSet rs = null;

	// sql문 저장할 변수
	String sql = null;
	String sql2 = null;

	String opt, sort, photo;

	int amount, price;

	String tempID;

	private JPanel contentPane;
	private JTextField jtf1;
	private JTextField jtf2;
	private JTextField jtf3;
	private JTextField jtf4;
	private JTextField jtf5;
	private JTextField jtf6;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Signup() {

		connect();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jl1 = new JLabel("회원가입");
		jl1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jl1.setHorizontalAlignment(SwingConstants.CENTER);
		jl1.setBounds(50, 35, 400, 29);
		contentPane.add(jl1);

		JLabel jl2 = new JLabel("아이디");
		jl2.setBounds(69, 113, 62, 18);
		jl2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentPane.add(jl2);

		JLabel jl3 = new JLabel("비밀번호");
		jl3.setBounds(69, 143, 62, 18);
		jl3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentPane.add(jl3);

		JLabel jl4 = new JLabel("비밀번호 재입력");
		jl4.setBounds(69, 173, 103, 18);
		jl4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentPane.add(jl4);

		JLabel jl5 = new JLabel("이름");
		jl5.setBounds(69, 203, 62, 18);
		jl5.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentPane.add(jl5);

		JLabel jl6 = new JLabel("주소");
		jl6.setBounds(69, 233, 62, 18);
		jl6.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentPane.add(jl6);

		jtf1 = new JTextField();
		jtf1.setBounds(186, 110, 116, 24);
		contentPane.add(jtf1);
		jtf1.setColumns(10);

		jtf2 = new JPasswordField();
		jtf2.setBounds(186, 170, 116, 24);
		contentPane.add(jtf2);
		jtf2.setColumns(10);

		jtf3 = new JPasswordField();
		jtf3.setBounds(186, 140, 116, 24);
		contentPane.add(jtf3);
		jtf3.setColumns(10);

		// 중복확인 버튼
		JButton jb1 = new JButton("중복 확인");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				confirmID();
			}
		});
		jb1.setBounds(316, 109, 96, 27);
		contentPane.add(jb1);

		JButton jb2 = new JButton("확인");
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				confirmPsw();

			}
		});
		jb2.setBounds(316, 169, 96, 27);
		contentPane.add(jb2);

		jtf4 = new JTextField();
		jtf4.setBounds(186, 200, 116, 24);
		contentPane.add(jtf4);
		jtf4.setColumns(10);

		jtf5 = new JTextField();
		jtf5.setBounds(186, 230, 226, 24);
		contentPane.add(jtf5);
		jtf5.setColumns(10);

		JLabel jl7 = new JLabel("휴대폰 번호");
		jl7.setBounds(69, 263, 75, 18);
		jl7.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		contentPane.add(jl7);

		jtf6 = new JTextField();
		jtf6.setBounds(186, 260, 116, 24);
		contentPane.add(jtf6);
		jtf6.setColumns(10);

		JButton jb3 = new JButton("작성 완료");
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sign();

			}
		});
		jb3.setBounds(185, 321, 105, 27);
		contentPane.add(jb3);

		setVisible(true);

	}

	void connect() {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";

		try {
			// 1. 접속할 오라클 데이터베이스 드라이버를 메모리에 올리자. - 동적 작업
			Class.forName(driver);

			// 2. 오라클 데이터베이스와 연결을 시도.
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // connect() 메서드 end

	// 아이디 중복 확인 메서드
	void confirmID() {

		try {
			// SQL문
			sql = "select id from member";
			pstmt = con.prepareStatement(sql);

			// SQL문 전송 및 실행.
			rs = pstmt.executeQuery();

			tempID = null;

			while (rs.next()) {
				id = rs.getString("id");
				if (id.equals(jtf1.getText())) {
					tempID = id;
				}
			}

			if (tempID != null) {
				JOptionPane.showMessageDialog(null, "이미 사용중인 아이디 입니다.");
			} else if (jtf1.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "아이디를 기입해주세요.");
			} else {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
			}

			// 자원 종료.
			// rs.close(); pstmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void confirmPsw() {

		if (jtf2.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "비밀번호를 기입해주세요.");
		} else if (jtf2.getText().equals(jtf3.getText())) {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치합니다.");
		} else {
			JOptionPane.showMessageDialog(null, "다시 입력해 주세요.");
		}
	}

	void sign() {

		try {
			// SQL문
			sql = "select id from member";
			pstmt = con.prepareStatement(sql);

			// SQL문 전송 및 실행.
			rs = pstmt.executeQuery();

			tempID = null;

			while (rs.next()) {
				id = rs.getString("id");
				if (id.equals(jtf1.getText())) {
					tempID = id;
				}
			}

			if (jtf1.getText().length() == 0 || jtf2.getText().length() == 0 || jtf3.getText().length() == 0
					|| jtf4.getText().length() == 0 || jtf5.getText().length() == 0 || jtf6.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "모든 항목을 기입해주세요.");
			} else if (tempID != null) {
				JOptionPane.showMessageDialog(null, "이미 사용중인 아이디 입니다.");
			} else if (jtf2.getText().equals(jtf3.getText())) {
				sql2 = "insert into member values(?, ?, ?, ?, ?)";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, jtf1.getText()); // id
				pstmt2.setString(3, jtf2.getText()); // password
				pstmt2.setString(2, jtf4.getText()); // name
				pstmt2.setString(4, jtf5.getText()); // address
				pstmt2.setString(5, jtf6.getText()); // phone

				int result = pstmt2.executeUpdate();

				if (result > 0) {
					JOptionPane.showMessageDialog(null, "회원가입 되었습니다.");
					dispose();
				}
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.");
			}

			// 자원 종료.
			// rs.close(); pstmt.close(); pstmt2.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}