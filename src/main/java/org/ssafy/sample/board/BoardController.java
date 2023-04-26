package org.ssafy.sample.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssafy.sample.board.service.BoardService;
import org.ssafy.sample.exception.NotAuthorException;
import org.ssafy.sample.member.MemberDto;

@Controller
@RequestMapping("/api")
public class BoardController {

	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/boardList")
	public ResponseEntity boardList() {
		System.out.println("보드");
		return new ResponseEntity<>("Board", HttpStatus.OK);
	}

	@PostMapping("/boards")
	public ResponseEntity postBoards(@RequestBody BoardDto boardDto, HttpSession session) {
		System.out.println(boardDto.toString());
		try {
//			MemberDto sessionMember = (MemberDto) session.getAttribute("MemberDto");
//			long sessionId = sessionMember.getMemberId();
			boardService.boardInsert(boardDto);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(boardDto, HttpStatus.OK);
	}

	@GetMapping("/boards")
	public ResponseEntity getBoardList() {
		Map<String, String> param = new HashMap<>();

		param.put("key", null);
		param.put("word", null);

		System.out.println(param);
		List<BoardDto> boardDtoList = boardService.boardList(param);

		return new ResponseEntity<>(boardDtoList, HttpStatus.OK);
	}

	@GetMapping("/boards/{board-id}")
	public ResponseEntity getBoardDetail(@PathVariable(value = "board-id") long boardId) {
//	public String getBoardDetail(@PathVariable(value = "board-id") long boardId, HttpServletRequest request){
		BoardDto findBoard = boardService.boardDetail(boardId);
//		request.setAttribute("board", findBoard);
//		return "/board/view.html";
		return new ResponseEntity<>(findBoard, HttpStatus.OK);
	}

	@PutMapping("/boards/{board-id}")
	public ResponseEntity putBoard(@PathVariable(value = "board-id") long boardId, @RequestBody BoardDto boardDto,
			HttpSession session) {
		System.out.println(boardDto.toString());
		BoardDto updatedDto = null;
		try {
//			MemberDto sessionMember = (MemberDto) session.getAttribute("MemberDto");
//			long sessionId = sessionMember.getMemberId();
			boardDto.setBoardId(boardId);

			updatedDto = boardService.boardUpdate(boardDto);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(updatedDto, HttpStatus.OK);
	}

	@GetMapping("/boards/modify/{boardId}")
	public String modifyBoard(@PathVariable(value = "boardId") long boardId, Model model) {
		BoardDto boardDto = boardService.boardDetail(boardId);
		model.addAttribute("boardDto", boardDto);
		return "board/modify";
	}

	@DeleteMapping("/boards/{board-id}")
	public ResponseEntity deleteBoard(@PathVariable(value = "board-id") long boardId, HttpSession session) {
		try {
//			MemberDto sessionMember = (MemberDto) session.getAttribute("MemberDto");
//			long sessionId = sessionMember.getMemberId();

			boardService.boardDelete(boardId);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NotAuthorException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
