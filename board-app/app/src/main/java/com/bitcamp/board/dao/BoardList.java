package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.ObjectList;

// 게시글 목록을 관리하는 역할
//
public class BoardList extends ObjectList {

  // 자동으로 증가하는 게시글 번호
  private int boardNo = 0;

  // 게시글을 저장할 때 
  // 자동으로 증가한 번호를 게시글 번호로 설정할 수 있도록 
  // add() 메서드를 재정의 한다.
  @Override
  // 야! 컴파일러야! 부탁이 있다.
  // 수퍼 클래스의 메서드를 재정의하기 위해 다음 메서드를 만들었는데
  // 내가 제대로 재정의했는지 확인해 줄래?
  //
  public void add(Object e) {
    Board board = (Board) e;
    board.no = nextNo();
    super.add(e);
  }

  // ObjectList의 get()에서 던지는 예외를 이 메서드에서 처리하지 않고 
  // 호출자에게 처리를 위임한다.
  // => ListException은 RuntimeException 계열이기 때문에
  // 메서드 선언부에 표시하지 않아도 된다.
  // Exception 예외를 다루는 것 보다 덜 번거롭다.
  @Override
  public Board get(int boardNo) {
    for (int i = 0; i < size(); i++) {
      Board board = (Board) super.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }

  @Override
  public boolean remove(int boardNo) {
    for (int i = 0; i < size(); i++) {
      Board board = (Board) super.get(i);
      if (board.no == boardNo) {
        return super.remove(i);
      }
    }

    return false;
  }

  private int nextNo() {
    return ++boardNo;
  }
}














