package org.ssafy.sample.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.ssafy.sample.board.BoardDto;
import org.ssafy.sample.member.MemberDto;

@Repository
public class BoardDaoImpl implements BoardDao{

    private final DataSource dataSource;

    public BoardDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public BoardDto boardDetail(long boardId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        BoardDto boardDto = null;
        MemberDto memberDto = null;
        
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT * ")
            .append(" FROM boards JOIN members ON (authorId = memberId) ")
            .append(" WHERE boardId = ? ");
            
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setLong(1, boardId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                boardDto = new BoardDto();
                boardDto.setBoardId(rs.getLong("boardId"));
                boardDto.setTitle(rs.getString("title"));
                boardDto.setText(rs.getString("text"));
                
                memberDto = new MemberDto();
                memberDto.setId(rs.getString("id"));
                memberDto.setNickName(rs.getString("nickname"));
                memberDto.setMemberId(rs.getLong("memberId"));
                boardDto.setMemberDto(memberDto);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return boardDto;
    }

    @Override
    public List<BoardDto> boardList(Map<String, String> param) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<BoardDto> list = new ArrayList<>();
        
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT * ")
            .append(" FROM boards JOIN members ON (authorId = memberId)");
            
            String key = param.get("key");
            String word = param.get("word");
            if (key != null) {
            	if (key.equals("nickName")) {
            		sb.append(" where nickname = '" + word + "'");
            	} else {
            		sb.append(" where title like '%" + word + "%'");
            	}
            }
            
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                BoardDto boardDto = new BoardDto();
                boardDto.setBoardId(rs.getLong("boardId"));
                boardDto.setTitle(rs.getString("title"));
                boardDto.setText(rs.getString("text"));
                
                MemberDto memberDto = new MemberDto();
                memberDto.setId(rs.getString("id"));
                memberDto.setNickName(rs.getString("nickname"));
                memberDto.setMemberId(rs.getLong("memberId"));
                
                boardDto.setMemberDto(memberDto);
                
                list.add(boardDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int boardInsert(BoardDto boardDto) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        int ret = -1;
        
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" INSERT INTO boards ")
            .append(" (authorId, title, text) ")
            .append(" values (?, ?, ?) ");
            
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setLong(1,boardDto.getMemberDto().getMemberId());
            pstmt.setString(2, boardDto.getTitle());
            pstmt.setString(3, boardDto.getText());
            
            ret = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ret;
    }

    @Override
    public int boardUpdate(BoardDto boardDto) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        int ret = -1;
        
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" UPDATE boards ")
            .append(" SET title = ?, text = ? ")
            .append(" WHERE boardId = ? ");
            
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1, boardDto.getTitle());
            pstmt.setString(2, boardDto.getText());
            pstmt.setLong(3, boardDto.getBoardId());
            
            ret = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ret;
    }

    @Override
    public void boardDelete(long boardId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        int ret = -1;
        
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" DELETE ")
            .append(" FROM boards ")
            .append(" WHERE boardId = ? ");
            
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setLong(1, boardId);
            
            ret = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
