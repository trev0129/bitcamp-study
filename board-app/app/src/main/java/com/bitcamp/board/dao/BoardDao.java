package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.LinkedList;
import com.bitcamp.util.List;

// 게시글 목록을 관리하는 역할
//
public class BoardDao {

  // List 인터페이스의 레퍼런인 list 변수는
  // List 규격에 따라 만든 객체 주소를 담을 수 있다.
  List list = new LinkedList();

  private int boardNo = 0;

  public void insert(Object e) {

    // 게시글 객체를 적절하게 준비한 다음
    Board board = (Board) e;
    board.no = nextNo();

    // List 규격에 따라 만든 객체를 사용하여 목록에 추가한다.
    // 메서드를 호출할 때는 List 규격에 따라 호출한다.
    list.add(e);
  }

  public Board findByNo(int boardNo) {

    // 의존 객체 BoardList를 이용하여 기존에 저장된 게시글 목록 중에 
    // 해당 번호의 게시글을 찾는다.
    for (int i = 0; i < list.size(); i++) {
      Board board = (Board) list.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }

    return null;
  }

  public boolean delete(int boardNo) {

    // 의존 객체 ObjectList을 이용하여 목록에 저장된 게시글을 찾아 삭제한다.
    for (int i = 0; i < list.size(); i++) {
      Board board = (Board) list.get(i);
      if (board.no == boardNo) {
        return list.remove(i) !=  null;
      }
    }

    return false;
  }

  public Board[] findAll() {

    // 의존 객체 ObjectList를 이용하여 목록에 저장된 게시글을 가져온다.
    Object[] arr = list.toArray();

    // Object[] 배열에 담긴 인스턴스 목록을 Board[] 배열에 담아 리턴한다.
    Board[] boards = new Board[arr.length];

    for (int i = 0; i < arr.length; i++) {
      boards[i] = (Board) arr[i];
    }

    return boards;
  }

  private int nextNo() {
    return ++boardNo;
  }
}














