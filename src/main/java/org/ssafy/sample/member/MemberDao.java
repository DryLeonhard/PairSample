package org.ssafy.sample.member;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberDao {
    @Insert("INSERT INTO members (id,password,nickname) VALUES(#{id}, #{password},#{nickName})")
    int saveMemberDto(MemberDto memberDto);

    @Select("SELECT * FROM members WHERE id = #{id}")
    MemberDto findMemberById(String id);

    @Delete("DELETE FROM members WHERE id = #{id}")
    int deleteMemberById(String id);
}
