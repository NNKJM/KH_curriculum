package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.model.MemberDAO;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;
	private static MemberDAO instance;

private MemberDAO() {	}

	// 싱글턴
	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	} // getInstnace

	public void openConn() {
		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();
			
			// 2단계 : lookup() 메서드를 이용해 매칭되는 커넥션을 찾는다
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			// 3단계 : DataSource 객체를 이용해 커넥션 객체를 하나 가져옴
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // openConn END
	
	public List<MemberDTO> getMemberList(){
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			openConn();
			sql = "select * from member10 order by num desc";
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // getMemberList END
	
	public int insertMember(MemberDTO dto) {
		int result = 0 , count = 0;
		try {
			openConn();
			sql = "select max(num) from member10";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into member10 values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getMemid());
			pstmt.setString(3, dto.getMemname());
			pstmt.setString(4, dto.getPwd());
			pstmt.setInt(5, dto.getAge());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getJob());
			pstmt.setString(8, dto.getAddr());
			
			result = pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // insertMember()
	
	public MemberDTO getContentMember(int no) {
		MemberDTO dto = new MemberDTO();
		try {
			openConn();
			sql = "select * from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
			}
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	} // getContentMember END

	public int updateMember(MemberDTO dto) {
		int result = 0;
		try {
			openConn();
	
			sql = "update member10 set age = ?, mileage = ?, job = ?, addr = ? where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getAge());
			pstmt.setInt(2, dto.getMileage());
			pstmt.setString(3, dto.getJob());
			pstmt.setString(4, dto.getAddr());
			pstmt.setInt(5, dto.getNum());
			result = pstmt.executeUpdate();
				
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // updateEND
	
	public int deleteMember(int no, String pwd) {
		int result = 0;
		try {
			openConn();
			sql = "select * from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(pwd.equals(rs.getString("pwd"))) {
					sql="delete from member10 where num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					result = pstmt.executeUpdate();
					
					// 중간에 있는 회원번호 삭제 시 회원번호 재작업 기능.
					sql = "update member10 set num = num - 1 where num > ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	} // deleteEND
	
}
