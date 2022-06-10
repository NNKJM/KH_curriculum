package com.spring.model;

import java.util.List;

public interface EmpDAO {
	List<EmpDTO> getEmpList();
	int insertEmp(EmpDTO dto);
	EmpDTO getEmp(int num);
	int updateEmp(EmpDTO dto);
	int deleteEmp(int num);
	List<DeptDTO> deptList();
	List<String> getJobList();
	String getMgr(int num);
}
