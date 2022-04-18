package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;
	private static EmpDAO instance;

	private EmpDAO() {
	}

	// 싱글턴
	public static EmpDAO getInstance() {
		if (instance == null) {
			instance = new EmpDAO();
		}
		return instance;
	} // getInstnace

	public void openConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "1234";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // openConn END

	public List<EmpDTO> getEmpList() {
		List<EmpDTO> list = new ArrayList<EmpDTO>();

		try {
			openConn();
			sql = "select * from emp order by empno";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));

				list.add(dto);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} // getEmpList() END
	
	public List<DeptDTO> getDeptList() {
		List<DeptDTO> list = new ArrayList<DeptDTO>();

		try {
			openConn();
			sql = "select * from dept order by deptno";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeptDTO dto = new DeptDTO();
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				
				list.add(dto);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} // getDeptList() END

	public int insertEmp(EmpDTO dto) {
		int result = 0, count = 0;
		openConn();

		try {
			sql = "insert into emp values(?,?,?,?,sysdate,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			System.out.println("DAO getEMPNO " + dto.getEmpno());
			pstmt.setInt(1, dto.getEmpno());
			System.out.println("DAO getENAME " + dto.getEname());
			pstmt.setString(2, dto.getEname());
			pstmt.setString(3, dto.getJob());
			pstmt.setInt(4, dto.getMgr());
			pstmt.setInt(5, dto.getSal());
			pstmt.setInt(6, dto.getComm());
			pstmt.setInt(7, dto.getDeptno());

			result = pstmt.executeUpdate();

			pstmt.close(); con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ㅋㅋ 허접");
		}
		System.out.println("result : " + result);
		return result;
	} // insertProduct
	
	public EmpDTO getContentEmp(int no) {
		EmpDTO dto = new EmpDTO();
		openConn();
		try {
			sql = "select * from emp where empno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
				sql = "select ename from emp where empno = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getMgr());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto.setMname(rs.getString("ename"));
				} else {
					dto.setMname("");
				}
			}
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	} // getContentEmp() 메서드 end
	
	public int updateEmp(EmpDTO dto) {

		openConn();
		int result = 0;
		sql="update emp set job = ?, mgr = ?, comm = ?, deptno = ? where empno = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getJob());
			pstmt.setInt(2, dto.getMgr());
			pstmt.setInt(3, dto.getComm());
			pstmt.setInt(4, dto.getDeptno());
			pstmt.setInt(5, dto.getEmpno());
			result=pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	} // updateEmp() END
	
	public int deleteEmp(int no) {
		int result = 0;
		try {
			openConn();
			sql = "delete from emp where empno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // deleteEMP () END

}
