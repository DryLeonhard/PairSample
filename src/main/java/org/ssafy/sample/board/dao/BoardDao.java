package org.ssafy.sample.board.dao;

import java.util.List;
import java.util.Map;

import org.ssafy.sample.board.BoardDto;

public interface BoardDao {
	public BoardDto boardDetail(long boardId);
	public List<BoardDto> boardList(Map<String, String> param);
	public int boardInsert(BoardDto boardDto);
	public int boardUpdate(BoardDto boardDto);
	public void boardDelete(long boardId);
}
