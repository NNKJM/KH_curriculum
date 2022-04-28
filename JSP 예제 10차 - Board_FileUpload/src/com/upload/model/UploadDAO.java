package com.upload.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UploadDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;
	private static UploadDAO instance;

	private UploadDAO() {
	}

	public static UploadDAO getInstance() {
		if (instance == null) {
			instance = new UploadDAO();
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
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");

			// 3단계 : DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // openConn END

	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<UploadDTO> getUploadList(){
		List<UploadDTO> list = new ArrayList<UploadDTO>();
		try {
			openConn();
			sql = "select * from upload order by upload_no";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UploadDTO dto = new UploadDTO();
				
				dto.setUpload_no(rs.getInt("upload_no"));
				dto.setUpload_writer(rs.getString("upload_writer"));
				dto.setUpload_title(rs.getString("upload_title"));
				dto.setUpload_cont(rs.getString("upload_cont"));
				dto.setUpload_pwd(rs.getString("upload_pwd"));
				dto.setUpload_file(rs.getString("upload_file"));
				dto.setUpload_hit(rs.getInt("upload_hit"));
				dto.setUpload_date(rs.getString("upload_date"));
				dto.setUpload_update(rs.getString("upload_update"));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
	
	
	public int insertUpload(UploadDTO dto) {
		int result = 0, count = 0;
		try {
			openConn();
			sql = "select max(upload_no) from upload";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1) + 1;
			}
			sql = "insert into upload values(?, ?, ?, ?, ?, ?, default, sysdate, '')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getUpload_writer());
			pstmt.setString(3, dto.getUpload_title());
			pstmt.setString(4, dto.getUpload_cont());
			pstmt.setString(5, dto.getUpload_pwd());
			pstmt.setString(6, dto.getUpload_file());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // insert
	
	public void uploadHit(int no) {
		try {
			openConn();
			sql = "update upload set upload_hit = upload_hit + 1 where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	} // Hit

	public UploadDTO uploadContent(int no) {
		UploadDTO dto = new UploadDTO();
		try {
			openConn();
			sql = "select * from upload where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setUpload_no(rs.getInt("upload_no"));
				dto.setUpload_writer(rs.getString("upload_writer"));
				dto.setUpload_title(rs.getString("upload_title"));
				dto.setUpload_cont(rs.getString("upload_cont"));
				dto.setUpload_pwd(rs.getString("upload_pwd"));
				dto.setUpload_hit(rs.getInt("upload_hit"));
				dto.setUpload_date(rs.getString("upload_date"));
				dto.setUpload_update(rs.getString("upload_update"));
				dto.setUpload_file(rs.getString("upload_file"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // Content
	
	public int updateUpload(UploadDTO dto) {
		int result = 0;
		try {
			openConn();
			sql = "select * from upload where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getUpload_no());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(dto.getUpload_pwd().contentEquals(rs.getString("upload_pwd"))) {
					if(dto.getUpload_file() == null) {
						sql = "update upload set upload_title = ?, upload_cont = ?, upload_update = sysdate where upload_no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getUpload_title());
						pstmt.setString(2, dto.getUpload_cont());
						pstmt.setInt(3, dto.getUpload_no());
					} else {
						sql = "update upload set upload_title = ?, upload_cont = ?, upload_file = ?, upload_update = sysdate where upload_no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getUpload_title());
						pstmt.setString(2, dto.getUpload_cont());
						pstmt.setString(3, dto.getUpload_file());
						pstmt.setInt(4, dto.getUpload_no());
					}
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // update

	public int deleteUpload(int no, String pwd) {
		int result = 0;
		try {
			openConn();
			sql = "select * from upload where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (pwd.equals(rs.getString("upload_pwd"))) {
					sql = "delete from upload where upload_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					result = pstmt.executeUpdate();
					
					// Sequence 앞당기기
					sql = "update upload set upload_no = upload_no - 1 where upload_no > ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // delete END
}
