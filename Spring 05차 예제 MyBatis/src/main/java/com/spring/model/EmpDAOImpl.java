package com.spring.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAOImpl implements EmpDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<EmpDTO> getEmpList() {
		return this.sqlSession.selectList("all");
	}

	@Override
	public int insertEmp(EmpDTO dto) {
		return this.sqlSession.insert("add", dto);
	}

	@Override
	public EmpDTO getEmp(int num) {
		return this.sqlSession.selectOne("content", num);
	}

	@Override
	public int updateEmp(EmpDTO dto) {
		return this.sqlSession.update("edit", dto);
	}

	@Override
	public int deleteEmp(int num) {
		return this.sqlSession.delete("del", num);
	}

	@Override
	public List<DeptDTO> deptList() {
		return this.sqlSession.selectList("dept");
	}

	@Override
	public List<String> getJobList() {
		return this.sqlSession.selectList("job");
	}

	@Override
	public String getMgr(int num) {
		return this.sqlSession.selectOne("mgr", num);
	}

}
