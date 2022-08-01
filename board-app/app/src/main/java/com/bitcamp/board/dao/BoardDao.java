package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.ObjectList;

// 게시글 목록을 관리하는 역할
//
public class BoardDao {

  // BoardDao는 ObjectList 객체를 포함한다.
  // => boardDao는ObjectList의 기능에 의한다.
  // => 따라서 ObjectList는 BoardDao가 의존하는 객체이다. => "dependency" 의존 객
  ObjectList list = new ObjectList();

  private int boardNo = 0;

  // BoardDao 에서 제공할 메서드를 정의하고,
  // 이 메서드가 호출되ㅣ면 ObjectList의 동무으 ㄹ받아 처리한다.
  public void insert(Object e) {
    //게시글 객체를 적절하게 준비한 다
    Board board = (Board) e;
    board.no = nextNo();

    // 의존객체 ObjectList를 사용하여 목록에 추가한다.
    list.add(e);
  }

  // ObjectList의 get()에서 던지는 예외를 이 메서드에서 처리하지 않고
  // 호출자에게 처리를 위임한다.
  // => ListException은 RuntimeException 계열이기 때문에 
  //    메서드 선언부에 표시하지 않아도 된다.
  //    Exception 계열의 예외를 다루는 것 보다 덜 번거롭다.
  //

  public Board findByNo(int boardNo) {

    // 의존 객체 를 이용하여 기존
    for (int i = 0; i < list.size(); i++) {
      Board board = (Board) list.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }

  // ObjectList의 get()에서 던지는 예외를 이 메서드에서 처리하지 않고
  // 호출자에게 처리를 위임한다.
  // => ListException은 RuntimeException 계열이기 때문에 
  //    메서드 선언부에 표시하지 않아도 된다.
  //    Exception 계열의 예외를 다루는 것 보다 덜 번거롭다.
  //

  public boolean delete(int boardNo) {
    // ObjectList를 이용하여 목록에 저장된 게시글을 찾아 삭제한다.
    for (int i = 0; i <list.size(); i++) {
      Board board = (Board) list.get(i);
      if (board.no == boardNo) {
        return list.remove(i);
      }
    }

    return false;
  }

  public Board[] findAll() {
    Object[] arr = list.toArray();
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