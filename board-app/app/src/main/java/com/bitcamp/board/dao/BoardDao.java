package com.bitcamp.board.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Board;

// 게시글 목록을 관리하는 역할
//
public class BoardDao {

  // BoardDao는 ObjectList 객체를 포함한다.
  // => BoardDao는 ObjectList의 기능에 의존한다.
  // => 따라서 ObjectList는 BoardDao가 의존하는 객체이다. ==> "dependency" 의존 객체 
  List<Board> list = new LinkedList<>();

  private int boardNo = 0;
  String filename;

  public BoardDao(String filename) {
    this.filename = filename;
  }

  public void load() throws Exception {
    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
      String str;
      while ((str = in.readLine()) != null) {
        Board board = Board.create(str);

        list.add(board);
        boardNo = board.no;
      }

    }
  }


  public void save() throws Exception {
    try (FileWriter out = new FileWriter(filename)) {
      for (Board board : list) {
        out.write(board.toCsv() + "\n");
      }
    }




  }




  public void insert(Board board) {
    board.no = nextNo();

    // 의존 객체 ObjectList를 사용하여 목록에 추가한다.
    list.add(board);
  }

  public Board findByNo(int boardNo) {

    // 의존 객체 BoardList를 이용하여 기존에 저장된 게시글 목록 중에 
    // 해당 번호의 게시글을 찾는다.
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }

    return null;
  }

  public boolean delete(int boardNo) {

    // 의존 객체 ObjectList을 이용하여 목록에 저장된 게시글을 찾아 삭제한다.
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return list.remove(i) != null;
      }
    }

    return false;
  }

  public Board[] findAll() {
    Iterator<Board> iterator = list.iterator();

    Board[] arr = new Board[list.size()];
    int index = list.size() - 1;
    while (iterator.hasNext()) {
      arr[index--] = iterator.next();
    }
    return arr;
  }

  private int nextNo() {
    return ++boardNo;
  }
}














