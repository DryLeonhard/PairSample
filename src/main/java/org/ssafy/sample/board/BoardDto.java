package org.ssafy.sample.board;

import org.ssafy.sample.member.MemberDto;

public class BoardDto {

	private long  boardId;
	private MemberDto memberDto;
	private String title;
	private String text;
	
	public BoardDto() {
		
	}
	
	public BoardDto(String title, String text) {
		super();
		this.title = title;
		this.text = text;
	}

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	
	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "BoardDto{" +
				"boardId=" + boardId +
				", memberDto=" + memberDto +
				", title='" + title + '\'' +
				", text='" + text + '\'' +
				'}';
	}
}
