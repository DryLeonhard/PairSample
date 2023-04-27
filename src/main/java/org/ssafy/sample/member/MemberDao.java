package org.ssafy.sample.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {


    private final DataSource dataSource;


    public MemberDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createMember(MemberDto memberDto){
        Connection connection = null;
        PreparedStatement pst = null;
        try{
            connection = dataSource.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO members (id,password,nickname) VALUES (?,?,?);");
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1,memberDto.getId());
            pst.setString(2,memberDto.getPassword());
            pst.setString(3, memberDto.getNickName());

            pst.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public MemberDto findMember(String id){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        MemberDto findMember = null;
        try{
            connection = dataSource.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM members WHERE id = ?");
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                findMember = new MemberDto();
                findMember.setMemberId(rs.getLong("memberId"));
                findMember.setId(rs.getString("id"));
                findMember.setNickName(rs.getString("nickname"));
                findMember.setPassword(rs.getString("password"));
                return findMember;
            }
            return null;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long findMemberId(String id){
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        MemberDto findMember = null;
        try{
            connection = dataSource.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT memberId FROM members WHERE id = ?");
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                return rs.getLong(1);
            }
            return -1;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
