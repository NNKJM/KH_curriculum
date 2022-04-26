package com.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BbsDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;
	private static BbsDAO instance;

	private BbsDAO() { }

	// 싱글턴
	public static BbsDAO getInstance() {
		if (instance == null) {
			instance = new BbsDAO();
		}
		return instance;
	} // getInstnace

	public void openConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "1234";

		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();
			
			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			// 3단계 : DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // openConn END
	
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	public List<BbsDTO> getBbsList() {
		List<BbsDTO> list = new ArrayList<BbsDTO>();
		try {
			openConn();
			sql = "select * from jsp_bbs order by board_group desc, board_step";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BbsDTO dto = new BbsDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} // getBoardList() END
	

	public int insertBoard(BbsDTO dto) {
		int result = 0, count = 0;
		try {
			openConn();
			sql = "select max(board_no) from jsp_bbs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1) + 1;
			}
			sql = "insert into jsp_bbs values(?, ?, ?, ?, ?, default, sysdate, '', ?, 0, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			pstmt.setInt(6, count);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // insertBoard

	public void boardHit(int no) {
		try {
			openConn();
			sql = "update jsp_bbs set board_hit = board_hit+1 where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	} // boardHit

	public BbsDTO getBbsContent(int no) {
		BbsDTO dto = new BbsDTO();
		try {
			openConn();
			sql = "select * from jsp_bbs where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // Content END

	public int updateBbs(BbsDTO dto) {
		int result = 0;
		try {
			openConn();
			sql = "select * from jsp_bbs where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBoard_no());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(dto.getBoard_pwd().contentEquals(rs.getString("board_pwd"))) {
					sql = "update jsp_bbs set board_title = ?, board_cont = ?, board_update = sysdate where board_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getBoard_title());
					pstmt.setString(2, dto.getBoard_cont());
					pstmt.setInt(3, dto.getBoard_no());
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // updateBoard

	public int deleteBbs(int no, String pwd) {
		int result = 0;
		try {
			openConn();
			sql = "select * from jsp_bbs where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (pwd.equals(rs.getString("board_pwd"))) {
					sql = "delete from jsp_bbs where board_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					result = pstmt.executeUpdate();
					
					// Sequence 앞당기기
					sql = "update jsp_bbs set board_no = board_no - 1, board_group = board_group - 1 where board_no > ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // deleteBoard() END
	
	public void replyUpdate(int group, int step) {
		openConn();
		try {
			sql = "update jsp_bbs set board_step = board_step + 1 where board_group = ? and board_step > ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, group);
			pstmt.setInt(2, step);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	} // replyUpdate() END
	
	public int replyBbs(BbsDTO dto) {
		int result = 0, count = 0;
		openConn();
		try {
			sql = "select max(board_no) from jsp_bbs";
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			sql = "insert into jsp_bbs values(?, ?, ?, ?, ?, default, sysdate, '', ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			pstmt.setInt(6, dto.getBoard_group());
			pstmt.setInt(7, dto.getBoard_step() + 1);
			pstmt.setInt(8, dto.getBoard_indent() + 1);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // replyBbs() END
}
