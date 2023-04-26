package org.ssafy.sample.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.ssafy.sample.board.BoardDto;
import org.ssafy.sample.board.dao.BoardDaoImpl;
import org.ssafy.sample.exception.NotAuthorException;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDaoImpl boardDao;

	public BoardServiceImpl(BoardDaoImpl boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public BoardDto boardDetail(long boardId) {
		return boardDao.boardDetail(boardId);
	}

	@Override
	public List<BoardDto> boardList(Map<String, String> param) {
		return boardDao.boardList(param);
	}

	@Override
	public int boardInsert(BoardDto boardDto) {
		return boardDao.boardInsert(boardDto);
	}

	@Override
	public BoardDto boardUpdate(BoardDto boardDto) {
		long toId = boardDto.getBoardId();
//		if(sessionId == boardDao.boardDetail(toId).getMemberDto().getMemberId()){
			boardDao.boardUpdate(boardDto);
			return boardDao.boardDetail(toId);
//		}else{
//			return null;
//		}
	}

	@Override
	public void boardDelete(long boardId) throws NotAuthorException {
//		if(sessionId == boardDao.boardDetail(boardId).getMemberDto().getMemberId()){
			boardDao.boardDelete(boardId);
//		}else{
//			throw new NotAuthorException("이새끼 누구임?");
//		}
	}

}
