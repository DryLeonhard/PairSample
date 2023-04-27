package org.ssafy.sample.member;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDto {
    private String id;
    private String password;
    private String nickName;
    private Long memberId;
}
