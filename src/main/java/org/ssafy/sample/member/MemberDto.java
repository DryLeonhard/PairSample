package org.ssafy.sample.member;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto {
    private String id;
    private String password;
    private String nickName;
    private Long memberId;
}
