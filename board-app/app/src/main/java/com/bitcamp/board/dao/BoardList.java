package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.ObjectList;

// 게시글 목록을 관리하는 역할
//
public class BoardList extends ObjectList{

  private int boardNo = 0;

  // 게시글 번호에 해당하는 Board 인스턴스를 찾아 리턴한다.
  @Override
  public  Board get(int boardNo) throws Exception {
    for (int i = 0; i < size(); i++) {
      Board board = (Board) super.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }

  // Board 인스턴스를 배열에 저장한다.
  @Override
  public void add(Object e) {
    Board board = (Board) e;
    board.no = nextNo();
    super.add(e);
  }

  @Override
  public boolean remove(int boardNo) throws Exception {
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














