package com.spring.model;

import java.util.List;

public interface BoardDAO {
	// 추상 메서드
	List<BoardDTO> getBoardList();
	int writeBoard(BoardDTO dto);
	int hitBoard(int num);
	BoardDTO readBoard(int num);
	int updateBoard(BoardDTO dto);
	int deleteBoard(int num);
	void updateBoard_no(int num);
	List<BoardDTO> searchBoardList(String field, String keyword);
}
