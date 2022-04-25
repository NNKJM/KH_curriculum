package com.khie.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO {
	Connection con = null;
	String sql = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public DeptDAO() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user="web";
		String password="1234";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if(con != null) {
				System.out.println("연결 성공");
			} else {
				System.out.println("연결 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	} // 기본생성자 end
	
	// selectList()라는 메서드 만들기
	public List<DeptDTO> selectList(){
		List<DeptDTO> list = new ArrayList<DeptDTO>(); // 다형성 적용
		
		// 3단계 : DB에 SQL문을 전송하기 위한 쿼리문 작성
		sql = "select * from dept order by deptno";
		
		try {

			// 4단계 : SQL문을 DB 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			// 5단계 : SQL문을 데이터베이스에 전송 및 SQL문 실행
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DeptDTO dto = new DeptDTO();
				
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				
				System.out.println("dto : " + dto);
				list.add(dto);
			}
			
			// 6단계 닫기
			rs.close(); pstmt.close(); con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} // selectList
	
	// 부서 테이블에 부서를 추가하는 메서드
	public int insertDept(DeptDTO dto) {
		int result = 0;
		try {
		// 데이터베이스에 SQL문을 전송하기 위한 쿼리문 작성
		sql="insert into dept values(?, ?, ?)";
		// SQL문을 DB 전송 객체 저장
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1,  dto.getDeptno());
		pstmt.setString(2,  dto.getDname());
		pstmt.setString(3,  dto.getLoc());
		
		// SQL문 DB에 전송 및 실행
		result = pstmt.executeUpdate();
		
		// open되어 있는 자원 종료
		pstmt.close(); con.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	} // insertDept
	
	public int deleteDept(int no) {
		int result = 0;
		
		try {
			sql = "delete from dept where deptno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();

			pstmt.close(); con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // deleteDept
}
