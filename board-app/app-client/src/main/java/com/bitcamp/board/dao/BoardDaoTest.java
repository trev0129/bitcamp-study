package com.bitcamp.board.dao;

import java.util.List;
import com.bitcamp.board.domain.Board;

public class BoardDaoTest {
  public static void main(String[] args) throws Exception {
    MariaDBBoardDao dao = new MariaDBBoardDao();
    List<Board> list = dao.findAll();
    for (Board b : list) {
      System.out.println(b);
    }

    System.out.println("=================================================================");

    //    Board board = new Board();
    //    board.title = "aaaa";
    //    board.content = "bbb";
    //    board.password = "1111";
    //    board.memberNo = 2;
    //    dao.insert(board);

    //    dao.delete(21);

    Board board = new Board();
    board.no = 12;
    board.title = "xxx";
    board.content = "okokok";
    dao.update(board);
    //
    Board member2 = dao.findByNo(12);
    System.out.println(member2);

    //    Board member = dao.findByNo(1);
    //    System.out.println(member);

    list = dao.findAll();
    for (Board b : list) {
      System.out.println(b);
    }
    System.out.println("=================================================================");



  }
}
