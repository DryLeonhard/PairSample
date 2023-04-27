package org.ssafy.sample.board;

import lombok.*;
import org.ssafy.sample.member.MemberDto;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BoardDto {
	private long  boardId;
	private MemberDto memberDto;
	private String title;
	private String text;
}
