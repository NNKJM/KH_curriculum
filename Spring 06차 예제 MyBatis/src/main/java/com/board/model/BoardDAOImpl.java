package com.board.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public int getListCount() {
		return this.sqlSession.selectOne("count");
	}

	@Override
	public List<BoardDTO> getBoardList(PageDTO dto) {
		return this.sqlSession.selectList("list", dto);
	}

	@Override
	public int insertBoard(BoardDTO dto) {
		return this.sqlSession.insert("add", dto);
	}

	@Override
	public void readCount(int no) {
		this.sqlSession.update("read", no);
	}

	@Override
	public BoardDTO boardCont(int no) {
		return this.sqlSession.selectOne("content", no);
	}

	@Override
	public int updateBoard(BoardDTO dto) {
		return this.sqlSession.update("modify", dto);
	}

	@Override
	public int deleteBoard(int no) {
		return this.sqlSession.delete("del", no);
	}

	@Override
	public void updateSequence(int no) {
		this.sqlSession.update("seq", no);
		
	}

	@Override
	public int searchBoardCount(String field, String keyword) {
		return this.sqlSession.selectOne(field, keyword);
	}

	@Override
	public List<BoardDTO> searchBoardList(PageDTO dto) {
		return this.sqlSession.selectList(dto.getField()+"_List", dto);
	}

}
