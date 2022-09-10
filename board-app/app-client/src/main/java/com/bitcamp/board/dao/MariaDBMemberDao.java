package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.Member;

// MemberDao와 통신을 담당할 대행 객체
//
public class MariaDBMemberDao {

  public int insert(Member member) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "insert into app_member2(name,email,pwd) values(?,?,sha2(?,256))")) {

      pstmt.setString(1, member.name);
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);

      return pstmt.executeUpdate();
    }
  }

  public Member findByNo(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "select mno,name,email,cdt from app_member2 where mno = " + no);
        ResultSet rs = pstmt.executeQuery();) {

      Member member = new Member();

      if (!rs.next()) {
        return null;
      }

      member.no = rs.getInt("mno");
      member.name = rs.getString("name");
      member.email = rs.getString("email");
      member.createdDate = rs.getDate("cdt");

      return member;
    }
  }

  public int update(Member member) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "update app_member2 set name=?,email=?,pwd=? where mno=?")) {

      pstmt.setString(1, member.name);
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);
      pstmt.setInt(4, member.no);

      return pstmt.executeUpdate();
    }
  }

  public int delete(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt1 = con.prepareStatement("delete from app_board2 where bno=?");
        PreparedStatement pstmt2 = con.prepareStatement("delete from app_member2 where mno=?")) {

      pstmt1.setInt(1, no);
      pstmt1.executeUpdate();

      pstmt2.setInt(1, no);
      return pstmt2.executeUpdate();
    }
  }

  public List<Member> findAll() throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement("select mno,name,email from app_member2");
        ResultSet rs = pstmt.executeQuery()) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();
        member.no = rs.getInt("mno");
        member.name = rs.getString("name");
        member.email = rs.getString("email");
        list.add(member);
      }

      return list;
    }
  }
}














