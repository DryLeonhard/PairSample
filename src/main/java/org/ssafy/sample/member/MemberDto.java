package org.ssafy.sample.member;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(builderMethodName = "memberDtoBuilder")
public class MemberDto {
    private String id;
    private String password;
    private String nickName;
    private Long memberId;
}
