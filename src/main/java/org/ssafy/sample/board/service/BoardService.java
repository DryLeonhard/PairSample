package org.ssafy.sample.board.service;

import java.util.List;
import java.util.Map;

import org.ssafy.sample.board.BoardDto;
import org.ssafy.sample.exception.NotAuthorException;

public interface BoardService {
	public BoardDto boardDetail(long boardId);
	public List<BoardDto> boardList(Map<String, String> param);
	public int boardInsert(BoardDto boardDto);
	public BoardDto boardUpdate(BoardDto boardDto);
	public void boardDelete(long boardId) throws NotAuthorException;

}
