package meal_kit;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class AdminPage extends JFrame {

	// db와 연결하는 객체
	Connection con = null;

	// db에 sql문을 전송하는 객체
	PreparedStatement pstmt = null;

	// sql문의 실행 결과를 저장할 객체
	ResultSet rs = null;

	// sql문 저장할 변수
	String sql = null;

	DefaultTableModel model;

	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8;
	JLabel jlHidden;
	JComboBox<String> jcb1;
	JTable table;

	public AdminPage() {

		setTitle("메뉴 추가");

		JPanel container1 = new JPanel(); // 상단 컨테이너
		JPanel container2 = new JPanel(); // 하단 컨테이너

		// 1. 컴포넌트를 만들어보자
		// 1-1. 상단 컨테이너에 올려질 컴포넌트 만들기
		JLabel jl1 = new JLabel("메뉴이름 : ");
		jtf1 = new JTextField(5);

		JLabel jl2 = new JLabel("분류 : ");
		jcb1 = new JComboBox<String>();
		jcb1.addItem("선택");
		jcb1.addItem("한식");
		jcb1.addItem("중식");
		jcb1.addItem("일식");
		jcb1.addItem("양식");
		jcb1.addItem("기타");

		JLabel jl3 = new JLabel("가격 : ");
		jtf3 = new JTextField(5);

		JLabel jl4 = new JLabel("사진위치 : ");
		jtf4 = new JTextField(13);

		JLabel jl5 = new JLabel("옵션 : ");
		jtf5 = new JTextField(5);

		JLabel jl6 = new JLabel("메뉴설명 : ");
		jtf6 = new JTextField(13);

		JLabel jl7 = new JLabel("재료구성 : ");
		jtf7 = new JTextField(13);

		JLabel jl8 = new JLabel("옵션가격 : ");
		jtf8 = new JTextField(5);

		jlHidden = new JLabel();

		// 1-2. 중앙에 들어갈 컴포넌트
		String[] header = { "no", "메뉴이름", "분류", "가격", "사진위치", "옵션", "메뉴설명", "재료구성", "옵션가격" };

		/*
		 * DefaultTableModel : 테이블을 만들고 난 후 데이터를 넣고 추가, 수정, 삭제 시에도 변경 가능 Jtable : 테이블을
		 * 만들고 난 후 데이터를 넣으면 한번 만든 테이블의 데이터는 변경 불가능 : 추가, 수정, 삭제 불가 : 추가, 수정, 삭제 시에는 다시
		 * 새로운 객체를 만들어야함
		 */

		model = new DefaultTableModel(header, 0);

		table = new JTable(model);

		table.getColumn("no").setMinWidth(0);
		table.getColumn("no").setMaxWidth(0);

		JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// 1-3. 하단 컨테이너에 들어갈 컴포넌트
		JButton jb1 = new JButton("전체 목록");
		JButton jb2 = new JButton("메뉴 추가");
		JButton jb3 = new JButton("메뉴 수정");
		JButton jb4 = new JButton("메뉴 삭제");
		JButton jb5 = new JButton("로그 아웃");

		// 2. 컴포넌트를 컨테이너에 올리기
		// 2-1. 상단 컨테이너에 1-1 컴포넌트를 올리기
		container1.add(jl1);
		container1.add(jtf1);
		container1.add(jl2);
		container1.add(jcb1);
		container1.add(jl3);
		container1.add(jtf3);
		container1.add(jl4);
		container1.add(jtf4);
		container1.add(jl5);
		container1.add(jtf5);
		container1.add(jl8);
		container1.add(jtf8);
		container1.add(jl6);
		container1.add(jtf6);
		container1.add(jl7);
		container1.add(jtf7);

		// 2-2. 하단 컨테이너에 1-3 컴포넌트를 올리기
		container2.add(jb1);
		container2.add(jb2);
		container2.add(jb3);
		container2.add(jb4);
		container2.add(jb5);

		// 3. 컨테이너를 프레임에 올리기
		add(container1, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(container2, BorderLayout.SOUTH);

		setBounds(200, 200, 1500, 250);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

		// 4. 이벤트 처리
		// 전체목록(jb1) 버튼을 클릭했을 때 menu테이블의 전체 리스트를 jtable에 출력
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 데베에 연결할 메소드 호출
				connect();
				// 전체 테이블의 하면을 지워주는 메소드
				model.setRowCount(0);
				// db에서 전체 내역 조회하는 메소드 호출
				select();

			}
		});

		// 메뉴추가(jb2)버튼을 클릭했을대 각각의 텍스트필드에 입력된 정보를 db에 추가한 후 추가된 전체리스트를 jtable에 다시 출력
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 숫자만 입력하게끔 도와주는 변수
				String num = "[0-9]+";

				if (jtf1.getText().length() == 0 || jtf3.getText().length() == 0 || jtf4.getText().length() == 0
						|| jcb1.getSelectedItem().toString() == "선택") {

					JOptionPane.showMessageDialog(null, "메뉴이름/분류/가격/사진위치 는 필수로 입력해 주세요.");

				} else if (jtf3.getText().matches(num)) { // 숫자만 입력되있는지 확인

					connect();
					insert();

					// 입력 텍스트필드 영역 초기화
					jlHidden.setText("");
					jtf1.setText("");
					jcb1.setSelectedIndex(0);
					jtf3.setText("");
					jtf4.setText("");
					jtf5.setText("");
					jtf6.setText("");
					jtf7.setText("");
					jtf8.setText("");
					jtf1.requestFocus();

					// 전체 테이블의 화면을 지워주는 메소드
					model.setRowCount(0);

					// db에서 전체 내역을 조회하는 메소드
					select();

				} else { // 숫자가 아닐때
					JOptionPane.showMessageDialog(null, "가격에는 숫자만 입력해 주세요.");
				}

			}
		});

		// 메뉴수정(jb3)버튼을 눌렀을 때 각각의 텍스트필드에 입력된 정보를 바탕으로 db에서 수정한 후, menu테이블 전체 리스트를
		// jtable에 출력
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				connect();
				update();

				// 입력 텍스트필드 영역 초기화
				jlHidden.setText("");
				jtf1.setText("");
				jtf3.setText("");
				jtf4.setText("");
				jtf5.setText("");
				jtf6.setText("");
				jtf7.setText("");
				jtf8.setText("");
				jtf1.requestFocus();
				jcb1.setSelectedIndex(0);
				// 전체 테이블의 화면을 지워주는 메소드
				model.setRowCount(0);

				// db에서 전체 내역을 조회하는 메소드
				select();

			}
		});

		// jtable의 특정 행을 클릭한 상태로 메뉴삭제(jb4)버튼을 눌렀을 때 클릭된 행을 DB에서 삭제시키는 작업 진행 후, menu테이블의
		// 전체 리스트를 jtable에 출력
		jb4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.CLOSED_OPTION) {
					JOptionPane.showMessageDialog(null, "취소를 클릭하셨습니다.");
				} else if (result == JOptionPane.YES_OPTION) {
					connect();
					delete();

					// 입력 텍스트필드 영역 초기화
					jlHidden.setText("");
					jtf1.setText("");
					jtf3.setText("");
					jtf4.setText("");
					jtf5.setText("");
					jtf6.setText("");
					jtf7.setText("");
					jtf8.setText("");
					jtf1.requestFocus();
					jcb1.setSelectedIndex(0);
				}

			}
		});
		
		jb5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
				
			}
		});

		table.addMouseListener(new MouseListener() {

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

				try {
					int row = table.getSelectedRow();

					jlHidden.setText(model.getValueAt(row, 0).toString());
					jtf1.setText(model.getValueAt(row, 1).toString());
					jcb1.setSelectedItem(model.getValueAt(row, 2).toString());
					jtf3.setText(model.getValueAt(row, 3).toString());
					jtf4.setText(model.getValueAt(row, 4).toString());
					if(model.getValueAt(row, 5)!=null) {
						jtf5.setText(model.getValueAt(row, 5).toString());
					} else {
						jtf5.setText("");
					}
					jtf6.setText(model.getValueAt(row, 6).toString());

					if(model.getValueAt(row, 6)!=null) {
						jtf6.setText(model.getValueAt(row, 6).toString());
					} else {
						jtf6.setText("");
					}
					
					if(model.getValueAt(row, 7)!=null) {
						jtf7.setText(model.getValueAt(row, 7).toString());
					} else {
						jtf7.setText("");
					}
					if(model.getValueAt(row, 8) != null) {
						jtf8.setText(model.getValueAt(row, 8).toString());
					} else {
						jtf8.setText("");
					}
				} catch (Exception e1) {
					// TODO: handle exception
				}

			}
		});

	}

	// db를 연동하는 메소드
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

	// menu테이블에서 sort를 조회하는 메소드
	void comboJob() {

		try {
			// 1. 오라클 데베에 전송할 sql문 작성
			sql = "select distinct sort from menu";
			pstmt = con.prepareStatement(sql);

			// 2. 오라클 데베로 sql문 전송 및 sql문 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String sort = rs.getString("menu");

				jcb1.addItem(sort);
			}

			// 3. 오라클 데베에 연결되있던 자원 종료
			rs.close();
			pstmt.close(); // con은 다음 메소드에서도 써야해서 종료안함
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // comboJob()메소드 end

	// menu테이블의 전체 내역을 조회하는 메소드
	void select() {

		try {
			// 1. 데이터베이스에 전송할 sql문 작성
			sql = "select * from menu";

			pstmt = con.prepareStatement(sql);

			// 2. 데베에 sql문전송 및 sql문 실행
			rs = pstmt.executeQuery();

			// 3. 레코드 수만큼 반복하여 데이터 추출
			while (rs.next()) {
				int no = rs.getInt("no");
				String menuName = rs.getString("menu_name");
				String sort = rs.getString("sort");
				int price = rs.getInt("price");
				String dir = rs.getString("dir");
				String opt = rs.getString("opt");
				String his = rs.getString("his");
				String ingredient = rs.getString("ingredient");
				int optPrice = rs.getInt("optprice");

				Object[] data = { no, menuName, sort, price, dir, opt, his, ingredient, optPrice };

				// 저장한 한 개의 레코드(data)를 model에 추가해 주면됨
				model.addRow(data);
			}

			// 4. 데베에 연결되어있던 객체 종료
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// select() 메소드 end

	// dept테이블에 부서 정보를 추가하는 메소드
	void insert() {
		try {
			// 1. 데베에 전송할 sql문 작성
			sql = "insert into menu values(meal_kit.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, jtf1.getText()); // 메뉴이름
			pstmt.setString(2, jcb1.getSelectedItem().toString()); // 분류
			pstmt.setInt(3, Integer.parseInt(jtf3.getText())); // 가격
			pstmt.setString(4, jtf4.getText());// 사진위치
			pstmt.setString(5, jtf5.getText());// 옵션
			pstmt.setString(6, jtf6.getText());// 메뉴설명
			pstmt.setString(7, jtf7.getText()); // 재료구성
			pstmt.setString(8, jtf8.getText()); // 옵션가격

			// 2. 오라클데베에 sql문 전송 및 sql문 실행
			int res = pstmt.executeUpdate();

			if (res > 0) {
				JOptionPane.showMessageDialog(null, "메뉴추가 성공");
			} else {
				JOptionPane.showMessageDialog(null, "메뉴추가 실패");
			}

			// 3. 오라클 데베에 연결되있던 객체 종료
			pstmt.close(); // con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // insert()메소드 end

	// dept테이블의 특정 레코드를 수정하는 메소드
	void update() {
		try {
			// 1. 데베에 전송할 sql문 작성
			sql = "update menu set menu_name = ?, sort = ?, price = ?, dir = ?, opt = ?, his = ?, ingredient = ?, optprice = ? where no = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, jtf1.getText()); // 메뉴이름
			pstmt.setString(2, jcb1.getSelectedItem().toString()); // 분류
			pstmt.setInt(3, Integer.parseInt(jtf3.getText())); // 가격
			pstmt.setString(4, jtf4.getText()); // 사진 위치
			pstmt.setString(5, jtf5.getText()); // 옵션
			pstmt.setString(6, jtf6.getText()); // 메뉴설명
			pstmt.setString(7, jtf7.getText()); // 재료구성
			pstmt.setString(8, jtf8.getText()); // 옵션가격
			pstmt.setInt(9, Integer.parseInt(jlHidden.getText())); // 고유번호

			// 2. 오라클 데베에 sql문 전송 및 sql문 실행
			int res = pstmt.executeUpdate();

			if (res > 0) {
				JOptionPane.showMessageDialog(null, "메뉴 정보 수정 성공");
			} else {
				JOptionPane.showMessageDialog(null, "수정 실패");
			}

			// 3. 오라클 데베에 연결된 객체 종료
			pstmt.close(); // con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // update() 메소드 end

	// dept 테이블에서 특정 행 삭제하는 메소드
	void delete() {
		try {
			// 1. 오라클 데베에 전송할 sql문 작성
			sql = "delete from menu where no = ?";

			pstmt = con.prepareStatement(sql);

			// jtable에서 선택된 셀의 row값을 int형으로 반환해주는 메소드
			int row = table.getSelectedRow();

			// getValueAt(세로인덱스(행), 가로인덱스(열))
			// int deptno = (int)model.getValueAt(row, 0);

			pstmt.setInt(1, Integer.parseInt(jlHidden.getText()));

			// 2. 오라클 데베에 sql문 전송 및 sql문 실행
			int res = pstmt.executeUpdate();

			if (res > 0) {
				JOptionPane.showMessageDialog(null, "삭제 성공");
			} else {
				JOptionPane.showMessageDialog(null, "삭제 실패");
			}

			// 테이블상에서 한줄 삭제
			model.removeRow(row);

			// 3. 오라클 데베에 연결되있던 객체 삭제
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
