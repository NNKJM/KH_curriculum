package meal_kit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField jtf1;
	private JTextField jtf2;

	static String id, password;

	// db와 연결하는 객체
	Connection con = null;

	// db에 sql문을 전송하는 객체
	PreparedStatement pstmt = null;

	// sql문의 실행 결과를 저장할 객체
	ResultSet rs = null;

	// sql문 저장할 변수
	String sql = null;

	String opt, sort, photo;

	int amount, price;

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

	public static void main(String[] args) {

		new Login();
	}

	public Login() {

		connect();

		setTitle("Meal_Kit");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jl1 = new JLabel("밀키트 주문시스템");
		jl1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jl1.setBounds(230, 68, 250, 36);
		contentPane.add(jl1);

		JLabel jl2 = new JLabel("아이디");
		jl2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jl2.setBounds(303, 183, 62, 18);
		contentPane.add(jl2);

		JLabel jl3 = new JLabel("비밀번호");
		jl3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jl3.setBounds(303, 219, 62, 18);
		contentPane.add(jl3);

		jtf1 = new JTextField();
		jtf1.setBounds(379, 180, 174, 24);
		contentPane.add(jtf1);
		jtf1.setColumns(10);

		jtf2 = new JPasswordField();
		jtf2.setBounds(379, 216, 174, 24);
		contentPane.add(jtf2);
		jtf2.setColumns(10);

		JButton jb1 = new JButton("회원가입");
		jb1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new Signup();
			}
		});

		jb1.setBounds(278, 289, 89, 27);
		contentPane.add(jb1);

		JButton jb2 = new JButton("로그인");
		jb2.setBounds(379, 289, 75, 27);
		contentPane.add(jb2);
		jb2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				getPwd();

			}
		});

		JButton jb3 = new JButton("비밀번호 찾기");
		jb3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new Password();
			}
		});
		;
		jb3.setBounds(468, 289, 121, 27);
		contentPane.add(jb3);

		JLabel image = new JLabel();
		image.setIcon(new ImageIcon("./img/cart.jpg"));
		image.setBounds(20, 60, 300, 300);
		contentPane.add(image);

		setVisible(true);
	}

	void getPwd() {

		try {
			sql = "select password from member where id = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, jtf1.getText());
			rs = pstmt.executeQuery();

			password = null;
			id = null;

			if (rs.next()) {
				password = rs.getString("password");
				id = jtf1.getText();
			}

			if (jtf1.getText().length() == 0 || jtf2.getText().length() == 0) { // 아이디/비밀번호 입력 X
				JOptionPane.showMessageDialog(null, "아이디/비밀번호를 입력해주세요.");
			} else if (password == null) { // ID가 잘못된 경우 => 입력한 ID가 존재하지 않는 ID여서 비밀번호에 아무 값이 들어간 경우.
				JOptionPane.showMessageDialog(null, "아이디/비밀번호가 틀렸습니다.");
			} else if (jtf2.getText().equals(password)) {

				if (id.equals("admin")) { // 관리자 로그인
					dispose(); // 열렸던 창 닫기.
					new AdminPage();
				} else { // 일반 회원 로그인
					dispose();
					new Main(id);
				}

			} else { // 비밀번호가 잘못된 경우
				JOptionPane.showMessageDialog(null, "아이디/비밀번호가 틀렸습니다.");
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}