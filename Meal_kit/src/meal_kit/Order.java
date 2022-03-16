package meal_kit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Order extends JFrame {

	Connection con = null;

	// db에 sql문을 전송하는 객체
	PreparedStatement pstmt = null;

	// sql문의 실행 결과를 저장할 객체
	ResultSet rs = null;
	ResultSet rsRank = null;

	// sql문 저장할 변수
	String sql = null;

	DefaultTableModel model;
	static String menu_name;

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

	public Order() {
		// 윗쪽 버튼
		setTitle("주문 페이지");

		JTabbedPane tab = new JTabbedPane();

		JPanel con1 = new JPanel(); // 한식
		JPanel con2 = new JPanel(); // 중식
		JPanel con3 = new JPanel(); // 일식
		JPanel con4 = new JPanel(); // 양식
		JPanel con5 = new JPanel(); // 기타

		con1.setLayout(null);
		con2.setLayout(null);
		con3.setLayout(null);
		con4.setLayout(null);
		con5.setLayout(null);
		JScrollPane jsp1 = new JScrollPane(con1, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane jsp2 = new JScrollPane(con2, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane jsp3 = new JScrollPane(con3, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane jsp4 = new JScrollPane(con4, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane jsp5 = new JScrollPane(con5, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tab.add(jsp1, "한식");
		tab.add(jsp2, "중식");
		tab.add(jsp3, "일식");
		tab.add(jsp4, "양식");
		tab.add(jsp5, "기타");

		connect();

		String[] header = { "name", "price", "rating", "image directory" };
		model = new DefaultTableModel(header, 0);

		String[] menu;
		int[] price;
		double[] rating;
		String[] imagedir;

		// 1. 한식
		model = getModel("한식");
		menu = new String[model.getRowCount()];
		price = new int[model.getRowCount()];
		rating = new double[model.getRowCount()];
		imagedir = new String[model.getRowCount()];

		JButton btKr[] = new JButton[model.getRowCount()]; // 버튼 생성용
		JLabel jlKr1[] = new JLabel[model.getRowCount()]; // 라벨 생성용
		JLabel jlKr2[] = new JLabel[model.getRowCount()];

		for (int i = 0; i < model.getRowCount(); i++) {
			menu[i] = model.getValueAt(i, 0).toString();
			price[i] = (int) model.getValueAt(i, 1);
			rating[i] = (double) model.getValueAt(i, 2);
			imagedir[i] = model.getValueAt(i, 3).toString();
			
			ImageIcon icon = new ImageIcon(imagedir[i]);
			Image imgsize = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			// 아이콘 리사이즈
			btKr[i] = new JButton();
			btKr[i].setIcon(new ImageIcon(imgsize));
			if (i % 2 == 0) {
				btKr[i].setBounds(25 + 90 * i, 25, 120, 120);
			} else {
				btKr[i].setBounds(25 + 90 * (i - 1), 205, 120, 120);
			}
			String menu_name = menu[i];
			btKr[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new Ordering(menu_name);
				}
			});
			con1.add(btKr[i]);

			jlKr1[i] = new JLabel(menu[i] + " : " + price[i] + "원", JLabel.CENTER);
			jlKr1[i].setBounds(btKr[i].getX(), btKr[i].getY() + 130, 120, 20);
			con1.add(jlKr1[i]);

			if (rating[i] != 6) {
				jlKr2[i] = new JLabel("평점 : " + rating[i] + " / 5.0", JLabel.CENTER);
				jlKr2[i].setBounds(btKr[i].getX(), btKr[i].getY() + 150, 120, 20);
				con1.add(jlKr2[i]);
			}

		}
		con1.setPreferredSize(new Dimension(180 * (model.getRowCount() + 1) / 2, 500));
		model.setRowCount(0);

		// 2. 중식
		model = getModel("중식");
		menu = new String[model.getRowCount()];
		price = new int[model.getRowCount()];
		rating = new double[model.getRowCount()];
		imagedir = new String[model.getRowCount()];

		JButton btCn[] = new JButton[model.getRowCount()]; // 버튼 생성용
		JLabel jlCn1[] = new JLabel[model.getRowCount()]; // 라벨 생성용
		JLabel jlCn2[] = new JLabel[model.getRowCount()];

		for (int i = 0; i < model.getRowCount(); i++) {
			menu[i] = model.getValueAt(i, 0).toString();
			price[i] = (int) model.getValueAt(i, 1);
			rating[i] = (double) model.getValueAt(i, 2);
			imagedir[i] = model.getValueAt(i, 3).toString();

			ImageIcon icon = new ImageIcon(imagedir[i]);
			Image imgsize = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			// 아이콘 리사이즈
			btCn[i] = new JButton();
			btCn[i].setIcon(new ImageIcon(imgsize));
			if (i % 2 == 0) {
				btCn[i].setBounds(25 + 90 * i, 25, 120, 120);
			} else {
				btCn[i].setBounds(25 + 90 * (i - 1), 205, 120, 120);
			}
			String menu_name = menu[i];
			btCn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new Ordering(menu_name);
				}
			});
			con2.add(btCn[i]);

			jlCn1[i] = new JLabel(menu[i] + " : " + price[i] + "원", JLabel.CENTER);
			jlCn1[i].setBounds(btCn[i].getX(), btCn[i].getY() + 130, 120, 20);
			con2.add(jlCn1[i]);

			if (rating[i] != 6) {
				jlCn2[i] = new JLabel("평점 : " + rating[i] + " / 5.0", JLabel.CENTER);
				jlCn2[i].setBounds(btCn[i].getX(), btCn[i].getY() + 150, 120, 20);
				con2.add(jlCn2[i]);
			}
		}
		con2.setPreferredSize(new Dimension(180 * (model.getRowCount() + 1) / 2, 500));
		model.setRowCount(0);

		// 3. 일식
		model = getModel("일식");
		menu = new String[model.getRowCount()];
		price = new int[model.getRowCount()];
		rating = new double[model.getRowCount()];
		imagedir = new String[model.getRowCount()];

		JButton btJp[] = new JButton[model.getRowCount()]; // 버튼 생성용
		JLabel jlJp1[] = new JLabel[model.getRowCount()]; // 라벨 생성용
		JLabel jlJp2[] = new JLabel[model.getRowCount()];

		for (int i = 0; i < model.getRowCount(); i++) {
			menu[i] = model.getValueAt(i, 0).toString();
			price[i] = (int) model.getValueAt(i, 1);
			rating[i] = (double) model.getValueAt(i, 2);
			imagedir[i] = model.getValueAt(i, 3).toString();

			ImageIcon icon = new ImageIcon(imagedir[i]);
			Image imgsize = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			// 아이콘 리사이즈
			btJp[i] = new JButton();
			btJp[i].setIcon(new ImageIcon(imgsize));
			if (i % 2 == 0) {
				btJp[i].setBounds(25 + 90 * i, 25, 120, 120);
			} else {
				btJp[i].setBounds(25 + 90 * (i - 1), 205, 120, 120);
			}
			String menu_name = menu[i];
			btJp[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new Ordering(menu_name);
				}
			});
			con3.add(btJp[i]);

			jlJp1[i] = new JLabel(menu[i] + " : " + price[i] + "원", JLabel.CENTER);
			jlJp1[i].setBounds(btJp[i].getX(), btJp[i].getY() + 130, 120, 20);
			con3.add(jlJp1[i]);

			if (rating[i] != 6) {
				jlJp2[i] = new JLabel("평점 : " + rating[i] + " / 5.0", JLabel.CENTER);
				jlJp2[i].setBounds(btJp[i].getX(), btJp[i].getY() + 150, 120, 20);
				con3.add(jlJp2[i]);
			}
		}
		con3.setPreferredSize(new Dimension(180 * (model.getRowCount() + 1) / 2, 500));
		model.setRowCount(0);

		// 4. 양식
		model = getModel("양식");
		menu = new String[model.getRowCount()];
		price = new int[model.getRowCount()];
		rating = new double[model.getRowCount()];
		imagedir = new String[model.getRowCount()];

		JButton btEn[] = new JButton[model.getRowCount()]; // 버튼 생성용
		JLabel jlEn1[] = new JLabel[model.getRowCount()]; // 라벨 생성용
		JLabel jlEn2[] = new JLabel[model.getRowCount()];

		for (int i = 0; i < model.getRowCount(); i++) {
			menu[i] = model.getValueAt(i, 0).toString();
			price[i] = (int) model.getValueAt(i, 1);
			rating[i] = (double) model.getValueAt(i, 2);
			imagedir[i] = model.getValueAt(i, 3).toString();
			
			ImageIcon icon = new ImageIcon(imagedir[i]);
			Image imgsize = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			// 아이콘 리사이즈
			btEn[i] = new JButton();
			btEn[i].setIcon(new ImageIcon(imgsize));
			if (i % 2 == 0) {
				btEn[i].setBounds(25 + 90 * i, 25, 120, 120);
			} else {
				btEn[i].setBounds(25 + 90 * (i - 1), 205, 120, 120);
			}
			String menu_name = menu[i];
			btEn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new Ordering(menu_name);
				}
			});
			con4.add(btEn[i]);

			jlEn1[i] = new JLabel(menu[i] + " : " + price[i] + "원", JLabel.CENTER);
			jlEn1[i].setBounds(btEn[i].getX(), btEn[i].getY() + 130, 120, 20);
			con4.add(jlEn1[i]);

			if (rating[i] != 6) {
				jlEn2[i] = new JLabel("평점 : " + rating[i] + " / 5.0", JLabel.CENTER);
				jlEn2[i].setBounds(btEn[i].getX(), btEn[i].getY() + 150, 120, 20);
				con4.add(jlEn2[i]);
			}
		}
		con4.setPreferredSize(new Dimension(180 * (model.getRowCount() + 1) / 2, 500));
		model.setRowCount(0);

		// 5. 기타
		model = getModel("기타");
		menu = new String[model.getRowCount()];
		price = new int[model.getRowCount()];
		rating = new double[model.getRowCount()];
		imagedir = new String[model.getRowCount()];

		JButton btEx[] = new JButton[model.getRowCount()]; // 버튼 생성용
		JLabel jlEx1[] = new JLabel[model.getRowCount()]; // 라벨 생성용
		JLabel jlEx2[] = new JLabel[model.getRowCount()];

		for (int i = 0; i < model.getRowCount(); i++) {
			menu[i] = model.getValueAt(i, 0).toString();
			price[i] = (int) model.getValueAt(i, 1);
			rating[i] = (double) model.getValueAt(i, 2);
			imagedir[i] = model.getValueAt(i, 3).toString();
			
			ImageIcon icon = new ImageIcon(imagedir[i]);
			Image imgsize = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			// 아이콘 리사이즈
			btEx[i] = new JButton();
			btEx[i].setIcon(new ImageIcon(imgsize));
			if (i % 2 == 0) {
				btEx[i].setBounds(25 + 90 * i, 25, 120, 120);
			} else {
				btEx[i].setBounds(25 + 90 * (i - 1), 205, 120, 120);
			}
			String menu_name = menu[i];
			btEx[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new Ordering(menu_name);
				}
			});
			con5.add(btEx[i]);

			jlEx1[i] = new JLabel(menu[i] + " : " + price[i] + "원", JLabel.CENTER);
			jlEx1[i].setBounds(btEx[i].getX(), btEx[i].getY() + 130, 120, 20);
			con5.add(jlEx1[i]);

			if (rating[i] != 6) {
				jlEx2[i] = new JLabel("평점 : " + rating[i] + " / 5.0", JLabel.CENTER);
				jlEx2[i].setBounds(btEx[i].getX(), btEx[i].getY() + 150, 120, 20);
				con5.add(jlEx2[i]);
			}
		}
		con5.setPreferredSize(new Dimension(180 * (model.getRowCount() + 1) / 2, 500));

		add(tab);

		setResizable(false);
		setBounds(300, 300, 900, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	DefaultTableModel getModel(String sort) {
		try {
			sql = "select * from menu where sort = ? order by no";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, sort);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("menu_name");
				int price = rs.getInt("price");
				double rating = 6;

				sql = "select count(*) from rating where no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				rsRank = pstmt.executeQuery();
				if (rsRank.next()) {
					int count = rsRank.getInt("count(*)");
					if (count != 0) {
						sql = "select avg(rating) as rate from rating where no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, no);
						rsRank = pstmt.executeQuery();
						if (rsRank.next()) {
							rating = rsRank.getDouble("rate");
							rating = (double) Math.round(rating * 10) / 10;
						}
					}
				}
				String dir = rs.getString("dir");
				Object[] data = { name, price, rating, dir };
				model.addRow(data);
			}
			pstmt = con.prepareStatement(sql);
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
