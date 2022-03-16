package meal_kit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class Main extends JFrame {
	protected static final GraphicsConfiguration String = null;
	Connection con = null;

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

	}

	public Main(String id) {
		setTitle("메인 페이지");

		connect();
		String name = "";
		try {
			String sql = "select name from member where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				name = rs.getString("name");
			}
			con.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 추후 로그인할 때 이름을 가져오자

		JLabel label = new JLabel(name + "님");
		label.setHorizontalAlignment(JLabel.RIGHT);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setBorder(BorderFactory.createEmptyBorder(0, 735 - name.length()*15, 0, 0));


		// 버튼
		JButton jb1 = new JButton("주문하기");
		jb1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		JButton jb2 = new JButton("장바구니");
		jb2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		JButton jb3 = new JButton("주문내역");
		jb3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		jb1.setBackground(new Color(241, 196, 15));
		jb2.setBackground(new Color(46, 204, 113));
		jb3.setBackground(new Color(52, 152, 219));
		JPanel group2 = new JPanel();

		group2.add(jb1);
		group2.add(jb2);
		group2.add(jb3);
		group2.setLayout(new GridLayout(1, 3, 20, 20));
		group2.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20)); // 좌측 여백 20, 우측 여백 20
		
		JPanel group1 = new JPanel();
		JButton logout = new JButton("로그아웃");
		logout.setSize(50, 50);
		group1.add(logout);
		group1.add(label);


		add(group1, BorderLayout.NORTH);
		add(group2, BorderLayout.CENTER);

		// setResizable(false);
		setBounds(200, 200, 900, 340);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Order();
				// 주문하기 창 실행
			}
		}); // 주문하기 버튼을 눌렀을 때

		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShoppingCart();
				// 장바구니창 실행
			}
		}); // 장바구니 버튼을 눌렀을 때

		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 주문내역창 실행
				new OrderList();
			}
		}); // 주문내역 버튼을 눌렀을 때
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
				
			}
		});
	}
}