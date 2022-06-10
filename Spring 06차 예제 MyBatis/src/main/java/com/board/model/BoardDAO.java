package com.board.model;

import java.util.List;

public interface BoardDAO {

	public int getListCount();
	public List<BoardDTO> getBoardList(PageDTO dto);
	public int insertBoard(BoardDTO dto);
	public void readCount(int no);
	public BoardDTO boardCont(int no);
	public int updateBoard(BoardDTO dto);
	public int deleteBoard(int no);
	public void updateSequence(int no);
	public int searchBoardCount(String field, String keyword);
	public List<BoardDTO> searchBoardList(PageDTO dto);
	
}
