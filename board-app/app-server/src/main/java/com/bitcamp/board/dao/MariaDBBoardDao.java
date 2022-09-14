package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.Board;

// BoardDao와 통신을 담당할 대행 객체
//
public class MariaDBBoardDao implements BoardDao{

  Connection con;

  public MariaDBBoardDao(Connection con) throws Exception {
    this.con = con;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "insert into app_board2(title,cont,mno) values(?,?,?)")) {
      pstmt.setString(1, board.title);
      pstmt.setString(2, board.content);
      pstmt.setInt(3, board.memberNo);
      return pstmt.executeUpdate();
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "select bno,title,cont,mno,cdt,vw_cnt from app_board2 where bno=" + no);
        ResultSet rs = pstmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }

      Board board = new Board();
      board.no = rs.getInt("bno");
      board.title = rs.getString("title");
      board.content = rs.getString("cont");
      board.memberNo = rs.getInt("mno");
      board.createdDate = rs.getDate("cdt");
      board.viewCount = rs.getInt("vw_cnt");

      return board;
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "update app_board2 set title=?, cont=? where bno=?")) {

      pstmt.setString(1, board.title);
      pstmt.setString(2, board.content);
      pstmt.setInt(3, board.no);

      return pstmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement("delete from app_board2 where bno=?")) {

      pstmt.setInt(1, no);
      return pstmt.executeUpdate();
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "select bno,title,mno,cdt,vw_cnt from app_board2");
        ResultSet rs = pstmt.executeQuery()) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.no = rs.getInt("bno");
        board.title = rs.getString("title");
        board.memberNo = rs.getInt("mno");
        board.createdDate = rs.getDate("cdt");
        board.viewCount = rs.getInt("vw_cnt");

        list.add(board);
      }

      return list;
    }
  }
}













