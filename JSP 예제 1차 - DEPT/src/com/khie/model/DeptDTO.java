package com.khie.model;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * DAO (Data Access Object) : 데이터 접근 객체 ==> DB에 접속(연동)하는 객체
 * 	- DAO란 데이터베이스에 접속해서 데이터 추가, 수정, 삭제, 조회 등의 작업을 하는 클래스
 *  - 일반적으로 JSP 또는 Servlet에서 위의 작업들을 같이 사용할 수 있지만, 유지 보수, 코드의 모듈화를 위해서 DAO 클래스를 따로 만들어 사용함
 * 
 */

public class DeptDTO {
	private int deptno;
	private String dname;
	private String loc;
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

}
